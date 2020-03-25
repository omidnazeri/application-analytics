package com.omid.app.analytics.core.domain.dto;

public enum ErrorStatus {
    APPLICATION_NOT_FOUNDED(300),
    USER_NOT_FOUNDED(302),
    INTERNAL_SERVER_ERROR(503),
    ;

    int value;

    ErrorStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
