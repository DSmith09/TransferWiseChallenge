package com.example.dmsmith.transferwisecodechallenge.spotify.service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.example.dmsmith.transferwisecodechallenge.model.paging.PlaylistTracks;
import com.example.dmsmith.transferwisecodechallenge.model.paging.Playlists;
import com.example.dmsmith.transferwisecodechallenge.network.RestInvoker;
import com.example.dmsmith.transferwisecodechallenge.spotify.enums.Endpoints;
import com.example.dmsmith.transferwisecodechallenge.spotify.enums.Scopes;
import com.example.dmsmith.transferwisecodechallenge.spotify.model.SpotifyEndpoint;
import com.example.dmsmith.transferwisecodechallenge.store.CacheStore;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistStore;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistTrackStore;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;

import javax.inject.Inject;

public class SpotifyService {
    private static final String SPOTIFY_SERVICE = "SpotifyService";
    private static final String CLIENT_ID = "e8da04ad2f454866956c8f249b11f5fe";
    private static final String REDIRECT_URI =
            "transferwise-code-challenge-spotify-login://callback";
    private static final String SPOTIFY_BASE_URL = "https://api.spotify.com";
    private static final int REQUEST_CODE = 1337;
    private static final CacheStore CACHE_STORE = CacheStore.getInstance();

    // Dagger Injected Fields
    private final RestInvoker mRestInvoker;

    private String mAuthToken;
    private Player mPlayer;

    @Inject
    public SpotifyService(RestInvoker restInvoker) {
        this.mRestInvoker = restInvoker;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + mAuthToken);
        return httpHeaders;
    }

    // Main Methods
    public void authenticate(Activity context) {
        AuthenticationRequest.Builder arBuilder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        AuthenticationRequest request = arBuilder.setScopes(
                new String[]{Scopes.USER_READ_PRIVATE.toString(), Scopes.PLAYLIST_READ_PRIVATE
                        .toString(), Scopes.STREAMING.toString()})
                .build();
        AuthenticationClient.openLoginActivity(context, REQUEST_CODE, request);
    }

    public void handleCallback(final Activity context, int requestCode, int responseCode,
            Intent intent) {
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient
                    .getResponse(responseCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                mAuthToken = response.getAccessToken();
                Config configPlayer = new Config(context, mAuthToken, CLIENT_ID);
                Spotify.getPlayer(configPlayer, context,
                        new Player.InitializationObserver() {
                            @Override
                            public void onInitialized(Player player) {
                                mPlayer = player;
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Log.e(SPOTIFY_SERVICE,
                                        "Could not initialize player: " + throwable.getMessage());
                            }
                        });
                PlaylistStore.getInstance()
                        .setPlaylists(Arrays.asList(getPlaylists(Playlists.class).getPlaylists()));
            }
        }
    }

    // Visible Only For Unit Testing Purposes
    @VisibleForTesting
    public <T> Playlists getPlaylists(Class<T> responseClass) {
        SpotifyEndpoint getPlaylistsEndpoint = Endpoints.GET_PLAYLIST.getEndpoint();
        try {
            String url = SPOTIFY_BASE_URL + getPlaylistsEndpoint.getEndpoint();
            HttpMethod method = getPlaylistsEndpoint.getHttpMethod();
            Playlists playlists = (Playlists) mRestInvoker
                    .invokeService(url, method, null, createHeaders(), responseClass);

            PlaylistStore.getInstance().setPlaylists(Arrays.asList(playlists.getPlaylists()));
            return playlists;
        } catch (Exception e) {
            Log.e(SPOTIFY_SERVICE, "Exception thrown on GET PLAYLISTS; RETURNING NULL", e);
            return null;
        }
    }

    public <T> PlaylistTracks getTracksForPlaylist(String href, Class<T> responseClass) {
        Object cachedTracks = CACHE_STORE.getValue(href);
        PlaylistTrackStore store = PlaylistTrackStore.getInstance();
        if (cachedTracks != null) {
            PlaylistTracks playlistTracks = (PlaylistTracks) cachedTracks;
            store.setPlaylistTracks(Arrays.asList(playlistTracks.getPlaylistTracks()));
            return playlistTracks;
        }
        SpotifyEndpoint getTracksEndpoint = Endpoints.GET_TRACKS.getEndpoint();
        try {
            HttpMethod method = getTracksEndpoint.getHttpMethod();
            PlaylistTracks playlistTracks = (PlaylistTracks) mRestInvoker
                    .invokeService(href, method, null, createHeaders(), responseClass);
            CACHE_STORE.add(href, playlistTracks);
            store.setPlaylistTracks(Arrays.asList(playlistTracks.getPlaylistTracks()));
            return playlistTracks;
        } catch (Exception e) {
            Log.e(SPOTIFY_SERVICE, "Exception thrown on GET TRACKS; RETURNING NULL", e);
            return null;
        }
    }

    // Pull Image From Internet and set to ImageView; Fail Gracefully on Exception
    public Bitmap getTrackAlbumArt(String uri, String imageUrl) {
        Object cachedImage = CACHE_STORE.getValue(uri);
        if (cachedImage != null) {
            return (Bitmap) cachedImage;
        }
        byte[] result = null;
        try {
            result = mRestInvoker
                    .invokeService(imageUrl, HttpMethod.GET, null, createHeaders(), byte[].class);
        } catch (Exception e) {
            Log.e(SPOTIFY_SERVICE, "Exception thrown on GET IMAGE; RETURNING NULL", e);
        }
        if (result != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
            CACHE_STORE.add(uri, bitmap);
            return bitmap;
        }
        return null;
    }

    // Player Functions
    public void startPlaying(String uri) {
        if (mPlayer.isLoggedIn()) {
            mPlayer.play(uri);
        }
    }

    public void pause() {
        mPlayer.pause();
    }

    public void resume() {
        mPlayer.resume();
    }
}
