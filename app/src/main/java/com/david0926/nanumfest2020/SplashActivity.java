package com.david0926.nanumfest2020;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.david0926.nanumfest2020.login.LoginActivity;
import com.david0926.nanumfest2020.onboard.OnBoardActivity;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            boolean isLandingShown = SharedPreferenceUtil
                    .getBoolean(this, "is_on_board_shown", false);

            // TODO: debug - remove this line to show welcome & onboard screen once
            // isLandingShown = false;

            if (isLandingShown) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                startActivity(new Intent(SplashActivity.this,
                        firebaseAuth.getCurrentUser() == null ?
                                LoginActivity.class : MainActivity.class));
            } else startActivity(new Intent(SplashActivity.this, OnBoardActivity.class));


            finish();
        }, 2000);
    }
}
