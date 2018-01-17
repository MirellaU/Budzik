package com.example.mirella.budzik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.StrictMode;
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

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    protected AlarmManager alarmManager;
    protected TimePicker timePicker;
    protected Button alarm;
    protected Button wylaczAlarm;
    protected ImageButton imageButton;
    Context context;
    PendingIntent pendingIntent;

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
        final Calendar calendar = Calendar.getInstance();

        final Intent AlarmReceiverIntent = new Intent(this.context, Alarm_Receiver.class);

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                Log.e("Jestem w onclick", "Yay");

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                Intent mIntent = getIntent();
                int ID = mIntent.getIntExtra("chosenSong", 0);

                AlarmReceiverIntent.putExtra("extra", "on");
                AlarmReceiverIntent.putExtra("ID", ID);

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, AlarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(alarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                Toast.makeText(getApplicationContext(), "Alarm został ustawiony na: " + hour_string + ":" + minute_string, Toast.LENGTH_LONG).show();


                try
                {
                    Plug();
                }
                catch(IOException ioe)
                {
                    Log.e("1","Nie udalo sie polaczyc");
                }

            }
        });

        wylaczAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Alarm został wyłączony", Toast.LENGTH_SHORT).show();
                alarmManager.cancel(pendingIntent);
                AlarmReceiverIntent.putExtra("extra", "off");
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

    public static void Plug() throws IOException {

        //{"system":{"set_relay_state":{"state":1}}} po zakodowaniu
        final byte [] wlacz = new byte[] { (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0,
                (byte)0xd0, (byte)0xf2, (byte)0x81, (byte)0xf8, (byte)0x8b,
                (byte)0xff, (byte)0x9a, (byte)0xf7, (byte)0xd5, (byte)0xef,
                (byte)0x94, (byte)0xb6, (byte)0xc5, (byte)0xa0, (byte)0xd4,
                (byte)0x8b, (byte)0xf9, (byte)0x9c, (byte)0xf0, (byte)0x91,
                (byte)0xe8, (byte)0xb7, (byte)0xc4, (byte)0xb0, (byte)0xd1,
                (byte)0xa5, (byte)0xc0, (byte)0xe2, (byte)0xd8, (byte)0xa3,
                (byte)0x81, (byte)0xf2, (byte)0x86, (byte)0xe7, (byte)0x93,
                (byte)0xf6, (byte)0xd4, (byte)0xee, (byte)0xdf, (byte)0xa2,
                (byte)0xa2 };
        //{"system":{"set_relay_state":{"state":0}}} po zakodowaniu
        byte [] wylacz = new byte[] { (byte)0x0, (byte)0x0, (byte)0x0, (byte)0x0,
                (byte)0xd0, (byte)0xf2, (byte)0x81, (byte)0xf8, (byte)0x8b,
                (byte)0xff, (byte)0x9a, (byte)0xf7, (byte)0xd5, (byte)0xef,
                (byte)0x94, (byte)0xb6, (byte)0xc5, (byte)0xa0, (byte)0xd4,
                (byte)0x8b, (byte)0xf9, (byte)0x9c, (byte)0xf0, (byte)0x91,
                (byte)0xe8, (byte)0xb7, (byte)0xc4, (byte)0xb0, (byte)0xd1,
                (byte)0xa5, (byte)0xc0, (byte)0xe2, (byte)0xd8, (byte)0xa3,
                (byte)0x81, (byte)0xf2, (byte)0x86, (byte)0xe7, (byte)0x93,
                (byte)0xf6, (byte)0xd4, (byte)0xee, (byte)0xde, (byte)0xa3,
                (byte)0xa3 };

        Socket s = null;
        try
        {
            // polacz z wtyczka
            s = new Socket("127.0.0.1", 9999);
        }
        catch (IOException e)
        {
            Log.e("2","Nie udalo sie polaczyc");
            return;
        }

        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        // przygotuj do wyslania
        dos.write(wlacz);
        // upewnij sie, ze zostanie wyslane
        dos.flush();
        s.close();
    }

}