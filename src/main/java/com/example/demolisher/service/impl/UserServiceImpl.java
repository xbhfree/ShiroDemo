package com.example.demolisher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolisher.service.UserService;
import com.example.demolisher.mapper.UserMapper;
import com.example.demolisher.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserInfoByPhoneNum(String phoneNum) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phoneNum);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public List<String> getUserRolesInfoByUsername(String username) {
        List<String> roles = userMapper.getRoleByUsername(username);
        return roles;
    }

    @Override
    public List<String> getUserPermissionsByRoles(List<String> roles) {
        return userMapper.getPermissionsByRoles(roles);
    }
}
