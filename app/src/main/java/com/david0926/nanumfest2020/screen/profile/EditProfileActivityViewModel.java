package com.david0926.nanumfest2020.screen.profile;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditProfileActivityViewModel extends ViewModel {

    public MutableLiveData<Uri> profile = new MutableLiveData<>();
    public MutableLiveData<String> profileLink = new MutableLiveData<>();

    public MutableLiveData<String> introduce = new MutableLiveData<>("");

    public MutableLiveData<String> errorMsg = new MutableLiveData<>("");

}
