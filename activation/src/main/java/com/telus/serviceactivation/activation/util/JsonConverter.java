package com.telus.serviceactivation.activation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> String convertToJson(T object) {
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        return jsonString;
    }
}