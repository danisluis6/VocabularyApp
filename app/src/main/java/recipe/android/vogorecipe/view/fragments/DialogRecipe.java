package recipe.android.vogorecipe.view.fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import recipe.android.vogorecipe.R;
import recipe.android.vogorecipe.storage.entities.Recipe;
import recipe.android.vogorecipe.utilities.Constants;
import recipe.android.vogorecipe.view.activities.main.MainActivity;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class DialogRecipe extends DialogFragment {

    private MainActivity mActivity;
    private Context mContext;
    private DialogIngredient mDialogIngredient;
    private Recipe mRecipe;

    @BindView(R.id.tvNewRecipe)
    TextView tvNewRecipe;

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtingredient)
    EditText edtingredient;

    @BindView(R.id.edtDescription)
    EditText edtDescription;

    @BindView(R.id.edtPortions)
    EditText edtPortions;

    @BindView(R.id.edtTerms)
    EditText edtTerms;

    @BindView(R.id.spRecipeTypes)
    Spinner spRecipeTypes;

    public DialogRecipe() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_recipe, container);
        setCancelable(false);
        ButterKnife.bind(this, view);
        tvNewRecipe.setText(getString(R.string.new_recipe));
        mRecipe = new Recipe();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.recipes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRecipeTypes.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        resetView();
        super.onResume();
    }

    private void resetView() {
        edtName.setText(Constants.EMPTY_STRING);
        edtDescription.setText(Constants.EMPTY_STRING);
        edtingredient.setText(Constants.EMPTY_STRING);
        edtPortions.setText(Constants.EMPTY_STRING);
        edtTerms.setText(Constants.EMPTY_STRING);
    }

    public void setParentFragment(Context context, MainActivity activity, DialogIngredient dialogIngredient) {
        mDialogIngredient = dialogIngredient;
        mDialogIngredient.attachEventInterface(new DialogIngredient.InterfaceIngredientFragment() {
            @Override
            public void getIngredients(List<String> items) {
                String temp = Constants.EMPTY_STRING;
                for (int index = 0; index < items.size(); index++) {
                    if (index == items.size()-1) {
                        temp += items.get(index);
                    } else {
                        temp += items.get(index)+',';
                    }
                    edtingredient.setText(temp);
                    mRecipe.setIngredients(temp);
                }
            }
        });
        mActivity = activity;
        mContext = context;
    }

    private InterfaceRecipeFragment mInterfaceRecipeFragment;

    public interface InterfaceRecipeFragment {
        void addRecipe(Recipe recipe);
    }

    public void attachEventInterface(InterfaceRecipeFragment interfaceRecipeFragment) {
        mInterfaceRecipeFragment = interfaceRecipeFragment;
    }

    @OnClick({R.id.btnNewRecipe, R.id.btnClose, R.id.edtingredient})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNewRecipe:
                if (TextUtils.isEmpty(edtName.getText())) {
                    Toast.makeText(mContext, "The name field is required", Toast.LENGTH_SHORT).show();
                } else {
                    mRecipe.setName(edtName.getText().toString());
                    mRecipe.setDescription(edtDescription.getText().toString());
                    if (TextUtils.isEmpty(edtPortions.getText().toString())) {
                        mRecipe.setPortions(0);
                    } else {
                        mRecipe.setPortions(Integer.parseInt(edtPortions.getText().toString()));
                    }
                    mRecipe.setTerms(edtTerms.getText().toString());
                    mRecipe.setRecipeTypes(spRecipeTypes.getSelectedItem().toString());
                    mInterfaceRecipeFragment.addRecipe(mRecipe);
                    this.dismiss();
                }
                break;
            case R.id.edtingredient:
                mDialogIngredient.show(mActivity.getFragmentManager(), Constants.INGREDIENT);
                break;
            case R.id.btnClose:
                this.dismiss();
                break;
        }
    }
}
