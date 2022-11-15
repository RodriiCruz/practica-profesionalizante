package com.practica.profesionalizante.negocio.util;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ExtractPayload {

    private String extractPayload(String token) {
        token = token.replace("Bearer ", "");
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        return new String(decoder.decode(chunks[1]));
    }

    public String extractUser(String token) throws JsonProcessingException {
        String payload = this.extractPayload(token);

        Map<String, Object> jsonMap = new ObjectMapper().readValue(payload, HashMap.class);

        return String.valueOf(jsonMap.getOrDefault("sub", null));
    }
}
