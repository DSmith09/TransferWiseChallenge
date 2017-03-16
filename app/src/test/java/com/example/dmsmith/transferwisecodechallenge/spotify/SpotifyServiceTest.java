package com.example.dmsmith.transferwisecodechallenge.spotify;


import android.graphics.Bitmap;

import com.example.dmsmith.transferwisecodechallenge.BuildConfig;
import com.example.dmsmith.transferwisecodechallenge.mocks.MockPlaylistTracks;
import com.example.dmsmith.transferwisecodechallenge.mocks.MockPlaylists;
import com.example.dmsmith.transferwisecodechallenge.model.Playlist;
import com.example.dmsmith.transferwisecodechallenge.model.PlaylistTrack;
import com.example.dmsmith.transferwisecodechallenge.model.Track;
import com.example.dmsmith.transferwisecodechallenge.model.paging.PlaylistTracks;
import com.example.dmsmith.transferwisecodechallenge.model.paging.Playlists;
import com.example.dmsmith.transferwisecodechallenge.network.MockRestInvokerClient;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class SpotifyServiceTest {
    private static final String TEST_GET_TRACKS = "test_get_tracks";
    private static final String TEST_GET_TRACKS_2 = "test_get_tracks_2";
    private static final String TEST_GET_ALBUM_ART = "test_get_album_art";
    private static final String TEST_IMAGE_URL = "http://google.com/images/test.jpeg";
    private static final SpotifyService SPOTIFY_SERVICE = new SpotifyService(
            new MockRestInvokerClient());

    @Test
    public void testGetPlaylists() {
        Playlists playlists = SPOTIFY_SERVICE.getPlaylists(MockPlaylists.class);
        assertNotNull("Should Have Playlists", playlists);
        assertEquals("Should Have 3 Playlist Objects", 3, playlists.getPlaylists().length);
        for (Playlist playlist : playlists.getPlaylists()) {
            assertNotNull(playlist);
            assertTrue(playlist.isPublic());
            assertFalse(playlist.isCollaborative());
            assertEquals("Name Should Be dmsmith", "dmsmith", playlist.getName());
        }
    }

    @Test
    public void testGetTracksForPlaylist() {
        PlaylistTracks playlistTracks = SPOTIFY_SERVICE.getTracksForPlaylist(TEST_GET_TRACKS,
                MockPlaylistTracks.class);
        assertNotNull("Should Have Playlist Tracks", playlistTracks);
        assertEquals("Should Have 3 Playlist Track Objects", 3,
                playlistTracks.getPlaylistTracks().length);
        for (PlaylistTrack playlistTrack : playlistTracks.getPlaylistTracks()) {
            assertNotNull(playlistTrack);
            Track track = playlistTrack.getTrack();
            assertNotNull(track);
        }
    }

    @Test
    public void testGetTracksForPlaylist_NullHref() {
        PlaylistTracks playlistTracks = SPOTIFY_SERVICE
                .getTracksForPlaylist(null, PlaylistTracks.class);
        assertNull(playlistTracks);
    }

    @Test
    public void testGetTracksForPlaylist_IncompatibleClass() {
        PlaylistTracks playlistTracks = SPOTIFY_SERVICE
                .getTracksForPlaylist(TEST_GET_TRACKS_2, Object.class);
        assertNull(playlistTracks);
    }

    @Test
    public void testGetAlbumArt() {
        Bitmap bitmap = SPOTIFY_SERVICE.getTrackAlbumArt(TEST_GET_ALBUM_ART, TEST_IMAGE_URL);
        assertNull(bitmap);
    }
}
