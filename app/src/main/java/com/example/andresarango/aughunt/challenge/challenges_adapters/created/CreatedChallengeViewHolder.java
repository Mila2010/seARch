package com.example.andresarango.aughunt.challenge.challenges_adapters.created;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andresarango.aughunt.R;
import com.example.andresarango.aughunt.challenge.ChallengePhoto;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Millochka on 3/5/17.
 */

class CreatedChallengeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_created_challenge_image) ImageView mChallengeImageIv;
    @BindView(R.id.tv_created_challenge_hint) TextView mHintTv;
    @BindView(R.id.tv_created_challenge_pursuing) TextView mPursuingTv;
    @BindView(R.id.tv_created_challenge_completed) TextView mCompletedTv;
    @BindView(R.id.tv_hint) TextView mHint;

    public CreatedChallengeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ChallengePhoto challenge) {
        Glide.with(itemView.getContext()).load(challenge.getPhotoUrl()).into(mChallengeImageIv);
        mHint.setTypeface(mHint.getTypeface(), Typeface.BOLD);
        mHint.setText("Hint: ");
        mHintTv.setText( challenge.getHint());
        mPursuingTv.setText("Pursuing: " + challenge.getPursuing());
        mCompletedTv.setText("Completed: " + challenge.getCompleted());
    }

}