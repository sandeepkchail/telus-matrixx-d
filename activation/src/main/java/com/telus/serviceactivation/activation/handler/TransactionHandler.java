package com.telus.serviceactivation.activation.handler;

import com.telus.serviceactivation.activation.dto.ServiceActivationRequest;
import com.telus.serviceactivation.activation.entity.TMFTransaction;
import com.telus.serviceactivation.activation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionHandler {

    @Autowired
    TransactionService transactionService;

    public TMFTransaction processRequest(ServiceActivationRequest request) {
        TMFTransaction transaction = transactionService.processRequest(request);

        transactionService.callBillingMatrixx("async");
        return transaction;
    }
}
