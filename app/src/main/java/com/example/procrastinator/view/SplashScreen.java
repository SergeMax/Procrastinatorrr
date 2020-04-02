package com.example.procrastinator.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.procrastinator.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private static int SPLAH_TIME_OUT = 2500;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        TextView txtTitre = (TextView) findViewById(R.id.titre);


        //Font///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/vfuturalight.otf");

        txtTitre.setTypeface(font);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser user) {
        // hideProgressDialog();
        if (user != null) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, ListeTaches.class);
                    startActivity(i);
                    finish();
                }
            }, SPLAH_TIME_OUT);


        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, ConnectActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLAH_TIME_OUT);
        }
    }


}
