package com.zero.callhepler.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zero.callhepler.R;

/**
 * 这个服务纯粹为了保活,和接听以及挂断功能无关,可以无视
 * 
 * created by Lin on 2017/12/16
 */

public class CallService extends Service {
    public static final int NOTICE_ID = 100;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //如果API大于18，需要弹出一个可见通知  
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("KeepAppAlive");
            builder.setContentText("DaemonService is runing...");
            startForeground(NOTICE_ID,builder.build());
            // 常驻通知栏体验不好  
            // 通过开启DaemonHideService将通知移除，oom_adj值不变  
            Intent intent = new Intent(this, CallHideService.class);
            startService(intent);
        } else {
            startForeground(NOTICE_ID, new Notification());
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 如果Service被终止  
        // 当资源允许情况下，重启service  
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 如果Service被杀死，干掉通知  
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
            NotificationManager mManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            mManager.cancel(NOTICE_ID);
        }
        // 重启自己  
        Intent intent = new Intent(getApplicationContext(),CallService.class);
        startService(intent);
    }
}
