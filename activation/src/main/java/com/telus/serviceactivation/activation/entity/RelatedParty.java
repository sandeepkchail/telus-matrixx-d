package com.telus.serviceactivation.activation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "related_party")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelatedParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rp_id")
    private Long relatedPartyId;

    @Column(name = "id")
    private Long id;

    //private String idValue;
    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "service_activation_id", nullable = false)
    @JsonBackReference
    private ServiceActivation serviceActivation;

    @JsonProperty("@referredType")
    @Column(name = "referred_type")
    private String referredType;


}

