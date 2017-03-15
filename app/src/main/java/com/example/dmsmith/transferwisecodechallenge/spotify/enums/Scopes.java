package com.example.dmsmith.transferwisecodechallenge.spotify.enums;

// SCOPES enum - Scopes Provide Permissions to Access Certain User Data
public enum Scopes {
    USER_READ_PRIVATE("user-read-private"),
    PLAYLIST_READ_PRIVATE("playlist-read-private"),
    PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
    STREAMING("streaming");

    private final String value;
    Scopes(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
