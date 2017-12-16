package com.zero.callhelper.lib.scheme;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;

/**
 * created by Lin on 2017/12/16
 */

public class CallSchemeAcceptAPI19 implements ICallSchemeAccept {
    
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void acceptCall(Context context) {
        /* 模拟耳机插入动作,用于接听电话 */
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        long eventTime = SystemClock.uptimeMillis() - 1;
        KeyEvent eventDown = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK);
        KeyEvent eventUp = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
        audioManager.dispatchMediaKeyEvent(eventDown);
        audioManager.dispatchMediaKeyEvent(eventUp);
    }
}
