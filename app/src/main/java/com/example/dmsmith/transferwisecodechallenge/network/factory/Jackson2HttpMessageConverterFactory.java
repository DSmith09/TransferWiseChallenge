package com.example.dmsmith.transferwisecodechallenge.network.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class Jackson2HttpMessageConverterFactory {

    private Jackson2HttpMessageConverterFactory() {
        throw new IllegalStateException();
    }

    public static MappingJackson2HttpMessageConverter createInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        return converter;
    }
}
