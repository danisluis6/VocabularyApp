package com.tienganh.vocabulary.view.fragments;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.tienganh.tuvungtienganh.R;
import com.tienganh.vocabulary.storage.entities.Recipe;
import com.tienganh.vocabulary.view.activities.main.MainActivity;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

@SuppressLint("ValidFragment")
public class DialogRecipeDetailed extends DialogFragment{

    @BindView(R.id.edtRecipeName)
    EditText edtRecipeName;

    @BindView(R.id.edtDescription)
    EditText edtDescription;

    @BindView(R.id.edtingredient)
    EditText edtingredient;

    @BindView(R.id.spRecipeTypes)
    Spinner spRecipeTypes;

    @BindView(R.id.edtPortions)
    EditText edtPortions;

    @BindView(R.id.edtTerms)
    EditText edtTerms;

    private Context mContext;
    private MainActivity mMainActivity;
    private Recipe mRecipe;
    private DialogIngredient mDialogIngredient;

    @SuppressLint("ValidFragment")
    public DialogRecipeDetailed(Context context, MainActivity mainActivity) {
        mContext = context;
        mMainActivity = mainActivity;
        mainActivity.addDialogInterface(new MainActivity.ParseDataToDialogInterface() {
            @Override
            public void openRecipe(Recipe recipe) {
                mRecipe = recipe;
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_detail, container);
        setCancelable(false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btnClose, R.id.btnUpdateRecipe})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                this.dismiss();
                break;
            case R.id.btnUpdateRecipe:
                Recipe recipe = new Recipe();
                recipe.setId(mRecipe.getId());
                recipe.setName(edtRecipeName.getText().toString());
                recipe.setDescription(edtDescription.getText().toString());
                if (TextUtils.isEmpty(edtPortions.getText().toString())) {
                    recipe.setPortions(0);
                } else {
                    recipe.setPortions(Integer.parseInt(edtPortions.getText().toString()));
                }
                recipe.setTerms(edtTerms.getText().toString());
                recipe.setRecipeTypes(spRecipeTypes.getSelectedItem().toString());
                recipe.setIngredients(edtingredient.getText().toString());
                mInterfaceRecipeFragment.updateRecipe(recipe);
                this.dismiss();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        viewRecipe(mRecipe);
    }

    private InterfaceRecipeFragment mInterfaceRecipeFragment;

    public interface InterfaceRecipeFragment {
        void updateRecipe(Recipe recipe);
    }

    public void attachEventInterface(InterfaceRecipeFragment interfaceRecipeFragment) {
        mInterfaceRecipeFragment = interfaceRecipeFragment;
    }

    public void viewRecipe(Recipe recipe) {
        edtRecipeName.setText(recipe.getName());
        edtDescription.setText(recipe.getDescription());
        edtingredient.setText(recipe.getIngredients());
        edtPortions.setText(String.valueOf(recipe.getPortions()));
        edtTerms.setText(recipe.getTerms());
        updateValueonSpinner(recipe);
    }

    private void updateValueonSpinner(Recipe recipe) {
        String compareValue = recipe.getRecipeTypes();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.recipes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRecipeTypes.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spRecipeTypes.setSelection(spinnerPosition);
        }
    }
}
