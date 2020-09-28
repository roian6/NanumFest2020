package com.david0926.nanumfest2020.login;

import android.content.res.Resources;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseLogin {

    private static OnLoginSuccessListener onLoginSuccessListener;
    private static OnLoginFailedListener onLoginFailedListener;

    private static Resources mResources;

    public interface OnLoginSuccessListener {
        void onLoginSuccess(UserModel user);
    }

    public interface OnLoginFailedListener {
        void onLoginFailed(String msg);
    }

    public static void login(String email, String pw, Resources res, OnLoginSuccessListener s, OnLoginFailedListener e) {
        onLoginSuccessListener = s;
        onLoginFailedListener = e;
        mResources = res;

        if (email == null || pw == null || email.isEmpty() || pw.isEmpty()) {
            onLoginFailedListener.onLoginFailed(mResources.getString(R.string.error_empty_field));
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onLoginFailedListener.onLoginFailed(mResources.getString(R.string.error_email_invalid));
            return;
        }

        checkAccountExists(email,
                s1 -> getUserData(email,
                        s2 -> signInWithEmail(email, pw,
                                s3 -> onLoginSuccessListener.onLoginSuccess(s2.toObject(UserModel.class)))
                ));
    }

    private static void checkAccountExists(String email, OnSuccessListener<SignInMethodQueryResult> s) {
        FirebaseAuth
                .getInstance()
                .fetchSignInMethodsForEmail(email)
                .addOnSuccessListener(result -> {
                    List<String> signInMethods = result.getSignInMethods();
                    if (signInMethods != null && !signInMethods.isEmpty()) s.onSuccess(result);
                    else onLoginFailedListener.onLoginFailed(
                            mResources.getString(R.string.error_no_user_account));
                })
                .addOnFailureListener(e -> onLoginFailedListener.onLoginFailed(
                        getErrorString(e, R.string.error_no_user_account)));
    }

    private static void getUserData(String document, OnSuccessListener<DocumentSnapshot> s) {
        FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(document)
                .get()
                .addOnSuccessListener(s)
                .addOnFailureListener(e -> onLoginFailedListener.onLoginFailed(
                        getErrorString(e, R.string.error_no_user_data)));
    }

    private static void signInWithEmail(String email, String pw, OnSuccessListener<AuthResult> s) {
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener(s)
                .addOnFailureListener(e -> onLoginFailedListener.onLoginFailed(
                        getErrorString(e, R.string.error_password_invalid)));
    }

    private static String getErrorString(Exception e, int id) {
        return e instanceof FirebaseNetworkException ?
                mResources.getString(R.string.error_no_internet) : mResources.getString(id);
    }
}
