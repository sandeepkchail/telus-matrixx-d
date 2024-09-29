package com.telus.serviceactivation.activation.service;

import com.telus.serviceactivation.activation.dto.ServiceActivationRequest;
import com.telus.serviceactivation.activation.dto.ServiceActivationResponseDto;
import com.telus.serviceactivation.activation.dto.ServiceCharacteristicRequestDto;
import com.telus.serviceactivation.activation.dto.ServiceRequestDto;
import com.telus.serviceactivation.activation.entity.*;
import com.telus.serviceactivation.activation.enums.ActivityCode;
import com.telus.serviceactivation.activation.exception.DataAlreadyExistsException;
import com.telus.serviceactivation.activation.exception.ResourceNotFoundException;
import com.telus.serviceactivation.activation.exception.ValidationException;
import com.telus.serviceactivation.activation.repository.*;
import com.telus.serviceactivation.activation.util.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceService {

    @Autowired
    private ServiceActivationRepository serviceActivationRepository;

    @Autowired
    private RelatedPartyRepository relatedPartyRepository;

    @Autowired
    private ServiceCharacteristicRepository serviceCharacteristicRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureCharacteristicRepository featureCharacteristicRepository;

    @Autowired
    RelatedPartyService relatedPartyService;

    @Autowired
    private ApiLogRepository apiLogRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    //private final String basepath = "localhost:8084";
    //private final String patchUrl = String.format("http://%s/tmf640/serviceactivation/v3/service/id/activityCd//B23", basepath);


    @Transactional
    public ServiceActivationResponseDto createServiceActivation(ServiceRequestDto serviceActivationDto) {
        System.out.println("Inside createServiceActivation method : " + serviceActivationDto.getRelatedParties());

        // Validating the input DTO
        if (serviceActivationDto == null) {
            throw new ValidationException("Service activation request cannot be null");
        }

        TMFTransaction transaction = new TMFTransaction();

        // Mapping fields from the request to the transaction entity
        transaction.setExternalId(serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "externalId".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse(null));

        transaction.setTxnSeqNo(serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "transactionSequenceNumber".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse(null));

        transaction.setBillingAccountNum(serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "billingAccountNumber".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse(null));

        transaction.setActivityCode(ActivityCode.valueOf(serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "activityCd".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse("NAC"))); // Default to NAC if not found

       /* transaction.setServiceType((serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "activityCd".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse("NAC"))); // Default to NAC if not found*/

        transaction.setRequestTime(LocalDate.now()); // Setting the request time
        transaction.setApiRequest(serviceActivationDto.toString()); // Saving the request payload as a string

        ApiJobs apiJobs = new ApiJobs();
        //apiJobs.getJobId();
        apiJobs.setExternalId(serviceActivationDto.getServiceCharacteristic().stream()
                .filter(sc -> "externalId".equals(sc.getName()))
                .map(ServiceCharacteristicRequestDto::getValueContent)
                .findFirst().orElse(null));

        apiJobs.setUpdatedAt(LocalDate.now());
        apiJobs.setTransaction(transaction);
        transaction.setJobs(apiJobs);
        JsonConverter jsonConverter = new JsonConverter();
        //String responseStr = jsonConverter.convertToJson(responseDTO);
        String requestStr = jsonConverter.convertToJson(serviceActivationDto);
        transaction.setApiRequest(requestStr);
        transactionRepository.save(transaction);

        // Converting Entity to Response DTO
        ServiceActivationResponseDto responseDTO = new ServiceActivationResponseDto();
       /* responseDTO.setId(savedServiceActivation.getId());
        responseDTO.setServiceType(savedServiceActivation.getServiceType());
        responseDTO.setCategory(savedServiceActivation.getCategory());
        responseDTO.setState(savedServiceActivation.getState());
        responseDTO.setDescription(savedServiceActivation.getDescription());
        responseDTO.setRelatedParties(savedServiceActivation.getRelatedParties());
        responseDTO.setServiceCharacteristics(savedServiceActivation.getServiceCharacteristics());
        responseDTO.setFeaturesRequests(savedServiceActivation.getFeatures());*/



        //logApiRequestResponse(requestStr, responseStr);

        return responseDTO;
    }

    private ServiceActivationResponseDto createResponseWithMessage(String message, ServiceActivation serviceActivation) {
        ServiceActivationResponseDto responseDTO = new ServiceActivationResponseDto();
        //responseDTO.setId(serviceActivation.getId());
        responseDTO.setServiceType(serviceActivation.getServiceType());
        responseDTO.setCategory(serviceActivation.getCategory());
        responseDTO.setState(serviceActivation.getState());
        responseDTO.setDescription(serviceActivation.getDescription());
        responseDTO.setRelatedParties(serviceActivation.getRelatedParties());
        //responseDTO.setServiceCharacteristics(serviceActivation.getServiceCharacteristics());
        responseDTO.setFeaturesRequests(serviceActivation.getFeatures());
        return responseDTO;
    }

    @Transactional
    public ServiceActivationResponseDto updateService(String id, ServiceRequestDto serviceRequestDto) {
        // Validating input DTO
        if (serviceRequestDto == null) {
            throw new ValidationException("Service request cannot be null");
        }

        // Fetching existing service activation
        ServiceActivation existingServiceActivation = serviceActivationRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("ServiceActivation not found for ID: " + id));

        // updating service activation details
        existingServiceActivation.setCategory(serviceRequestDto.getCategory());
        existingServiceActivation.setDescription(serviceRequestDto.getDescription());
        existingServiceActivation.setServiceType(serviceRequestDto.getServiceType());
        existingServiceActivation.setState(serviceRequestDto.getState());

        // updating Related Parties
        if (serviceRequestDto.getRelatedParties() != null) {
            List<RelatedParty> relatedParties = serviceRequestDto.getRelatedParties().stream().map(dto -> {
                RelatedParty relatedParty = relatedPartyRepository.findById(dto.getId()).orElse(new RelatedParty());
                relatedParty.setId(dto.getId());
                relatedParty.setRole(dto.getRole());
                relatedParty.setReferredType(dto.getReferredType());
                // relatedParty.setServiceActivation(existingServiceActivation);
                return relatedParty;
            }).collect(Collectors.toList());

            // Saving or updating related parties
            relatedPartyRepository.saveAll(relatedParties);
            existingServiceActivation.setRelatedParties(relatedParties);
        }

        // Updating Service Characteristics
        if (serviceRequestDto.getServiceCharacteristic() != null) {
            List<ServiceCharacteristic> serviceCharacteristics = serviceRequestDto.getServiceCharacteristic().stream().map(dto -> {
                ServiceCharacteristic characteristic = serviceCharacteristicRepository.findByNameAndServiceActivation(dto.getName(), existingServiceActivation).orElse(new ServiceCharacteristic());
                characteristic.setName(dto.getName());
                characteristic.setValueContent(dto.getValueContent());
                characteristic.setValueType(dto.getValueType());
                characteristic.setServiceActivation(existingServiceActivation);
                return characteristic;
            }).collect(Collectors.toList());

            // Saving or updating service characteristics
            serviceCharacteristicRepository.saveAll(serviceCharacteristics);
            existingServiceActivation.setServiceCharacteristics(serviceCharacteristics);
        }

        // Updating Features
        if (serviceRequestDto.getFeature() != null) {
            List<Feature> features = serviceRequestDto.getFeature().stream().map(dto -> {
                Feature feature = featureRepository.findByNameAndServiceActivation(dto.getName(), existingServiceActivation).orElse(new Feature());
                feature.setName(dto.getName());
                feature.setServiceActivation(existingServiceActivation);

                // Updating Feature Characteristics
                List<FeatureCharacteristic> featureCharacteristics = dto.getFeatureCharacteristic().stream().map(fcDto -> {
                    FeatureCharacteristic featureCharacteristic = featureCharacteristicRepository.findByNameAndFeature(fcDto.getName(), feature).orElse(new FeatureCharacteristic());
                    featureCharacteristic.setName(fcDto.getName());
                    featureCharacteristic.setValueType(fcDto.getValueType());
                    featureCharacteristic.setFeature(feature);
                    return featureCharacteristic;
                }).collect(Collectors.toList());

                // Saving or updating feature characteristics
                featureCharacteristicRepository.saveAll(featureCharacteristics);
                feature.setFeatureCharacteristic(featureCharacteristics);
                return feature;
            }).collect(Collectors.toList());

            // Saving or updating features
            featureRepository.saveAll(features);
            existingServiceActivation.setFeatures(features);
        }

        // Saving updated service activation
        ServiceActivation updatedServiceActivation = serviceActivationRepository.save(existingServiceActivation);

        // Converting Entity to Response DTO
        ServiceActivationResponseDto responseDTO = createResponseWithMessage("Service updated successfully", updatedServiceActivation);

        return responseDTO;
    }

    @Transactional
    public ServiceActivation updateService(Long id, String valueContent, ServiceActivation serviceActivation) {
        ServiceActivation service = null;
        List<ServiceActivation> serviceActivations = serviceActivationRepository.findAll();

        Optional<RelatedParty> relatedParties = relatedPartyRepository.findById(id);

        // Checking if each ServiceActivation has valid RelatedParties and ServiceCharacteristics
        for (ServiceActivation servActivation : serviceActivations) {
            boolean relatedPartyExists = false;
            boolean serviceCharacteristicExists = false;

            List<RelatedParty> relParties = servActivation.getRelatedParties();
            relatedPartyExists = relParties.stream().anyMatch(relParty -> relParty.getId().equals(id));

            List<ServiceCharacteristic> servCharacteristics = servActivation.getServiceCharacteristics();
            serviceCharacteristicExists = servCharacteristics.stream().anyMatch(serviceCharacteristic -> Objects.equals(valueContent, serviceCharacteristic.getValueContent()));

            if (relatedPartyExists && serviceCharacteristicExists) {
                log.info("ServiceActivation ID " + servActivation.getId() + " has valid RelatedParties and ServiceCharacteristics.");
                serviceActivation.getRelatedParties().stream().forEach(serviceActivation::addRelatedParty);
                serviceActivation.getServiceCharacteristics().stream().forEach(serviceActivation::addServiceCharacteristic);
                service = serviceActivation;
            } else {
                log.info("ServiceActivation ID " + servActivation.getId() + " is missing RelatedParties or ServiceCharacteristics.");
                ServiceActivation newService = new ServiceActivation();
                //newService.setServiceCharacteristics(servCharacteristics);
                // newService.setRelatedParties(relParties);
                newService.getRelatedParties().stream().forEach(newService::addRelatedParty);
                newService.getServiceCharacteristics().stream().forEach(newService::addServiceCharacteristic);
                service = newService;
            }
        }
        return serviceActivationRepository.save(service);
    }

    private void logApiRequestResponse(String requestData, String responseData) {
        ApiLog log = new ApiLog();
        log.setRequestData(requestData);
        log.setResponseData(responseData);
        apiLogRepository.save(log);
    }
}
