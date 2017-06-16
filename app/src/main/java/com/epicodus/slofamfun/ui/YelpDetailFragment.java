package com.epicodus.slofamfun.ui;


import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.models.Activity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YelpDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    private ArrayList<Activity> mActivities;
    private int mPosition;

    @Bind(R.id.activityImageView) ImageView mImageLabel;
    @Bind(R.id.activityNameTextView) TextView mNameLabel;
    @Bind(R.id.categoryTextView) TextView mCategoriesLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;

    private Activity mActivity;


    public static YelpDetailFragment newInstance(ArrayList<Activity> activities, Integer position) {
        YelpDetailFragment yelpDetailFragment = new YelpDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable("activity", Parcels.wrap(activities));
        args.putInt("position", position);

        yelpDetailFragment.setArguments(args);
        return yelpDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivities = Parcels.unwrap(getArguments().getParcelable("activity"));
        mPosition = getArguments().getInt("position");
        mActivity = mActivities.get(mPosition);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yelp_detail, container, false);
        ButterKnife.bind(this, view);

        try {
            Picasso.with(view.getContext())
                    .load(mActivity.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mImageLabel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        mNameLabel.setText(mActivity.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mActivity.getCategories()));
        mPhoneLabel.setText(mActivity.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mActivity.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mActivity.getWebsite()));
            startActivity(webIntent);
        }
        if(v ==mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mActivity.getPhone()));
            startActivity(phoneIntent);
        }
        if(v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mActivity.getLatitude() + "," + mActivity.getLongitude() + "?q=(" + mActivity.getName() + ")"));
            startActivity(mapIntent);
        }
    }

}
