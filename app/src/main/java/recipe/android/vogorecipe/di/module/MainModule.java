package recipe.android.vogorecipe.di.module;

import dagger.Module;
import dagger.Provides;
import recipe.android.vogorecipe.di.scope.ActivityScope;
import recipe.android.vogorecipe.view.activities.main.MainActivity;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

@Module
public class MainModule {

    private MainActivity mActivity;

    public MainModule(MainActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mActivity;
    }
}
