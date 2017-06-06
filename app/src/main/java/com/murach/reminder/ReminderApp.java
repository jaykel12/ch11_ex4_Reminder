package com.murach.reminder;

import android.app.Application;
import android.content.Intent;
import android.util.Log;


public class ReminderApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Reminder", "App started");

        //start service
        Intent service = new Intent(this, ReminderService.class);
        startService(service);
    }

}
