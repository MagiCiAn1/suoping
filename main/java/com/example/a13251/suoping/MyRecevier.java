package com.example.a13251.suoping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 13251 on 2018/3/11.
 */

public class MyRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,myService.class));
    }
}
