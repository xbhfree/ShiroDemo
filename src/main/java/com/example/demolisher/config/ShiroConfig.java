package com.example.demolisher.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //设置安全管理器
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //设置matcher匹配器
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(5);
        matcher.setHashAlgorithmName("md5");
        //匹配器加入realm
        myRealm.setCredentialsMatcher(matcher);
        //realm加入管理器
        manager.setRealm(myRealm);
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
