package com.example.music_player_a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context,"POWER_DISCONNECTED",Toast.LENGTH_SHORT).show();
        }
        else if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context,"BATTERY_LOW",Toast.LENGTH_SHORT).show();
        }
        else if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)){
            Toast.makeText(context,"BATTERY_OKAY",Toast.LENGTH_SHORT).show();
        }
    }
}
