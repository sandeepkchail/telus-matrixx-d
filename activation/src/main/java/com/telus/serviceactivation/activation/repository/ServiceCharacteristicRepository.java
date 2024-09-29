package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.ServiceActivation;
import com.telus.serviceactivation.activation.entity.ServiceCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceCharacteristicRepository extends JpaRepository<ServiceCharacteristic, Long> {
    Optional<ServiceCharacteristic> findByNameAndServiceActivation(String name, ServiceActivation serviceActivation);

    List<ServiceCharacteristic> findByServiceActivation(ServiceActivation serviceActivation);

    //List<ServiceCharacteristic> findByValueContent(String valueContent);

    @Query("SELECT sc FROM ServiceCharacteristic sc WHERE sc.valueContent = :valueContent")
    List<ServiceCharacteristic> findByValueContent(@Param("valueContent") String valueContent);

}