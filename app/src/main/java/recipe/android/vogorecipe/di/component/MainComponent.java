package recipe.android.vogorecipe.di.component;

import dagger.Subcomponent;
import recipe.android.vogorecipe.di.module.DialogModule;
import recipe.android.vogorecipe.di.module.MainModule;
import recipe.android.vogorecipe.di.scope.ActivityScope;
import recipe.android.vogorecipe.view.activities.main.MainActivity;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

@ActivityScope
@Subcomponent(

        modules = {
                MainModule.class,
                DialogModule.class
        }
)
public interface MainComponent {
    MainActivity inject(MainActivity activity);
}


