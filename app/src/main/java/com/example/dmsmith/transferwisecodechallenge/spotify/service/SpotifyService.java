package com.example.dmsmith.transferwisecodechallenge.spotify.service;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.dmsmith.transferwisecodechallenge.model.paging.Playlists;
import com.example.dmsmith.transferwisecodechallenge.model.paging.PlaylistTracks;
import com.example.dmsmith.transferwisecodechallenge.network.RestInvoker;
import com.example.dmsmith.transferwisecodechallenge.spotify.enums.Endpoints;
import com.example.dmsmith.transferwisecodechallenge.spotify.enums.Scopes;
import com.example.dmsmith.transferwisecodechallenge.spotify.model.SpotifyEndpoint;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistStore;
import com.example.dmsmith.transferwisecodechallenge.store.PlaylistTrackStore;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;

import javax.inject.Inject;

public class SpotifyService implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    private static final String SPOTIFY_SERVICE = "SpotifyService";
    private static final String CLIENT_ID = "e8da04ad2f454866956c8f249b11f5fe";
    private static final String REDIRECT_URI =
            "transferwise-code-challenge-spotify-login://callback";
    private static final String SPOTIFY_BASE_URL = "https://api.spotify.com";
    private static final int REQUEST_CODE = 1337;

    // Account Manager Constants
    private static final String ACCOUNT_NAME = SPOTIFY_SERVICE;
    private static final String ACCOUNT_TYPE = "Test";

    // Dagger Injected Fields
    private final AccountManager mAccountManager;
    private final RestInvoker mRestInvoker;

    private String mAuthToken;
    private Player mPlayer;

    @Inject
    public SpotifyService(AccountManager accountManager, RestInvoker restInvoker) {
        this.mAccountManager = accountManager;
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
                        new SpotifyPlayer.InitializationObserver() {
                            @Override
                            public void onInitialized(SpotifyPlayer spotifyPlayer) {
                                mPlayer = spotifyPlayer;
                                mPlayer.addNotificationCallback(SpotifyService.this);
                                mPlayer.addConnectionStateCallback(SpotifyService.this);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Log.e(SPOTIFY_SERVICE,
                                        "Could not initialize player: " + throwable.getMessage());
                            }
                        });
                PlaylistStore.getInstance()
                        .setPlaylists(Arrays.asList(getPlaylists().getPlaylists()));
            }
        }
    }

    private Playlists getPlaylists() {
        SpotifyEndpoint getPlaylistsEndpoint = Endpoints.GET_PLAYLIST.getEndpoint();
        try {
            String url = SPOTIFY_BASE_URL + getPlaylistsEndpoint.getEndpoint();
            HttpHeaders headers = createHeaders();
            HttpMethod method = getPlaylistsEndpoint.getHttpMethod();
            Playlists playlists = mRestInvoker.invokeService(url, method, null, headers, Playlists.class);
            PlaylistStore.getInstance().setPlaylists(Arrays.asList(playlists.getPlaylists()));
            return playlists;
        } catch (Exception e) {
            Log.e(SPOTIFY_SERVICE, "Exception thrown on GET PLAYLISTS; RETURNING NULL", e);
            return null;
        }
    }

    public PlaylistTracks getTracksForPlaylist(String href) {
        SpotifyEndpoint getTracksEndpoint = Endpoints.GET_TRACKS.getEndpoint();
        try {
            String url = href;
            HttpHeaders headers = createHeaders();
            HttpMethod method = getTracksEndpoint.getHttpMethod();
            PlaylistTracks playlistTracks = mRestInvoker.invokeService(url, method, null, headers, PlaylistTracks.class);
            PlaylistTrackStore.getInstance().setPlaylistTracks(Arrays.asList(playlistTracks.getPlaylistTracks()));
            return playlistTracks;
        } catch (Exception e) {
            Log.e(SPOTIFY_SERVICE, "Exception thrown on GET TRACKS; RETURNING NULL", e);
            return null;
        }
    }

    // Callback Interface Methods
    @Override
    public void onLoggedIn() {
        Log.d(SPOTIFY_SERVICE, "User logged in");

        mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
    }

    @Override
    public void onLoggedOut() {
        Log.d(SPOTIFY_SERVICE, "User logged out");
        mAccountManager.invalidateAuthToken(ACCOUNT_TYPE, mAuthToken);
    }

    @Override
    public void onLoginFailed(Error error) {
        Log.d(SPOTIFY_SERVICE, "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d(SPOTIFY_SERVICE, "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String s) {
        Log.d(SPOTIFY_SERVICE, "Received connection message: " + s);
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d(SPOTIFY_SERVICE, "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d(SPOTIFY_SERVICE, "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }
}
