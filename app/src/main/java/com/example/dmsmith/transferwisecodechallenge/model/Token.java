package com.example.dmsmith.transferwisecodechallenge.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    private String mAccessToken;
    private String mTokenType;
    private String mScope;
    private int mDuration;
    private String mRefreshToken;

    public Token() {}

    public Token(String accessToken, String tokenType, String scope, int duration,
            String refreshToken) {
        mAccessToken = accessToken;
        mTokenType = tokenType;
        mScope = scope;
        mDuration = duration;
        mRefreshToken = refreshToken;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    @JsonProperty("scope")
    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }

    @JsonProperty("expires_in")
    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }
}
