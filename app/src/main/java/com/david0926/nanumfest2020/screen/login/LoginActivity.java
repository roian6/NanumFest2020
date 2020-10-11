package com.david0926.nanumfest2020.screen.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityLoginBinding;
import com.david0926.nanumfest2020.dialog.LoadingDialog;
import com.david0926.nanumfest2020.screen.main.MainActivity;
import com.david0926.nanumfest2020.screen.register.RegisterActivity;
import com.david0926.nanumfest2020.util.KeyboardUtil;
import com.david0926.nanumfest2020.util.UserCache;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new LoginActivityClickHandler());

        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    public class LoginActivityClickHandler {

        public void btnResetPwClick() {
            //TODO: Mission 3 - 비밀번호 재설정 버튼을 누르면 해당 화면을 보여주도록 코드를 작성해 주세요.

        }

        public void btnLoginClick() {
            // TODO: Mission 4 - 로그인 버튼을 누르면 로그인을 진행하도록 코드를 작성해 주세요.

        }

        public void btnRegisterClick() {
            // TODO: Mission 5 - 회원가입 버튼을 누르면 회원가입이 진행되도록 코드를 작성해 주세요.

        }

    }

    /* method field
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */

    private void showRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_up, R.anim.slide_up_before);
    }

    private void doLogin() {
        LoadingDialog dialog = new LoadingDialog(LoginActivity.this);
        dialog.setMessage(getString(R.string.login_loading)).show();

        FirebaseLogin.login(viewModel.email.getValue(), viewModel.pw.getValue(), getResources(),
                user -> {
                    UserCache.setUser(LoginActivity.this, user);
                    dialog.setMessage(getString(R.string.login_success)).setOnAnimationFinishListener(() -> {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }).finish(true);
                },
                errorMsg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(errorMsg);
                });
    }

    private void hideKeyBoard() {
        KeyboardUtil.hideKeyboard(LoginActivity.this);
    }

    private void showResetPw() {
        startActivity(new Intent(LoginActivity.this, ResetPwActivity.class));
    }
}