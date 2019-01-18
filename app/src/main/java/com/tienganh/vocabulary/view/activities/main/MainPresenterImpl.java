package com.tienganh.vocabulary.view.activities.main;

import android.content.Context;

import java.util.ArrayList;

import com.tienganh.vocabulary.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class MainPresenterImpl implements MainPresenter {

    private MainModel mMainModel;
    private MainView mMainView;

    public MainPresenterImpl(Context context, MainView view) {
        mMainModel = new MainModelImpl(context, this);
        mMainView = view;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        mMainModel.addRecipe(recipe);
    }

    @Override
    public void addRecipeSuccess(Recipe recipe) {
        mMainView.addRecipeSuccess(recipe);
    }

    @Override
    public void addRecipeFailed() {
        mMainView.addRecipeFailed();
    }

    @Override
    public void getAllRecipes() {
        mMainModel.getAllRecipes();
    }

    @Override
    public void getAllRecipesSuccess(ArrayList<Recipe> recipes) {
        mMainView.getAllRecipesSuccess(recipes);
    }

    @Override
    public void getAllRecipesFailure() {
        mMainView.getAllRecipesFailure();
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        mMainModel.deleteRecipe(recipe);
    }

    @Override
    public void deleteRecipeSuccess() {
        mMainView.deleteRecipeSuccess();
    }

    @Override
    public void deleteRecipeFailure() {
        mMainView.deleteRecipeFailure();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        mMainModel.updateRecipe(recipe);
    }

    @Override
    public void updateRecipeSuccess(Recipe recipe) {
        mMainView.updateRecipeSuccess(recipe);
    }

    @Override
    public void updateRecipeFailure() {
        mMainView.updateRecipeFailure();
    }
}
