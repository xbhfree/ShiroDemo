# shiro学习笔记
## 简介
### 功能划分
1. Authentication 认证登录
2. Authorization  授权、权限验证
3. Session Management 会话管理
4. Cryptography 加密
### shiro架构
Application Code(应用程序)->
Subject(当前用户)->
Shiro Security Manager(shiro安全管理)->
Realm(数据源)
## 登陆
### 登录基本步骤
``` java
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
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

## 授权
### 构成
1. subject(主体)
2. resource(资源)
3. permission(权限，主体访问资源的权限)
4. role(角色，权限的集合)
### 授权方式
1. 编程式(直接在代码中判断权限)
2. 注解式(在方法中加入注解，判断权限@RequiresRoles("admin"))
3. JSP标签(<shiro:hasRole name="admin"></shiro:hasRole>)
### 授权过程
subject-->security manager-->authorizer-->realm

