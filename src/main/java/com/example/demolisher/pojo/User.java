package com.example.demolisher.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId
    private Long id;

    private String name;

    private String pwd;

    private Long rid;

    @Version
    private Long version;

    @TableLogic
    private byte isDelete;
}
