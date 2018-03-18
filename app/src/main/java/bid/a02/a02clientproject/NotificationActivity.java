package bid.a02.a02clientproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import bid.a02.a02clientproject.customViews.MyCustomView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private int count = 0;
    private final int mNotificationId = 1;
    private final int mSpecialNotificationId = 2;


    public void btnNotify(View view) {
        // The id of the channel.
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle("My notification ")
                        .setContentText("Take you to camera activity! + ")
                        .setAutoCancel(true);


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, CameraActivity.class);

        // this basically adds a fake back stack. addNextIntent specifies the intent that should be fired
        // addParentStack specifies the parent stack it should create the fake stack from. This
        // info is stored in manifest
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(CameraActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mNotificationId is a unique integer your app uses to identify the
        // notification.
        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }

    public void btnAddCounter(View view)
    {
        MyCustomView myCustomView = (MyCustomView) findViewById(R.id.custView);
        myCustomView.labelCounter++;
        myCustomView.invalidate();

    }

    public void btnSpecialNotification(View view) {

        // Instantiate a Builder object.
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .setContentTitle("My special notification ")
                        .setContentText("Take you to special activity! + ")
                        .setAutoCancel(true);

        // Creates an Intent for the Activity
        Intent notifyIntent =
                new Intent(this, SpecialNotificationActivity.class);
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        mBuilder.setContentIntent(notifyPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mSpecialNotificationId, mBuilder.build());

    }
}
