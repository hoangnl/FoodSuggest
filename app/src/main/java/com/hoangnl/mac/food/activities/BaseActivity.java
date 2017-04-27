package com.hoangnl.mac.food.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.hoangnl.mac.food.R;

/**
 * Created by mac on 1/30/17.
 */

public class BaseActivity extends AppCompatActivity {
    private final String TAG = "BaseActivity";

    void activateToolBar(boolean enableHome) {
        Log.d(TAG, "activateToolBar: starts");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }


        }

        if (actionBar != null) {
            actionBar.setDefaultDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
