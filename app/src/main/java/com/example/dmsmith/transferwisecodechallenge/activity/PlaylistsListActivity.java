package com.example.dmsmith.transferwisecodechallenge.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.dmsmith.transferwisecodechallenge.fragment.PlaylistsListFragment;

public class PlaylistsListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new PlaylistsListFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, PlaylistsListActivity.class);
        return intent;
    }
}
