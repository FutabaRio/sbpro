package com.example.sbpro.controller.response;

import com.example.sbpro.common.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResultException extends RuntimeException {
    private final int code;
    private final String msg;

    public ResultException(String msg) {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.msg = msg;
    }

    public ResultException(StatusCode statusCode) {
        this.code = statusCode.code;
        this.msg = statusCode.message;
    }
}
