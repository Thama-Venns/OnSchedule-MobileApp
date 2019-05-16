package com.app.onschedule;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.Manifest.permission.WRITE_SETTINGS;

public class ActionSchedule extends AppCompatActivity implements View.OnClickListener {

    private Button sound_btn;
    private Button screen_btn;
    private Button power_btn;
    private Button saver_btn;

    Intent scheduleIntent;
    public String clickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_schedule);

        sound_btn = findViewById(R.id.sound_profile_btn);
        screen_btn = findViewById(R.id.brightness_btn);
        power_btn = findViewById(R.id.power_btn);
        saver_btn = findViewById(R.id.easy_saver);

        sound_btn.setOnClickListener(this);
        screen_btn.setOnClickListener(this);
        power_btn.setOnClickListener(this);
        saver_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        boolean permission = Settings.System.canWrite(this);

        switch (v.getId()) {
            case R.id.sound_profile_btn:
                clickedButton = "Silent mode";

                //Check if app has notifications access, if not prompt user to grant access
                if (!notificationManager.isNotificationPolicyAccessGranted()) {
                    this.startActivity(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
                } else //Set the schedule
                    setSchedule(clickedButton);

                break;
            case R.id.brightness_btn:
                clickedButton = "Dim mode";

                //Check if app has write access otherwise prompt user
                if (permission != true) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:" + this.getPackageName()));
                    this.startActivity(intent);
                    permission = ContextCompat.checkSelfPermission(this, WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
                } else
                    setSchedule(clickedButton);

                break;
            case R.id.power_btn:
                clickedButton = "Power-off";
                setSchedule(clickedButton);
                break;
            case R.id.easy_saver:
                clickedButton = "Easy Power Saver";

                //Prompt for notification permission
                if (!notificationManager.isNotificationPolicyAccessGranted()) {
                    this.startActivity(new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS));
                } else if (permission != true) {
                    //Prompt for notification permission
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:" + this.getPackageName()));
                    this.startActivity(intent);
                    permission = ContextCompat.checkSelfPermission(this, WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
                } else
                    //Set schedule
                    setSchedule(clickedButton);
        }

    }

    public void setSchedule (String btn) {
        System.err.println("From Buttons: "+btn);
        scheduleIntent = new Intent(this, SetterActivity.class);
        scheduleIntent.putExtra("pressed", btn);
        startActivity(scheduleIntent);
    }
}
