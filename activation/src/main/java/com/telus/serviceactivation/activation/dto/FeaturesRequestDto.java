package com.telus.serviceactivation.activation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.telus.serviceactivation.activation.deserializer.FeatureDeserializer;
import com.telus.serviceactivation.activation.deserializer.RelatedPartyDeserializer;
import com.telus.serviceactivation.activation.entity.FeatureCharacteristic;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize(using = FeatureDeserializer.class)
public class FeaturesRequestDto {
    //private Long id;
    private String name;
    private ServiceActivation serviceActivation;
    private String type;
    private List<FeatureCharacteristic> featureCharacteristic;

}
