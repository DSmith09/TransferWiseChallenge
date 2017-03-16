package com.example.dmsmith.transferwisecodechallenge.network;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.springframework.http.HttpMethod.GET;

public class MockRestInvokerClient implements RestInvoker {

    @Override
    public <T> T invokeService(String url, HttpMethod httpMethod, Object request,
            HttpHeaders httpHeaders, Class<T> responseClass) throws Exception {
        if (url == null) {
            throw new Exception("Missing url");
        }
        switch (httpMethod) {
            case GET:
                return responseClass.newInstance();
            // TODO: Add in extra cases if extended
            default:
                throw new Exception("Unexpected HTTP Method Returned");
        }
    }
}
