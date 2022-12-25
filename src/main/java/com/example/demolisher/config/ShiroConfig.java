package com.example.demolisher.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    @Autowired
    private MyPhoneNumRealm phoneNumRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //设置安全管理器
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //创建人称对象，并设置认证策略,modular模块化的
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        manager.setAuthenticator(modularRealmAuthenticator);
        //设置matcher匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(5);
        matcher.setHashAlgorithmName("md5");
        //匹配器加入realm
        myRealm.setCredentialsMatcher(matcher);
        phoneNumRealm.setCredentialsMatcher(matcher);
        //rememberMe加入管理器
        manager.setRememberMeManager(cookieRememberMeManager());
        //realm加入管理器
        List<Realm> list = new ArrayList<>();
        list.add(myRealm);
        list.add(phoneNumRealm);
        manager.setRealms(list);
        return manager;
    }

    @Bean
    public DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/UserController/doLogin", "anon");
        definition.addPathDefinition("/UserController/login", "anon");
        //配置登出过滤器
        definition.addPathDefinition("/logout", "logout");
        definition.addPathDefinition("/**", "authc");
        definition.addPathDefinition("/**", "user");
        return definition;
    }


    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60);
        return cookie;
    }

    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(simpleCookie());
        //shiro有反序列化bug https://www.cnblogs.com/Blackie/p/16657281.html
        manager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return manager;
    }

}
