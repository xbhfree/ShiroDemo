CREATE TABLE `user`(
                       `id` BIGINT(20) not NULL AUTO_INCREMENT COMMENT '编号',
                       `name` VARCHAR(30) DEFAULT NULL COMMENT '用户名',
                       `pwd` VARCHAR(50) DEFAULT NULL COMMENT '密码',
                       `rid` BIGINT(20) DEFAULT NULL COMMENT '角色编号',
                       `version` BIGINT(20) DEFAULT(0)  COMMENT '版本号',
                       `is_delete` SMALLINT(1) DEFAULT(0) COMMENT '逻辑删除',
                       PRIMARY KEY(`id`)
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表'