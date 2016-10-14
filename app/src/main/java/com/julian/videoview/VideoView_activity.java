package com.julian.videoview;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoView_activity extends AppCompatActivity {

   private VideoView videoView;
    private MediaController mc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_activity);

        videoView=(VideoView)findViewById(R.id.videoView);
        mc=new MediaController(this);

        String urlLocal="android.resource://"+getPackageName()+"/"+R.raw.video;
        String url="https:////www.youtube.com//watch?v=7M-jsjLB20Y" ;

        videoView.setMediaController(mc);

        videoView.setVideoPath(urlLocal);

        //por si esta en memoria external
        //videoView.setVideoPath(Environment.getExternalStorageDirectory().getAbsolutePath()+"nombreFichero");
        //otro modo altenativo del set videoPath
        //Uri uri= Uri.parse("android:resource://"+getPackageName()+"/"+R.raw.video);

        videoView.start();// no es recomendable usarlo
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int position= videoView.getCurrentPosition();

        //guarda la posicion del video en la que se quedó.
        outState.putInt("position",position);
        outState.putBoolean("playing",videoView.isPlaying());

    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }
/*
    @Override
    protected void onPause() {
        super.onPause();
        //videoView.pause();

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

            videoView.seekTo(savedInstanceState.getInt("position"));

        if(!savedInstanceState.getBoolean("playing")){
            videoView.pause();
        }

    }


}
