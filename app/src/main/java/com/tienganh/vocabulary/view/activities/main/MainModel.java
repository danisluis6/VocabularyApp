package com.tienganh.vocabulary.view.activities.main;

import com.tienganh.vocabulary.storage.entities.Recipe;

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
