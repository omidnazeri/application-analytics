package com.omid.app.analytics.controller.api;

import com.omid.app.analytics.core.domain.dto.ErrorStatus;
import com.omid.app.analytics.core.domain.dto.Result;
import com.omid.app.analytics.core.domain.dto.user.UserInformation;
import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.exception.AppNotFoundException;
import com.omid.app.analytics.core.exception.UserNotFoundException;
import com.omid.app.analytics.core.service.AppService;
import com.omid.app.analytics.core.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    AppService appService;

    @RequestMapping(method = RequestMethod.POST, value = "get-user")
    public ResponseEntity<UserInformation> getUser(@RequestParam("token") String token,
                                                   @RequestParam("androidId") String androidId,
                                                   @RequestParam(value = "googleAdId", required = false) String googleAdId) {
        try {
            User user = userService.findAndCreateUser(androidId, googleAdId);

            appService.addUser(token, user);

            return ResponseEntity.ok(new UserInformation(user.getId()));
        } catch (AppNotFoundException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(new UserInformation(false, ErrorStatus.APPLICATION_NOT_FOUNDED.name()));
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "save-user-info")
    public ResponseEntity<UserInformation> saveUserInfo(@RequestParam("token") String token,
                                                        @RequestParam("userId") String userId,
                                                        @RequestParam("imei") String imei,
                                                        @RequestParam("imsi") String imsi,
                                                        @RequestParam("brand") String brand,
                                                        @RequestParam("model") String model,
                                                        @RequestParam("androidVersion") String androidVersion,
                                                        @RequestParam("simType") String simType,
                                                        @RequestParam("installedPackages") String installedPackages,
                                                        @RequestParam(value = "bluetoothAddress", required = false) String bluetoothAddress) {
        try {
            User user = userService.saveUserInfo(userId, imei, imsi, brand,
                    model, androidVersion, simType, installedPackages, bluetoothAddress);

            return ResponseEntity.ok(new UserInformation(user.getId()));
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(new UserInformation(false, ErrorStatus.USER_NOT_FOUNDED.name()));
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "set-user-tag")
    public ResponseEntity<Result> setUserTag(@RequestParam("token") String token,
                                             @RequestParam("userId") String userId,
                                             @RequestParam("tagName") String tagName,
                                             @RequestParam("tagValue") String tagValue) {
        try {
            User user = userService.getUser(userId);

            appService.addUserTag(token, user, tagName, tagValue);

            return ResponseEntity.ok(new Result(true));
        } catch (AppNotFoundException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(new Result(false, ErrorStatus.APPLICATION_NOT_FOUNDED.name()));
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity.badRequest().body(new Result(false, ErrorStatus.USER_NOT_FOUNDED.name()));
        }
    }

}
