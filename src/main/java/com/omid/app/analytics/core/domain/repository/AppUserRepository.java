package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.AppInfo;
import com.omid.app.analytics.core.domain.entity.AppUser;
import com.omid.app.analytics.core.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, String> {
    AppUser getByAppAndUser(AppInfo appInfo, User user);
}