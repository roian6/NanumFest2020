package com.david0926.nanumfest2020.util.dialog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoadingDialogViewModel extends ViewModel {

    public MutableLiveData<String> msg = new MutableLiveData<>("");

}
