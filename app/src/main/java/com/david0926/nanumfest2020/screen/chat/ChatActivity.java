package com.david0926.nanumfest2020.screen.chat;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.ActivityChatBinding;
import com.david0926.nanumfest2020.util.LinearLayoutManagerWrapper;
import com.david0926.nanumfest2020.util.UserCache;

import gun0912.tedkeyboardobserver.TedKeyboardObserver;

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

        binding.recyclerChat.setLayoutManager(new LinearLayoutManagerWrapper(
                this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerChat.setAdapter(new ChatAdapter(UserCache.getUser(this)));

        FirebaseObserveChat.observeMessage(getResources(),
                chatDoc -> {
                    // TODO: Mission 14 - 채팅 메시지를 수신했을 때 화면에 추가할 수 있도록 코드를 작성해 주세요.

                },
                errorMsg -> Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show());

        new TedKeyboardObserver(this).listen(isShow -> {
            if (isShow) scrollToBottom();
        });
    }

    public class ChatActivityClickHandler {
        public void btnBackClick() {
            onBackPressed();
        }

        public void btnSendClick() {
            // TODO: Mission 15 - 전송 버튼을 누르면 메시지가 전송되도록 코드를 작성해 주세요.

        }
    }

    private void scrollToBottom() {
        if (!viewModel.chatModels.isEmpty())
            binding.recyclerChat.smoothScrollToPosition(viewModel.chatModels.size() - 1);
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

    private void refreshChat(ChatDocModel chatDoc) {
        viewModel.chatModels.clear();
        viewModel.chatModels.addAll(chatDoc.getChatModels());
    }

    private void clearInput() {
        viewModel.message.setValue("");
    }

    private void sendMessage() {
        FirebaseSendChat.sendMessage(UserCache.getUser(ChatActivity.this),
                viewModel.message.getValue(), getResources(),
                () -> {

                },
                errorMsg -> Toast.makeText(ChatActivity.this, errorMsg, Toast.LENGTH_SHORT).show());
    }

}

