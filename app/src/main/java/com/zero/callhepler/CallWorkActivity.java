package com.zero.callhepler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.zero.callhelper.lib.CallHelper;

/**
 * created by Lin on 2017/12/16
 */

public class CallWorkActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        
        mMainHandler.postDelayed(mRunnable, 1000);
    }

    public enum CALL_OP {
        CALL_ACCEPT,    /* 自动接听 */
        CALL_REJECT,    /* 自动挂断 */
        NONE            /* 不干扰 */
    }
    public static CALL_OP sCall_op = CALL_OP.NONE;
    
    private Handler mMainHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (sCall_op == CALL_OP.CALL_ACCEPT) {
                try {
                    CallHelper.getsInstance(CallWorkActivity.this)
                            .acceptCall(CallWorkActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } if (sCall_op == CALL_OP.CALL_REJECT) {
                try {
                    CallHelper.getsInstance(CallWorkActivity.this)
                            .rejectCall(CallWorkActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            CallWorkActivity.this.finish();
        }
    };
}
