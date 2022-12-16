package com.jorgediaz.emeals.core.network.exceptions;

import java.io.IOException;

public class ServerException extends IOException {
    private final String code;
    private final String message;
    private final int httpCode;

    public ServerException(String code, String message, int httpCode) {
        super(message);
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }
}