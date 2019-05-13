package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.onschedule.Actions;

public class PowerSaving extends BroadcastReceiver {
    Actions actions = new Actions();
    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println("Easy Power Saver");
        actions.easyPower(context);
    }
}
