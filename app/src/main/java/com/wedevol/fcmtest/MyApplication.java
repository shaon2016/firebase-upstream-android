package com.wedevol.fcmtest;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

/**
 * Created by hp on 8/8/2017.
 */

public class MyApplication extends Application {
    Handler h = new Handler() {
        public void handleMessage(Message m) {
            if (realCallback!=null) {
                realCallback.handleMessage(m);
            }
        }
    };
    Handler.Callback realCallback=null;

    public Handler getHandler() {
        return h;
    }

    public void setCallback(Handler.Callback c) {
        realCallback = c;
    }
}
