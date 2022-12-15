package com.example.demolisher.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class UserController {
    @GetMapping("UserController/doLogin")
    public String doLogin(String name, String pwd){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(name, pwd);
        System.out.println("!!!!!!!!!!!");
        try {
            subject.login(token);
            System.out.println("登录成功");
            return "登录成功";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }

}
