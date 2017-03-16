package com.example.dmsmith.transferwisecodechallenge.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dmsmith.transferwisecodechallenge.MyApp;
import com.example.dmsmith.transferwisecodechallenge.R;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TrackPlayerFragment extends Fragment {
    private static final String ARG_TRACK_IMAGE_URL = "track_image_url";
    private static final String ARG_TRACK_HREF = "track_href";
    @Inject
    SpotifyService mSpotifyService;
    @BindView(R.id.track_image_view) ImageView mTrackImage;
    private String track_href;
    private String image_url;
    private Unbinder mUnbinder;
    private Player mPlayer;

    public static TrackPlayerFragment newInstance(String image_url, String track_href) {
        Bundle args = new Bundle();
        args.putCharSequence(ARG_TRACK_IMAGE_URL, image_url);
        args.putCharSequence(ARG_TRACK_HREF, track_href);
        TrackPlayerFragment fragment = new TrackPlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_player, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        updateUI();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getActivity().getApplication()).getTrackPlayerFragmentComponents().inject(this);
        mPlayer = mSpotifyService.getPlayer();
        image_url = getArguments().getString(ARG_TRACK_IMAGE_URL);
        track_href = getArguments().getString(ARG_TRACK_HREF);
        mPlayer.playUri(null, track_href, 0, 0);
    }

    private void updateUI() {
        Bitmap bitmap = mSpotifyService.getTrackAlbumArt(track_href, image_url);
        if (bitmap == null) {
            mTrackImage.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_lock_lock));
        } else {
            mTrackImage.setImageBitmap(bitmap);
        }
        mTrackImage.setOnClickListener(new View.OnClickListener() {
            boolean isPaused = false;
            @Override
            public void onClick(View v) {
                if (isPaused) {
                    mPlayer.resume(null);
                } else {
                    mPlayer.pause(null);
                }
                isPaused = !isPaused;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Spotify.destroyPlayer(this);
    }
}
