package com.rohit.serviceforground;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ForegroundServic extends Service {
    public static final String TAG = "Service";

    public ForegroundServic() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");

                int i = 0;
                while (i <= 10) {
                    Log.d(TAG, "run: " + (i + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                Log.d(TAG, "download complete: ");
                stopForeground(true);
                stopSelf();
            }
        });
        thread.start();

        return START_STICKY;
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelId");
        builder.setSmallIcon(R.mipmap.ic_launcher).setContentText("Noti").setContentTitle("notification");

        Notification notification=builder.build();
        startForeground(123,notification);



    }
}
