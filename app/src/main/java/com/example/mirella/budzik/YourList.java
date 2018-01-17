package com.example.mirella.budzik;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class YourList extends AppCompatActivity {

    private ArrayList<Song> songList;
    private RecyclerView songView;
    Button mysong;
    int Song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                return;
            }}

        mysong = (Button)findViewById(R.id.mysong);
        songView = (RecyclerView) findViewById(R.id.song_list);
        songList = new ArrayList<Song>();

        getSongList();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        songView.setLayoutManager(layoutManager);

        final SongAdapter songAdapter = new SongAdapter(songList);
        songView.setAdapter(songAdapter);


//        mysong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent sendSong = new Intent(YourList.this, MainActivity.class);
//                sendSong.putExtra("Song", Song);
//                startActivity(sendSong);
//            }
//        });
    }

//    public void Song(View view)
//    {
//        Toast.makeText(getApplicationContext(), "Wybrałes swoja piosenke", Toast.LENGTH_SHORT).show();
//        Log.e("jestem w song","1");
//        Intent sendSong = new Intent(YourList.this, MainActivity.class);
//        sendSong.putExtra("Song", Song);
//        startActivity(sendSong);
//    }

    public void getSongList() {
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }

}
