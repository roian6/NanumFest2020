package com.david0926.nanumfest2020.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.FragmentRegister2Binding;

public class Register2Fragment extends Fragment {

    public static Register2Fragment newInstance() {
        return new Register2Fragment();
    }

    private FragmentRegister2Binding binding;
    private RegisterViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_2, container, false);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(requireActivity()).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.pw.observe(getViewLifecycleOwner(), s -> checkNextPageEnabled());
        viewModel.pwConfirm.observe(getViewLifecycleOwner(), s -> checkNextPageEnabled());

        return binding.getRoot();
    }

    private void checkNextPageEnabled() {
        String pw = viewModel.pw.getValue();
        viewModel.isNextEnabled.setValue(!pw.isEmpty() && viewModel.pwConfirm.getValue().equals(pw));
    }
}
