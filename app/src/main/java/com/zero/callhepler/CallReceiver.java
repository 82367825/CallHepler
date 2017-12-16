package com.zero.callhepler;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * created by Lin on 2017/12/16
 */

public class CallReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            int currentCallState = telephonyManager.getCallState();
            switch (currentCallState) {
                case TelephonyManager.CALL_STATE_RINGING:
                    Intent workIntent = new Intent(context, CallWorkActivity.class);
                    workIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(workIntent);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
            }
        }
    }
    
}
