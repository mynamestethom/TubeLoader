package com.example.tubeloader.Activityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tubeloader.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Arrays;

public class ViewVideoActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private String ytlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreen();
        setContentView(R.layout.activity_view_video);


        Bundle argum = getIntent().getExtras();
        ytlink = argum.get("ytlink").toString();

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);



        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "in1KxgyG_cQ";
                String id[] = ytlink.split("/");
                youTubePlayer.loadVideo(id[3], 0);
            }
        });
    }

    public static String getVideoid(String url){
        String videoId = "in1KxgyG_cQ";
        if (url.contains("://youtu.be/")) {
            videoId = url.split(".be/")[1];

        } else if (url.contains("://youtube.com/watch?")) {
            videoId = url.split("\\?v=")[1];
        }
        return videoId;
    }
    public void FullScreen(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}