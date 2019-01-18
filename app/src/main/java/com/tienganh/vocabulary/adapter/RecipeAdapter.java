package com.tienganh.vocabulary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.tienganh.tuvungtienganh.R;
import com.tienganh.vocabulary.storage.entities.Recipe;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private Context mContext;
    private List<Recipe> mGroupRecipes;

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvIngredient)
        TextView tvIngredient;

        @BindView(R.id.imvDelete)
        ImageView imvDelete;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public RecipeAdapter(Context context, List<Recipe> groupRecipes, RecipeInterface _interInterface) {
        mContext = context;
        mGroupRecipes = groupRecipes;
        mRecipeInterface = _interInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_ingredient, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Recipe recipe = mGroupRecipes.get(position);
        holder.tvIngredient.setText(recipe.getName());
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRecipe(recipe);
                mRecipeInterface.removeRecipe(recipe);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecipeInterface.showRecipeDetailed(recipe);
            }
        });
    }

    public void updateListByType(List<Recipe> originalRecipes, CharSequence title) {
        if (TextUtils.equals(title, "All")) {
            mGroupRecipes = originalRecipes;
        } else {
            List<Recipe> temps = new ArrayList<>();
            for (int index = 0; index < originalRecipes.size(); index++) {
                if (TextUtils.equals(title, originalRecipes.get(index).getRecipeTypes())) {
                    temps.add(originalRecipes.get(index));
                }
            }
            mGroupRecipes = temps;
        }
        notifyDataSetChanged();
    }

    public interface RecipeInterface {
        void removeRecipe(Recipe recipe);
        void showRecipeDetailed(Recipe recipe);
    }

    public RecipeInterface mRecipeInterface;

    private void removeRecipe(Recipe item) {
        mGroupRecipes.remove(item);
        notifyDataSetChanged();
    }

    public void addRecipe(Recipe recipe) {
        mGroupRecipes.add(recipe);
        notifyDataSetChanged();
    }

    public void updateListRecipies(ArrayList<Recipe> recipes) {
        mGroupRecipes = recipes;
        notifyDataSetChanged();
    }

    public void updateRecipeList(Recipe recipe) {
        for (int index = 0; index < mGroupRecipes.size(); index++) {
            if (mGroupRecipes.get(index).getId() == recipe.getId()) {
                mGroupRecipes.remove(mGroupRecipes.get(index));
            }
        }
        mGroupRecipes.add(recipe);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGroupRecipes.size();
    }
}