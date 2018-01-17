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
    ListView MusicList;
    int chooseSong;
    MediaPlayer mP;

    private final String[] listContent = {"chimes", "chord", "ding", "notify",
            "recycle", "ringin", "ringout", "tada", "ringring", "alarm"};
    private long[] resID = {R.raw.alarm1, R.raw.alarm2, R.raw.alarm3,
            R.raw.alarm4, R.raw.alarm5, R.raw.alarm6, R.raw.alarm7, R.raw.alarm8, R.raw.alarm9, R.raw.alarm10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_chooser);


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

    public void SetTheSong(View view) {
        mP.stop();
        Intent sendID = new Intent(MusicChooser.this, MainActivity.class);
        sendID.putExtra("chosenSong", chooseSong);
        startActivity(sendID);
        Toast.makeText(getApplicationContext() , "Wybrany dźwięk to: " + listContent[chooseSong], Toast.LENGTH_SHORT)
                .show();
    }

    public void PlayTheSong(long id){
        if (id==0)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
            mP.release();
            mP= null;
            }
            mP = MediaPlayer.create(this,R.raw.alarm1);
            mP.start();
        }
        else if (id==1)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm2);
            mP.start();
        }
        else if (id==2)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm3);
            mP.start();

        } else if (id==3)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm4);
            mP.start();

        } else if (id==4)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm5);
            mP.start();

        } else if (id==5)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm6);
            mP.start();

        } else if (id==6)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm7);
            mP.start();

        } else if (id==7)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm8);
            mP.start();
        } else if (id==8)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm9);
            mP.start();
        }
        else if (id==9)
        {
            if (mP!=null && mP.isPlaying())
            {
                mP.stop();
                mP.release();
                mP= null;
            }
            mP = MediaPlayer.create(this ,R.raw.alarm10);
            mP.start();
       }

    }
}
