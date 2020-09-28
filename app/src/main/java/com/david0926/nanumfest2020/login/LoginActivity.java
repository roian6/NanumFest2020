package com.david0926.nanumfest2020.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.MainActivity;
import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityLoginBinding;
import com.david0926.nanumfest2020.dialog.LoadingDialog;
import com.david0926.nanumfest2020.register.RegisterActivity;
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
        binding.setActivity(this);

        viewModel = ViewModelProviders.of(this).get(LoginActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    public void btnLoginClick() {
        KeyboardUtil.hideKeyboard(this);

        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.login_loading));
        dialog.show();

        FirebaseLogin.login(viewModel.email.getValue(), viewModel.pw.getValue(), getResources(),
                user -> {
                    UserCache.setUser(this, user);
                    dialog.setMessage(getString(R.string.login_success));
                    dialog.finish(true);
                    dialog.setOnAnimationFinishListener(() -> {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    });
                },
                errorMsg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(errorMsg);
                });
    }

    public void btnRegisterClick() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}