package com.telus.serviceactivation.activation.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_activation")
public class ServiceActivation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String description;
    private String serviceType;
    private String state;


  /*  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @JsonManagedReference
    private List<RelatedParty> relatedParties;*/

    @OneToMany(mappedBy = "serviceActivation", cascade = CascadeType.MERGE, orphanRemoval = true)
    @JsonManagedReference
    private List<RelatedParty> relatedParties = new ArrayList<>();

    public void addRelatedParty(RelatedParty relatedParty) {
        relatedParties.add(relatedParty);
        relatedParty.setServiceActivation(this);
    }

    public void removeRelatedParty(RelatedParty relatedParty) {
        relatedParties.remove(relatedParty);
        relatedParty.setServiceActivation(null);
    }

    // Adding a ServiceCharacteristic
    public void addServiceCharacteristic(ServiceCharacteristic serviceCharacteristic) {
        serviceCharacteristics.add(serviceCharacteristic);
        serviceCharacteristic.setServiceActivation(this);
    }

    // Removing a ServiceCharacteristic
    public void removeServiceCharacteristic(ServiceCharacteristic serviceCharacteristic) {
        serviceCharacteristics.remove(serviceCharacteristic);
        serviceCharacteristic.setServiceActivation(null);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "serviceActivation")
    private List<ServiceCharacteristic> serviceCharacteristics;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "serviceActivation")
    private List<Feature> features;

}

