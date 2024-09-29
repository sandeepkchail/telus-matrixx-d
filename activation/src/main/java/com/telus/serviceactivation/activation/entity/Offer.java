package com.telus.serviceactivation.activation.entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "offer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerId;
    private String effectiveDate;
    private String expiryDate;
    private String offerCd;
    private String offerSequenceNumber;
    private String offerTypeCd;
    private String name;
    private String valueType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "offer")
    private List<OfferParameter> offerParameters;

    @ManyToOne
    @JoinColumn(name = "feature_characteristic_id")
    private FeatureCharacteristic featureCharacteristic;

}

