package com.epicodus.slofamfun.ui;


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

import butterknife.Bind;
import butterknife.ButterKnife;

public class YelpDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.activityImageView) ImageView mImageLabel;
    @Bind(R.id.activityNameTextView) TextView mNameLabel;
    @Bind(R.id.categoryTextView) TextView mCategoriesLabel;
//    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
//    @Bind(R.id.addActivityButton) TextView mAddActivityButton;

    private Activity mActivity;


    public static YelpDetailFragment newInstance(Activity activity) {
        YelpDetailFragment yelpDetailFragment = new YelpDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("activity", Parcels.wrap(activity));
        yelpDetailFragment.setArguments(args);
        return yelpDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = Parcels.unwrap(getArguments().getParcelable("activity"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yelp_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mActivity.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        Log.d("image", mActivity.getImageUrl());


        mNameLabel.setText(mActivity.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mActivity.getCategories()));
//        mRatingLabel.setText(Double.toString(mActivity.getRating()) + "/5");
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
