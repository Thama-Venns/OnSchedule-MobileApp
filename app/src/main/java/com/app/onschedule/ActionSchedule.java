package com.app.onschedule;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ActionSchedule extends AppCompatActivity implements View.OnClickListener {

    private Button sound_btn;
    private Button screen_btn;
    private Button power_btn;

    Intent scheduleIntent;
    public String clickedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_schedule);

        sound_btn = findViewById(R.id.sound_profile_btn);
        screen_btn = findViewById(R.id.brightness_btn);
        power_btn = findViewById(R.id.power_btn);

        sound_btn.setOnClickListener(this);
        screen_btn.setOnClickListener(this);
        power_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sound_profile_btn:
                clickedButton = "Silent mode";
                setSchedule(clickedButton);
                break;
            case R.id.brightness_btn:
                clickedButton = "Dim mode";
                setSchedule(clickedButton);
                break;
            case R.id.power_btn:
                clickedButton = "Power-off";
                setSchedule(clickedButton);
                break;
        }

    }

    public void setSchedule (String btn) {
        System.err.println("From Buttons: "+btn);
        scheduleIntent = new Intent(this, SetterActivity.class);
        scheduleIntent.putExtra("pressed", btn);
        startActivity(scheduleIntent);
    }
}
