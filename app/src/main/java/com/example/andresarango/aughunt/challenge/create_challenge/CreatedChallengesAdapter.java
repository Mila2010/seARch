package com.example.andresarango.aughunt.challenge.create_challenge;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andresarango.aughunt.R;
import com.example.andresarango.aughunt.challenge.Challenge;
import com.example.andresarango.aughunt.challenge.challenges_adapters.ChallengeViewholderListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Millochka on 3/5/17.
 */

public class CreatedChallengesAdapter extends RecyclerView.Adapter<CreatedChallengeViewHolder> {

    private List<Challenge<Bitmap>> mChallengeList = new ArrayList<>();
    private ChallengeViewholderListener<Bitmap> mListener;

    public CreatedChallengesAdapter(ChallengeViewholderListener<Bitmap> listener) {
        this.mListener = listener;
    }

    @Override
    public CreatedChallengeViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.created_challenge, parent, false);

        return new CreatedChallengeViewHolder(view);


    }

    @Override
    public void onBindViewHolder(CreatedChallengeViewHolder holder, int position) {
        final int viewHolderPosition = position;

        holder.bind(mChallengeList.get(viewHolderPosition));
        holder.getmCreatedChallenge().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChallengeClicked(mChallengeList.get(viewHolderPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mChallengeList.size();
    }

    public void setChallengeList(List<Challenge<Bitmap>> challengeList) {
        this.mChallengeList.clear();
        this.mChallengeList.addAll(challengeList);
        notifyDataSetChanged();
    }
}
