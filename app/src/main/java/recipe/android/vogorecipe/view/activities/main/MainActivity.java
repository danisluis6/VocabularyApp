package recipe.android.vogorecipe.view.activities.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import recipe.android.vogorecipe.adapter.RecipeAdapter;
import recipe.android.vogorecipe.app.Application;
import recipe.android.vogorecipe.di.module.MainModule;
import recipe.android.vogorecipe.R;
import recipe.android.vogorecipe.storage.entities.Recipe;
import recipe.android.vogorecipe.utilities.Constants;
import recipe.android.vogorecipe.utilities.GaussianBlur;
import recipe.android.vogorecipe.utilities.Utils;
import recipe.android.vogorecipe.view.activities.BaseActivity;
import recipe.android.vogorecipe.view.fragments.DialogIngredient;
import recipe.android.vogorecipe.view.fragments.DialogRecipe;
import recipe.android.vogorecipe.view.fragments.DialogRecipeDetailed;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    Context mContext;

    @Inject
    MainActivity mMainActivity;

    @Inject
    DialogRecipe mDialogRecipe;

    @Inject
    DialogIngredient mDialogIngredient;

    @Inject
    DialogRecipeDetailed mDialogRecipeDetailed;

    @BindView(R.id.fabCreateRecipe)
    FloatingActionButton fabCreateRecipe;

    @BindView(R.id.rcvRecipe)
    RecyclerView rcvRecipe;

    @BindView(R.id.container)
    ImageView container;

    @BindView(R.id.layout)
    LinearLayout layout;

    private MainPresenter mMainPresenter;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> originalRecipes;
    private GaussianBlur blur;

    @Override
    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mDialogRecipe.setParentFragment(mContext, this, mDialogIngredient);
        mMainPresenter = new MainPresenterImpl(mContext, this);
        originalRecipes = new ArrayList<>();
        mRecipeAdapter = new RecipeAdapter(mContext, new ArrayList<Recipe>(), new RecipeAdapter.RecipeInterface() {
            @Override
            public void removeRecipe(Recipe recipe) {
                mMainPresenter.deleteRecipe(recipe);
            }

            @Override
            public void showRecipeDetailed(Recipe recipe) {
                mDialogRecipeDetailed.show(mMainActivity.getFragmentManager(), Constants.DETAIL);
                mParseDataToDialogInterface.openRecipe(recipe);
            }
        });
        rcvRecipe.setLayoutManager(new LinearLayoutManager(mContext));
        rcvRecipe.setAdapter(mRecipeAdapter);

        mDialogRecipe.attachEventInterface(new DialogRecipe.InterfaceRecipeFragment() {
            @Override
            public void addRecipe(Recipe recipe) {
                mMainPresenter.addRecipe(recipe);
            }
        });

        mDialogRecipeDetailed.attachEventInterface(new DialogRecipeDetailed.InterfaceRecipeFragment() {
            @Override
            public void updateRecipe(Recipe recipe) {
                mMainPresenter.updateRecipe(recipe);
            }
        });
        initComponents();
    }

    private void initComponents() {
        container.setVisibility(View.GONE);
        blur = new GaussianBlur(MainActivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarGradiant(this);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        container.setVisibility(View.VISIBLE);

        layout.setDrawingCacheEnabled(true);
        layout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

        Bitmap bitmap = layout.getDrawingCache();

        container.setImageBitmap(blur.gaussianBlur(25, bitmap));

        layout.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.custom_bg);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            }
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_more:
                PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.ic_more));
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_vegetarian:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                return true;
                            case R.id.ic_fast_food:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                return true;
                            case R.id.ic_healthy:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                return true;
                            case R.id.ic_no_cook:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                return true;
                            case R.id.ic_make_ahead:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                return true;
                            case R.id.ic_all:
                                mRecipeAdapter.updateListByType(originalRecipes, item.getTitle());
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public interface ParseDataToDialogInterface {
        void openRecipe(Recipe recipe);
    }

    public ParseDataToDialogInterface mParseDataToDialogInterface;

    public void addDialogInterface(ParseDataToDialogInterface parseDataToDialogInterface) {
        mParseDataToDialogInterface = parseDataToDialogInterface;
    }

    @OnClick({R.id.fabCreateRecipe})
    void onClick(View v) {
        if (Utils.isDoubleClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.fabCreateRecipe:
                showDialogRecipe();
                break;
        }
    }

    @Override
    public void showDialogRecipe() {
        mDialogRecipe.show(mMainActivity.getFragmentManager(), Constants.RECIPE);
    }

    @Override
    public void addRecipeSuccess(Recipe recipe) {
        mRecipeAdapter.addRecipe(recipe);
    }

    @Override
    public void addRecipeFailed() {

    }

    @Override
    public void getAllRecipesSuccess(ArrayList<Recipe> recipes) {
        originalRecipes = recipes;
        mRecipeAdapter.updateListRecipies(recipes);
    }

    @Override
    public void getAllRecipesFailure() {

    }

    @Override
    public void deleteRecipeFailure() {
        Log.i("TAG", "Delete recipe failure");
    }

    @Override
    public void deleteRecipeSuccess() {
        Log.i("TAG", "Delete recipe successfully");
    }

    @Override
    public void updateRecipeFailure() {
        Log.i("TAG", "Update recipe failure");
    }

    @Override
    public void updateRecipeSuccess(Recipe recipe) {
        mRecipeAdapter.updateRecipeList(recipe);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.getAllRecipes();
    }
}