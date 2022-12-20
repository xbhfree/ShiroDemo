package com.example.demolisher.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId
    private Long id;

    private String name;

    @TableField("phone")
    private Long phoneNum;

    private String pwd;

    private Long rid;

    @Version
    private Long version;

    @TableLogic
    private byte isDelete;
}
