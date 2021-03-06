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

import java.util.Arrays;


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

        OnBoardPagerAdapter adapter = new OnBoardPagerAdapter(this, viewModel.fragments);
        binding.pagerOnBoard.setAdapter(adapter);
    }

    public class OnBoardActivityClickHandler {
        public void btnFinishClick() {
            SharedPreferenceUtil.put(OnBoardActivity.this, "user_state", "login");
            startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!viewModel.isFirstPage()) viewModel.previousPage();
        else super.onBackPressed();
    }
}