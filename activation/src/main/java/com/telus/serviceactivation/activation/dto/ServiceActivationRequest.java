package com.telus.serviceactivation.activation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ServiceActivationRequest {

    @NotNull(message = "Category is required")
    @Size(min = 1, max = 100, message = "Service type must be between 1 and 100 characters")
    private String category;

    @NotNull(message = "Description type is required")
    @Size(min = 1, max = 100, message = "Service type must be between 1 and 100 characters")
    private String description;

    @NotNull(message = "Service type is required")
    @Size(min = 1, max = 100, message = "Service type must be between 1 and 100 characters")
    private String serviceType;

    @NotNull(message = "State is required")
    private String state;


    private List<Feature> feature;
    private List<RelatedParty> relatedParty;
    private List<ServiceCharacteristic> serviceCharacteristic;

    @Data
    public static class Feature {
        private String name;
        private List<FeatureCharacteristic> featureCharacteristic;
        private String type;
    }

    @Data
    public static class FeatureCharacteristic {
        private String name;
        private String valueType;
        private List<Offer> valueContent;
    }

    @Data
    public static class Offer {
        private String offerId;
        private String effectiveDate;
        private String expiryDate;
        private String offerCd;
        private String offerSequenceNumber;
        private String offerTypeCd;
        private List<OfferParameter> offerParameterList;
    }

    @Data
    public static class OfferParameter {
        private String parameterCd;
        private String parameterName;
        private String parameterEffectiveDate;
        private String parameterExpiryDate;
        private String parameterValueTx;
    }

    @Data
    public static class RelatedParty {
        private String id;
        private String role;
        private String referredType;
    }

    @Data
    public static class ServiceCharacteristic {
        private String name;
        private String valueType;
        private String valueContent;
    }
}
