package com.telus.serviceactivation.activation.entity;

import com.telus.serviceactivation.activation.enums.ActivityCode;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tmfsac_api_jobs")
public class ApiJobs {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transactionId", nullable = false)
    private TMFTransaction transaction;

    @Column(name = "external_id")
    private String externalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_code")
    private ActivityCode activityCode;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "max_retries")
    private Integer maxRetries;

    @Column(name = "dependent_job_id")
    private Integer dependentJobId;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
