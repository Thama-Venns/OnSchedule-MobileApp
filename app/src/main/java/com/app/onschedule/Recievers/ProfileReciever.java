package com.app.onschedule.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

import com.app.onschedule.Actions;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.AUDIO_SERVICE;

public class ProfileReciever extends BroadcastReceiver {

    Actions actions = new Actions();

    @Override
    public void onReceive(Context context, Intent intent) {

        System.err.println(Calendar.getInstance().getTime() + "From Broadcast: Silent");

        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        //Check if the phone is not in silent mode
        if(audioManager.getRingerMode() != AudioManager.RINGER_MODE_SILENT) {
            //Activate silent mode if not already activated
            if (actions.silentMode(context)) {
                Toast.makeText(context, "Do not Disturb mode Activated", Toast.LENGTH_LONG).show();
            }
        } else {
            //If the phone is already in silent mode
            Toast.makeText(context, "Your mobile phone is already on Silent mode", Toast.LENGTH_LONG).show();
        }
    }
}
