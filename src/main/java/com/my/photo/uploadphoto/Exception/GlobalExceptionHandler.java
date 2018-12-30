package com.my.photo.uploadphoto.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public void globalErrorHandler(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
    }

    @ExceptionHandler(value = UserException.class)
    public UserResult UserErrorHandler(UserException ex) {
        return UserResult.error(ex.getMessage());
    }


}