package com.example.a13251.suoping;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private myService.MyBinder myBinder;
    private myService myService;
    private MyServiceConnection myServiceConnection=new MyServiceConnection();
    public class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            myBinder= (com.example.a13251.suoping.myService.MyBinder) binder;
            myService=((com.example.a13251.suoping.myService.MyBinder) binder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,myService.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(intent);
                //bindService(intent,myServiceConnection,Context.BIND_AUTO_CREATE);
                finish();
            }
        });
    }

    private void initPermission() {
        String[] permissions={
                "android.permission.SYSTEM_ALERT_WINDOW",
                "android.permission.RECEIVE_BOOT_COMPLETED",
                "android.permission.INTERNET",
                "android.permission.READ_PHONE_STATE"
        };
        ArrayList<String> toApplyList=new ArrayList<>();
        for(String perm:permissions){
            if(PackageManager.PERMISSION_GRANTED!= ContextCompat.checkSelfPermission(this,perm)){
                toApplyList.add(perm);
            }
        }
        String tmpList[]=new String[toApplyList.size()];
        if(!toApplyList.isEmpty()){
            ActivityCompat.requestPermissions(this,toApplyList.toArray(tmpList),123);
        }
    }

}
