package com.fa.imaged;

import android.app.Application;
import android.content.Context;

/**
 * Created by Florent on 20.03.2015.
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
