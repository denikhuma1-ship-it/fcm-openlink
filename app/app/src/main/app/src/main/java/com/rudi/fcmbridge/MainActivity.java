package com.rudi.fcmbridge;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private TextView tokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        int pad = (int) (32 * getResources().getDisplayMetrics().density);
        root.setPadding(pad, pad, pad, pad);

        tokenView = new TextView(this);
        tokenView.setText("Mengambil token...");
        root.addView(tokenView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        ));

        Button copy = new Button(this);
        copy.setText("Salin Token");
        copy.setOnClickListener(v -> {
            CharSequence t = tokenView.getText();
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("FCM Token", t));
            Toast.makeText(this, "Token disalin", Toast.LENGTH_SHORT).show();
        });
        root.addView(copy);

        setContentView(root);

        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(token -> tokenView.setText("FCM Token:\n\n" + token))
                .addOnFailureListener(e -> tokenView.setText("Gagal ambil token: " + e.getMessage()));
    }
}
