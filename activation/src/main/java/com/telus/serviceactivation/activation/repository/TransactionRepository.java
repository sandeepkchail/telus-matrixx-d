package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.dto.ServiceCharacteristicRequestDto;
import com.telus.serviceactivation.activation.entity.ServiceActivation;
import com.telus.serviceactivation.activation.entity.TMFTransaction;
import com.telus.serviceactivation.activation.enums.ActivityCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TMFTransaction, Long> {

    /*Optional<ServiceActivation> findByExternalIdAndTransactionSequenceNumberAndBillingAccountNumberAndActivityCd(
            String externalId, String transactionSequenceNumber, String billingAccountNumber, String activityCd);*/
}