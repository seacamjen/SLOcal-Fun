package com.epicodus.slofamfun.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.models.LocalActivity;
import com.epicodus.slofamfun.ui.YelpDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;


    View mView;
    Context mContext;

    public FirebaseActivityViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindActivity(LocalActivity localActivity) {
        TextView localActivityName = (TextView) mView.findViewById(R.id.localNameText);
        TextView localActivityAddress = (TextView) mView.findViewById(R.id.localAddressText);
        TextView localActivityComments = (TextView) mView.findViewById(R.id.localCommentsText);

        localActivityName.setText(localActivity.getName());
        localActivityAddress.setText(localActivity.getAddress());
        localActivityComments.setText(localActivity.getComments());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<LocalActivity> localActivities = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cities");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    localActivities.add(snapshot.getValue(LocalActivity.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, YelpDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("localActivites", Parcels.wrap(localActivities));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
