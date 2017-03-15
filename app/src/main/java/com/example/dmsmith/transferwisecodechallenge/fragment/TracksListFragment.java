package com.example.dmsmith.transferwisecodechallenge.fragment;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmsmith.transferwisecodechallenge.MyApp;
import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistTrackStore;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class TracksListFragment extends RecyclerListViewFragment {
    private PlaylistTrackStore mStore = PlaylistTrackStore.getInstance();

    @Inject
    SpotifyService mSpotifyService;

    @Override
    public void updateUI() {
        ((MyApp) getActivity().getApplication()).getTracksListFragmentComponents().inject(this);
        mRecyclerView.setAdapter(new TracksAdapter(mStore.getPlaylistTracks()));
    }

    class TracksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PlaylistTrack mPlaylistTrack;

        TracksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTrack(PlaylistTrack playlistTrack) {
            this.mPlaylistTrack = playlistTrack;
        }

        @Override
        public void onClick(View v) {

        }
    }

    private class TracksAdapter extends RecyclerView.Adapter<TracksHolder> {
        private final List<PlaylistTrack> mPlaylistTracks;

        TracksAdapter(List<PlaylistTrack> playlistTracks) {
            this.mPlaylistTracks = playlistTracks;
        }

        @Override
        public TracksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new TracksHolder(
                    inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(TracksHolder holder, int position) {
            holder.bindTrack(mPlaylistTracks.get(position));
        }

        @Override
        public int getItemCount() {
            return mPlaylistTracks.size();
        }
    }
}
