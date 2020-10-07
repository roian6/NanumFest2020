package com.david0926.nanumfest2020.chat;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class ChatActivityViewModel extends ViewModel {

    @BindingAdapter("bindToolbarNavigationClick")
    public static void bindToolbarIconClick(Toolbar t, Runnable r) {
        t.setNavigationOnClickListener(v -> r.run());
    }


}
