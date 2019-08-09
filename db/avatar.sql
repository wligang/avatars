/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : mktdb1

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 10/08/2019 01:01:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1.有效  2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发布号作者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (5, '莎士比亚', '2017-10-25 09:08:45', '2017-10-30 17:59:41', '1');
INSERT INTO `article` VALUES (6, '亚里士多德', '2017-10-26 10:49:28', '2017-11-18 09:54:15', '1');
INSERT INTO `article` VALUES (10, '亚历山大', '2017-10-26 14:57:45', '2017-11-08 13:28:52', '1');
INSERT INTO `article` VALUES (11, '李白', '2017-10-26 15:23:42', '2017-10-26 15:23:42', '1');
INSERT INTO `article` VALUES (19, '文章test写得好', '2017-11-18 13:37:07', '2019-04-26 16:53:59', '1');
INSERT INTO `article` VALUES (20, 'My Name Is Anay', '2019-04-26 22:27:21', '2019-04-26 22:27:21', '1');

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL DEFAULT 0 COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (101, 'article', '文章管理', 'article:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (102, 'article', '文章管理', 'article:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (103, 'article', '文章管理', 'article:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (601, 'user', '用户', 'user:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (602, 'user', '用户', 'user:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (603, 'user', '用户', 'user:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (701, 'role', '角色权限', 'role:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (702, 'role', '角色权限', 'role:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (703, 'role', '角色权限', 'role:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (704, 'role', '角色权限', 'role:delete', '删除', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES (2, '作家', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES (3, '程序员', '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role` VALUES (4, '业务员', '2019-04-26 16:52:25', '2019-04-26 16:52:25', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 2, 101, '2017-11-22 16:26:21', '2017-11-22 16:26:32', '1');
INSERT INTO `sys_role_permission` VALUES (2, 2, 102, '2017-11-22 16:26:21', '2017-11-22 16:26:32', '1');
INSERT INTO `sys_role_permission` VALUES (5, 2, 602, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (6, 2, 601, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (7, 2, 603, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (8, 2, 703, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (9, 2, 701, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (10, 2, 702, '2017-11-22 16:28:28', '2017-11-22 16:28:28', '1');
INSERT INTO `sys_role_permission` VALUES (11, 2, 704, '2017-11-22 16:28:31', '2017-11-22 16:28:31', '1');
INSERT INTO `sys_role_permission` VALUES (12, 2, 103, '2017-11-22 16:28:31', '2017-11-22 16:28:31', '1');
INSERT INTO `sys_role_permission` VALUES (13, 3, 601, '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role_permission` VALUES (14, 3, 701, '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role_permission` VALUES (15, 3, 702, '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES (16, 3, 704, '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES (17, 3, 102, '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES (18, 3, 101, '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES (19, 3, 603, '2017-11-22 16:35:01', '2017-11-22 16:35:01', '1');
INSERT INTO `sys_role_permission` VALUES (20, 4, 101, '2019-04-26 16:52:25', '2019-04-26 16:52:25', '1');
INSERT INTO `sys_role_permission` VALUES (21, 4, 102, '2019-04-26 16:52:25', '2019-04-26 16:52:25', '1');
INSERT INTO `sys_role_permission` VALUES (22, 4, 601, '2019-04-26 16:52:25', '2019-04-26 16:52:25', '1');
INSERT INTO `sys_role_permission` VALUES (23, 4, 602, '2019-04-26 16:52:25', '2019-04-26 16:52:25', '1');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `Sys_User_id` int(4) NULL DEFAULT NULL,
  `Sys_Role_id` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1, 1);
INSERT INTO `sys_role_user` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) NULL DEFAULT 0 COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10003, 'admin', '123456', '超级用户', 1, '2017-10-30 11:52:38', '2019-04-27 17:02:37', '1');
INSERT INTO `sys_user` VALUES (10004, 'user', '123456', '莎士比亚', 2, '2017-10-30 16:13:02', '2017-11-18 02:48:24', '1');
INSERT INTO `sys_user` VALUES (10005, 'aaa', '123456', '总经理', 1, '2017-11-15 14:02:56', '2019-04-27 00:14:35', '1');
INSERT INTO `sys_user` VALUES (10007, 'test', '123456', '业务员小刘', 4, '2017-11-22 16:29:41', '2019-04-26 16:52:57', '1');

-- ----------------------------
-- Table structure for t1
-- ----------------------------
DROP TABLE IF EXISTS `t1`;
CREATE TABLE `t1`  (
  `C1` int(11) NOT NULL,
  `C2` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`C1`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t1
-- ----------------------------
INSERT INTO `t1` VALUES (1, '1');
INSERT INTO `t1` VALUES (2, '2');
INSERT INTO `t1` VALUES (3, '3');

-- ----------------------------
-- Table structure for t2
-- ----------------------------
DROP TABLE IF EXISTS `t2`;
CREATE TABLE `t2`  (
  `C1` int(11) NOT NULL,
  `C2` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`C1`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t2
-- ----------------------------
INSERT INTO `t2` VALUES (1, '1');
INSERT INTO `t2` VALUES (2, '1');
INSERT INTO `t2` VALUES (3, '2');
INSERT INTO `t2` VALUES (4, '4');

-- ----------------------------
-- Table structure for t_actor
-- ----------------------------
DROP TABLE IF EXISTS `t_actor`;
CREATE TABLE `t_actor`  (
  `Id` bigint(20) NOT NULL,
  `openid` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信的openid',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `org_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构ID',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别：0未知，1：男，2：女',
  `head_img` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像uri',
  `wx_body` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信信息使用标准json格式存储',
  `employee_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '雇员号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` int(2) NULL DEFAULT NULL COMMENT '参与者状态0:未参与，1:手机未认证，2：正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `org_id`(`org_id`, `openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '参与者' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_actor
-- ----------------------------
INSERT INTO `t_actor` VALUES (1137363323835183106, NULL, '15501033589', NULL, 'h3', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-08 22:18:42', NULL);
INSERT INTO `t_actor` VALUES (1137401001620303873, NULL, NULL, NULL, 'h4', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-09 00:48:25', NULL);
INSERT INTO `t_actor` VALUES (1137402390442418177, NULL, NULL, NULL, 'h6', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-09 00:53:56', NULL);
INSERT INTO `t_actor` VALUES (1137402741056901122, NULL, NULL, NULL, 'h7', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-09 00:55:20', NULL);
INSERT INTO `t_actor` VALUES (1137402808778133506, NULL, NULL, NULL, 'h9', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-09 00:55:36', NULL);
INSERT INTO `t_actor` VALUES (1137404493177065473, NULL, NULL, NULL, 'h11', 'master', NULL, NULL, NULL, NULL, NULL, NULL, '2019-06-09 01:02:18', NULL);

-- ----------------------------
-- Table structure for t_awards
-- ----------------------------
DROP TABLE IF EXISTS `t_awards`;
CREATE TABLE `t_awards`  (
  `Id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `org_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '奖品名称',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '奖品等级',
  `total` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '奖品总数',
  `sent_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '派送出去的数量',
  `status` int(11) NULL DEFAULT NULL COMMENT '奖品发送状态：0，未开始，1，已开始，2已结束',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `refuse_uid` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拒绝用户列表',
  `keep_uid` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保留中奖用户',
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `org_id`(`org_id`, `name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '奖项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_awards
-- ----------------------------
INSERT INTO `t_awards` VALUES ('2e430c6ac46d4e16959e87d6c90b91d0', '1', '三等奖', '1', '10', '0', 0, '2017-11-29 16:29:54', NULL, '166b722305a64445a56d2c0fb16e85e1,2be0bc02c197463aa2be2e0342a1aec4,a4d81f6ae8004d6d9e5cd11bca0b8197', '85ca5cffed8045bb802484d3378b3242,a7f74285f738451b979f7240a46eeb53');
INSERT INTO `t_awards` VALUES ('34961f98f260434e8772f8b88bdb4c6e', '0', '三等奖', '1', '10', '0', 0, '2017-11-28 14:34:47', NULL, NULL, NULL);
INSERT INTO `t_awards` VALUES ('540e81abafa248e6bea644cffda97c0e', '0', '一等奖', '1', '10', '0', 0, '2017-11-28 14:34:22', NULL, NULL, NULL);
INSERT INTO `t_awards` VALUES ('b19d908b5a8e44ae9390a3919d51c71e', '0', '特等奖', '1', '10', '0', 0, '2017-11-28 14:35:06', NULL, NULL, NULL);
INSERT INTO `t_awards` VALUES ('b9fb86c07a7942dfa346c6f00e3393fd', '0', '感恩节大回馈', '1', '10', '0', 0, '2017-11-28 14:33:16', NULL, NULL, NULL);
INSERT INTO `t_awards` VALUES ('ebf1dfe36a7244b6888cde938239e5f4', '0', '二等奖', '1', '10', '0', 0, '2017-11-28 14:34:37', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` int(4) NOT NULL,
  `org_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `charge_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人名称',
  `charge_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构联系电话',
  `appid` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appsecret` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号介入token',
  `access_token` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号访问token',
  `token_fresh_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `backUrl` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权后跳转的页面',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES (0, NULL, '马化腾', '15501033589', 'wx3cb81c3c95c1a755', '600bd6c8edb8ca5340e409910845ca5c', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES (1, NULL, '马化腾', '15501033589', 'wx3cb81c3c95c1a755', '600bd6c8edb8ca5340e409910845ca5c', '71D8AC937E27443A', NULL, NULL, NULL, 0, 'https://www.baidu.com/');
INSERT INTO `t_org` VALUES (2, '阿里巴巴', '马化腾', '15501033589', 'wx3cb81c3c95c1a755', '600bd6c8edb8ca5340e409910845ca5c', 'F98663360852E242', NULL, NULL, NULL, 0, NULL);

-- ----------------------------
-- Table structure for t_owner
-- ----------------------------
DROP TABLE IF EXISTS `t_owner`;
CREATE TABLE `t_owner`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `build` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '楼号',
  `unit` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '單元',
  `floor` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '  楼层',
  `room` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '房间号',
  `electfee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `waterfee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `updatetime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_room`(`room`, `build`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = ' 业主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_owner
-- ----------------------------
INSERT INTO `t_owner` VALUES ('3528017ef4484ddc99a1c69cf67a7253', '冯鑫亮', NULL, '6', '1', '4', '414', '0.00', '0.00', '2018-01-28 17:10:20', '2018-01-28 17:10:20');
INSERT INTO `t_owner` VALUES ('415b17cf8f0b4ac98d6cd165bd7f4540', '李狗蛋', NULL, '6', '1', '5', '523', '0.00', '0.00', '2018-01-28 17:05:37', '2018-01-28 17:05:37');
INSERT INTO `t_owner` VALUES ('63f93407be6d47278f3182333b1c52ef', '贾业增', NULL, '6', '1', '4', '416', '0.00', '0.00', '2018-01-28 17:08:30', '2018-01-28 17:08:30');
INSERT INTO `t_owner` VALUES ('9460d2d2926b42b99ad7c09efdd804b6', '亲亲我', NULL, '6', '1', '1', '113', '0.00', '0.00', '2018-01-28 17:12:46', '2018-01-28 17:12:46');
INSERT INTO `t_owner` VALUES ('b24e3551cafb4f65be5767ace0f1a82e', '王利刚', NULL, '6', '1', '1', '115', '66.45', '23.88', '2018-01-28 13:29:40', '2018-01-28 18:04:33');

-- ----------------------------
-- Table structure for t_prize
-- ----------------------------
DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize`  (
  `Id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '中奖用户表',
  `award_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '奖项ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '中奖时间',
  `reward_index` bigint(20) NOT NULL DEFAULT 0 COMMENT '中奖的序列，纳秒级别',
  `receive_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '领取时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '奖品状态：0可领取，1暂时不可领取，2已经作废，3已领取',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `uid`(`uid`, `award_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '奖品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_prize
-- ----------------------------
INSERT INTO `t_prize` VALUES ('49f6ba5a5c434066b8db7b583511cd4f', '810e29852abc4360a68628a1cad17068', '34961f98f260434e8772f8b88bdb4c6e', '2017-11-28 18:13:07', 1067541279477033, NULL, '0');
INSERT INTO `t_prize` VALUES ('8d3803bc160248b9a0cb60358add3e52', '85ca5cffed8045bb802484d3378b3242', '34961f98f260434e8772f8b88bdb4c6e', '2017-11-28 18:13:07', 1067541279495215, NULL, '0');
INSERT INTO `t_prize` VALUES ('ca395050c5cd4d4d8e770e2e94e376b7', '7a85550705714a38bc5b4d2a55a39a71', '34961f98f260434e8772f8b88bdb4c6e', '2017-11-28 18:13:07', 1067541279287596, NULL, '0');
INSERT INTO `t_prize` VALUES ('cb8c69e64b3842f585f621ad322c1a7b', '', '540e81abafa248e6bea644cffda97c0e', '2017-11-28 17:53:11', 1066345721510330, NULL, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `password` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `transactor` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `update_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'A', 's', NULL, '2019-05-10');
INSERT INTO `user` VALUES (2, '李四', 's', NULL, NULL);
INSERT INTO `user` VALUES (11, '哈哈', '1234abc', NULL, NULL);
INSERT INTO `user` VALUES (12, '大山', NULL, NULL, NULL);
INSERT INTO `user` VALUES (13, '大山', NULL, NULL, NULL);
INSERT INTO `user` VALUES (14, '大山', NULL, NULL, NULL);
INSERT INTO `user` VALUES (15, 'B', NULL, NULL, NULL);
INSERT INTO `user` VALUES (16, 'A', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
