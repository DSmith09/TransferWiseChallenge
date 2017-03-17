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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TrackPlayerFragment extends Fragment {
    private static final String ARG_TRACK_IMAGE_URL = "track_image_url";
    private static final String ARG_TRACK_URI = "track_uri";
    @Inject
    SpotifyService mSpotifyService;
    @BindView(R.id.track_image_view) ImageView mTrackImage;
    private String track_uri;
    private String image_url;
    private Unbinder mUnbinder;

    public static TrackPlayerFragment newInstance(String image_url, String track_uri) {
        Bundle args = new Bundle();
        args.putCharSequence(ARG_TRACK_IMAGE_URL, image_url);
        args.putCharSequence(ARG_TRACK_URI, track_uri);
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
        image_url = getArguments().getString(ARG_TRACK_IMAGE_URL);
        track_uri = getArguments().getString(ARG_TRACK_URI);
    }

    private void updateUI() {
        Bitmap bitmap = mSpotifyService.getTrackAlbumArt(track_uri, image_url);
        if (bitmap == null) {
            mTrackImage
                    .setImageDrawable(getResources().getDrawable(android.R.drawable.ic_lock_lock));
        } else {
            mTrackImage.setImageBitmap(bitmap);
        }
        mTrackImage.setOnClickListener(new View.OnClickListener() {
            boolean isPaused = true;
            boolean initialPlay = true;

            @Override
            public void onClick(View v) {
                if (initialPlay) {
                    mSpotifyService.startPlaying(track_uri);
                    initialPlay = false;
                }
                if (isPaused) {
                    mSpotifyService.resume();
                } else {
                    mSpotifyService.pause();
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
}
