package com.david0926.nanumfest2020.register;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityRegisterBinding;
import com.david0926.nanumfest2020.dialog.LoadingDialog;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.currentPage.setValue(getIntent().getIntExtra("register_page", 0));
    }

    public void btnNextPressed() {
        switch (viewModel.currentPage.getValue()) {
            case 0:
                emailNext();
                break;
            case 1:
                pwNext();
                break;
            default:
                viewModel.nextPage();
        }
    }

    private void emailNext() {
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.register_email_check)).show();
        FirebaseCheckEmail.checkEmail(viewModel.email.getValue(), getResources(),
                () -> {
                    dialog.setMessage(getString(R.string.register_email_check_success))
                            .setOnAnimationFinishListener(() -> viewModel.nextPage())
                            .finish(true);
                },
                msg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(msg);
                });
    }

    private void pwNext() {
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.register_signing)).show();
        FirebaseRegister.register(viewModel.name.getValue(), viewModel.email.getValue(),
                viewModel.pw.getValue(), getResources(),
                () -> {
                    dialog.setMessage(getString(R.string.register_success))
                            .setOnAnimationFinishListener(() -> viewModel.nextPage())
                            .finish(true);
                    SharedPreferenceUtil.put(this, "user_state", "upload_profile");
                },
                msg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(msg);
                });
    }

    public void finishRegister() {
        SharedPreferenceUtil.put(this, "user_state", "login");
        finish();
    }

    @Override
    public void onBackPressed() {
        switch (viewModel.currentPage.getValue()) {
            case 0: //first page
                super.onBackPressed();
                break;

            case 2: //last page
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage(R.string.reigster_skip_profile)
                        .setPositiveButton(R.string.register_skip, (dialog, which) -> finishRegister())
                        .setNegativeButton(R.string.register_cancel, (dialog, which) -> {
                        }).show();
                break;

            default:
                viewModel.previousPage();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_down_before, R.anim.slide_down);
    }
}