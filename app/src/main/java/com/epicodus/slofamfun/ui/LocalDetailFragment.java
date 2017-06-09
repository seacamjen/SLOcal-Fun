package com.epicodus.slofamfun.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.models.LocalActivity;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalDetailFragment extends Fragment {
    @Bind(R.id.localActivityNameTextView) TextView mLocalActiveName;
    @Bind(R.id.localAddressTextView) TextView mLocalActiveAddress;
    @Bind(R.id.localPhoneTextView) TextView mLocalActivePhone;
    @Bind(R.id.localWebsiteTextView) TextView mLocalActiveWebsite;
    @Bind(R.id.localAgeTextView) TextView mLocalAge;
    @Bind(R.id.localStrollerTextView) TextView mLocalStroller;
    @Bind(R.id.localCommentTextView) TextView mLocalComment;

    private LocalActivity mLocalActivity;


    public static LocalDetailFragment newInstance(LocalActivity localActivity) {
        LocalDetailFragment localDetailFragment = new LocalDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("localActivity", Parcels.wrap(localActivity));
        localDetailFragment.setArguments(args);
        return localDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalActivity = Parcels.unwrap(getArguments().getParcelable("localActivity"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_local_detail, container, false);
        ButterKnife.bind(this, view);

        mLocalActiveName.setText(mLocalActivity.getName());
        mLocalActiveAddress.setText(mLocalActivity.getAddress());
        mLocalActivePhone.setText(mLocalActivity.getPhone());
        mLocalActiveWebsite.setText(mLocalActivity.getWebsite());
        mLocalAge.setText(mLocalActivity.getAgeRange());
        mLocalStroller.setText(mLocalActivity.getStrollerAccess());
        mLocalComment.setText(mLocalActivity.getComments());

        return view;
    }

}
