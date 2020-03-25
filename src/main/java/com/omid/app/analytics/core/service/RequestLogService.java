package com.omid.app.analytics.core.service;

import com.omid.app.analytics.core.domain.entity.AppInfo;
import com.omid.app.analytics.core.domain.entity.RequestLog;
import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.domain.repository.RequestLogRepository;
import com.omid.app.analytics.core.exception.AppNotFoundException;
import com.omid.app.analytics.core.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestLogService {
    private final RequestLogRepository requestLogRepository;

    private final AppService appService;

    private final UserService userService;

    public RequestLogService(RequestLogRepository requestLogRepository,
                             AppService appService,
                             UserService userService) {
        this.requestLogRepository = requestLogRepository;
        this.appService = appService;
        this.userService = userService;
    }

    public void addRequestLog(String token, String userId,
                              String ipAddress, String pageURI,
                              String userAgent, String result) throws UserNotFoundException, AppNotFoundException {
        User user = userService.getUser(userId);
        AppInfo appInfo = appService.getApplication(token);

        addRequestLog(appInfo, user, ipAddress, pageURI, userAgent, result);
    }

    public void addRequestLog(String token, User user,
                              String ipAddress, String pageURI,
                              String userAgent, String result) throws AppNotFoundException {
        AppInfo appInfo = appService.getApplication(token);

        addRequestLog(appInfo, user, ipAddress, pageURI, userAgent, result);
    }

    public void addRequestLog(AppInfo appInfo, User user,
                              String ipAddress, String pageURI,
                              String userAgent, String result) {
        RequestLog requestLog = new RequestLog();

        requestLog.setDate(new Date());
        requestLog.setApp(appInfo);
        requestLog.setUser(user);
        requestLog.setIpAddress(ipAddress);
        requestLog.setPageURI(pageURI);
        requestLog.setUserAgent(userAgent);
        requestLog.setResult(result);

        requestLogRepository.save(requestLog);
    }

}
