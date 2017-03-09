package com.example.andresarango.aughunt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.andresarango.aughunt.challenge.Challenge;
import com.example.andresarango.aughunt.challenge.challenges_adapters.ChallengeViewholderListener;
import com.example.andresarango.aughunt.challenge.create_challenge.CompletedChallengeViewholderListener;
import com.example.andresarango.aughunt.challenge.create_challenge.ReviewChallengesFragment;
import com.example.andresarango.aughunt.challenge.create_challenge.ChallengeTemplateActivity;
import com.example.andresarango.aughunt.challenge.CompletedChallenge;
import com.example.andresarango.aughunt.challenge.FirebaseEmulator;
import com.example.andresarango.aughunt.challenge.create_challenge.CreatedChallengesAdapter;
import com.example.andresarango.aughunt.challenge.create_challenge.CompareChallengesFragment;

import java.util.ArrayList;
import java.util.List;


public class CreateChallengeActivity extends AppCompatActivity implements
        ViewGroup.OnClickListener, ChallengeViewholderListener<Bitmap>, CompletedChallengeViewholderListener<Bitmap> {

    private FirebaseEmulator mFirebaseEmulator;
    private ReviewChallengesFragment mReviewChallengesFragment;
    private Boolean mIsInflated;
    private CompareChallengesFragment mCompareChallengesFragment;
    private Challenge<Bitmap> mSelectedChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_challenge);
        initialize();

    }

    private void initialize() {
        Button mCreateChallenge = (Button) findViewById(R.id.new_challenge);
        mCreateChallenge.setOnClickListener(this);
        mIsInflated = false;
        mFirebaseEmulator = new FirebaseEmulator(this);
        initRecyclerView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChallengeTemplateActivity.class);
        startActivity(intent);


    }

    public void initRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.created_challenges);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Challenge<Bitmap>> currentChallengesList = historyOfCreatedChalalnges(
                mFirebaseEmulator.getChallenges(),
                mFirebaseEmulator.getCurrentUser().getUserId());
        CreatedChallengesAdapter challengesAdapter = new CreatedChallengesAdapter(this);
        challengesAdapter.setChallengeList(currentChallengesList);
        mRecyclerView.setAdapter(challengesAdapter);

    }


    public List<Challenge<Bitmap>> historyOfCreatedChalalnges(List<Challenge<Bitmap>> challengeList, String ownerID) {

        List<Challenge<Bitmap>> userChallengeList = new ArrayList<>();

        for (Challenge<Bitmap> challenge : challengeList) {
            if (challenge.getmOwnerId().equalsIgnoreCase(ownerID)) {
                userChallengeList.add(challenge);
            }

        }
        return userChallengeList;

    }


    @Override
    public void onChallengeClicked(Challenge<Bitmap> challenge) {
        mSelectedChallenge = challenge;
        startReviewChallengeFragment(challenge);
    }

    @Override
    public void onCompletedChallengeClicked(CompletedChallenge<Bitmap> completedChallenge) {
        startCompareChallengeFragment(completedChallenge, mSelectedChallenge);
    }

    private void startReviewChallengeFragment(Challenge<Bitmap> challenge) {
        mReviewChallengesFragment = new ReviewChallengesFragment();
        mReviewChallengesFragment.setChallengeToReview(challenge);
        mReviewChallengesFragment.setmListener(this);
        mIsInflated = !mIsInflated;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_for_review, mReviewChallengesFragment)
                .commit();
    }



    private void startCompareChallengeFragment(CompletedChallenge<Bitmap> completedChallenge, Challenge<Bitmap> challenge) {
        mCompareChallengesFragment = new CompareChallengesFragment();
        mCompareChallengesFragment.setCompletedChallenge(completedChallenge);
        mCompareChallengesFragment.setCurrentChallenge(challenge);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_for_review, mCompareChallengesFragment)
                .commit();
    }


    @Override
    public void onBackPressed() {

        if (mIsInflated) {
            getSupportFragmentManager().beginTransaction().remove(mReviewChallengesFragment).commit();
            mIsInflated = !mIsInflated;

        } else {
            super.onBackPressed();
        }

    }

    public void reviewResult(View view) {

        switch (view.getId()) {

            case R.id.decline:

                getSupportFragmentManager().beginTransaction().remove(mCompareChallengesFragment).commit();
                break;

            case R.id.accept:

                getSupportFragmentManager().beginTransaction().remove(mCompareChallengesFragment).commit();
                break;

        }


    }


}
