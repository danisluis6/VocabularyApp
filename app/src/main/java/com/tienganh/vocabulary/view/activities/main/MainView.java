package com.tienganh.vocabulary.view.activities.main;

import java.util.ArrayList;

import com.tienganh.vocabulary.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public interface MainView {
    void showDialogWord();

    void addRecipeSuccess(Recipe recipe);

    void addRecipeFailed();

    void getAllRecipesSuccess(ArrayList<Recipe> recipes);

    void getAllRecipesFailure();

    void deleteRecipeFailure();

    void deleteRecipeSuccess();

    void updateRecipeFailure();

    void updateRecipeSuccess(Recipe recipe);
}
