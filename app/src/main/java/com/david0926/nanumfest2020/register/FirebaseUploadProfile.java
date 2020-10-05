package com.david0926.nanumfest2020.register;

import android.content.res.Resources;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.david0926.nanumfest2020.R;
import com.david0926.nanumfest2020.util.FirebaseErrorUtil;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FirebaseUploadProfile {

    private static OnUploadSuccessListener onUploadSuccessListener;
    private static OnUploadFailedListener onUploadFailedListener;

    private static Resources mResources;

    public interface OnUploadSuccessListener {
        void onUploadSuccess();
    }

    public interface OnUploadFailedListener {
        void onUploadFailed(String errorMsg);
    }

    public static void uploadProfile(Uri profile, String introduce, Resources res, OnUploadSuccessListener s, OnUploadFailedListener e) {
        onUploadSuccessListener = s;
        onUploadFailedListener = e;
        mResources = res;

        if (!getMimeType(profile).equals("image/jpeg") && !getMimeType(profile).equals("image/png")) {
            onUploadFailedListener.onUploadFailed(mResources.getString(R.string.error_image_invalid_type));
            return;
        }

        // TODO: check image size later - file.length()

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            onUploadFailedListener.onUploadFailed(mResources.getString(R.string.error_profile_logged_out));
            return;
        }

        String email = user.getEmail();

        uploadProfile(email, profile,
                s1 -> updateUserInfo(email, s1.toString(), introduce,
                        s2 -> onUploadSuccessListener.onUploadSuccess()));
    }

    private static String getMimeType(Uri uri) {
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
    }

    private static void uploadProfile(String email, Uri profile, OnSuccessListener<Uri> s) {
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/png")
                .build();

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("profile/" + email + ".png");

        UploadTask uploadTask = storageReference.putFile(profile, metadata);

        uploadTask.continueWithTask((Continuation<UploadTask.TaskSnapshot, Task<Uri>>) task -> {
            if (task.isSuccessful()) return storageReference.getDownloadUrl();

            onUploadFailedListener.onUploadFailed(FirebaseErrorUtil
                    .getErrorString(mResources, task.getException(), R.string.error_image_upload_failed));
            return null;
        })
                .addOnSuccessListener(s)
                .addOnFailureListener(e -> onUploadFailedListener.onUploadFailed(
                        FirebaseErrorUtil.getErrorString(mResources, e, R.string.error_image_url_failed)));
    }

    private static void updateUserInfo(String email, String profile, String introduce, OnSuccessListener<Void> s) {
        FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(email)
                .update("introduce", introduce, "profile", profile)
                .addOnSuccessListener(s)
                .addOnFailureListener(e -> onUploadFailedListener.onUploadFailed(
                        FirebaseErrorUtil.getErrorString(mResources, e, mResources.getString(R.string.error_user_info_update_failed))));
    }
}