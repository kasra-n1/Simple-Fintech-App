package com.snapp.pay.commons.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class GeneralResponse<T> {

    private LocalDateTime time;
    private int code;
    private String message;
    private T body;


    public GeneralResponse(int code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
        this.time = LocalDateTime.now();
    }

    public GeneralResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public GeneralResponse(T body) {
        this.code = 200;
        this.message = "successful";
        this.body = body;
        this.time = LocalDateTime.now();
    }
}
