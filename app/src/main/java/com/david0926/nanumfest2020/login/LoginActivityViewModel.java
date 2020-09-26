package com.david0926.nanumfest2020.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginActivityViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>("");
    public MutableLiveData<String> pw = new MutableLiveData<>("");

}
