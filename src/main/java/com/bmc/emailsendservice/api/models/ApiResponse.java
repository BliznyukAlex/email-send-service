package com.bmc.emailsendservice.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse {
    private final Boolean success;
    private Object data;
    private Error error;

    public ApiResponse() {
        this.success = true;
    }

    public ApiResponse(Object data) {
        this.success = true;
        this.data = data;
    }

    public ApiResponse(Error error) {
        this.success = false;
        this.error = error;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    public static class Error {
        private final String code;
        private final String message;
    }
}
