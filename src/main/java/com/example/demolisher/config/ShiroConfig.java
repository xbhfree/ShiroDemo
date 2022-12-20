package com.example.demolisher.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
}
