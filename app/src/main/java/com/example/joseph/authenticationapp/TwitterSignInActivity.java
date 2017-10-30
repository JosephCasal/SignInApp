package com.example.joseph.authenticationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TwitterSignInActivity extends AppCompatActivity {

    private static final String TAG = "TwitterSignInActivity";
    private FirebaseAuth mAuth;
    private TextView userName, email;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_sign_in);

        email = findViewById(R.id.tvTwitterEmail);
        userName = findViewById(R.id.tvTwitterUserName);
        image = findViewById(R.id.ivTwitterImgProfile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Glide.with(this).load(user.getPhotoUrl()).into(image);
        userName.setText(user.getDisplayName());
        email.setText(user.getEmail());

    }
}
