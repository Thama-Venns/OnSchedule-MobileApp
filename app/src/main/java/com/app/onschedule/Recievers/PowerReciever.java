package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.app.onschedule.Actions;

import java.util.Calendar;
import java.util.Date;

public class PowerReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println(Calendar.getInstance().getTime() + "From Broadcast: Power Off");
        actions.powerOff(context);
    }
}
