package com.omid.app.analytics.core.service;

import com.omid.app.analytics.core.domain.entity.AppInfo;
import com.omid.app.analytics.core.domain.entity.AppUser;
import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.domain.entity.UserTag;
import com.omid.app.analytics.core.domain.repository.AppInfoRepository;
import com.omid.app.analytics.core.domain.repository.AppUserRepository;
import com.omid.app.analytics.core.domain.repository.UserTagRepository;
import com.omid.app.analytics.core.exception.AppNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppService {
    private final AppInfoRepository appInfoRepository;

    private final AppUserRepository appUserRepository;

    private final UserTagRepository userTagRepository;

    public AppService(AppInfoRepository appInfoRepository,
                      AppUserRepository appUserRepository,
                      UserTagRepository userTagRepository) {
        this.appInfoRepository = appInfoRepository;
        this.appUserRepository = appUserRepository;
        this.userTagRepository = userTagRepository;
    }

    public AppInfo addApplication(String packageName, String appToken, String serviceKey) {
        AppInfo appInfo = appInfoRepository.getByAppToken(appToken);

        if (appInfo != null) {
            throw new DuplicateKeyException(String.format("application %s already exist!!!", appToken));
        }

        appInfo = new AppInfo();
        appInfo.setPackageName(packageName);
        appInfo.setAppToken(appToken);
        appInfo.setServiceKey(serviceKey);

        appInfoRepository.save(appInfo);

        return appInfo;
    }

    public AppInfo getApplication(String token) throws AppNotFoundException {
        AppInfo appInfo = appInfoRepository.getByAppToken(token);
        if (appInfo == null) {
            throw new AppNotFoundException();
        }

        return appInfo;
    }

    public AppInfo getByServiceKey(String serviceKey) throws AppNotFoundException {
        AppInfo appInfo = appInfoRepository.getByServiceKey(serviceKey);
        if (appInfo == null)
            throw new AppNotFoundException();

        return appInfo;
    }

    public void addUser(String token, User user) throws AppNotFoundException {
        AppInfo appInfo = getApplication(token);

        AppUser appUser = appUserRepository.getByAppAndUser(appInfo, user);

        if (appUser == null) {
            appUser = new AppUser();
            appUser.setDate(new Date());
            appUser.setApp(appInfo);
            appUser.setUser(user);

            appUserRepository.save(appUser);
        }

        return;
    }

    public void addUserTag(String token, User user, String tagName, String tagValue) throws AppNotFoundException {
        AppInfo appInfo = getApplication(token);

        UserTag userTag = userTagRepository.getByAppAndUserAndName(appInfo, user, tagName);

        if (userTag == null) {
            userTag = new UserTag();
            userTag.setApp(appInfo);
            userTag.setUser(user);
            userTag.setName(tagName);
        }

        if (userTag.getValue() == null ||
                !userTag.getValue().equals(tagValue)) {
            userTag.setDate(new Date());
            userTag.setValue(tagValue);

            userTagRepository.save(userTag);
        }

        return;
    }

    public List<AppInfo> getAll() {
        return (List<AppInfo>) appInfoRepository.findAll();
    }


}
