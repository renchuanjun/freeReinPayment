
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_datapermission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_datapermission`;
CREATE TABLE `sys_datapermission` (
  `dp_id` varchar(50) NOT NULL COMMENT '数据权限主键',
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `data_id` varchar(50) DEFAULT NULL COMMENT '数据主线id',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`dp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_operategroup`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operategroup`;
CREATE TABLE `sys_operategroup` (
  `group_id` varchar(50) NOT NULL DEFAULT '' COMMENT '操作组主键',
  `group_name` varchar(50) NOT NULL COMMENT '操作组名称',
  `group_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `group_permission_md5` varchar(50) DEFAULT NULL COMMENT '组的权限的md5值',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_organ`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ`;
CREATE TABLE `sys_organ` (
  `organ_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织机构id',
  `parent_id` int(11) NOT NULL DEFAULT '-1' COMMENT '组织父id',
  `organ_full_id` varchar(200) DEFAULT NULL COMMENT '组织机构全路径id',
  `organ_code` varchar(50) DEFAULT NULL COMMENT '组织机构代码',
  `organ_name` varchar(50) NOT NULL COMMENT '组织机构简称',
  `organ_full_name` varchar(100) DEFAULT NULL COMMENT '组织机构全名称',
  `organ_level` tinyint(11) NOT NULL DEFAULT '0' COMMENT '组织机构等级',
  `order_by` tinyint(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `telphone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `office_address` varchar(100) DEFAULT NULL COMMENT '办公地址',
  `postal_code` varchar(50) DEFAULT NULL COMMENT '邮政编码',
  `faxphone` varchar(50) DEFAULT NULL COMMENT '传真机',
  `link_man` varchar(50) DEFAULT NULL COMMENT '联系人',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`organ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organ
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `row_id` varchar(50) NOT NULL COMMENT '权限表复合主键',
  `permission_id` varchar(50) NOT NULL DEFAULT '' COMMENT '权限表复合主键',
  `permission_parent_id` varchar(50) NOT NULL COMMENT 'permission的父Id',
  `permission_name` varchar(50) NOT NULL COMMENT '权限字段的名称',
  `order_by` tinyint(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `ptype` tinyint(4) DEFAULT NULL COMMENT '0:根，1：功能节点，2:页面,3：操作动作',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '00-00-00-00-00-00', '-1', '系统权限', '0', '0', '0', '1', '0', '1', '2017-08-10 16:00:56', '1', '1', '2017-08-10 16:01:01', '1', null, null);

-- ----------------------------
-- Table structure for `sys_permission_operategroup`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_operategroup`;
CREATE TABLE `sys_permission_operategroup` (
  `group_row_id` varchar(50) NOT NULL,
  `group_id` varchar(50) NOT NULL COMMENT '操作组Id',
  `row_id` varchar(50) NOT NULL COMMENT '权限表Id',
  `check_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未选中,1选中，2半选中',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`group_row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `organ_id` varchar(50) DEFAULT NULL COMMENT '组织机构id',
  `group_id` varchar(50) DEFAULT NULL COMMENT '操作组id',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `organ_full_id` varchar(200) DEFAULT NULL COMMENT '组织机构全路径',
  `comments` varchar(100) DEFAULT NULL COMMENT '备注',
  `role_user_md5` varchar(50) DEFAULT NULL COMMENT '角色与人员的md5值',
  `role_datapermission_md5` varchar(50) DEFAULT NULL COMMENT '角色与数据权限的md5值',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(50) NOT NULL COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '帐号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `sex` tinyint(11) DEFAULT '1' COMMENT '1为男，0为女',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `default_home` varchar(100) DEFAULT NULL COMMENT '个人主页',
  `moblie_phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `tel_phone` varchar(100) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮件',
  `post_title` varchar(10) DEFAULT NULL COMMENT '职位名称',
  `organ_full_id` varchar(200) DEFAULT NULL COMMENT '组织机构全路径',
  `organ_id` varchar(50) DEFAULT NULL COMMENT '当前所在机构id',
  `identity_card_id` varchar(20) DEFAULT NULL COMMENT '身份证',
  `card_id` varchar(20) DEFAULT NULL COMMENT '工作工号',
  `order_by` tinyint(4) NOT NULL DEFAULT '1' COMMENT '排序字段',
  `default_langage` varchar(50) DEFAULT NULL COMMENT '语言',
  `password_change_day` tinyint(11) DEFAULT '90' COMMENT '密码多少天后过期',
  `password_last_change_date` datetime DEFAULT NULL COMMENT '最后更改密码的日期',
  `offic_phone` varchar(100) DEFAULT NULL COMMENT '办公室电话',
  `office_address` varchar(100) DEFAULT NULL COMMENT '办公地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_num` tinyint(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `super_user` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为超级用户，0为普通用户',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户,0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人Id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人Id',
  `update_on` datetime NOT NULL COMMENT '更新时间',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_user_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `fore_back_type` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为前台用户，0为后台用户',
  `is_enable` tinyint(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` tinyint(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` varchar(50) NOT NULL COMMENT '创建人id',
  `create_on` datetime NOT NULL COMMENT '创建时间',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` varchar(50) NOT NULL COMMENT '更新人id',
  `update_on` datetime NOT NULL COMMENT '更新人的日期',
  `update_by_name` varchar(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` varchar(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

DROP TABLE IF EXISTS `sys_delete_constraint`;
CREATE TABLE `sys_delete_constraint` (
  `del_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_from` VARCHAR(50) NOT NULL COMMENT '当前表',
  `table_to` VARCHAR(50) NOT NULL COMMENT '受关联的表',
  `table_to_pkId` VARCHAR(50) NOT NULL COMMENT '受关联的表主键',
  `constraint_field` VARCHAR(50) NOT NULL COMMENT '关联字段',
  `is_enable` TINYINT(11) NOT NULL DEFAULT '1' COMMENT '1有启用，0为禁用',
  `is_deleted` TINYINT(11) NOT NULL DEFAULT '0' COMMENT '1为已经删除，0为未删除',
  `create_by` VARCHAR(50) NOT NULL COMMENT '创建人id',
  `create_on` DATETIME NOT NULL COMMENT '创建时间',
  `create_by_name` VARCHAR(50) NOT NULL COMMENT '创建人的姓名',
  `update_by` VARCHAR(50) NOT NULL COMMENT '更新人id',
  `update_on` DATETIME NOT NULL COMMENT '更新人的日期',
  `update_by_name` VARCHAR(50) NOT NULL COMMENT '更新人的姓名',
  `platform_id` VARCHAR(50) DEFAULT NULL COMMENT '平台id',
  `data_permission_id` VARCHAR(50) DEFAULT NULL COMMENT '数据权限id',
  PRIMARY KEY (`del_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;