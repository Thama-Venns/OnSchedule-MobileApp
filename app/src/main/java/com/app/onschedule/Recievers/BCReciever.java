package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.onschedule.ActionSchedule;
import com.app.onschedule.Actions;

public class BCReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {

        String pressed = intent.getStringExtra("btn_pressed");
        System.err.println("From Broadcast: " + pressed);

        switch(pressed) {
            case "silent":
                actions.silentMode(context);
                break;
            case "dim":
                actions.dimScreen(context);
                break;
            case "power":
                actions.powerOff(context);
                break;

                default:
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}
