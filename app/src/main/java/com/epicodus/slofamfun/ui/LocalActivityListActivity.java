package com.epicodus.slofamfun.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.adapters.FirebaseActivityViewHolder;
import com.epicodus.slofamfun.models.LocalActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocalActivityListActivity extends AppCompatActivity {
    private DatabaseReference mLocalReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.localRecyclerView) RecyclerView mLocalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        mLocalReference = FirebaseDatabase.getInstance().getReference();
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        try {
            mFirebaseAdapter = new FirebaseRecyclerAdapter<LocalActivity, FirebaseActivityViewHolder>(LocalActivity.class, R.layout.local_activity_list, FirebaseActivityViewHolder.class, mLocalReference) {
                @Override
                protected void populateViewHolder(FirebaseActivityViewHolder viewHolder, LocalActivity model, int position) {
                    viewHolder.bindActivity(model);
                }
            };
            mLocalRecyclerView.setHasFixedSize(true);
            mLocalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mLocalRecyclerView.setAdapter(mFirebaseAdapter);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
