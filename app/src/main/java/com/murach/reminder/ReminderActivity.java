package com.murach.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ReminderActivity extends Activity implements OnClickListener {

    private Button startServiceButton;
    private Button stopServiceButton;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);



        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);		
	}

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
        	case R.id.startServiceButton:
        	    Intent serviceIntentStart = new Intent(this, ReminderService.class);
                startService(serviceIntentStart);
                Toast.makeText(this, "Service Started",Toast.LENGTH_LONG).show();
        		break;
        	case R.id.stopServiceButton:
                Intent serviceIntentStop = new Intent(this, ReminderService.class);
                stopService(serviceIntentStop);
                Toast.makeText(this, "Service Stopped",Toast.LENGTH_LONG).show();
        		break;
        }
    }
}