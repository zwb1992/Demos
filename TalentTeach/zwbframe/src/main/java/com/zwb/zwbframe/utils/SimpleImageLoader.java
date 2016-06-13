package com.zwb.zwbframe.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.zwb.zwbframe.http.RequestInstance;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class SimpleImageLoader extends ImageLoader {
    private static SimpleImageLoader imageLoader;

    private static BitmapCache bitmapCache;

    public SimpleImageLoader(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }

    public static SimpleImageLoader getInstance(Context context,RequestQueue requestQueue) {
        if (imageLoader == null) {
            synchronized (SimpleImageLoader.class) {
                if (imageLoader == null) {
                    bitmapCache = new BitmapCache();
                    imageLoader = new SimpleImageLoader(requestQueue, bitmapCache);
                }
            }

        }
        return imageLoader;
    }

    public static class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> mCache;

        @SuppressLint("NewApi")
        public BitmapCache() {
            int maxSize = 8 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
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
