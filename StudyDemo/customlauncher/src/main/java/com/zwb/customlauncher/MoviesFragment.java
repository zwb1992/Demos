package com.zwb.customlauncher;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zwb.customlauncher.bean.VideoBean;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private List<VideoBean.VAV3H6JSNBean> videos = new ArrayList<>();
    private int index;//当前播放的位置
    private JCVideoPlayerStandard jcVideoPlayerStandard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("info", "onCreate--------->");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("info", "====onCreateView=======");
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer);
        JCVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JCVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JCVideoPlayer.TOOL_BAR_EXIST = false;
        JCVideoPlayer.ACTION_BAR_EXIST = false;
        jcVideoPlayerStandard.setUp("1"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, " ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("info", "====onActivityCreated=======");
        handler.post(videoRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("info", "====onPause=======");
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            JCVideoPlayer.releaseAllVideos();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("info", "====onStop=======");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("info", "====onDestroy=======");
        if (videosCall != null) {
            videosCall.cancel();
        }
        handler.removeCallbacks(videoRunnable);
        handler = null;
        if (jcVideoPlayerStandard != null && !videos.isEmpty()) {
            jcVideoPlayerStandard.release();
        }
        jcVideoPlayerStandard = null;
    }

    private Call videosCall;

    private void getVideo() {
        Request request = new Request.Builder().get().url(C.VIDEO_API).addHeader("User-Agent", "Mozilla/4.0").build();
        videosCall = new OkHttpClient().newCall(request);
        videosCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if (handler != null) {
                    handler.removeCallbacks(videoRunnable);
                    //失败之后隔一分钟尝试一次
                    handler.postDelayed(videoRunnable, 1000 * 60);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.e("info", "video-----" + result);
                try {
                    VideoBean videoBean = JSON.parseObject(result, VideoBean.class);
                    if (videoBean != null && videoBean.getVAV3H6JSN() != null && !videoBean.getVAV3H6JSN().isEmpty()) {
                        videos.clear();
                        videos.addAll(videoBean.getVAV3H6JSN());
                        handler.sendEmptyMessage(2);
                    }
                    Log.e("info", "video-----" + videos);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (handler != null) {
                        handler.removeCallbacks(videoRunnable);
                        handler.postDelayed(videoRunnable, 1000 * 60);
                    }
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            index = 0;
            VideoBean.VAV3H6JSNBean vav3H6JSNBean = videos.get(index);
            jcVideoPlayerStandard.setUp(vav3H6JSNBean.getMp4_url()
                    , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "" + vav3H6JSNBean.getTitle());
            Glide.with(MoviesFragment.this).load("" + vav3H6JSNBean.getCover()).into(jcVideoPlayerStandard.thumbImageView);
        }
    };
    /**
     * 两个小时刷新一次
     */
    private Runnable videoRunnable = new Runnable() {
        @Override
        public void run() {
            getVideo();
            handler.postDelayed(this, 1000 * 60 * 120);
        }
    };

    /**
     * 播放下一个
     */
    public void next() {
        if (videos.size() <= 1) {
            return;
        }
        index--;
        index = index < 0 ? 0 : index;
        VideoBean.VAV3H6JSNBean vav3H6JSNBean = videos.get(index);
        jcVideoPlayerStandard.setUp(vav3H6JSNBean.getMp4_url()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "" + vav3H6JSNBean.getTitle());
        Glide.with(MoviesFragment.this).load("" + vav3H6JSNBean.getCover()).into(jcVideoPlayerStandard.thumbImageView);
    }

    /**
     * 播放下一个
     */
    public void pre() {
        if (videos.size() <= 1) {
            return;
        }
        index++;
        index = index >= videos.size() ? 0 : index;
        VideoBean.VAV3H6JSNBean vav3H6JSNBean = videos.get(index);
        jcVideoPlayerStandard.setUp(vav3H6JSNBean.getMp4_url()
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "" + vav3H6JSNBean.getTitle());
        Glide.with(MoviesFragment.this).load("" + vav3H6JSNBean.getCover()).into(jcVideoPlayerStandard.thumbImageView);
    }
}
