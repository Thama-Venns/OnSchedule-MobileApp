package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.view.Display;

import com.app.onschedule.Actions;

import java.util.Calendar;
import java.util.Date;

public class DisplayReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println(Calendar.getInstance().getTime() + "From Broadcast: Dim Screen");
        actions.dimScreen(context);
    }
}
