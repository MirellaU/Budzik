package com.example.mirella.budzik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MusicChooser extends AppCompatActivity {

    Button yourMusic;
    Context context;
    ListView MusicList;
    int chooseSong;
    MediaPlayer mP;

    private final String[] listContent = {"chimes", "chord", "ding", "notify",
            "recycle", "ringin", "ringout", "tada", "ringring", "alarm"};
    private final int[] resID = {R.raw.alarm1, R.raw.alarm2, R.raw.alarm3,
            R.raw.alarm4, R.raw.alarm5, R.raw.alarm6, R.raw.alarm7, R.raw.alarm8, R.raw.alarm9, R.raw.alarm10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_chooser);


        yourMusic = (Button) findViewById(R.id.yourMusic);
        MusicList = (ListView) findViewById(R.id.MusicList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContent);
        MusicList.setAdapter(adapter);

        MusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PlayTheSong(id);
                chooseSong = (int) id;
                
            }
        });
    }

//    public boolean GoBack(MenuItem item)
//    {
//        // If the back button in the ActionBar is selected, call the Activity's onBackPressed().
//        int id = item.getItemId();
//        if(android.R.id.home == item.getItemId())
//        {
//            Activity activity =
//            activity.onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void NewActivity(View view) {
        Intent newIntent = new Intent(this, YourList.class);
        startActivity(newIntent);
    }

    public void SetTheSong(View view) {
        Intent sendID = new Intent(MusicChooser.this, MainActivity.class);
        sendID.putExtra("chosenSong", chooseSong);
        startActivity(sendID);
        Toast.makeText(getApplicationContext() , "Wybrany dźwięk to: " + listContent[chooseSong], Toast.LENGTH_SHORT)
                .show();
    }

    public void PlayTheSong(long id){
        if (id==0)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm1);
            mP.start();
        }
        else if (id==1)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm2);
            mP.start();

        }
        else if (id==2)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm2);
            mP.start();

        } else if (id==3)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm3);
            mP.start();

        } else if (id==4)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm4);
            mP.start();

        } else if (id==5)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm5);
            mP.start();

        } else if (id==6)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm6);
            mP.start();

        } else if (id==7)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm7);
            mP.start();
        } else if (id==8)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm8);
            mP.start();
        }
        else if (id==9)
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm9);
            mP.start();
       }
        else
        {
            MediaPlayer mP = MediaPlayer.create(this ,R.raw.alarm10);
            mP.start();
        }
    }
    public void BackButton(View view){
        onPause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mP != null) {
            mP.pause();
            if (isFinishing()) {
                mP.stop();
                mP.release();
            }
        }
    }


}
