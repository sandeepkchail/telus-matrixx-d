package com.telus.serviceactivation.activation.entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "offer_parameter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String parameterCd;
    private String parameterName;
    private String parameterEffectiveDate;
    private String parameterExpiryDate;
    private String parameterValueTx;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

}
