package com.vicky.thefirst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    final String CHANNEL_ID = "com.vicky.thefirst";

    private void sendNotification(String title, String description) {
        // Vibration pattern to vibrate for 500 md, to 1000 ms, to 1500 ms
        final long[] VIBRATE_PATTERN = {0, 500, 100, 1000, 100, 1500};

        // Configure the vibration pattern for the notification
        final NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "channel_name", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setVibrationPattern(VIBRATE_PATTERN);

        // Obtain the notification manager
        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // and register the channel with the custom vibration pattern
        notificationManager.createNotificationChannel(notificationChannel);

        // Replace the notification with the new one if a previous one exists
        // Otherwise, create a new one.
        final Intent intent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the notification
        final Notification ntf = new Notification.Builder(this, CHANNEL_ID)
                // Notification title
                .setContentTitle(title)
                // Notification description
                .setContentText(description)
                // Notification icon
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                // Notification icon when expanded
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher_background))
                // Tell the application the intent with this notification
                .setContentIntent(pendingIntent)
                .build();

        // Send the notification!
        notificationManager.notify(1234, ntf);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Basic Stuff
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.sendNotification("Hello!", "World OwO!");

        EditText editText = findViewById(R.id.editTextTextPersonName);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String message = editText.getText().toString();

            sendNotification("Hello!", message);
        });
    }
}