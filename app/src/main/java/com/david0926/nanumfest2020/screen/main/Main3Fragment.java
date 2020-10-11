package com.david0926.nanumfest2020.screen.main;

import android.content.Intent;
import android.net.Uri;
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

        public void btnLinkClick(String url) {
            // TODO: Mission 12 - 버튼을 누르면 브라우저가 실행되도록 코드를 작성해 주세요.

        }

        public void btnLogoutClick() {
            // TODO: Mission 13 - 버튼을 누르면 로그아웃이 진행되도록 코드를 작성해 주세요.

        }
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

    private void startUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void startLogin() {
        startActivity(new Intent(requireContext(), LoginActivity.class));
    }

    private void finish() {
        requireActivity().finish();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        UserCache.logout(requireContext());
    }
}
