package com.example.dmsmith.transferwisecodechallenge.spotify.model;


import org.springframework.http.HttpMethod;

public class SpotifyEndpoint {
    private final HttpMethod mHttpMethod;
    private final String mEndpoint;

    public SpotifyEndpoint(HttpMethod httpMethod, String endpoint) {
        mHttpMethod = httpMethod;
        mEndpoint = endpoint;
    }

    public HttpMethod getHttpMethod() {
        return mHttpMethod;
    }

    public String getEndpoint() {
        return mEndpoint;
    }
}
