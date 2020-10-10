package com.david0926.nanumfest2020.screen.register;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityRegisterBinding;
import com.david0926.nanumfest2020.dialog.LoadingDialog;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new RegisterActivityClickHandler());

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.currentPage.setValue(getIntent().getIntExtra("register_page", 0));
    }

    public class RegisterActivityClickHandler {

        public void btnNextClick() {
            switch (viewModel.currentPage.getValue()) {
                case 0:
                    // TODO: Mission 6 - 첫 번째 회원가입 페이지에서 넘어갈 때,
                    //  이메일 중복 검사를 진행하도록 코드를 작성해 주세요.
                    checkEmail();
                    break;
                case 1:
                    // TODO: Mission 7 - 두 번째 회원가입 페이지에서 넘어갈 때,
                    //  회원가입을 진행하도록 코드를 작성해 주세요.
                    createAccount();
                    break;
                case 2:
                    // TODO: Mission 8 - 세 번째 회원가입 페이지에서 넘어갈 때,
                    //  프로필 정보 업로드를 진행하도록 코드를 작성해 주세요.
                    uploadProfile();
            }
        }

        public void btnBackClick() {
            onBackPressed();
        }

        public void btnProfileSkipClick() {
            finishRegister();
        }
    }

    private void checkEmail() {
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.register_email_check)).show();

        FirebaseCheckEmail.checkEmail(viewModel.email.getValue(), getResources(),
                () -> {
                    dialog.setMessage(getString(R.string.register_email_check_success))
                            .setOnAnimationFinishListener(() -> viewModel.nextPage())
                            .finish(true);
                },
                errorMsg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(errorMsg);
                });
    }

    private void createAccount() {
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.register_signing)).show();

        FirebaseRegister.register(viewModel.name.getValue(), viewModel.email.getValue(),
                viewModel.pw.getValue(), getResources(),
                () -> {
                    SharedPreferenceUtil.put(this, "user_state", "upload_profile");
                    dialog.setMessage(getString(R.string.register_success))
                            .setOnAnimationFinishListener(() -> viewModel.nextPage())
                            .finish(true);
                },
                errorMsg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(errorMsg);
                });
    }

    private void uploadProfile() {
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.setMessage(getString(R.string.register_profile_uploading)).show();

        FirebaseUploadProfile.uploadProfile(viewModel.profile.getValue(), viewModel.introduce.getValue(), getResources(),
                (profile, introduce) -> {
                    dialog.setMessage(getString(R.string.register_profile_upload_success))
                            .setOnAnimationFinishListener(this::finishRegister)
                            .finish(true);
                },
                errorMsg -> {
                    dialog.finish(false);
                    viewModel.errorMsg.setValue(errorMsg);
                });
    }

    public void finishRegister() {
        SharedPreferenceUtil.put(this, "user_state", "login");
        FirebaseAuth.getInstance().signOut();
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
                builder.setMessage(R.string.register_skip_profile)
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