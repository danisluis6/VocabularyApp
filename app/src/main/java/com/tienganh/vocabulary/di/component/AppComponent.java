package com.tienganh.vocabulary.di.component;

import javax.inject.Singleton;

import dagger.Component;
import com.tienganh.vocabulary.di.module.AppModule;
import com.tienganh.vocabulary.di.module.MainModule;

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
