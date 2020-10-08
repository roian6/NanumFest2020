package com.david0926.nanumfest2020.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.databinding.FragmentMain3Binding;
import com.david0926.nanumfest2020.screen.login.LoginActivity;
import com.david0926.nanumfest2020.util.UserCache;
import com.google.firebase.auth.FirebaseAuth;

public class Main3Fragment extends Fragment {

    public static Main3Fragment newInstance() {
        return new Main3Fragment();
    }

    private FragmentMain3Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_3, container, false);
        binding.setLifecycleOwner(this);
        binding.setClickHandler(new Main3FragmentClickHandler());

        return binding.getRoot();
    }

    public class Main3FragmentClickHandler {
        public void btnDevInfoClick() {

        }

        public void btnClubInfoClick() {

        }

        public void btnOpenSourceClick() {

        }

        public void btnLogoutClick() {
            FirebaseAuth.getInstance().signOut();
            UserCache.logout(requireContext());
            requireActivity().finish();
            startActivity(new Intent(requireContext(), LoginActivity.class));
        }
    }
}
