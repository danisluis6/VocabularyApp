package com.tienganh.vocabulary.view.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by lorence on 7/9/2018.
 *
 * @author lorence
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        distributedDaggerComponents();
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initViews();
    }

    public abstract void distributedDaggerComponents();

    protected abstract int getLayoutRes();

    protected abstract void initViews();

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this).unbind();
        super.onDestroy();
    }
}