package com.omid.app.analytics.controller.api;

import com.omid.app.analytics.core.domain.dto.ErrorStatus;
import com.omid.app.analytics.core.domain.dto.Result;
import com.omid.app.analytics.core.exception.AppNotFoundException;
import com.omid.app.analytics.core.exception.UserNotFoundException;
import com.omid.app.analytics.core.service.RequestLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/request")
public class RequestController {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    RequestLogService requestLogService;

    @RequestMapping(method = RequestMethod.POST, value = "visit")
    public ResponseEntity<Result> visit(HttpServletRequest request,
                                        @RequestParam("token") String token,
                                        @RequestParam("userId") String userId,
                                        @RequestParam("page") String pageURI,
                                        @RequestParam("result") String result) {
        try {
            String ipAddress = request.getHeader("IpAddress");
            if (ipAddress == null || ipAddress.equals(""))
                ipAddress = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");

            requestLogService.addRequestLog(token, userId, ipAddress, pageURI, userAgent, result);

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
