package com.example.demolisher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demolisher.pojo.User;

import java.util.List;


/**
 * @author Administrator
 */
public interface UserService extends IService<User> {

    /**
     * @param name 用户名
     * @return 用户
     */
    public User getUserInfoByName(String name);

    public User getUserInfoByPhoneNum(String phoneNum);

    public List<String> getUserRolesInfoByUsername(String username);

    public List<String> getUserPermissionsByRoles(List<String> roles);
}
