package recipe.android.vogorecipe.view.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import recipe.android.vogorecipe.utilities.GaussianBlur;
import recipe.android.vogorecipe.utilities.OverrideFonts;
import recipe.android.vogorecipe.view.activities.main.MainActivity;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class DialogWord extends DialogFragment {

    private MainActivity mActivity;
    private Context mContext;
    private DialogIngredient mDialogIngredient;
    private Recipe mRecipe;

    @BindView(R.id.tvYourWord)
    TextView tvYourWord;

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

    @BindView(R.id.container)
    ImageView container;

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.blurHeader)
    RelativeLayout blurHeader;

    @BindView(R.id.tvUK)
    TextView tvUK;

    @BindView(R.id.tvUS)
    TextView tvUS;

    @BindView(R.id.phoneticUK)
    TextView phoneticUK;

    @BindView(R.id.phoneticUS)
    TextView phoneticUS;

    private GaussianBlur blur;

    public DialogWord() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_word, container);
        setCancelable(false);
        ButterKnife.bind(this, view);
        mRecipe = new Recipe();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.recipes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRecipeTypes.setAdapter(adapter);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        container.setVisibility(View.GONE);
        blur = new GaussianBlur(mActivity);
        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean hasFocus) {
                container.setVisibility(View.VISIBLE);

                layout.setDrawingCacheEnabled(true);
                layout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

                Bitmap bitmap = layout.getDrawingCache();

                container.setImageBitmap(blur.gaussianBlur(25, bitmap));

                layout.setVisibility(View.VISIBLE);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarGradiant(mActivity);
        }

        tvYourWord.setTypeface(OverrideFonts.getTypeFace(mContext, OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.LIGHT));
        tvUK.setTypeface(OverrideFonts.getTypeFace(mContext, OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.MEDIUM));
        tvUS.setTypeface(OverrideFonts.getTypeFace(mContext, OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.MEDIUM));
        phoneticUK.setTypeface(OverrideFonts.getTypeFace(mContext, OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.LIGHT));
        phoneticUS.setTypeface(OverrideFonts.getTypeFace(mContext, OverrideFonts.TYPE_FONT_NAME.HELVETICANEUE, OverrideFonts.TYPE_STYLE.LIGHT));
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
