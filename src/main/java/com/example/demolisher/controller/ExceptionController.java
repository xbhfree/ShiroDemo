package com.example.demolisher.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * 异常处理
 */
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception e){
        return "unauthorizedException";
    }


    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception e){
        return "AuthorizationException";
    }
}
