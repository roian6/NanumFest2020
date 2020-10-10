package com.david0926.nanumfest2020.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.screen.login.LoginActivity;
import com.david0926.nanumfest2020.screen.main.MainActivity;
import com.david0926.nanumfest2020.screen.onboard.OnBoardActivity;
import com.david0926.nanumfest2020.screen.register.RegisterActivity;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //TODO: Mission 1 - 스플래시 스크린이 2초 뒤에 넘어갈 수 있도록 코드를 작성해 주세요.
        nextScreen(2000);

    }

    private void nextScreen(int delay) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            String userState = SharedPreferenceUtil.getString(this, "user_state", "on_board");

            switch (userState) {
                case "on_board":
                    startActivity(new Intent(SplashActivity.this, OnBoardActivity.class));
                    break;
                case "upload_profile":
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    intent.putExtra("register_page", 2);
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    startActivity(intent);
                    break;
                default:
                    startActivity(new Intent(SplashActivity.this,
                            FirebaseAuth.getInstance().getCurrentUser() == null ?
                                    LoginActivity.class : MainActivity.class));
            }
            finish();
        }, delay);
    }
}
