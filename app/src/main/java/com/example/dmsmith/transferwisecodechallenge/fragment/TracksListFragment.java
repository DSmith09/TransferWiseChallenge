package com.example.dmsmith.transferwisecodechallenge.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dmsmith.transferwisecodechallenge.MyApp;
import com.example.dmsmith.transferwisecodechallenge.R;
import com.example.dmsmith.transferwisecodechallenge.activity.TrackPlayerPagerActivity;
import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.model.Track;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistTrackStore;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TracksListFragment extends RecyclerListViewFragment {
    @Inject
    SpotifyService mSpotifyService;
    private PlaylistTrackStore mStore = PlaylistTrackStore.getInstance();

    @Override
    public void updateUI() {
        ((MyApp) getActivity().getApplication()).getTracksListFragmentComponents().inject(this);
        mRecyclerView.setAdapter(new TracksAdapter(mStore.getPlaylistTracks()));
    }

    class TracksHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.playlist_track_name)
        TextView mPlaylistTrackName;
        @BindView(R.id.playlist_track_duration)
        TextView mPlaylistTrackDuration;
        @BindView(R.id.explicit_checkbox)
        CheckBox mExplicitCheckbox;
        private PlaylistTrack mPlaylistTrack;

        TracksHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindTrack(PlaylistTrack playlistTrack) {
            this.mPlaylistTrack = playlistTrack;
            Track track = mPlaylistTrack.getTrack();
            mPlaylistTrackName.setText(track.getName());
            mPlaylistTrackDuration
                    .setText("Length: " + convertDuration(mPlaylistTrack.getTrack().getDuration()));
            mExplicitCheckbox.setChecked(track.isExplicit());
        }

        @Override
        public void onClick(View v) {
            mSpotifyService.getPlayer().playUri(null, mPlaylistTrack.getTrack().getHref(), 0, 0);
            startActivity(
                    TrackPlayerPagerActivity.newIntent(getActivity(), mPlaylistTrack.getTrack()));
        }

        private String convertDuration(int ms) {
            ms = 1000 * Math.round(ms / 1000);
            DateTime dateTime = new DateTime(ms);
            int minute = dateTime.getMinuteOfHour();
            int seconds = dateTime.getSecondOfMinute();
            if (seconds >= 60) {
                minute += 1;
                seconds -= 60;
            }
            return minute + ":" + (seconds < 10 ? "0" : "") + seconds;
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
                    inflater.inflate(R.layout.list_item_playlist_track, parent, false));
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
