package com.david0926.nanumfest2020.login;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityLoginBinding;
import com.david0926.nanumfest2020.util.dialog.LoadingDialog;

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
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage("로그인 중...");
        dialog.show();
        dialog.setOnSuccessListener(() -> {
            Toast.makeText(this, "wow", Toast.LENGTH_SHORT).show();
        });

        new Handler(getMainLooper()).postDelayed(() ->
                dialog.finish(true, "로그인 성공!"), 3000);
    }

    public void btnRegisterClick() {

    }
}