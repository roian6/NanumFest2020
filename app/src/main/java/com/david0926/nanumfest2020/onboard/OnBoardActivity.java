package com.david0926.nanumfest2020.onboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.MainActivity;
import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityOnBoardBinding;
import com.david0926.nanumfest2020.login.LoginActivity;
import com.david0926.nanumfest2020.util.SharedPreferenceUtil;


public class OnBoardActivity extends AppCompatActivity {

    private ActivityOnBoardBinding binding;
    private OnBoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_board);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        viewModel = ViewModelProviders.of(this).get(OnBoardViewModel.class);
        binding.setViewModel(viewModel);

        //TODO: move to viewModel
        OnBoardPagerAdapter adapter = new OnBoardPagerAdapter(this);
        binding.pagerOnBoard.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (viewModel.currentPage.getValue() != 0) viewModel.previousPage();
        else super.onBackPressed();
    }

    public void finishOnBoard() {
        SharedPreferenceUtil.put(this, "is_on_board_shown", true);
        startActivity(new Intent(OnBoardActivity.this, LoginActivity.class));
        finish();
    }
}