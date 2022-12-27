package com.example.demolisher;

import com.example.demolisher.mapper.UserMapper;
import com.example.demolisher.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShiroDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void testMapper(){
		List<String> alisa = userMapper.getRoleByUsername("alisa");
		System.out.println(alisa);
		List<String> permissionsByRoles = userMapper.getPermissionsByRoles(alisa);
		System.out.println(permissionsByRoles);
	}

	@Test
	void contextLoads() {
		// 1 初始化security management
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 2 创建subject
		Subject subject = SecurityUtils.getSubject();
		// 3 创建token对象
		AuthenticationToken token = new UsernamePasswordToken("alisa", "123456");
		// 4 完成登录
		subject.login(token);
		try {
			System.out.println("登录成功");
			//判断角色
			boolean role1 = subject.hasRole("role1");
			System.out.println("是否有role1角色 " + role1);
			//判断权限
			boolean permitted = subject.isPermitted("user:select");
			System.out.println("是否拥有user:select权限 " + permitted);
			//判断权限，有通过，无抛出UnauthorizedException
			//subject.checkPermission("user:select2");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
