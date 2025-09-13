package com.rudi.fcmbridge;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d("FCM", "Token baru: " + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String link = remoteMessage.getData() != null ? remoteMessage.getData().get("link") : null;
        if (link == null || link.trim().isEmpty()) return;

        Log.d("FCM", "Push diterima: " + link);

        // 1) Buka langsung di browser
        try {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (Throwable t) {
            Log.w("FCM", "startActivity gagal: " + t.getMessage());
        }

        // 2) Broadcast ke MacroDroid (fallback)
        try {
            Intent b = new Intent("com.rudi.FCM_LINK");
            b.putExtra("url", link);
            sendBroadcast(b);
        } catch (Throwable t) {
            Log.w("FCM", "Broadcast gagal: " + t.getMessage());
        }
    }
}
