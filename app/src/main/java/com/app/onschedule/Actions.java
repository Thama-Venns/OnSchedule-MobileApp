package com.app.onschedule;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import static android.Manifest.permission.*;
import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;
import static android.support.v4.content.ContextCompat.startActivity;

public class Actions {

    public boolean dimScreen(Context context) {

        boolean success = false;

        try {
            boolean permission;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permission = Settings.System.canWrite(context);
            } else {
                permission = ContextCompat.checkSelfPermission(context, WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED;
            }

            if (permission) {
                //do your code
                android.provider.Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 0);
                success = true;
            }  else {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                }
            }
            //android.provider.Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 200);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return success;
    }

    public boolean silentMode(Context context) {

        boolean success = false;
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            if (Build.VERSION.SDK_INT >= 23) {
                //Silent mode if android version > 23
                if ( notificationManager.isNotificationPolicyAccessGranted()) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                } else{
                    // Open Setting screen to ask for permission
                    Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                    context.startActivity(intent);
                }

            } else if( Build.VERSION.SDK_INT < 23 )
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                success = true;
                //Toast.makeText(context, "Do not Disturb mode Activated", Toast.LENGTH_SHORT).show();

        } catch ( SecurityException e ) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    public void easyPower(Context context) {
        dimScreen(context);
        silentMode(context);
        powerOff(context);
        Toast.makeText(context, "Easy power saver", Toast.LENGTH_SHORT).show();
    }

    public void powerOff(Context context) {
        try {
            Intent i = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
            i.putExtra("android.intent.extra.KEY_CONFIRM", true);
//            i.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*public void powerOff(Context context) {
        try {

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.REBOOT)
                    != PackageManager.PERMISSION_GRANTED) {
                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REBOOT}, 1);

                if(ContextCompat.checkSelfPermission(context, Manifest.permission.REBOOT) != PackageManager.PERMISSION_GRANTED) {
                    PowerManager pm = (PowerManager) context.getSystemService(POWER_SERVICE);
                    pm.reboot(null);
                }

            } else {
                PowerManager pm = (PowerManager) context.getSystemService(POWER_SERVICE);
                pm.reboot(null);
                Toast.makeText(context, "Power Off Activated", Toast.LENGTH_SHORT).show();
            }

            if(ContextCompat.checkSelfPermission(context, REBOOT) != PackageManager.PERMISSION_GRANTED) {
                //ActivityCompat.requestPermissions(this , new String[]{REBOOT}, 1);
            } else {
                PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                powerManager.reboot(null);
                Toast.makeText(context, "Power Off Activated", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }*/
}
