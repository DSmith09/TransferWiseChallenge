package com.example.dmsmith.transferwisecodechallenge.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.dmsmith.transferwisecodechallenge.fragment.TracksListFragment;

public class TracksListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, TracksListActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return new TracksListFragment();
    }
}
