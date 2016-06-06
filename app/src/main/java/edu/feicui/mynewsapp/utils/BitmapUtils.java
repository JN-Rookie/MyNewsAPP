package edu.feicui.mynewsapp.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 设置缓存图片大小
 * Created by Administrator on 2016/6/6.
 */
public class BitmapUtils implements ImageLoader.ImageCache{
    private LruCache<String,Bitmap> mCache;

    public BitmapUtils() {
        int maxSize=10*1024*1024;//设置图片最大值
        mCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()* value.getHeight();//位图每一行所占用的内存字节数*位图的高度
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
