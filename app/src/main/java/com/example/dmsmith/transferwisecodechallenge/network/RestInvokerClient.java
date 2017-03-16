package com.example.dmsmith.transferwisecodechallenge.network;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dmsmith.transferwisecodechallenge.network.factory
        .Jackson2HttpMessageConverterFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

public class RestInvokerClient implements RestInvoker {
    private static final String REST_INVOKER_SERVICE = "RestInvokerClient";
    private static final MappingJackson2HttpMessageConverter
            MAPPING_JACKSON_2_HTTP_MESSAGE_CONVERTER = Jackson2HttpMessageConverterFactory
            .createInstance();

    // Dagger Injected Fields
    private final RestTemplate mRestTemplate;

    @Inject
    public RestInvokerClient(RestTemplate restTemplate) {
        this.mRestTemplate = restTemplate;
    }

    @Override
    public <T> T invokeService(String url, HttpMethod httpMethod, Object request,
            HttpHeaders httpHeaders, Class<T> responseClass) throws Exception {
        final HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
        setMessageConverters();
        AsyncTask<Void, Void, AsyncTaskResult<T>> restCallTask = createRestTask(url, httpMethod,
                httpEntity,
                responseClass);
        AsyncTaskResult<T> taskResult = restCallTask.execute().get();
        if (taskResult.exception != null) {
            throw new Exception(taskResult.exception.getMessage());
        }
        return taskResult.result;
    }

    private void setMessageConverters() {
        mRestTemplate.getMessageConverters().add(MAPPING_JACKSON_2_HTTP_MESSAGE_CONVERTER);
        mRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        mRestTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    }

    private <T> AsyncTask<Void, Void, AsyncTaskResult<T>> createRestTask(final String url,
            final HttpMethod httpMethod, final HttpEntity<?> httpEntity,
            final Class<T> responseClass) {
        return new AsyncTask<Void, Void, AsyncTaskResult<T>>() {

            @Override
            protected AsyncTaskResult<T> doInBackground(Void... params) {
                try {
                    String logMessage = String
                            .format("Invoking Service; url: %s, Method: %s", url, httpMethod);
                    Log.d(REST_INVOKER_SERVICE, logMessage);
                    ResponseEntity<T> responseEntity = mRestTemplate
                            .exchange(url, httpMethod, httpEntity, responseClass);
                    if (responseEntity == null) {
                        String errorMessage = String.format("%s: Response Object is Null", url);
                        Log.w(REST_INVOKER_SERVICE, errorMessage);
                        return new AsyncTaskResult<>(new Exception(errorMessage));
                    }
                    String responseMessage = String
                            .format("%s: Response Object: Response Headers: %s, Response Body: %s",
                                    url, responseEntity.getHeaders(), responseEntity.getBody());
                    Log.d(REST_INVOKER_SERVICE, responseMessage);
                    return new AsyncTaskResult<>(responseEntity.getBody());
                } catch (HttpClientErrorException e) {
                    String errorMessage = String
                            .format("HttpClientErrorException: Response Status Code: %s, Response" +
                                            " Body: %s",
                                    e.getStatusCode(), e.getResponseBodyAsString());
                    Log.e(REST_INVOKER_SERVICE, errorMessage);
                    return new AsyncTaskResult<>(new Exception(errorMessage));
                } catch (HttpServerErrorException e) {
                    String errorMessage = String
                            .format("HttpServerErrorException: Response Status Code: %s, Response" +
                                            " Body: %s",
                                    e.getStatusCode(), e.getResponseBodyAsString());
                    Log.e(REST_INVOKER_SERVICE, errorMessage);
                    return new AsyncTaskResult<>(new Exception(errorMessage));
                }
            }
        };
    }

    private class AsyncTaskResult<T> {
        @Nullable private final T result;
        @Nullable private final Exception exception;

        AsyncTaskResult(@NonNull T result) {
            super();
            this.result = result;
            this.exception = null;
        }

        AsyncTaskResult(@NonNull Exception exception) {
            super();
            this.exception = exception;
            this.result = null;
        }

        public T getResult() {
            return result;
        }

        public Exception getException() {
            return exception;
        }
    }
}
