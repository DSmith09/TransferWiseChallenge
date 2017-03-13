package com.example.dmsmith.transferwisecodechallenge.spotify;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

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

public class SpotifyService implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    private static final String SPOTIFY_SERVICE = "SpotifyService";
    private static final String CLIENT_ID = "e8da04ad2f454866956c8f249b11f5fe";
    private static final String REDIRECT_URI = "transferwise-code-challenge-spotify-login://callback";

    private static final int REQUEST_CODE = 1337;

    // SCOPES enum - Scopes Provide Permissions to Access Certain User Data
    private enum Scopes {
        USER_READ_PRIVATE("user-read-private"),
        PLAYLIST_READ_PRIVATE("playlist-read-private"),
        PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
        STREAMING("streaming");

        private final String val;
        Scopes(String val) {
            this.val = val;
        }
    }

    private Player mPlayer;

    // Main Methods
    public void authenticate(Activity context) {
        AuthenticationRequest.Builder arBuilder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);
        AuthenticationRequest request = arBuilder.setScopes(new String[]{Scopes.USER_READ_PRIVATE.val, Scopes.PLAYLIST_READ_PRIVATE.val, Scopes.STREAMING.val}).build();
        AuthenticationClient.openLoginActivity(context, REQUEST_CODE, request);
    }

    public void handleCallback(final Activity context, int requestCode, int responseCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(responseCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config configPlayer = new Config(context, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(configPlayer, context, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addNotificationCallback(SpotifyService.this);
                        mPlayer.addConnectionStateCallback(SpotifyService.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(SPOTIFY_SERVICE, "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
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
