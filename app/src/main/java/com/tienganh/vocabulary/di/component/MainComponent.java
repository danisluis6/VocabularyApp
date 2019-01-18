package com.tienganh.vocabulary.di.component;

import dagger.Subcomponent;
import com.tienganh.vocabulary.di.module.DialogModule;
import com.tienganh.vocabulary.di.module.MainModule;
import com.tienganh.vocabulary.di.scope.ActivityScope;
import com.tienganh.vocabulary.view.activities.main.MainActivity;

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


