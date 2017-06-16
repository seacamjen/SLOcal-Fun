package com.epicodus.slofamfun.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.slofamfun.R;
import com.epicodus.slofamfun.models.LocalActivity;
import com.epicodus.slofamfun.ui.LocalDetailActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;


public class FirebaseActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseActivityViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindActivity(LocalActivity localActivity) {
        TextView activityName = (TextView) mView.findViewById(R.id.localNameTextView);
        TextView activityAddress = (TextView) mView.findViewById(R.id.localAddressTextView);
        TextView activityComment = (TextView) mView.findViewById(R.id.localCommentTextView);
        ImageView activityImage = (ImageView) mView.findViewById(R.id.localImageView);

        try {
            Bitmap imageBitmap = decodeFromFirebaseBase64(localActivity.getImage());
            activityImage.setImageBitmap(imageBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        activityName.setText(localActivity.getName());
        activityAddress.setText(localActivity.getAddress());
        activityComment.setText(localActivity.getComments());
    }

    @Override
    public void onClick(View view) {
        String preference = PreferenceManager.getDefaultSharedPreferences(mContext).getString("city", "whatIwant");
        final ArrayList<LocalActivity> localActivity = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(preference);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    localActivity.add(snapshot.getValue(LocalActivity.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, LocalDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("localActivity", Parcels.wrap(localActivity));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
