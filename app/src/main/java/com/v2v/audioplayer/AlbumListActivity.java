package com.v2v.audioplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class AlbumListActivity extends AppCompatActivity {

    String[] albumNames = {"English Songs", "Hip Hop", "Marathi Songs", "Vintage Songs"};
    int[] albumImages = {R.drawable.english, R.drawable.hiphop, R.drawable.marathi, R.drawable.vintage};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        GridView gridView = findViewById(R.id.albumGrid);
        AlbumAdapter adapter = new AlbumAdapter(this, albumNames, albumImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AlbumListActivity.this, SongListActivity.class);
            intent.putExtra("albumName", albumNames[position]);
            startActivity(intent);
        });
    }
}