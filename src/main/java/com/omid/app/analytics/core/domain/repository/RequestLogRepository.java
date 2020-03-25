package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.RequestLog;
import org.springframework.data.repository.CrudRepository;

public interface RequestLogRepository extends CrudRepository<RequestLog, String> {
}