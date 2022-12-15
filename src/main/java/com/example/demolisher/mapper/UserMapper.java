package com.example.demolisher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolisher.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */

@Repository
public interface UserMapper extends BaseMapper<User> {
}

