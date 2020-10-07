package com.david0926.nanumfest2020.chat;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    // TODO: better approach - https://thdev.tech/android/2018/01/31/Recycler-Adapter-Distinguish/

    protected static class ChatHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ChatHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
