package com.shadspace.soup_it.admin;

import android.app.Application;

import com.onesignal.OneSignal;

public class PushNotify extends Application {


    private static final String ONESIGNAL_APP_ID = "dbdfeb01-92e6-490b-b328-ca161c1a98bf";

    @Override
    public void onCreate() {
        super.onCreate();



        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }


}
