package com.zero.callhepler.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.zero.callhepler.R;


/**
 * created by Lin on 2017/11/2
 */

public class CallHideService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(CallService.NOTICE_ID,builder.build());
            // 开启一条线程，去移除Service弹出的通知  
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 延迟1s  
                    SystemClock.sleep(1000);
                    // 取消CancelNoticeService的前台  
                    stopForeground(true);
                    // 移除Service弹出的通知  
                    NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(CallService.NOTICE_ID);
                    // 任务完成，终止自己  
                    stopSelf();
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
