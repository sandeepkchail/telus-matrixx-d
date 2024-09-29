package com.telus.serviceactivation.activation.entity;

import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_characteristic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCharacteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value_content")
    private String valueContent;

    @Column(name = "value_type")
    private String valueType;

    @ManyToOne
    @JoinColumn(name = "service_activation_id")
    private ServiceActivation serviceActivation;
}

