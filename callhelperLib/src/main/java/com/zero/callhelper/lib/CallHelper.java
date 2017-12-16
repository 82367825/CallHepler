package com.zero.callhelper.lib;

import android.content.Context;

import com.zero.callhelper.lib.scheme.CallSchemeAcceptADB;
import com.zero.callhelper.lib.scheme.CallSchemeAcceptAPI19;
import com.zero.callhelper.lib.scheme.CallSchemeAcceptAPI26;
import com.zero.callhelper.lib.scheme.CallSchemeAcceptAPI26_23;
import com.zero.callhelper.lib.scheme.CallSchemeReject;
import com.zero.callhelper.lib.scheme.ICallSchemeAccept;
import com.zero.callhelper.lib.scheme.ICallSchemeReject;

/**
 * created by Lin on 2017/12/16
 */

public class CallHelper {
    
    private static CallHelper sInstance = null;
    public synchronized static CallHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CallHelper(context.getApplicationContext());
        }
        return sInstance;
    }
    
    private ICallSchemeAccept mICallSchemeAccept;
    private ICallSchemeReject mICallSchemeReject;
    private CallHelper(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            if (context.getApplicationInfo().targetSdkVersion <= 22) {
                mICallSchemeAccept = new CallSchemeAcceptAPI26();
            } else {
                mICallSchemeAccept = new CallSchemeAcceptAPI26_23();
            }
        } else if (android.os.Build.VERSION.SDK_INT >= 19) {
            mICallSchemeAccept = new CallSchemeAcceptAPI19();
        } else {
            mICallSchemeAccept = new CallSchemeAcceptADB();
        }
        mICallSchemeReject = new CallSchemeReject();
    }
    
    public void rejectCall(Context context) throws Exception {
        mICallSchemeAccept.acceptCall(context);
    }
    
    public void acceptCall(Context context) throws Exception {
        mICallSchemeReject.rejectCall(context);
    }
    
}
