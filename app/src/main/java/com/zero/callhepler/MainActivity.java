package com.zero.callhepler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zero.callhepler.service.CallService;

/**
 * created by Lin on 2017/12/16
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.button_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallWorkActivity.sCall_op = CallWorkActivity.CALL_OP.CALL_ACCEPT;
            }
        });
        findViewById(R.id.button_reject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallWorkActivity.sCall_op = CallWorkActivity.CALL_OP.CALL_REJECT;
            }
        });
        findViewById(R.id.button_none).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallWorkActivity.sCall_op = CallWorkActivity.CALL_OP.NONE;
            }
        });
        startService(new Intent(this, CallService.class));
    }
}
