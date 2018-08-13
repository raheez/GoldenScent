package com.developer.raheez.goldenscent;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoView;
    ImageButton playIcon;

    ProgressDialog progressDialog;

    String video_url = "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.video_view);
        playIcon = (ImageButton) findViewById(R.id.play_Icon);


        playIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();




        try {


            if (!videoView.isPlaying()){

                Uri uri = Uri.parse(video_url);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        playIcon.setImageResource(R.drawable.play_icon);

                    }
                });
            }
            else {
                videoView.pause();
                playIcon.setImageResource(R.drawable.play_icon);
            }

        }
        catch (Exception e){

        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                progressDialog.dismiss();
                mediaPlayer.setLooping(true);
                videoView.start();
                playIcon.setImageResource(R.drawable.play_icon);
            }
        });

    }
}
