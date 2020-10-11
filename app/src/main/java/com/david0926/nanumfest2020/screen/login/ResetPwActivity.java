package com.david0926.nanumfest2020.screen.login;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityResetPwBinding;
import com.david0926.nanumfest2020.util.UserCache;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPwActivity extends AppCompatActivity {

    private ActivityResetPwBinding binding;
    private ResetPwActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_pw);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new ResetPwActivityClickHandler());

        viewModel = ViewModelProviders.of(this).get(ResetPwActivityViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.email.observe(this, s -> checkSendEnabled());
    }

    public class ResetPwActivityClickHandler {
        public void btnBackClick() {
            finish();
        }

        public void btnSendClick() {
            // TODO: Mission 9 - 비밀전호 재설정 메일을 보낼 수 있도록 코드를 작성해 주세요.

        }
    }

    private void checkSendEnabled() {
        viewModel.isSendEnabled.setValue(
                android.util.Patterns.EMAIL_ADDRESS.matcher(viewModel.email.getValue()).matches());
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

    private void sendEmail(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(viewModel.email.getValue());
    }

    private void showMessage(){
        Toast.makeText(ResetPwActivity.this, R.string.reset_pw_success, Toast.LENGTH_SHORT).show();
    }


}