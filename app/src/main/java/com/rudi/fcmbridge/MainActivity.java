package com.rudi.fcmbridge;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Buat TextView sederhana untuk menampilkan token
        TextView tv = new TextView(this);
        tv.setPadding(50, 100, 50, 100);
        tv.setText("Mengambil FCM token...");
        setContentView(tv);

        // Ambil token FCM dari Firebase
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(token -> {
                    tv.setText("✅ FCM Token:\n\n" + token + "\n\nCopy token ini ke server kamu.");
                })
                .addOnFailureListener(e -> {
                    tv.setText("❌ Gagal ambil token:\n" + e.getMessage());
                });
    }
}
