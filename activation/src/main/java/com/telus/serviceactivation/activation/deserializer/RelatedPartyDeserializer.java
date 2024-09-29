package com.telus.serviceactivation.activation.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.telus.serviceactivation.activation.dto.RelatedPartyRequestDto;

import java.io.IOException;

public class RelatedPartyDeserializer extends JsonDeserializer<RelatedPartyRequestDto> {

    @Override
    public RelatedPartyRequestDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        Long id = node.get("id").asLong();
        String role = node.get("role").asText();
        String referredType = node.get("@referredType").asText();
        RelatedPartyRequestDto dto = new RelatedPartyRequestDto();
        dto.setId(id);
        dto.setRole(role);
        dto.setReferredType(referredType);
        return dto;
    }
}
