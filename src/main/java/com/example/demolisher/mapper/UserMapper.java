package com.example.demolisher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demolisher.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select name from role where id in (select rid from role_user where uid in (select id from user where name = #{username}))")
    public List<String> getRoleByUsername(@Param("username") String username);


    @Select({
            "<script>",
            "select info from permissions where id in (",
            "select pid from role_pr where rid in (",
            "select id from role where name in",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
    })
    public List<String> getPermissionsByRoles(@Param("roles")List<String> roles);
}

