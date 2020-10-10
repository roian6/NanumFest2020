package com.david0926.nanumfest2020.screen.onboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityOnBoardBinding;
import com.david0926.nanumfest2020.screen.login.LoginActivity;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;


public class OnBoardActivity extends AppCompatActivity {

    private ActivityOnBoardBinding binding;
    private OnBoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_board);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new OnBoardActivityClickHandler());

        viewModel = ViewModelProviders.of(this).get(OnBoardViewModel.class);
        binding.setViewModel(viewModel);

        OnBoardPagerAdapter adapter = new OnBoardPagerAdapter(this);
        binding.pagerOnBoard.setAdapter(adapter);
    }

    public class OnBoardActivityClickHandler {
        public void btnFinishClick() {
            //TODO: Mission 2 - 온보딩 화면이 끝난 후, 로그인 화면으로 넘어갈 수 있도록 코드를 작성해 주세요.
            onBoardIsShown();
            showLogin();
            finish();
        }
    }

    private void onBoardIsShown(){
        SharedPreferenceUtil.put(OnBoardActivity.this, "user_state", "login");
    }

    private void showLogin(){
        startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (!viewModel.isFirstPage()) viewModel.previousPage();
        else super.onBackPressed();
    }
}