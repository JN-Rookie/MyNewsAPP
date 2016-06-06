package edu.feicui.mynewsapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网络图片的类
 * Created by Administrator on 2016/6/3.
 */
public class GetIntnetImage {
    public static byte[] getImages(String path) throws IOException {
        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");//用GET方法获取图片
        connection.setReadTimeout(5*1000);//设置等待超时时间为5秒
        connection.disconnect();
        InputStream inputStream=connection.getInputStream();//用connection通过输入流获取图片
        byte[] data=read(inputStream);//获取图片的二进制数据

        return data;

    }
    /*
   从数据流中获得数据
    */
    public static byte[]read(InputStream inputStream){
        byte[] buff=new byte[1024];//定义缓冲区
        int len=0;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();//定义输出流
        try {
            while ((len=inputStream.read(buff))!=-1){
                bos.write(buff,0,len);
            }
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
