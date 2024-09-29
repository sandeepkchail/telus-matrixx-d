package com.telus.serviceactivation.activation.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telus.serviceactivation.activation.dto.FeaturesRequestDto;
import com.telus.serviceactivation.activation.entity.FeatureCharacteristic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FeatureDeserializer extends JsonDeserializer<FeaturesRequestDto> {
    @Override
    public FeaturesRequestDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        // Extract fields
        String name = node.has("name") ? node.get("name").asText() : null;
        String type = node.has("@type") ? node.get("@type").asText() : null;

        // Deserialize feature characteristics
        List<FeatureCharacteristic> featureCharacteristics = new ArrayList<>();
        JsonNode featureCharacteristicsNode = node.has("featureCharacteristic")
                ? node.get("featureCharacteristic") : null;
        if (featureCharacteristicsNode != null && featureCharacteristicsNode.isArray()) {
            for (JsonNode fcNode : featureCharacteristicsNode) {
                FeatureCharacteristic featureCharacteristic = mapper.treeToValue(fcNode, FeatureCharacteristic.class);
                featureCharacteristics.add(featureCharacteristic);
            }
        }

        return FeaturesRequestDto.builder()
                .name(name)
                .type(type)
                .featureCharacteristic(featureCharacteristics)
                .build();
    }
}
