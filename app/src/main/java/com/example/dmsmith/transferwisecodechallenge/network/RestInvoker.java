package com.example.dmsmith.transferwisecodechallenge.network;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public interface RestInvoker {
    <T> T invokeService(String url, HttpMethod httpMethod, Object request, HttpHeaders httpHeaders, Class<T> responseClass) throws Exception;
}
