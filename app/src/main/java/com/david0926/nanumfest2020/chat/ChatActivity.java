package com.david0926.nanumfest2020.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private ChatActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new ChatActivityClickHandler());

        viewModel = ViewModelProviders.of(this).get(ChatActivityViewModel.class);
        binding.setViewModel(viewModel);
    }

    public class ChatActivityClickHandler {
        public void btnBackClick() {
            onBackPressed();
        }

        public void btnSendClick() {

        }
    }
}

