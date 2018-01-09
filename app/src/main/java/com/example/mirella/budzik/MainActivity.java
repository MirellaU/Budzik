package com.example.mirella.budzik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    protected AlarmManager alarmManager;
    protected TimePicker timePicker;
    protected Button alarm;
    protected Button wylaczAlarm;
    protected ImageButton imageButton;
    Context context;
    PendingIntent pendingIntent;
    MediaPlayer mP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm = (Button) findViewById(R.id.alarm);
        wylaczAlarm = (Button) findViewById(R.id.wylaczAlarm);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        final Calendar calendar=Calendar.getInstance();

        final Intent AlarmReceiverIntent = new Intent(this.context, Alarm_Receiver.class);

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());

                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();
                Log.e("Jestem w onclick","Yay");

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (minute < 10)
                {
                    minute_string = "0" + String.valueOf(minute);
                }

                Intent mIntent = getIntent();
                int ID = mIntent.getIntExtra("chosenSong", 0);

                AlarmReceiverIntent.putExtra("extra","on");
                AlarmReceiverIntent.putExtra("ID",ID);

                pendingIntent=PendingIntent.getBroadcast(MainActivity.this, 0,AlarmReceiverIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

                Toast.makeText(getApplicationContext() , "Alarm został ustawiony na: " +  hour_string + ":" + minute_string, Toast.LENGTH_LONG).show();
            }
        });

        wylaczAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "Alarm został wyłączony", Toast.LENGTH_SHORT).show();
                alarmManager.cancel(pendingIntent);
                AlarmReceiverIntent.putExtra("extra","off");
                sendBroadcast(AlarmReceiverIntent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicChooser.class);
                startActivity(intent);
            }
        });

    }
}