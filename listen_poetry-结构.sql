/*
 Navicat Premium Data Transfer

 Source Server         : ubuntu
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 192.168.15.53:3306
 Source Schema         : listen_poetry

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/03/2020 10:45:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `user_name` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `subject_id` bigint(0) NULL DEFAULT NULL COMMENT '评论的主体id，比如Poetry、author',
  `subject_type` tinyint(0) NULL DEFAULT NULL COMMENT '主体类型: 1poetry; 2author',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_1`(`user_id`) USING BTREE,
  INDEX `index_2`(`subject_id`) USING BTREE,
  INDEX `index_3`(`subject_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `subject_id` bigint(0) NULL DEFAULT NULL COMMENT '评论的主体id，比如Poetry、author',
  `subject_type` tinyint(0) NULL DEFAULT NULL COMMENT '主体类型: 1poetry; 2author',
  `star` int(0) NULL DEFAULT 1,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reply` tinyint(0) NULL DEFAULT 0 COMMENT '0未回复; 1已回复',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poet
-- ----------------------------
DROP TABLE IF EXISTS `poet`;
CREATE TABLE `poet`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者名',
  `read_count` int(0) NULL DEFAULT 0 COMMENT '阅读数',
  `like_count` int(0) NULL DEFAULT 0 COMMENT '喜欢数',
  `hot_score` int(0) NULL DEFAULT 0 COMMENT '热度分值',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_1`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36963 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poet_extend
-- ----------------------------
DROP TABLE IF EXISTS `poet_extend`;
CREATE TABLE `poet_extend`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `poet_id` bigint(0) NULL DEFAULT NULL COMMENT '作者编号',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户简介',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_1`(`poet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22630 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poetry
-- ----------------------------
DROP TABLE IF EXISTS `poetry`;
CREATE TABLE `poetry`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `author_id` bigint(0) NULL DEFAULT NULL COMMENT '作者id',
  `author_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者姓名',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `dynasty` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '朝代',
  `read_count` int(0) NULL DEFAULT 0 COMMENT '阅读数',
  `like_count` int(0) NULL DEFAULT 0 COMMENT '喜欢数',
  `hot_score` int(0) NULL DEFAULT 0 COMMENT '热度分值',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_1`(`title`) USING BTREE,
  INDEX `index_2`(`author_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 530130 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '诗词' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for poetry_extend
-- ----------------------------
DROP TABLE IF EXISTS `poetry_extend`;
CREATE TABLE `poetry_extend`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `poetry_id` bigint(0) NULL DEFAULT NULL,
  `translation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '翻译',
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评析',
  `annotation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '注释',
  `appreciation` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '赏析',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `i_poetry_id`(`poetry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 530130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `source_type` tinyint(0) NULL DEFAULT NULL COMMENT '类型: 1诗词; 2作者',
  `source_id` bigint(0) NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序，小的在前',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` bigint(0) NULL DEFAULT NULL,
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '推荐' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for search
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT 0,
  `delete_flag` tinyint(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别 0：未知、1：男、2：女',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `avatar_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `union_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
