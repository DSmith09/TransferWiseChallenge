package com.example.dmsmith.transferwisecodechallenge.fake;

import com.example.dmsmith.transferwisecodechallenge.network.MockRestInvokerClient;
import com.example.dmsmith.transferwisecodechallenge.spotify.service.SpotifyService;


public class FakeSpotifyService extends SpotifyService {

    public FakeSpotifyService(
            MockRestInvokerClient restInvoker) {
        super(restInvoker);
    }
}
