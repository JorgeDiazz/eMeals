package com.jorgediaz.presentation.core;

public interface Logger {
    void v(String message, Throwable throwable);

    void d(String message, Throwable throwable);

    void i(String message, Throwable throwable);

    void w(String message, Throwable throwable);

    void e(String message, Throwable throwable);

    void http(String url, String method, String request, String response, Integer statusCode);
}
