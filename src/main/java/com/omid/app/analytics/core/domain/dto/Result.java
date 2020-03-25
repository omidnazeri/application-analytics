package com.omid.app.analytics.core.domain.dto;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean success;
    private String errorMessage;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}