package com.telus.serviceactivation.activation.dto;

import com.telus.serviceactivation.activation.entity.Feature;
import com.telus.serviceactivation.activation.entity.RelatedParty;
import com.telus.serviceactivation.activation.entity.ServiceCharacteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceActivationResponseDto {

    private Long id;
    private String category;
    private String description;
    private String serviceType;
    private String state;
    private List<RelatedParty> relatedParties;
    private List<ServiceCharacteristic> serviceCharacteristics;
    private List<Feature> featuresRequests;




}
