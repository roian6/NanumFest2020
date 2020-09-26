package com.david0926.nanumfest2020.onboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.david0926.nanumfest2020.R;


public class OnBoardPagerAdapter extends FragmentStateAdapter {

    public OnBoardPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        int layoutId;
        switch (position) {
            case 0:
                layoutId = R.layout.fragment_on_board_1;
                break;
            case 1:
                layoutId = R.layout.fragment_on_board_2;
                break;
            case 2:
                layoutId = R.layout.fragment_on_board_3;
                break;
            default:
                layoutId = R.layout.fragment_on_board_4;
                break;
        }

        return OnBoardPagerFragment.newInstance(layoutId);
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
