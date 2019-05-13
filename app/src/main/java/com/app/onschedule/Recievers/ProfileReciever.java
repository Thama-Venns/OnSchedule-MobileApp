package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.onschedule.Actions;

import java.util.Calendar;
import java.util.Date;

public class ProfileReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println(Calendar.getInstance().getTime() + "From Broadcast: Silent");
        if(actions.silentMode(context)) {
            Toast.makeText(context, "Do not Disturb mode Activated", Toast.LENGTH_SHORT).show();
        }
    }
}
