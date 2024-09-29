/*
package com.telus.serviceactivation.activation.controller;

import com.telus.serviceactivation.activation.dto.ServiceActivationResponseDto;
import com.telus.serviceactivation.activation.dto.ServiceRequestDto;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import com.telus.serviceactivation.activation.exception.DataAlreadyExistsException;
import com.telus.serviceactivation.activation.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/tmf640/serviceactivation/v3")
@Validated
public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @PostMapping("/service")
    @Operation(summary = "Create a new connection",
            description = "Activation of new user connection with the provided details")
    public ResponseEntity<ServiceActivationResponseDto> createServiceActivation(@Valid @RequestBody ServiceRequestDto serviceActivation) {

        log.info("Received request: {}", serviceActivation);

        // Debugging related parties
        serviceActivation.getRelatedParties().forEach(p -> {
            log.info("Related Party: ID = {}, Role = {}, ReferredType = {}", p.getId(), p.getRole(), p.getReferredType());
        });

        try {
            ServiceActivationResponseDto savedServiceActivation = serviceService.createServiceActivation(serviceActivation);
            return new ResponseEntity<>(savedServiceActivation, HttpStatus.CREATED);
        } catch (DataAlreadyExistsException e) {
            log.error("Exception occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error occurred: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/service/id/{id}/activityCd/{valueContent}")
    public ResponseEntity<ServiceActivation> updateService(
            @PathVariable Long id,
            @PathVariable String valueContent,
            @RequestBody ServiceActivation serviceActivation) {

        // Calling the service to handle the update or create logic
        ServiceActivation updatedService = serviceService.updateService(id, valueContent, serviceActivation);

        // Return the response
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }
}


*/
