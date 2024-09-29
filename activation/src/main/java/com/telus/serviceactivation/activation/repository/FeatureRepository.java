package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.Feature;
import com.telus.serviceactivation.activation.entity.RelatedParty;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    Optional<Feature> findByNameAndServiceActivation(String name, ServiceActivation serviceActivation);

}