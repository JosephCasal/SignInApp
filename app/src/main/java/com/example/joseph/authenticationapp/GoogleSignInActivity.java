package com.example.joseph.authenticationapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GoogleSignInActivity extends AppCompatActivity{

    private static final String TAG = "GoogleSignInActivity";
    private TextView userName, email;
    private ImageView image;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        Log.d(TAG, "onCreate: ");

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.tvGmailEmail);
        userName = findViewById(R.id.tvGmailUserName);
        image = findViewById(R.id.ivGoogleImgProfile);

        FirebaseUser user = mAuth.getCurrentUser();
        Glide.with(this).load(user.getPhotoUrl()).into(image);
        userName.setText(user.getDisplayName());
        email.setText(user.getEmail());

        Log.d(TAG, "onCreate: username: " + user.getDisplayName());

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(TAG, "onAuthStateChanged: ");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Glide.with(GoogleSignInActivity.this)
                            .load(user.getPhotoUrl())
                            .into(image);
                    userName.setText(user.getDisplayName());
                    email.setText(user.getEmail());

                } else {
                    Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
                    intent.putExtra("logout", true);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }
}
