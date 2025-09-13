package com.rudi.fcmbridge;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Ambil data "link" dari payload FCM
        if (remoteMessage.getData().containsKey("link")) {
            String url = remoteMessage.getData().get("link");
            Log.d("FCM", "Opening link: " + url);

            // Intent untuk buka browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("FCM", "New token: " + token);
        // Bisa kamu kirim balik token ini ke server kamu
    }
}
