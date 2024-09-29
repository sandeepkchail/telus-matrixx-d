package com.telus.serviceactivation.activation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {

    private String category;
    private String description;
    private String serviceType;

    @JsonProperty("serviceCharacteristic")
    private List<ServiceCharacteristic> serviceCharacteristics;
    private String state;
    private String type;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServiceCharacteristic {
        private String name;
        private String valueType;
        private String value;

    }
}
