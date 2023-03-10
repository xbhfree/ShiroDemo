package com.example.demolisher.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("UserController")
public class UserController {

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }


    @GetMapping("doLogin")
    public String doLogin(String name, String pwd, HttpSession session, @RequestParam(defaultValue = "false") Boolean rememberMe){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
        System.out.println("!!!!!!!!!!!");
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";
            //System.out.println("登录成功");
            //return "登录成功";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //System.out.println("登录失败");
            return "登录失败";
        }
    }

    @GetMapping("doLoginRm")
    public String doLoginRm(HttpSession session){
        session.setAttribute("user", "rememberMe");
        return "main";
    }


    @GetMapping("checkRole")
    @RequiresRoles({"admin", "boss"})
    @ResponseBody
    public String checkRole(){
        return "拥有角色";
    }


    @GetMapping("checkPermission")
    @RequiresPermissions({"user:create"})
    @ResponseBody
    public String checkPermission(){
        return "拥有权限";
    }
}
