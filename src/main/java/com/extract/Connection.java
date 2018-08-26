package com.extract;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//连接URL 返回页面信息
public class Connection {
    public static String Connet(String url){
        HttpURLConnection conn=null;
        URL ur=null;
        InputStream in=null;
        BufferedReader reader=null;
        StringBuffer stringBuffer=null;
        try{
            ur=new URL(url);
            conn=(HttpURLConnection)ur.openConnection();
            //设置连接和读取超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.connect();
            in=conn.getInputStream();
            reader=new BufferedReader(new InputStreamReader(in));
            stringBuffer=new StringBuffer();
            String line=null;
            while ((line=reader.readLine())!=null){
                stringBuffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //断开连接 关闭资源
            conn.disconnect();
            try{
                in.close();
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
