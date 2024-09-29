package com.telus.serviceactivation.activation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telus.serviceactivation.activation.model.ServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequestDto {

    @NotNull(message = "Service type is required")
    @Size(min = 1, max = 100, message = "Service type must be between 1 and 100 characters")
    private String serviceType;

    @NotBlank(message = "Category is required")
    @Size(min = 1, max = 100, message = "Category must be between 1 and 100 characters")
    private String category;

    @NotNull(message = "State is required")
    private String state;

    @Size(min = 5, max = 30, message = "Description must be between 5 and 30 characters long")
    @NotNull(message = "Category is required")
    private String description;

  /*  @NotNull(message = "Related parties are required")
    private List<RelatedParty> relatedParties;
*/
    @NotNull(message = "Service characteristics are required")
    private List<ServiceCharacteristicRequestDto> serviceCharacteristic;

    @NotNull(message = "Features are required")
    private List<FeaturesRequestDto> feature;

    @JsonProperty("relatedParties")
    private List<RelatedPartyRequestDto> relatedParties;

  /*  @JsonProperty("serviceCharacteristic")
    private List<ServiceRequest.ServiceCharacteristic> serviceCharacteristics;
*/
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
