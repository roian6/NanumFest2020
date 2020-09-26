package com.david0926.nanumfest2020.util.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.DialogLoadingBinding;

public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    private Context mContext;

    private OnSuccessListener onSuccessListener;
    private String msg = "";

    private DialogLoadingBinding binding;
    private LoadingDialogViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setCancelable(false);

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_loading, null, false);
        binding.setLifecycleOwner((FragmentActivity) mContext);
        setContentView(binding.getRoot());

        ViewModelProvider.NewInstanceFactory f = new ViewModelProvider.NewInstanceFactory();
        viewModel = f.create(LoadingDialogViewModel.class);

        binding.setViewModel(viewModel);

        viewModel.msg.setValue(msg);

    }

    public void setMessage(String msg) {
        if (viewModel != null) viewModel.msg.setValue(msg);
        else this.msg = msg;
    }

    public void finish(boolean success, String message) {

        //TODO: MVVM
        viewModel.msg.setValue(message);
        binding.lottieLoading.setRepeatCount(0);
        binding.lottieLoading.setAnimation(R.raw.success);
        binding.lottieLoading.playAnimation();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            onSuccessListener.onSuccess();
            cancel();
        }, 1000 + binding.lottieLoading.getDuration());
    }

    public interface OnSuccessListener {
        void onSuccess();
    }

    public void setOnSuccessListener(OnSuccessListener listener) {
        onSuccessListener = listener;
    }


}
