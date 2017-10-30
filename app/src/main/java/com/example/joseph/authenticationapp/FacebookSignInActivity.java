package com.example.joseph.authenticationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FacebookSignInActivity extends AppCompatActivity {

    private static final String TAG = "FacebookSignInActivity";
    private FirebaseAuth mAuth;
    private TextView userName, email;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_sign_in);
        Log.d(TAG, "onCreate: ");

        email = findViewById(R.id.tvFacebookEmail);
        userName = findViewById(R.id.tvFacebookUserName);
        image = findViewById(R.id.ivFacebookImgProfile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Glide.with(this).load(user.getPhotoUrl()).into(image);
        userName.setText(user.getDisplayName());
        email.setText(user.getEmail());

    }
}
