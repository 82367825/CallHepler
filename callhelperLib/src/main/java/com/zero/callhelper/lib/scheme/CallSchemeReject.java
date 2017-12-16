package com.zero.callhelper.lib.scheme;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created by Lin on 2017/12/16
 */

public class CallSchemeReject implements ICallSchemeReject {
    @Override
    public void rejectCall(Context context) throws 
            ClassNotFoundException, InvocationTargetException, 
            IllegalAccessException, NoSuchMethodException, RemoteException {
        Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
        IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.TELEPHONY_SERVICE});
        ITelephony telephony = ITelephony.Stub.asInterface(binder);
        telephony.endCall();
    }
}
