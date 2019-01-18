package com.tienganh.vocabulary.app;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.tienganh.vocabulary.di.component.AppComponent;
import com.tienganh.vocabulary.di.component.DaggerAppComponent;
import com.tienganh.vocabulary.di.module.AppModule;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class Application extends android.app.Application {

    private AppComponent mApplicationComponent;
    private Context mContext;
    private static Application sInstance;

    public static synchronized Application getInstance() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sInstance = this;
        initAppComponent();
    }

    private void initAppComponent() {
        mApplicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this,mContext))
                .build();
    }

    public AppComponent getAppComponent() {
        return mApplicationComponent;
    }

}
