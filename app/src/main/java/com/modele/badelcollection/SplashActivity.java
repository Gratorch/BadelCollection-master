package com.modele.badelcollection;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth =FirebaseAuth.getInstance();

        SystemClock.sleep(3000);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser==null){
            Intent registerIntent =new Intent(SplashActivity.this,RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }else{
            Intent mainItent =new Intent(SplashActivity.this,MainActivity.class);
            startActivity(mainItent);
            finish();
        }
    }
}
