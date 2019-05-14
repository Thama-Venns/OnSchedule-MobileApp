package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.onschedule.Actions;

public class PowerSaving extends BroadcastReceiver {
    Actions actions = new Actions();
    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println("Easy Power Saver Activated");;
        if (actions.easyPower(context))
            Toast.makeText(context, "Easy power saver", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Easy power saver couldn't be activated", Toast.LENGTH_SHORT).show();
    }
}
