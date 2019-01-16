package recipe.android.vogorecipe.di.component;

import javax.inject.Singleton;

import dagger.Component;
import recipe.android.vogorecipe.di.module.AppModule;
import recipe.android.vogorecipe.di.module.MainModule;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
        MainComponent plus(MainModule module);
}
