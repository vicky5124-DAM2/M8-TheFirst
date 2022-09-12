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

public class MainActivity extends AppCompatActivity {
    final String CHANNEL_ID = "i.apps.notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Basic Stuff
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        // Vibration pattern to vibrate for 500 ms twice
        final long[] VIBRATE_PATTERN = {0, 500, 0, 500};

        // Configure the vibration pattern for the notification
        final var notificationChannel = new NotificationChannel(CHANNEL_ID, "channel_name", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setVibrationPattern(VIBRATE_PATTERN);

        // Obtain the notification manager
        final var notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // and register the channel with the custom vibration pattern
        notificationManager.createNotificationChannel(notificationChannel);

        // Replace the notification with the new one if a previous one exists
        // Otherwise, create a new one.
        final var intent = new Intent(this, MainActivity.class);
        final var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the notification
        final var ntf = new Notification.Builder(this, CHANNEL_ID)
                // Notification title
                .setContentTitle("Hello!")
                // Notification description
                .setContentText("This is a notification test")
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
}