package com.app.onschedule;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.app.onschedule.Recievers.DisplayReciever;
import com.app.onschedule.Recievers.PowerReciever;
import com.app.onschedule.Recievers.PowerSaving;
import com.app.onschedule.Recievers.ProfileReciever;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SetterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat;

    TextView dateTime;
    View update;
    Button setButton;
    Button editButton;
    Button changeAction;
    Button easySaver;

    String buttonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setter);

        //Show calendar setting dialog
        CalendarSet();

        update = findViewById(R.id.update);
        setButton = findViewById(R.id.setButton);
        editButton = findViewById(R.id.editButton);
        changeAction = findViewById(R.id.changeAction);
        easySaver = findViewById(R.id.easy_saver);


        update.setOnClickListener(this);
        setButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        changeAction.setOnClickListener(this);

        dateTime = (TextView) findViewById(R.id.dateTime);
        buttonPressed = getIntent().getStringExtra("pressed");
        dateFormat = new SimpleDateFormat("EEE, d MMM yy HH:mm a", Locale.getDefault());
        System.err.println(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setButton:
                // Begin scheduling ...
                System.err.println(calendar.getTime());
                SetActionSchedule(calendar.getTimeInMillis());

                Intent MainIntent = new Intent(this, MainActivity.class);
                startActivity(MainIntent);
                break;
            case R.id.update:
                CalendarSet();
                break;
            case  R.id.editButton:
                CalendarSet();
                break;
            case R.id.changeAction:
                Intent ActionIntent = new Intent(this, ActionSchedule.class);
                startActivity(ActionIntent);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        new TimePickerDialog(this,this , calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false)
        .show();

        System.err.println(year+"/"+(month+1)+"/"+dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        dateTime.setText(dateFormat.format(calendar.getTime()));
    }

    public void CalendarSet() {
        new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    //Trigger and transfere action to broadcast
    private void SetActionSchedule(long timeInMillis) {
        System.err.println("From Time setter: "+ buttonPressed);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = null;

        switch (buttonPressed){
            case "Silent mode":
                intent = new Intent(this, ProfileReciever.class);
                break;
            case "Dim mode":
                intent = new Intent(this, DisplayReciever.class);
                break;
            case "Power-off":
                intent = new Intent(this, PowerReciever.class);
                break;
            case "Easy Power Saver":
                intent = new Intent(this, PowerSaving.class);
                break;
                default:
                    System.err.println("undefined action" + buttonPressed);
        }

        intent.putExtra("btn_pressed", buttonPressed);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC, timeInMillis, pendingIntent);
        Toast.makeText(this, buttonPressed + " scheduled", Toast.LENGTH_LONG).show();
    }
}
