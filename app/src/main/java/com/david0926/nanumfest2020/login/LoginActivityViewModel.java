package com.david0926.nanumfest2020.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.david0926.nanumfest2020.R;

public class LoginActivityViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> pw = new MutableLiveData<>("");

    @BindingAdapter("bindErrorMsg")
    public static void bindErrorMsg(TextView t, String s) {
        if (s == null) return;
        t.setText(s);
        if (s.isEmpty()) return;
        t.startAnimation(AnimationUtils.loadAnimation(t.getContext(), R.anim.shake));
    }

    public MutableLiveData<String> errorMsg = new MutableLiveData<>("");

    @BindingAdapter("bindTextWatcher")
    public static void bindTextWatcher(EditText e, TextWatcher w) {
        e.addTextChangedListener(w);
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            errorMsg.setValue("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
