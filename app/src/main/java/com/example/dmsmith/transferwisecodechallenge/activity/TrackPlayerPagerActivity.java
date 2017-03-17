package com.example.dmsmith.transferwisecodechallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dmsmith.transferwisecodechallenge.R;
import com.example.dmsmith.transferwisecodechallenge.fragment.TrackPlayerFragment;
import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.model.Track;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistTrackStore;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackPlayerPagerActivity extends FragmentActivity {
    private static final String EXTRA_TRACK_ID =
            "com.example.dmsmith.transferwisecodechallenge.model.track.id";

    @BindView(R.id.activity_view_pager) ViewPager mViewPager;

    private List<PlaylistTrack> mPlaylistTracks = PlaylistTrackStore.getInstance()
            .getPlaylistTracks();

    public static Intent newIntent(Context packageContext, Track track) {
        Intent intent = new Intent(packageContext, TrackPlayerPagerActivity.class);
        intent.putExtra(EXTRA_TRACK_ID, track.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_player_pager);
        ButterKnife.bind(this);
        final String trackId = getIntent().getStringExtra(EXTRA_TRACK_ID);
        final PlaylistTrack playlistTrack = PlaylistTrackStore.getInstance()
                .getPlaylistTrack(trackId);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Track track = mPlaylistTracks.get(position).getTrack();
                String imageUrl = track.getAlbum().getImages()[0].getUrl();
                String trackUri = track.getUri();
                return TrackPlayerFragment.newInstance(imageUrl, trackUri);
            }

            @Override
            public int getCount() {
                return mPlaylistTracks.size();
            }
        });
        mViewPager.setCurrentItem(mPlaylistTracks.indexOf(playlistTrack));
    }
}
