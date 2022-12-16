package com.jorgediaz.emeals.base.data;

public class HttpObject {

    private String method;
    private String request;
    private String response;
    private int httpCode;

    public HttpObject(String method, String request, String response, int httpCode) {
        this.method = method;
        this.request = request;
        this.response = response;
        this.httpCode = httpCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
