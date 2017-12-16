package com.zero.callhelper.lib.scheme;

import android.content.Context;
import android.view.KeyEvent;
/**
 * created by Lin on 2017/12/16
 */

public class CallSchemeAcceptADB implements ICallSchemeAccept {
    
    @Override
    public void acceptCall(Context context) throws Exception {
        /* Android中通过Runtime.getRuntime().exec来执行底层Linux下的程序或脚本（bat）*/
        /* adb shell input keyevent “value” 一般用于模拟物理事件, 例如, 调用input keyevent 3 则是模拟点击Home键 */
        Runtime.getRuntime().exec("input keyevent " + Integer.toString(KeyEvent.KEYCODE_HEADSETHOOK));
    }
}
