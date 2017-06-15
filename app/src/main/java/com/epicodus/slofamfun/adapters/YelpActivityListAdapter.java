package com.epicodus.slofamfun.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.models.Activity;
import com.epicodus.slofamfun.ui.YelpDetailActivity;
import com.epicodus.slofamfun.ui.YelpDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class YelpActivityListAdapter extends RecyclerView.Adapter<YelpActivityListAdapter.YelpViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Activity> mActivities = new ArrayList<>();
    private Context mContext;

    public YelpActivityListAdapter(Context context, ArrayList<Activity> activities) {
        mContext = context;
        mActivities = activities;
    }

    @Override
    public YelpActivityListAdapter.YelpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yelp_activity_list_item, parent, false);
        YelpViewHolder viewHolder = new YelpViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(YelpActivityListAdapter.YelpViewHolder holder, int position) {
        holder.bindActivity(mActivities.get(position));
    }

    @Override
    public int getItemCount() {
        return mActivities.size();
    }

    public class YelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.activityImageView) ImageView mRestaurantImageView;
        @Bind(R.id.activityNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
//        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;
        private int mOrientation;

        public YelpViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        private void createDetailFragment(int position) {
            YelpDetailFragment detailFragment = YelpDetailFragment.newInstance(mActivities, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.yelpDetailContainer, detailFragment);
            ft.commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, YelpDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("activities", Parcels.wrap(mActivities));
                mContext.startActivity(intent);
            }
        }

        public void bindActivity(Activity activity) {
            Picasso.with(mContext)
                    .load(activity.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mRestaurantImageView);

            mNameTextView.setText(activity.getName());
            mCategoryTextView.setText(activity.getCategories().get(0));
//            mRatingTextView.setText(("Rataing: " + activity.getRating() + "/5"));
        }
    }

}
