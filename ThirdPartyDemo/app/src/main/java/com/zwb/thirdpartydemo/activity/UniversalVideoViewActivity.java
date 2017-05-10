package com.zwb.thirdpartydemo.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;
import com.zwb.thirdpartydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversalVideoViewActivity extends AppCompatActivity implements UniversalVideoView.VideoViewCallback{

    private static final String TAG = "UniversalVideoView";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @BindView(R.id.videoView)
    UniversalVideoView mVideoView;
    @BindView(R.id.media_controller)
    UniversalMediaController mMediaController;
    @BindView(R.id.video_layout)
    FrameLayout mVideoLayout;

    private boolean isFullscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_video_view);
        ButterKnife.bind(this);

        mVideoView = (UniversalVideoView) findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);

        mVideoView.setVideoViewCallback(this);
    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);
            //GONE the unconcerned views to leave room for video and controller
//                    mBottomLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
//                    mBottomLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) { // Video pause
        Log.d(TAG, "onPause UniversalVideoView callback");
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
        Log.d(TAG, "onStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
        Log.d(TAG, "onBufferingStart UniversalVideoView callback");
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
    }
}
