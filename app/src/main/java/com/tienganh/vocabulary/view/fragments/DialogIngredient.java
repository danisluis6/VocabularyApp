package com.tienganh.vocabulary.view.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.tienganh.tuvungtienganh.R;
import com.tienganh.vocabulary.utilities.Constants;
import com.tienganh.vocabulary.view.activities.main.MainActivity;
import com.tienganh.vocabulary.adapter.IngredientAdapter;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public class DialogIngredient extends DialogFragment implements View.OnTouchListener {

    private MainActivity mActivity;
    private Context mContext;
    private IngredientAdapter mIngredientAdapter;

    @BindView(R.id.edtingredient)
    EditText edtingredient;

    @BindView(R.id.rcvIngredient)
    RecyclerView rcvIngredient;

    public DialogIngredient() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_ingredient, container);
        setCancelable(false);
        ButterKnife.bind(this, view);
        mIngredientAdapter = new IngredientAdapter(mContext, new ArrayList<String>());
        rcvIngredient.setLayoutManager(new LinearLayoutManager(mContext));
        rcvIngredient.setAdapter(mIngredientAdapter);
        edtingredient.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(event.getRawX() >= (edtingredient.getRight() - edtingredient.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                if (edtingredient.getText().toString().trim().length() !=0 ) {
                    mIngredientAdapter.addIngredient(edtingredient.getText().toString());
                    edtingredient.setText(Constants.EMPTY_STRING);
                }
                return true;
            }
        }
        return false;
    }

    public interface InterfaceIngredientFragment {
        void getIngredients(List<String> items);
    }

    private InterfaceIngredientFragment mInterfaceIngredientFragment;

    public void attachEventInterface(InterfaceIngredientFragment interfaceIngredientFragment) {
        mInterfaceIngredientFragment = interfaceIngredientFragment;
    }

    @OnClick({R.id.btnClose, R.id.btnDone})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDone:
                mInterfaceIngredientFragment.getIngredients(mIngredientAdapter.getListOfIngredients());
                this.dismiss();
                break;
            case R.id.btnClose:
                this.dismiss();
                break;
        }
    }
}
