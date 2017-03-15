package com.example.dmsmith.transferwisecodechallenge.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dmsmith.transferwisecodechallenge.MyApp;
import com.example.dmsmith.transferwisecodechallenge.R;
import com.example.dmsmith.transferwisecodechallenge.activity.TracksListActivity;
import com.example.dmsmith.transferwisecodechallenge.model.Playlist;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistStore;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PlaylistsListFragment extends RecyclerListViewFragment {
    @Inject
    SpotifyService mSpotifyService;
    private PlaylistStore mStore = PlaylistStore.getInstance();

    @Override
    public void updateUI() {
        ((MyApp) getActivity().getApplication()).getPlaylistsListFragmentComponents().inject(this);
        mRecyclerView.setAdapter(new PlaylistsAdapter(mStore.getPlaylists()));
    }

    class PlaylistsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.playlist_name) TextView mPlaylistName;
        @BindView(R.id.playlist_track_count) TextView mPlaylistTrackCount;
        private Playlist mPlaylist;

        PlaylistsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindPlaylist(Playlist playlist) {
            mPlaylist = playlist;
            mPlaylistName.setText(playlist.getName());
            mPlaylistTrackCount.setText("Track Count: " + playlist.getTracks().getTotal());
        }

        @Override
        public void onClick(View v) {
            mSpotifyService.getTracksForPlaylist(mPlaylist.getTracks().getHref());
            startActivity(TracksListActivity.newIntent(getActivity(), mPlaylist.getId()));
        }
    }

    private class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistsHolder> {
        private final List<Playlist> mPlaylistList;

        PlaylistsAdapter(List<Playlist> playlists) {
            this.mPlaylistList = playlists;
        }

        @Override
        public PlaylistsHolder onCreateViewHolder(ViewGroup parent,
                int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new PlaylistsHolder(
                    inflater.inflate(R.layout.list_item_playlist, parent, false));
        }

        @Override
        public void onBindViewHolder(PlaylistsHolder holder, int position) {
            holder.bindPlaylist(mPlaylistList.get(position));
        }

        @Override
        public int getItemCount() {
            return mPlaylistList.size();
        }
    }
}
