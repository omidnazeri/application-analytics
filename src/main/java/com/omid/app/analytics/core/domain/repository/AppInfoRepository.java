package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.AppInfo;
import org.springframework.data.repository.CrudRepository;

public interface AppInfoRepository extends CrudRepository<AppInfo, String> {
    AppInfo getByAppToken(String appToken);
    AppInfo getByServiceKey(String serviceKey);
}