package com.example.sbpro.handler;


import cn.hutool.jwt.JWTException;
import com.example.sbpro.controller.response.Result;
import com.example.sbpro.controller.response.ResultException;
import com.example.sbpro.service.jwt.JwtInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> missingServletRequestParameterException(MissingServletRequestParameterException e,
                                                                  HttpServletRequest request) {
        logWarn(request);
        log.warn("请求参数缺失", e);
        return Result.fail(400, String.format("请求参数缺失:%s", e.getParameterName()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                              HttpServletRequest request) {
        logWarn(request);
        log.warn("参数类型不匹配", e);
        return Result.fail(400, String.format("参数类型不匹配:%s", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            return Result.fail(400, String.format("参数校验错误:%s", fieldError.getDefaultMessage()));
        }
        return Result.fail(400, String.format("参数校验错误:%s", e.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<String> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.warn("请求地址不存在", e);
        return Result.fail(404, String.format("请求地址 %s 不存在", e.getRequestURL()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                                        HttpServletRequest request) {
        logWarn(request);
        log.warn("请求方式错误", e);
        return Result.fail(405, String.format("请求方法不正确:%s", e.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public Result<String> illegalArgumentOrStateExceptionHandler(RuntimeException e) {
        return Result.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(ResultException.class)
    public Result<String> ResultExceptionHandler(ResultException e) {
        return Result.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result<String> authExceptionHandler(AuthenticationException e) {
        return Result.fail(401, e.getMessage());
    }

    @ExceptionHandler({JwtInvalidException.class,JwtInvalidException.class})
    public Result<String> jwtExceptionHandler(JWTException e) {
        return Result.fail(401, e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result<String> fileSizeThresholdHandler(MultipartException e) {
        return Result.fail(413, e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Result<String> handleResponseStatusException(ResponseStatusException ex) {
        return Result.fail(ex.getStatusCode().value(), ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<?> defaultExceptionHandler(Exception e,
                                             HttpServletRequest request) {
        logError(request);
        log.error("Exception: ", e);
        return Result.fail(500, "服务器内部错误", null);
    }

    private void logWarn(HttpServletRequest request) {
        log.warn("Request URL: {}", request.getRequestURL());
        log.warn("Request Method: {}", request.getMethod());
        log.warn("Request IP: {}", request.getRemoteAddr());
        log.warn("Request Headers: {}", request.getHeaderNames());
        log.warn("Request Parameters: {}", request.getParameterMap());
    }

    private void logError(HttpServletRequest request) {
        log.error("Request URL: {}", request.getRequestURL());
        log.error("Request Method: {}", request.getMethod());
        log.error("Request IP: {}", request.getRemoteAddr());
        log.error("Request Headers: {}", request.getHeaderNames());
        log.error("Request Parameters: {}", request.getParameterMap());
    }
}
