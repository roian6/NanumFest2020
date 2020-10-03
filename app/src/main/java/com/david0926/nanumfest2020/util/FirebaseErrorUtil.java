package com.david0926.nanumfest2020.util;

import android.content.res.Resources;

import com.david0926.nanumfest2020.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;

public class FirebaseErrorUtil {

    // TODO: handle more error - task.getException().getErrorCode()...

    public static String getErrorString(Resources res, Exception e, int id) {
        return e instanceof FirebaseNetworkException ?
                res.getString(R.string.error_no_internet) : res.getString(id);
    }

    public static String getErrorString(Resources res, Exception e, String msg) {
        return e instanceof FirebaseNetworkException ?
                res.getString(R.string.error_no_internet) : msg;
    }
}
