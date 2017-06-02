package com.epicodus.slofamfun.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class YelpDetailFragment extends Fragment {
    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

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

        Picasso.with(view.getContext()).load(mActivity.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mActivity.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mActivity.getCategories()));
        mRatingLabel.setText(Double.toString(mActivity.getRating()) + "/5");
        mPhoneLabel.setText(mActivity.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mActivity.getAddress()));

        return view;
    }

}
