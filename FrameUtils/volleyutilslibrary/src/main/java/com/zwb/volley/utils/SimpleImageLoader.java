package com.zwb.volley.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by zwb
 * Description
 * Date 16/10/17.
 */
public class SimpleImageLoader extends ImageLoader {

    private static SimpleImageLoader simpleImageLoader;
    private static BitmapCache bitmapCache;

    public static SimpleImageLoader getInstance(RequestQueue requestQueue){
        if(simpleImageLoader == null){
            synchronized (SimpleImageLoader.class){
                if(simpleImageLoader == null){
                    bitmapCache = new BitmapCache();
                    simpleImageLoader = new SimpleImageLoader(requestQueue,bitmapCache);
                }
            }
        }
        return simpleImageLoader;
    }
    public SimpleImageLoader(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }
    public static class BitmapCache implements ImageLoader.ImageCache{
        private LruCache<String, Bitmap> mCache;

        @SuppressLint("NewApi")
        public BitmapCache() {
            mCache = new LruCache<String, Bitmap>(8 * 1024 * 1024){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }

                @Override
                protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                    super.entryRemoved(evicted, key, oldValue, newValue);
                }
            };
        }

        @SuppressLint("NewApi")
        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @SuppressLint("NewApi")
        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }

}
