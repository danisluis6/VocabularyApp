package com.tienganh.vocabulary.view.activities.main;

import java.util.ArrayList;

import com.tienganh.vocabulary.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public interface MainPresenter {
    void addRecipe(Recipe recipe);

    void addRecipeSuccess(Recipe recipe);

    void addRecipeFailed();

    void getAllRecipes();

    void getAllRecipesSuccess(ArrayList<Recipe> all);

    void getAllRecipesFailure();

    void deleteRecipe(Recipe recipe);

    void deleteRecipeSuccess();

    void deleteRecipeFailure();

    void updateRecipe(Recipe recipe);

    void updateRecipeSuccess(Recipe recipe);

    void updateRecipeFailure();
}
