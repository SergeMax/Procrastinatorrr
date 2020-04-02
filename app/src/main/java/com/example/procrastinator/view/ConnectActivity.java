package com.example.procrastinator.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.procrastinator.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnectActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static final String TAG = "main";
    private String email;
    private String password;
    private TextView statusConnexion;
    private static int timeOut = 1500;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        init();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }


    public void init(){

        emailLayout = (TextInputLayout) findViewById(R.id.adresseMailUser);
        passwordLayout = (TextInputLayout) findViewById(R.id.password);


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
            //  statusConnexion.setText("Bonjour, " + user.getEmail() + " " +  user.getUid());

            //   findViewById(R.id.btnLogin).setVisibility(View.GONE);
            //  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);

            //    statusConnexion.setVisibility(View.GONE);
            // emailLayout.setVisibility(View.GONE);
            //  passwordLayout.setVisibility(View.GONE);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(ConnectActivity.this, ListeTaches.class);
                    startActivity(i);
                    finish();
                }
            }, timeOut);




        } else {
//            statusConnexion.setText("Connexion");
            //  mDetailTextView.setText(null);

            //  findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            //   findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    public void setEmailPassUser(View view){

        email = emailLayout.getEditText().getText().toString();
        password = passwordLayout.getEditText().getText().toString();



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ConnectActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }
}