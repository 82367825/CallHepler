package com.zero.callhelper.lib.scheme;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;

import java.util.List;

/**
 * created by Lin on 2017/12/16
 */

public class CallSchemeAcceptAPI26 implements ICallSchemeAccept {
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void acceptCall(Context context) {
        /* 模拟耳机插入动作,用于接听电话 */
        /* 由于Android8.0的限制, 这里通过通知栏去触发模拟事件, 算是走了个大弯 */
        MediaSessionManager mediaSessionManager =  (MediaSessionManager)
                context.getApplicationContext().getSystemService(Context.MEDIA_SESSION_SERVICE);
        List<MediaController> mediaControllerList = mediaSessionManager
                .getActiveSessions(new ComponentName(context.getApplicationContext(), CallNotiReceiverService.class));
        for (MediaController m : mediaControllerList) {
            if ("com.android.server.telecom".equals(m.getPackageName())) {
                m.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_HEADSETHOOK));
                m.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
                break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static class CallNotiReceiverService extends NotificationListenerService {
        public CallNotiReceiverService() {
        }
    }
    
}
