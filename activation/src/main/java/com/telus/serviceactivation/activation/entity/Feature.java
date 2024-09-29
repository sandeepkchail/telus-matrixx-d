package com.telus.serviceactivation.activation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "feature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "feature")
    private List<FeatureCharacteristic> featureCharacteristic;

    @ManyToOne
    @JoinColumn(name = "service_activation_id")
    private ServiceActivation serviceActivation;

    @JsonProperty("@type")
    @Column(name = "type")
    private String type;
}
