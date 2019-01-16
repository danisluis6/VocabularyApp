package recipe.android.vogorecipe.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import recipe.android.vogorecipe.di.scope.ActivityScope;
import recipe.android.vogorecipe.view.activities.main.MainActivity;
import recipe.android.vogorecipe.view.fragments.DialogIngredient;
import recipe.android.vogorecipe.view.fragments.DialogWord;
import recipe.android.vogorecipe.view.fragments.DialogRecipeDetailed;

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
