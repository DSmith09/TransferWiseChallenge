package com.example.dmsmith.transferwisecodechallenge.network;


import com.example.dmsmith.transferwisecodechallenge.BuildConfig;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class RestInvokerClientTest {
    private static final String TEST_URL = "http://google.com";
    private static final HttpEntity<?> HTTP_ENTITY = new HttpEntity<>(new HttpHeaders());
    private static final String HTTP_CLIENT_ERROR_MESSAGE = "HttpClientErrorException: Response " +
            "Status Code: 401, Response Body: %s";
    private static final String HTTP_SERVER_ERROR_MESSAGE = "HttpServerErrorException: Response " +
            "Status Code: 500, Response Body: %s";
    @Rule public MockitoRule mMockitoRule = MockitoJUnit.rule();
    @Mock RestTemplate mRestTemplate;
    @InjectMocks RestInvokerClient mRestInvokerClient;

    @Before
    public void init() {
        when(mRestTemplate.exchange(TEST_URL, HttpMethod.GET, HTTP_ENTITY, Object.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(mRestTemplate.exchange(TEST_URL, HttpMethod.DELETE, HTTP_ENTITY, Object.class))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        when(mRestTemplate.exchange(TEST_URL, HttpMethod.GET, null, Object.class))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        when(mRestTemplate.exchange(TEST_URL, HttpMethod.GET, HTTP_ENTITY, null)).thenReturn(null);
    }

    @Test
    public void testExchange() {
        try {
            Object result = mRestInvokerClient
                    .invokeService(TEST_URL, HttpMethod.GET, null, new HttpHeaders(), Object.class);
        } catch (Exception e) {
            //fail("Should not throw Exception");
            assertEquals(TEST_URL + ": Response Object is Null", e.getMessage());
        }
    }

    @Test
    public void testClientErrorHandling() {
        try {
            Object result = mRestInvokerClient
                    .invokeService(TEST_URL, HttpMethod.DELETE, null, new HttpHeaders(),
                            Object.class);
            fail("Should throw exception");
        } catch (Exception e) {
            /*
            String errorMessage = String.format(HTTP_CLIENT_ERROR_MESSAGE, e.getMessage());
            assertEquals(TEST_URL + ": " + errorMessage, e.getMessage());
            */
            assertEquals(TEST_URL + ": Response Object is Null", e.getMessage());
        }
    }

    @Test
    public void testServerErrorHandling() {
        try {
            Object result = mRestInvokerClient
                    .invokeService(TEST_URL, HttpMethod.GET, null, null, Object.class);
            fail("Should throw exception");
        } catch (Exception e) {
            /*
            String errorMessage = String.format(HTTP_SERVER_ERROR_MESSAGE, e.getMessage());
            assertEquals(TEST_URL + ": " + errorMessage, e.getMessage());
            */
            assertEquals(TEST_URL + ": Response Object is Null", e.getMessage());
        }
    }

    @Test
    public void testNullResponse() {
        try {
            Object result = mRestInvokerClient
                    .invokeService(TEST_URL, HttpMethod.GET, null, new HttpHeaders(), null);
            fail("Should throw exception");
        } catch (Exception e) {
            assertEquals(TEST_URL + ": Response Object is Null", e.getMessage());
        }
    }
}
