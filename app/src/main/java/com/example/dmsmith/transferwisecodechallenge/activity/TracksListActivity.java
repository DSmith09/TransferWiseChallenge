package com.example.dmsmith.transferwisecodechallenge.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.dmsmith.transferwisecodechallenge.fragment.TracksListFragment;

public class TracksListActivity extends SingleFragmentActivity {
    private static final String EXTRA_PLAYLIST_ID = "com.example.dmsmith" +
            ".transferwisecodechallenge.model.playlist.id";

    public static Intent newIntent(Context packageContext, String playlistId) {
        Intent intent = new Intent(packageContext, TracksListActivity.class);
        intent.putExtra(EXTRA_PLAYLIST_ID, playlistId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return new TracksListFragment();
    }
}
