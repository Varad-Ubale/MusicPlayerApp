package com.v2v.audioplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class SongListActivity extends AppCompatActivity {

    String[] englishSongs = {"Real Gone", "Danza Kuduro"};
    String[] hiphopSongs = {"All The Way Up", "Despacito"};
    String[] marathiSongs = {"Shivba Raja(Sher Shivraj)", "Morya Morya"};
    String[] vintageSongs = {"Badshah O Badshah Baadshah", "Zindagi Ek Safar Hai Suhana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        String albumName = getIntent().getStringExtra("albumName");
        setTitle(albumName);

        String[] songs;
        if (albumName.equals("English Songs")) {
            songs = englishSongs;
        } else if (albumName.equals("Hip Hop")) {
            songs = hiphopSongs;
        } else if (albumName.equals("Marathi Songs")) {
            songs = marathiSongs;
        } else if (albumName.equals("Vintage Songs")) {
            songs = vintageSongs;
        } else {
            songs = new String[]{};
        }

        ListView listView = findViewById(R.id.songList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(SongListActivity.this, PlayerActivity.class);
            intent.putExtra("songName", songs[position]);
            intent.putExtra("albumName", albumName);
            startActivity(intent);
        });
    }
}