package com.telus.serviceactivation.activation.request.data;

import com.telus.serviceactivation.activation.entity.Feature;
import com.telus.serviceactivation.activation.entity.RelatedParty;
//import com.telus.serviceactivation.activation.entity.ServiceCharacteristic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceActivationData {

    private String category;
    private String description;
    private String serviceType;
    private String state;
    /*private List<Feature> features;
    private List<RelatedParty> relatedParties;
    private List<ServiceCharacteristic> serviceCharacteristics;*/

}

