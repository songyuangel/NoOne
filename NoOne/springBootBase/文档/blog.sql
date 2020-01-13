/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Schema         : weixin

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/01/2020 22:49:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_account
-- ----------------------------
DROP TABLE IF EXISTS `blog_account`;
CREATE TABLE `blog_account` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `stop_flag` varchar(2) DEFAULT NULL COMMENT '停用标记',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `mark` varchar(255) DEFAULT NULL COMMENT '备注',
  `weixin_id` varchar(255) DEFAULT NULL COMMENT '微信登录ID',
  `weibo_id` varchar(255) DEFAULT NULL COMMENT '微博登录ID',
  `QQ_id` varchar(255) DEFAULT NULL COMMENT 'QQ登录ID',
  PRIMARY KEY (`id`,`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_account
-- ----------------------------
BEGIN;
INSERT INTO `blog_account` VALUES (100001, '123', '202cb962ac59075b964b07152d234b70', '309222152@qq.com', NULL, NULL, '2019-12-22 19:10:03', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '对应userinfo的id',
  `cate_name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `state` varchar(2) DEFAULT NULL COMMENT '有效状态 1有效 0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '对应userinfo的id',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '标签名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `state` varchar(2) DEFAULT NULL COMMENT '状态 1有效 0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `blog_userinfo`;
CREATE TABLE `blog_userinfo` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_id` int(6) DEFAULT NULL COMMENT '对应account表的ID',
  `account` varchar(255) DEFAULT NULL COMMENT '账号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_code` varchar(2) DEFAULT NULL COMMENT '性别 1男 2女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `QQ` varchar(255) DEFAULT NULL COMMENT 'QQ',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `weixin` varchar(255) DEFAULT NULL COMMENT '微信号',
  `weibo` varchar(255) DEFAULT NULL COMMENT '微博号',
  `avatarurl` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `sidename` varchar(255) DEFAULT NULL COMMENT '博客名称',
  `mark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_userinfo
-- ----------------------------
BEGIN;
INSERT INTO `blog_userinfo` VALUES (200001, 100001, '123', 'sy', '小火车_哔_', '1', '1993-06-01', '12345678', '1234567@qq.com', '12345678', '12345678', '0987654321', '1234567890', 'https://tvax3.sinaimg.cn/crop.0.0.320.320.180/5f851e6dly8fhioicvm0vj208w08wdg3.jpg?KID=imgbed,tva&Expires=1577555711', '1233', NULL, '2019-12-22 19:14:03', '2020-01-09 22:19:58');
COMMIT;

-- ----------------------------
-- Table structure for blog_userinfo_ext
-- ----------------------------
DROP TABLE IF EXISTS `blog_userinfo_ext`;
CREATE TABLE `blog_userinfo_ext` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `description` varchar(255) DEFAULT NULL COMMENT '用户一句话介绍',
  `aboutme_html` text COMMENT '关于我html形式',
  `aboutme_md` text COMMENT '关于我markdown形式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(255) NOT NULL COMMENT '登录账号',
  `account_id` int(6) NOT NULL COMMENT 'account.id',
  `token` varchar(255) NOT NULL COMMENT '令牌',
  `statue` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态 0正常 1注销 2更新',
  `due_date` datetime DEFAULT NULL COMMENT '到期时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_token
-- ----------------------------
BEGIN;
INSERT INTO `sys_token` VALUES (1, '123', 100001, '25ab32f0-28c0-11ea-8393-e8517ecbd719', '2', '2019-12-28 00:47:12', NULL, NULL);
INSERT INTO `sys_token` VALUES (2, '123', 100001, 'b45db260-28c1-11ea-8393-e8517ecbd719', '2', '2019-12-28 00:58:21', NULL, NULL);
INSERT INTO `sys_token` VALUES (3, '123', 100001, '2b75d350-28c2-11ea-8393-e8517ecbd719', '2', '2019-12-28 01:01:41', NULL, NULL);
INSERT INTO `sys_token` VALUES (4, '123', 100001, '42beabe0-28c2-11ea-8393-e8517ecbd719', '2', '2019-12-28 01:02:20', NULL, NULL);
INSERT INTO `sys_token` VALUES (5, '123', 100001, 'bdccd430-2988-11ea-6b19-bb1063bc5489', '2', '2019-12-29 00:43:06', NULL, NULL);
INSERT INTO `sys_token` VALUES (6, '123', 100001, '41794140-298b-11ea-6b19-bb1063bc5489', '2', '2019-12-29 01:01:07', NULL, NULL);
INSERT INTO `sys_token` VALUES (7, '123', 100001, '85d23400-298b-11ea-4a6d-31bc7d47771b', '2', '2019-12-29 01:03:01', NULL, NULL);
INSERT INTO `sys_token` VALUES (8, '123', 100001, '690d9700-298c-11ea-f50e-cafe321ac5e8', '2', '2019-12-29 01:09:22', NULL, NULL);
INSERT INTO `sys_token` VALUES (9, '123', 100001, 'a31b3150-298c-11ea-f50e-cafe321ac5e8', '2', '2019-12-29 01:11:00', NULL, NULL);
INSERT INTO `sys_token` VALUES (10, '123', 100001, 'e9faa7e0-298c-11ea-f50e-cafe321ac5e8', '1', '2019-12-29 01:12:59', NULL, NULL);
INSERT INTO `sys_token` VALUES (11, '123', 100001, '5f6edcc0-2a51-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:39:17', NULL, NULL);
INSERT INTO `sys_token` VALUES (12, '123', 100001, '8f6b8f90-2a51-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:40:38', NULL, NULL);
INSERT INTO `sys_token` VALUES (13, '123', 100001, 'c07f30a0-2a51-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:42:00', NULL, NULL);
INSERT INTO `sys_token` VALUES (14, '123', 100001, 'd8eb2c70-2a51-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:42:41', NULL, NULL);
INSERT INTO `sys_token` VALUES (15, '123', 100001, '3a56bfb0-2a52-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:45:24', NULL, NULL);
INSERT INTO `sys_token` VALUES (16, '123', 100001, '53ec51f0-2a53-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:53:17', NULL, NULL);
INSERT INTO `sys_token` VALUES (17, '123', 100001, 'e5e9ac10-2a53-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 00:57:22', NULL, NULL);
INSERT INTO `sys_token` VALUES (18, '123', 100001, '891a19b0-2a54-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 01:01:55', NULL, NULL);
INSERT INTO `sys_token` VALUES (19, '123', 100001, '92f5c060-2a54-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 01:02:12', NULL, NULL);
INSERT INTO `sys_token` VALUES (20, '123', 100001, '9e78d580-2a54-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 01:02:31', NULL, NULL);
INSERT INTO `sys_token` VALUES (21, '123', 100001, 'f0972c40-2a54-11ea-91ea-a8b9ac66676d', '1', '2019-12-30 01:04:49', NULL, NULL);
INSERT INTO `sys_token` VALUES (22, '123', 100001, 'f9814b20-2b11-11ea-d59f-84cec6822014', '1', '2019-12-30 23:37:59', NULL, NULL);
INSERT INTO `sys_token` VALUES (23, '123', 100001, 'b1f86dd0-2b14-11ea-d59f-84cec6822014', '1', '2019-12-30 23:57:27', NULL, NULL);
INSERT INTO `sys_token` VALUES (24, '123', 100001, 'a50ad6c0-2b15-11ea-d59f-84cec6822014', '1', '2019-12-31 00:04:15', NULL, NULL);
INSERT INTO `sys_token` VALUES (25, '123', 100001, '64e956b0-2b16-11ea-d59f-84cec6822014', '1', '2019-12-31 00:09:37', NULL, NULL);
INSERT INTO `sys_token` VALUES (26, '123', 100001, 'af0d9800-2b16-11ea-d59f-84cec6822014', '1', '2019-12-31 00:11:42', NULL, NULL);
INSERT INTO `sys_token` VALUES (27, '123', 100001, 'ca218250-2b16-11ea-d59f-84cec6822014', '1', '2019-12-31 00:12:27', NULL, NULL);
INSERT INTO `sys_token` VALUES (28, '123', 100001, 'dce17760-2b16-11ea-d59f-84cec6822014', '1', '2019-12-31 00:12:58', NULL, NULL);
INSERT INTO `sys_token` VALUES (29, '123', 100001, '0373f3a0-2e30-11ea-2de7-fd2f27c53d10', '2', '2020-01-03 22:50:34', '2020-01-03 21:50:34', '2020-01-03 21:51:52');
INSERT INTO `sys_token` VALUES (30, '123', 100001, '323f7970-2e30-11ea-2de7-fd2f27c53d10', '1', '2020-01-03 22:51:52', '2020-01-03 21:51:52', '2020-01-03 21:54:43');
INSERT INTO `sys_token` VALUES (31, '123', 100001, 'e25a1720-2efd-11ea-1851-b86aed2701f7', '3', '2020-01-04 23:24:15', '2020-01-04 22:24:15', '2020-01-05 22:51:42');
INSERT INTO `sys_token` VALUES (32, '123', 100001, 'e916e600-2fcb-11ea-35a2-33e4d7f850b4', '3', '2020-01-05 23:59:02', '2020-01-05 22:59:02', '2020-01-05 22:59:10');
INSERT INTO `sys_token` VALUES (33, '123', 100001, '05467980-2fcc-11ea-35a2-33e4d7f850b4', '3', '2020-01-05 23:59:50', '2020-01-05 22:59:50', '2020-01-05 23:02:32');
INSERT INTO `sys_token` VALUES (34, '123', 100001, 'd66a3560-2fcc-11ea-35a2-33e4d7f850b4', '3', '2020-01-06 00:05:41', '2020-01-05 23:05:41', '2020-01-05 23:06:54');
INSERT INTO `sys_token` VALUES (35, '123', 100001, '35326e00-2fcd-11ea-c87a-bb0294b089e6', '3', '2020-01-06 00:08:20', '2020-01-05 23:08:20', '2020-01-09 21:17:40');
INSERT INTO `sys_token` VALUES (36, '123', 100001, '9080a890-32e2-11ea-5781-8714c8965de6', '2', '2020-01-09 22:18:46', '2020-01-09 21:18:46', '2020-01-09 22:14:14');
INSERT INTO `sys_token` VALUES (37, '123', 100001, '51133210-32ea-11ea-e36a-3e628d35c1e0', '3', '2020-01-09 23:14:15', '2020-01-09 22:14:15', '2020-01-10 21:35:04');
INSERT INTO `sys_token` VALUES (38, '123', 100001, '57bb1960-33b8-11ea-0746-717802e8a76d', '0', '2020-01-10 23:49:03', '2020-01-10 22:49:03', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'test', '123', '456');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
