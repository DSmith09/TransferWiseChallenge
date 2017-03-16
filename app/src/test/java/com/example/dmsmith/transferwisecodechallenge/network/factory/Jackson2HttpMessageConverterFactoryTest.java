package com.example.dmsmith.transferwisecodechallenge.network.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Jackson2HttpMessageConverterFactoryTest {

    @Test
    public void testInstance() {
        Object result = Jackson2HttpMessageConverterFactory.createInstance();
        MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter)
                result;
        ObjectMapper objectMapper = converter.getObjectMapper();
        assertNotNull(objectMapper);
        SerializationConfig config = objectMapper.getSerializationConfig();
        assertNotNull(config);
        assertTrue(config.getSerializationInclusion().equals(JsonInclude.Include.NON_NULL));
    }
}
