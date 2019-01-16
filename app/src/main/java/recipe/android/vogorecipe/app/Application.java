package recipe.android.vogorecipe.app;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import recipe.android.vogorecipe.di.component.AppComponent;
import recipe.android.vogorecipe.di.component.DaggerAppComponent;
import recipe.android.vogorecipe.di.module.AppModule;

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
