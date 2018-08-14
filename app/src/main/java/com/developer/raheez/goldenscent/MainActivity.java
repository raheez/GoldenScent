package com.developer.raheez.goldenscent;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;
import android.widget.VideoView;

import com.developer.raheez.goldenscent.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public VideoView videoView;
    public ImageButton playIcon,previous_icon,next_icon;
    public LinearLayoutManager layoutManager;
    public ProgressDialog progressDialog;

    public RecyclerView recyclerView;
    public RecyclerViewAdapter adapter;
    public String video_url = "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8";
    private ArrayList<String> images;
    public String image_url = "https://picsum.photos/300/20";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        videoView = (VideoView) findViewById(R.id.video_view);
        playIcon = (ImageButton) findViewById(R.id.play_Icon);

        previous_icon = (ImageButton) findViewById(R.id.previous_button);
        next_icon = (ImageButton) findViewById(R.id.next_button);



        images = new ArrayList<>();
        getimages();
        play_video();







    }

    private void initRecyclerView(){

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(getApplicationContext(),images);
        recyclerView.setAdapter(adapter);
    }

    public void previous_click(View view){

        int pos = layoutManager.findFirstCompletelyVisibleItemPosition();
        if (pos >0 &&pos <7)
            pos --;
        recyclerView.smoothScrollToPosition(pos);
    }

    public void next_click(View view){

        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        if (pos >=0 &&pos <7)
            pos ++;

        recyclerView.smoothScrollToPosition(pos);
    }


    public void play_icon_click(View view) {

        if (haveNetworkConnection())
        play_video();
//        else


    }


    public void play_video(){

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


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



}
