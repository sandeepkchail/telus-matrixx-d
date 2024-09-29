package com.telus.serviceactivation.activation.repository;

import com.telus.serviceactivation.activation.entity.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}