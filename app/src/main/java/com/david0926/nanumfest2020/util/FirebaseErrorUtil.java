package com.david0926.nanumfest2020.util;

import android.content.res.Resources;

import com.david0926.nanumfest2020.R;
import com.google.firebase.FirebaseNetworkException;

import javax.annotation.Nullable;

public class FirebaseErrorUtil {

    // TODO: handle more error - task.getException().getErrorCode()...

    public static String getErrorString(Resources res, Exception e, String defaultMsg) {
        return e instanceof FirebaseNetworkException ?
                res.getString(R.string.error_no_internet) : defaultMsg;
    }

    public static String getErrorString(Resources res, Exception e, int defaultMsg) {
        return getErrorString(res, e, res.getString(defaultMsg));
    }
}
