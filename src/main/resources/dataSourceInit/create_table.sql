CREATE TABLE IF NOT EXISTS `t_auth_url` (
  `auth_url_id` VARCHAR(36) NOT NULL,
  `auth_id` VARCHAR(36) NOT NULL COMMENT '权限id',
  `url_id` VARCHAR(36) NOT NULL COMMENT '请求id',
  `auth_url_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_url_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `auth_url_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `auth_url_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `auth_url_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`auth_url_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限-请求';

CREATE TABLE IF NOT EXISTS `t_auth_user` (
  `auth_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `auth_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `auth_user_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_user_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `auth_user_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `auth_user_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `auth_user_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`auth_user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限-用户';

CREATE TABLE IF NOT EXISTS `t_m_code` (
  `code_group` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'code分组',
  `code_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'code的名字',
  `code_value` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'code的值',
  `code_order` VARCHAR(2) NOT NULL COMMENT '排序',
  `code_del_flg` VARCHAR(1) NOT NULL,
  `code_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `code_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `code_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `code_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`code_group`,`code_value`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_m_format` (
  `format_group` VARCHAR(100) NOT NULL,
  `format_regex` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `format_regex_value` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `format_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `format_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `format_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `format_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `format_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`format_group`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_s_auth` (
  `auth_id` VARCHAR(36) NOT NULL,
  `auth_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `auth_level` VARCHAR(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限级别',
  `auth_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `auth_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `auth_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `auth_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `auth_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`auth_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统权限';

CREATE TABLE IF NOT EXISTS `t_s_file` (
  `file_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件id',
  `file_type` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件类型',
  `file_name_realy` VARCHAR(500) NOT NULL COMMENT '文件名-未处理',
  `file_name` VARCHAR(500) NOT NULL COMMENT '文件名-处理后',
  `file_size` VARCHAR(50) NOT NULL COMMENT '文件大小，单位kb',
  `file_folder_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件所属文件夹id',
  `file_del_flg` VARCHAR(2) NOT NULL,
  `file_create_time` DATETIME NOT NULL,
  `file_create_user` VARCHAR(36) NOT NULL,
  `file_modify_time` DATETIME NOT NULL,
  `file_modify_user` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文件';

CREATE TABLE IF NOT EXISTS `t_s_folder` (
  `folder_id` VARCHAR(36) NOT NULL COMMENT '文件夹id',
  `folder_name` VARCHAR(50) NOT NULL COMMENT '文件夹名字',
  `parent_folder_id` VARCHAR(36) DEFAULT NULL COMMENT '上级文件夹id',
  `folder_owner` VARCHAR(36) NOT NULL COMMENT '所属者id',
  `folder_del_flg` VARCHAR(1) NOT NULL COMMENT '删除标志',
  `folder_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `folder_create_user` VARCHAR(36) NOT NULL COMMENT '创建者',
  `folder_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `folder_modify_user` VARCHAR(36) NOT NULL COMMENT '修改者',
  PRIMARY KEY (`folder_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文件夹';

CREATE TABLE IF NOT EXISTS `t_s_log` (
  `log_id` VARCHAR(36) NOT NULL,
  `exception_type` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_message` VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_info` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exception_status` VARCHAR(1) NOT NULL,
  `log_del_flg` VARCHAR(1) NOT NULL,
  `log_create_time` DATETIME NOT NULL,
  `log_create_user` VARCHAR(36) NOT NULL,
  `log_modify_time` DATETIME NOT NULL,
  `log_modify_user` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统日志';

CREATE TABLE IF NOT EXISTS `t_s_url` (
  `url_id` VARCHAR(36) NOT NULL,
  `url_path` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求路径',
  `url_method` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求方法post,get',
  `url_remarks` VARCHAR(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `url_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `url_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `url_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `url_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`url_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统请求';

CREATE TABLE IF NOT EXISTS `t_s_user` (
  `user_id` VARCHAR(36) NOT NULL,
  `user_name` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `user_number` VARCHAR(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `user_pwd` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_head_portrait` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像路径',
  `user_del_flg` VARCHAR(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_create_time` DATETIME NOT NULL COMMENT '创建时间',
  `user_create_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `user_modify_time` DATETIME NOT NULL COMMENT '修改时间',
  `user_modify_user_id` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='系统用户';
