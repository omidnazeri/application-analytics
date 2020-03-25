package com.omid.app.analytics.core.domain.dto.user;

import com.omid.app.analytics.core.domain.dto.Result;

public class UserInformation extends Result {
    private String userId;

    public UserInformation() {
    }

    public UserInformation(String userId) {
        super(true);
        this.userId = userId;
    }

    public UserInformation(boolean success, String errorMessage) {
        super(success, errorMessage);
        this.userId = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
