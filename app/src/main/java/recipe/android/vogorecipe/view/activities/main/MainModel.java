package recipe.android.vogorecipe.view.activities.main;

import recipe.android.vogorecipe.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public interface MainModel {
    void addRecipe(Recipe recipe);

    void getAllRecipes();

    void deleteRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void updateRecipeFailure();

    void updateRecipeSuccess(Recipe recipe);
}
