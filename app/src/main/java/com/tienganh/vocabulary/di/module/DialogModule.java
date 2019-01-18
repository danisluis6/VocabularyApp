package com.tienganh.vocabulary.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.tienganh.vocabulary.di.scope.ActivityScope;
import com.tienganh.vocabulary.view.activities.main.MainActivity;
import com.tienganh.vocabulary.view.fragments.DialogIngredient;
import com.tienganh.vocabulary.view.fragments.DialogWord;
import com.tienganh.vocabulary.view.fragments.DialogRecipeDetailed;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

@Module
public class DialogModule {

    public DialogModule() {
    }

    @Provides
    @ActivityScope
    DialogWord provideDialogRecipe() {
        return new DialogWord();
    }

    @Provides
    @ActivityScope
    DialogIngredient provideDialogIngredient() {
        return new DialogIngredient();
    }

    @Provides
    @ActivityScope
    DialogRecipeDetailed provideDialogRecipeDetailed(Context context, MainActivity activity) {
        return new DialogRecipeDetailed(context, activity);
    }
}
