package com.telus.serviceactivation.activation.entity;

import com.telus.serviceactivation.activation.enums.ActivityCode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tmfsac_api_transaction")
public class TMFTransaction {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "external_id", length = 512)
    private String externalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_code", length = 255)
    private ActivityCode activityCode;

    @Column(name = "status_cd")
    private String statusCd = "PENDING"; // Default to PENDING

    @Column(name = "txn_seq_no")
    private String txnSeqNo;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "txn_effective_ts")
    private String txnEffectiveTs;

    @Column(name = "txn_request_id")
    private String txnRequestId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "billing_account_num")
    private String billingAccountNum;

    @Column(name = "request_time")
    private LocalDate requestTime;

    @Column(name = "response_time")
    private LocalDate responseTime;

    @Column(name = "api_request", columnDefinition = "TEXT")
    private String apiRequest;

    @Column(name = "api_response", columnDefinition = "TEXT")
    private String apiResponse;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "retry_count")
    private Integer retryCount = 3; // Default to 3

    @Column(name = "last_retry_at")
    private LocalDate lastRetryAt;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ApiJobs jobs;
}
