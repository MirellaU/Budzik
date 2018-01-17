package com.example.mirella.budzik;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Mirella on 31.12.2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mP;
    int startId;
    boolean isRunning;
    PendingIntent PendingIntentMainActivity;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){

        String state = intent.getExtras().getString("extra");
        Integer songID = intent.getExtras().getInt("ID");

        Log.e("Alarm jest",state);
        Log.e("Wybrana muzyka to", songID.toString());


        assert  state!=null;
        switch (state) {
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId==1){
            this.isRunning=true;
            this.startId=0;

            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Intent IntentMainActivity = new Intent(this.getApplicationContext(),MainActivity.class);
            PendingIntentMainActivity  = getActivity(this,0,IntentMainActivity,0);
            Notification NotificationPopUp = new Notification.Builder(this)
                    .setContentTitle("Pobudka!")
                    .setContentText("Kliknij aby wyłączyć")
                    .setContentIntent(PendingIntentMainActivity)
                    .setSmallIcon(R.drawable.notifications)
                    .setAutoCancel(true)
                    .build();
            notificationManager.notify(0, NotificationPopUp);
            Log.e("Notyfikacja działa ok","ok");


            if (songID==0)
            {
                mP = MediaPlayer.create(this,R.raw.alarm1);
                mP.start();
            }
            else if (songID==1)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm2);
                mP.start();
            }
            else if (songID==2)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm3);
                mP.start();

            } else if (songID==3)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm4);
                mP.start();

            } else if (songID==4)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm5);
                mP.start();

            } else if (songID==5)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm6);
                mP.start();

            } else if (songID==6)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm7);
                mP.start();

            } else if (songID==7)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm8);
                mP.start();
            } else if (songID==8)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm9);
                mP.start();
            }
            else if (songID==9)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm10);
                mP.start();
            }
            else if (songID==null)
            {
                mP = MediaPlayer.create(this ,R.raw.alarm1);
                mP.start();
                Log.e("jestem w ifie",songID.toString());
            }
        }
        else if(this.isRunning && startId==0)
        {
            mP.stop();
            mP.reset();
            this.isRunning=false;
            this.startId=0;
        }
        else if(!this.isRunning && startId==0){
            this.isRunning=false;
            this.startId=0;
        }
        else if(this.isRunning && startId==1){
            this.isRunning=true;
            this.startId=1;
        }

        return START_NOT_STICKY;
    }

    public void onDestroy(){

        Toast.makeText(this,"Błąd!",Toast.LENGTH_SHORT).show();
    }

}
