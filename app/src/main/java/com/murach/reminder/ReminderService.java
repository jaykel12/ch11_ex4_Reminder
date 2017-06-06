package com.murach.reminder;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class ReminderService extends Service {

    private Timer timer;
    @Override
    public void onCreate() {
        Log.d("Reminder", "Service Created");


        startTImer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("Reminder", "No binding for this service");
        return null;
    }

    @Override
    public void onDestroy(){
        Log.d("Reminder", "Service Destroyed");
        stopTimer();
    }

    private void startTImer(){

        //create task
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Log.d("Reminder", "Look into the distance. It’s good for your eyes!");
                notification();
            }
        };

        //create and start timer
        timer = new Timer(true);
        int delay = 1000*10; //10 secodns
        int interval = 1000*10;//10 seconds
        timer.schedule(task, delay,interval);
    }

    private void stopTimer(){
        if (timer != null){
            timer.cancel();
        }
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private  void notification(){
        //create intent
        Intent notificationIntent = new Intent(this,ReminderActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //create the pending intent
        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flag);
        //start the timer

        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "Look into the distance. It’s good for your eyes!";
        CharSequence contentTitle = "Look into the distance. It’s good for your eyes!";
        CharSequence contentText = "Look into the distance. It’s good for your eyes!";

        Notification notification = new Notification.Builder(this).setSmallIcon(icon)
                .setTicker(tickerText).setContentTitle(contentTitle)
                .setContentText(contentText).setContentIntent(pendingIntent)
                .setAutoCancel(true).build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final int Notification_ID = 1;
        manager.notify(Notification_ID, notification);
    }
}
