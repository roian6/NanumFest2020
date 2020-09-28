package com.david0926.nanumfest2020.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.DialogLoadingBinding;

public class LoadingDialog extends Dialog {

    private Context mContext;
    private String msg = "";

    private DialogLoadingBinding binding;
    private LoadingDialogViewModel viewModel;

    private OnAnimationFinishListener onAnimationFinishListener;

    public interface OnAnimationFinishListener {
        void onAnimationFinish();
    }

    public void setOnAnimationFinishListener(OnAnimationFinishListener listener) {
        onAnimationFinishListener = listener;
    }

    public LoadingDialog(@NonNull Context context) {
        super(context);
        super.setCancelable(false);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_loading, null, false);
        setContentView(binding.getRoot());

        binding.setLifecycleOwner((FragmentActivity) mContext);

        ViewModelProvider.NewInstanceFactory f = new ViewModelProvider.NewInstanceFactory();
        viewModel = f.create(LoadingDialogViewModel.class);

        binding.setViewModel(viewModel);

        viewModel.msg.setValue(msg);
    }

    public void setMessage(String msg) {
        if (viewModel != null) viewModel.msg.setValue(msg);
        else this.msg = msg;
    }

    public void finish(boolean success) {
        if (!success) {
            cancel();
            return;
        }

        viewModel.lottieLoop.setValue(false);
        viewModel.lottieAnimation.setValue(R.raw.success);

        //TODO: hardcoded delay - replace with lottie duration
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            onAnimationFinishListener.onAnimationFinish();
            cancel();
        }, 1500);
    }


}
