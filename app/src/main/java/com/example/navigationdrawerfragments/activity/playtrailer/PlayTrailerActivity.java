package com.example.navigationdrawerfragments.activity.playtrailer;

import android.content.Intent;
import android.os.Bundle;

import com.example.navigationdrawerfragments.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import androidx.annotation.Nullable;

public class PlayTrailerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "playersuccess";
    //private String mVideoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
     final String videoId = "K2U-NWDLXM4";

    public static final String GOOGLE_API_KEY = "AIzaSyBUCze1KUh3e011xrW_svbYGFI_E7eEn8s";
    public static final String THE_RING_ID = "K2U-NWDLXM4";
    public static final String DEVIL_ID = "EIzazUv2gtI";
    public static final String GHOST_ID = "a7xNXTpQA5Q";
    public static final String ALIVE_ID = "jQ8CCg1tOqc";
    public static final String IT_ID = "hAUTdjf9rko";

    public static final int REQUEST_CODE_ERROR = 100;
    YouTubePlayerView youtubePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_trailer);
        youtubePlayer = (YouTubePlayerView)findViewById(R.id.playVideo);
        youtubePlayer.initialize(GOOGLE_API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(intent.hasExtra("thering")){
            youTubePlayer.loadVideo(THE_RING_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
        if (intent.hasExtra("devil")){
            youTubePlayer.loadVideo(DEVIL_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
        if (intent.hasExtra("ghost")){
            youTubePlayer.loadVideo(GHOST_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
        if (intent.hasExtra("alive")){
            youTubePlayer.loadVideo(ALIVE_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
        if (intent.hasExtra("itmovie")){
            youTubePlayer.loadVideo(IT_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE_ERROR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ERROR){
            youtubePlayer.initialize(GOOGLE_API_KEY,this);
        }
    }
}
