package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.domain.entity.UserPackage;
import org.springframework.data.repository.CrudRepository;

public interface UserPackageRepository extends CrudRepository<UserPackage, String> {
    UserPackage findByUserAndPackageName(User user, String packageName);
}