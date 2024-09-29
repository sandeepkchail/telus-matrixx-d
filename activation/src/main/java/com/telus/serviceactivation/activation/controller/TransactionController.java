package com.telus.serviceactivation.activation.controller;

import com.telus.serviceactivation.activation.dto.ServiceActivationRequest;
import com.telus.serviceactivation.activation.dto.ServiceActivationResponseDto;
import com.telus.serviceactivation.activation.dto.ServiceRequestDto;
import com.telus.serviceactivation.activation.entity.TMFTransaction;
import com.telus.serviceactivation.activation.exception.DataAlreadyExistsException;
import com.telus.serviceactivation.activation.handler.TransactionHandler;
import com.telus.serviceactivation.activation.service.ServiceService;
import com.telus.serviceactivation.activation.service.TransactionService;
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
@RequestMapping("/tmf640/transaction/v3")
@Validated
public class TransactionController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    TransactionHandler transactionHandler;

  /*  @PostMapping("/service")
    public ResponseEntity<String> activateService(@Valid @RequestBody ServiceActivationRequest request) {
        TMFTransaction transaction = transactionHandler.processRequest(request);
        return ResponseEntity.ok("Transaction created with ID: " + transaction.getTransactionId());
    }*/


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
            //TMFTransaction transaction = transactionHandler.processRequest(request);

            return new ResponseEntity<>(savedServiceActivation, HttpStatus.CREATED);
        } catch (DataAlreadyExistsException e) {
            log.error("Exception occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error occurred: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
