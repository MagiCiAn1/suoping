package com.example.a13251.suoping;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 13251 on 2018/3/13.
 */

public class httpConnection {
    private static String murl="http://10.255.213.65:8080/Magician/magic.do";
    public void doHttpPost(final Map<String,String> params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection=null;
                try {
                    URL url = new URL(murl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    httpURLConnection.setDoInput(false);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    DataOutputStream out=new DataOutputStream(outputStream);
                    StringBuffer sb=new StringBuffer();
                    for(Map.Entry<String,String> e:params.entrySet()){
                        sb.append(e.getKey()+"="+e.getValue()+"&");
                    }
                    String s=sb.substring(0,sb.length()-1).toString();
                    Log.d("params",s);
                    out.writeBytes(s);
                    out.flush();
                    out.close();
                    if(httpURLConnection.getResponseCode()!=404){
                        Log.d("url","connection error");
                    }else{
                        Log.d("url","sucess");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    httpURLConnection.disconnect();
                }

            }
        }).start();
    }

}
