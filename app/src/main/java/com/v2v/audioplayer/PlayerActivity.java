package com.v2v.audioplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlayerActivity extends AppCompatActivity {

    TextView songTitle;
    ImageButton playPause, prev, next;
    SeekBar seekBar;
    ImageView songImage;
    MediaPlayer mediaPlayer;
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songTitle = findViewById(R.id.songTitle);
        playPause = findViewById(R.id.playPause);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        seekBar = findViewById(R.id.seekBar);
        songImage = findViewById(R.id.songImage);

        String albumName = getIntent().getStringExtra("albumName");
        String songName = getIntent().getStringExtra("songName");
        songTitle.setText(songName);

        int songResId = 0;
        if (albumName.equals("Marathi Songs")) {
            if (songName.equals("Shivba Raja(Sher Shivraj)")) {
                songResId = R.raw.shivba_raja;
                songImage.setImageResource(R.drawable.shivba_raja);
            } else if (songName.equals("Morya Morya")) {
                songResId = R.raw.morya_morya;
                songImage.setImageResource(R.drawable.morya_morya);
            }
        } else if (albumName.equals("English Songs")) {
            if (songName.equals("Real Gone")) {
                songResId = R.raw.real_gone;
                songImage.setImageResource(R.drawable.real_gone);
            } else if (songName.equals("Danza Kuduro")) {
                songResId = R.raw.danza_kuduro;
                songImage.setImageResource(R.drawable.danza_kuduro);
            }
        } else if (albumName.equals("Hip Hop")) {
            if (songName.equals("All The Way Up")) {
                songResId = R.raw.all_the_way_up;
                songImage.setImageResource(R.drawable.all_the_way_up);
            } else if (songName.equals("Despacito")) {
                songResId = R.raw.despacito_luis_fonsi;
                songImage.setImageResource(R.drawable.despacito);
            }
        } else if (albumName.equals("Vintage Songs")) {
            if (songName.equals("Badshah O Badshah Baadshah")) {
                songResId = R.raw.badshah_o_badshah_baadshah;
                songImage.setImageResource(R.drawable.badshah_o_badshah_baadshah);
            } else if (songName.equals("Zindagi Ek Safar Hai Suhana")) {
                songResId = R.raw.zindagi_ek_safar_hai_suhana;
                songImage.setImageResource(R.drawable.zindagi_ek_safar_hai_suhana);
            }
        }

        if (songResId != 0) {
            mediaPlayer = MediaPlayer.create(this, songResId);
            seekBar.setMax(mediaPlayer.getDuration());
        }

        playPause.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                playPause.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mediaPlayer.start();
                playPause.setImageResource(android.R.drawable.ic_media_pause);
            }
            isPlaying = !isPlaying;
        });

        prev.setOnClickListener(v -> mediaPlayer.seekTo(0));
        next.setOnClickListener(v -> mediaPlayer.seekTo(mediaPlayer.getDuration()));

        new Thread(() -> {
            while (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
