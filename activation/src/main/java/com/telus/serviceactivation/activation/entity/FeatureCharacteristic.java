package com.telus.serviceactivation.activation.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "feature_characteristic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeatureCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value_type")
    private String valueType;


    //private List<valueContent> valueContent;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

}

