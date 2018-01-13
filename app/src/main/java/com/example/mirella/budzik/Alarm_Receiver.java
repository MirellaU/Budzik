package com.example.mirella.budzik;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Mirella on 31.12.2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Jestem w receiverze","Yay");
        String GetYourString = intent.getExtras().getString("extra");
        Integer GetYourSong = intent.getExtras().getInt("ID");
        Integer GetSongFromYourList = intent.getExtras().getInt("Song");
        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);
        serviceIntent.putExtra("extra",GetYourString);
        serviceIntent.putExtra("ID",GetYourSong);
        serviceIntent.putExtra("Song",GetSongFromYourList);
        context.startService(serviceIntent);
    }
}
