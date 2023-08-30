package org.caja.ideal.shoppingcart.api;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

public class ApiResponseException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String message;
    private int code;
    private HttpStatus http;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getHttp() {
        return http;
    }

    public void setHttp(HttpStatus http) {
        this.http = http;
    }

    public ApiResponseException() {
    }

    public ApiResponseException(String message, int code, HttpStatus http) {
        this.message = message;
        this.code = code;
        this.http = http;
    }
}
