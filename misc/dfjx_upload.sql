/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : dfjx_upload

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 14/01/2020 16:43:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tenant_id` int(10) UNSIGNED NOT NULL,
  `object_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `total_downloads` int(10) UNSIGNED NULL DEFAULT 0,
  `size` int(10) UNSIGNED NOT NULL,
  `filename` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `disk_filename` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `extension` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `has_thumb` tinyint(1) UNSIGNED NULL DEFAULT 0,
  `folder` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `media_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` smallint(5) UNSIGNED NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_jdroplet_attachments_site`(`tenant_id`) USING BTREE,
  INDEX `FK_jdroplet_attachments_post`(`object_id`) USING BTREE,
  INDEX `FK_jdroplet_attachments_user`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `path` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', b'1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
