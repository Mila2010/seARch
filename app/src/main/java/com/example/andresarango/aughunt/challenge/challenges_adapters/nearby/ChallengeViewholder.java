package com.example.andresarango.aughunt.challenge.challenges_adapters.nearby;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andresarango.aughunt.R;
import com.example.andresarango.aughunt.challenge.ChallengePhoto;
import com.example.andresarango.aughunt.challenge.challenges_adapters.ChallengeViewholderListener;

class ChallengeViewholder<T> extends RecyclerView.ViewHolder {

    private final TextView mHintTextView;
    private final ImageView mChallengeImageView;
    private final ChallengeViewholderListener mListener;

    public ChallengeViewholder(View itemView, ChallengeViewholderListener listener) {
        super(itemView);
        mHintTextView = (TextView) itemView.findViewById(R.id.viewholder_challenge_hint);
        mChallengeImageView = (ImageView) itemView.findViewById(R.id.viewholder_challenge_picture);
        mListener = listener;

    }

    public void bind(ChallengePhoto challenge) {
        mHintTextView.setText(challenge.getHint());
        itemView.setOnClickListener(onClick(challenge));
    }

    private View.OnClickListener onClick(final ChallengePhoto challenge) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChallengeClicked(challenge);
            }
        };
    }


}
