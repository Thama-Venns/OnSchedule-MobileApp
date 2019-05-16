package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.Display;
import android.widget.Toast;

import com.app.onschedule.Actions;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

public class DisplayReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {

        System.err.println(Calendar.getInstance().getTime() + "From Broadcast: Dim Screen");

        try {
            //Get and and play sound before action execute.
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, notification);
            ringtone.play();

            //Execute dim screen action
            if(actions.dimScreen(context)) {
                Toast.makeText(context, "Dim Screen mode Activated", Toast.LENGTH_LONG).show();
            }

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
