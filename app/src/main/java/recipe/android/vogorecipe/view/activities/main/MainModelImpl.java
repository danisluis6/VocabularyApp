package recipe.android.vogorecipe.view.activities.main;

import android.content.Context;

import recipe.android.vogorecipe.storage.dbaccess.DARecipe;
import recipe.android.vogorecipe.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class MainModelImpl implements MainModel {

    private DARecipe mDARecipe;
    private MainPresenter mMainPresenter;
    private Context mContext;

    public MainModelImpl(Context context, MainPresenter presenter) {
        mDARecipe = new DARecipe();
        mMainPresenter = presenter;
        mContext = context;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        long id = mDARecipe.add(recipe, mContext);
        if (id > 0) {
            recipe.setId((int) id);
            mMainPresenter.addRecipeSuccess(recipe);
        } else {
            mMainPresenter.addRecipeFailed();
        }
    }

    @Override
    public void getAllRecipes() {
        try {
            mMainPresenter.getAllRecipesSuccess(mDARecipe.getAll(mContext));
        } catch (Exception ex) {
            mMainPresenter.getAllRecipesFailure();
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        if(mDARecipe.deleteById(recipe.getId(), mContext)) {
            mMainPresenter.deleteRecipeSuccess();
        } else {
            mMainPresenter.deleteRecipeFailure();
        }
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        if (mDARecipe.edit(recipe, mContext)) {
            mMainPresenter.updateRecipeSuccess(recipe);
        } else {
            mMainPresenter.updateRecipeFailure();
        }
    }

    @Override
    public void updateRecipeFailure() {

    }

    @Override
    public void updateRecipeSuccess(Recipe recipe) {

    }
}
