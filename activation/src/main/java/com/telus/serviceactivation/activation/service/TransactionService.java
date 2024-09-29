package com.telus.serviceactivation.activation.service;

import com.telus.serviceactivation.activation.dto.ServiceActivationRequest;
import com.telus.serviceactivation.activation.entity.TMFTransaction;
import com.telus.serviceactivation.activation.enums.ActivityCode;
import com.telus.serviceactivation.activation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {


    private final RestTemplate restTemplate;

    @Autowired
    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private TransactionRepository transactionRepository;



    public TMFTransaction processRequest(ServiceActivationRequest request) {
        TMFTransaction transaction = new TMFTransaction();

        // Mapping fields from the request to the transaction entity
        transaction.setExternalId(request.getServiceCharacteristic().stream()
                .filter(sc -> "externalId".equals(sc.getName()))
                .map(ServiceActivationRequest.ServiceCharacteristic::getValueContent)
                .findFirst().orElse(null));

        transaction.setTxnSeqNo(request.getServiceCharacteristic().stream()
                .filter(sc -> "transactionSequenceNumber".equals(sc.getName()))
                .map(ServiceActivationRequest.ServiceCharacteristic::getValueContent)
                .findFirst().orElse(null));

        transaction.setBillingAccountNum(request.getServiceCharacteristic().stream()
                .filter(sc -> "billingAccountNumber".equals(sc.getName()))
                .map(ServiceActivationRequest.ServiceCharacteristic::getValueContent)
                .findFirst().orElse(null));

        transaction.setActivityCode(ActivityCode.valueOf(request.getServiceCharacteristic().stream()
                .filter(sc -> "activityCd".equals(sc.getName()))
                .map(ServiceActivationRequest.ServiceCharacteristic::getValueContent)
                .findFirst().orElse("NAC"))); // Default to NAC if not found

        transaction.setRequestTime(LocalDate.now()); // Setting the request time
        transaction.setApiRequest(request.toString()); // Saving the request payload as a string

        // Save to the database
        return transactionRepository.save(transaction);
    }

    public String callBillingMatrixx(String mode) {
        if ("async".equalsIgnoreCase(mode)) {
            // Triggering asynchronous call
            return triggerAsyncCall();
        } else {
            // Synchronous call with retry mechanism
            return triggerSyncCallWithRetry();
        }
    }

    private String triggerAsyncCall() {
        CompletableFuture.runAsync(() -> {
            String url = "http://localhost:8086//matrixx/billing";
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("Async response: " + response);
        });
        return "Async call initiated";
    }

    private String triggerSyncCallWithRetry() {
        String url = "http://localhost:8086//matrixx/billing";
        int attempts = 0;
        int maxAttempts = 5;
        long waitDuration = 500;

        while (attempts < maxAttempts) {
            try {
                String response = restTemplate.getForObject(url, String.class);
                return response;
            } catch (Exception e) {
                attempts++;
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Max retry attempts reached", e);
                }
                try {
                    Thread.sleep(waitDuration); // Waiting before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted", ie);
                }
            }
        }
        return null;
    }

}
