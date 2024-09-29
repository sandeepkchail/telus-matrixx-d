package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.Feature;
import com.telus.serviceactivation.activation.entity.FeatureCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureCharacteristicRepository extends JpaRepository<FeatureCharacteristic, Long> {
    Optional<FeatureCharacteristic> findByNameAndFeature(String name, Feature feature);

}