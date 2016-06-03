package edu.feicui.mynewsapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网络数据的类
 * Created by Administrator on 2016/6/3.
 */
public class HttpUtils {
    private static final String TAG = "NewsListActivity";
    public static String getInfo(String path){
        String rs = null;
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        HttpURLConnection urlConnection = null;
        try {
            URL url1 = new URL(path);
            urlConnection = (HttpURLConnection) url1.openConnection();//获取网络数据
            urlConnection.setRequestMethod("GET");//请求类型
            urlConnection.setConnectTimeout(5000);//超时时间
            InputStream in = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead).append("\n");
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert urlConnection != null;
            urlConnection.disconnect();
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "getInfo: "+rs);
        return rs;
    }
}
