package com.developer.raheez.goldenscent;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.developer.raheez.goldenscent.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public VideoView videoView;
    public ImageButton playIcon,previous_icon,next_icon;
    public LinearLayoutManager layoutManager;
    public ProgressDialog progressDialog;

    public RecyclerView recyclerView;
    public RecyclerViewAdapter adapter;
    public String video_url = "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8";
    private ArrayList<String> images;
    public String image_url = "https://picsum.photos/300/20";

    int position =0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.video_view);
        playIcon = (ImageButton) findViewById(R.id.play_Icon);

        previous_icon = (ImageButton) findViewById(R.id.previous_button);
        next_icon = (ImageButton) findViewById(R.id.next_button);

        playIcon.setOnClickListener(this);


        images = new ArrayList<>();
        getimages();





    }

    public void previous_click(View view){



        int pos = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (pos >=0 &&pos <7)
            pos --;
        recyclerView.getLayoutManager().scrollToPosition(pos );
    }

    public void next_click(View view){

        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        if (pos >=0 &&pos <7)
            pos ++;
        recyclerView.getLayoutManager().scrollToPosition(pos );
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

    public void getimages() {

       for (int i=0;i<7;i++){


           images.add(image_url+i);
           Log.d("MainActivity","the image url is  " +images.get(i));

       }

       initRecyclerView();
    }

    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: init recyclerview");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(getApplicationContext(),images);
        recyclerView.setAdapter(adapter);
    }
}
