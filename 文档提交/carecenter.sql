/*
 Navicat Premium Data Transfer

 Source Server         : CentOS
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.10.110:3306
 Source Schema         : carecenter

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 07/12/2021 18:32:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for BED
-- ----------------------------
DROP TABLE IF EXISTS `BED`;
CREATE TABLE `BED`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomnum` int(11) NULL DEFAULT NULL,
  `bedstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of BED
-- ----------------------------
INSERT INTO `BED` VALUES (1, 2, '0', '1', 'OneDrive 入门.pdf');
INSERT INTO `BED` VALUES (9, 2, '0', '1', 'OneDrive 入门.pdf');

-- ----------------------------
-- Table structure for BEDANDCUST
-- ----------------------------
DROP TABLE IF EXISTS `BEDANDCUST`;
CREATE TABLE `BEDANDCUST`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NULL DEFAULT NULL,
  `bedid` int(11) NULL DEFAULT NULL,
  `starttime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `endtime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for CUSTANDNUR
-- ----------------------------
DROP TABLE IF EXISTS `CUSTANDNUR`;
CREATE TABLE `CUSTANDNUR`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nurid` int(11) NULL DEFAULT NULL,
  `custid` int(11) NULL DEFAULT NULL,
  `starttime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `endtime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of CUSTANDNUR
-- ----------------------------
INSERT INTO `CUSTANDNUR` VALUES (1, 2, 2, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for CUSTOMER
-- ----------------------------
DROP TABLE IF EXISTS `CUSTOMER`;
CREATE TABLE `CUSTOMER`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `bednum` int(11) NULL DEFAULT NULL,
  `height` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attention` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of CUSTOMER
-- ----------------------------
INSERT INTO `CUSTOMER` VALUES (2, '小黑', '男', 18, NULL, '180.0', '120.0', '1999.09.07', '羽毛球', '13083482737');
INSERT INTO `CUSTOMER` VALUES (3, '小明', '男', 11, NULL, '180.0', '180.0', '1999.09.07', '羽毛球', '13083482737');

-- ----------------------------
-- Table structure for DIETS
-- ----------------------------
DROP TABLE IF EXISTS `DIETS`;
CREATE TABLE `DIETS`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `food5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `taste` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for HEALTHY
-- ----------------------------
DROP TABLE IF EXISTS `HEALTHY`;
CREATE TABLE `HEALTHY`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custId` int(11) NULL DEFAULT NULL,
  `nurid` int(11) NULL DEFAULT NULL,
  `temp` double(255, 0) NULL DEFAULT NULL,
  `pressure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sugar` double(255, 0) NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  `pulse` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `selfCare` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of HEALTHY
-- ----------------------------
INSERT INTO `HEALTHY` VALUES (2, 2, NULL, 37, '10', 7, 120, '180', '可自理', '星期六');
INSERT INTO `HEALTHY` VALUES (3, 2, NULL, 37, '190', 7, 120, '200', '轻度依赖', '星期一');
INSERT INTO `HEALTHY` VALUES (4, 2, NULL, 37, '190', 7, 120, '200', '轻度依赖', '星期一');

-- ----------------------------
-- Table structure for NURCONTENT
-- ----------------------------
DROP TABLE IF EXISTS `NURCONTENT`;
CREATE TABLE `NURCONTENT`  (
  `nurid` int(10) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `levelid` int(11) NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  INDEX `nurid`(`nurid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of NURCONTENT
-- ----------------------------
INSERT INTO `NURCONTENT` VALUES (2, '张三', '男', 18, '还行', 1, 111);

-- ----------------------------
-- Table structure for NURLEVEL
-- ----------------------------
DROP TABLE IF EXISTS `NURLEVEL`;
CREATE TABLE `NURLEVEL`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `levelname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of NURLEVEL
-- ----------------------------
INSERT INTO `NURLEVEL` VALUES (1, '普通');
INSERT INTO `NURLEVEL` VALUES (2, '专业');
INSERT INTO `NURLEVEL` VALUES (3, '大师');

-- ----------------------------
-- Table structure for NURRECORD
-- ----------------------------
DROP TABLE IF EXISTS `NURRECORD`;
CREATE TABLE `NURRECORD`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `custid` int(10) NULL DEFAULT NULL,
  `nurid` int(10) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `starttime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `endtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of NURRECORD
-- ----------------------------
INSERT INTO `NURRECORD` VALUES (1, 2, 2, '按摩', '2021-12-06', '2021-12-06');

-- ----------------------------
-- Table structure for ORDERDIET
-- ----------------------------
DROP TABLE IF EXISTS `ORDERDIET`;
CREATE TABLE `ORDERDIET`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brekid` int(11) NULL DEFAULT NULL,
  `lunchid` int(11) NULL DEFAULT NULL,
  `dinnerid` int(11) NULL DEFAULT NULL,
  `delstatus` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for OUT
-- ----------------------------
DROP TABLE IF EXISTS `OUT`;
CREATE TABLE `OUT`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `starttime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exptime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `acttime` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aidphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for SIGNON
-- ----------------------------
DROP TABLE IF EXISTS `SIGNON`;
CREATE TABLE `SIGNON`  (
  `userid` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of SIGNON
-- ----------------------------
INSERT INTO `SIGNON` VALUES (5, '123123');
INSERT INTO `SIGNON` VALUES (8, '123456789');

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `phonenumber` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of USER
-- ----------------------------
INSERT INTO `USER` VALUES (3, 'qq', 'qq', 'qw', 123, '123', 'qq');
INSERT INTO `USER` VALUES (5, '123', '男', '1123132@qq.com', 1, '123', '护士');
INSERT INTO `USER` VALUES (8, '18181', '1', 'xiaozhi@163.com', 1, '123456789', '1');

-- ----------------------------
-- Table structure for carecenter_admin
-- ----------------------------
DROP TABLE IF EXISTS `carecenter_admin`;
CREATE TABLE `carecenter_admin`  (
  `adminName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carecenter_admin
-- ----------------------------
INSERT INTO `carecenter_admin` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NULL DEFAULT NULL,
  `manage_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nurse_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract
-- ----------------------------
INSERT INTO `contract` VALUES (7, 1, '1', '1', 'asd22', '2021-12-03 06:10:05');
INSERT INTO `contract` VALUES (8, 1, '1', '1', 'asd', '2021-12-03 06:22:07');
INSERT INTO `contract` VALUES (9, 1, '1', '1', 'asd', '2021-12-03 06:22:11');
INSERT INTO `contract` VALUES (12, 1, '1', '1', 'https://carecenter-1379.oss-cn-hangzhou.aliyuncs.com/2021-12-04/1.jpg', '2021-12-04 09:28:12.315');
INSERT INTO `contract` VALUES (13, 2, '1', '1', 'https://carecenter-1379.oss-cn-hangzhou.aliyuncs.com/2021-12-04/英语四级核心高频词汇表.pdf', '2021-12-04 10:48:51.479');

-- ----------------------------
-- Table structure for cust_dise_relation
-- ----------------------------
DROP TABLE IF EXISTS `cust_dise_relation`;
CREATE TABLE `cust_dise_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disease_id` int(255) NOT NULL,
  `cust_id` int(11) NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '病情程度',
  `duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患病时长',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cust_id`(`cust_id`) USING BTREE,
  INDEX `disease_id`(`disease_id`) USING BTREE,
  CONSTRAINT `cust_dise_relation_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `CUSTOMER` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cust_dise_relation_ibfk_2` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_dise_relation
-- ----------------------------
INSERT INTO `cust_dise_relation` VALUES (2, 2, 2, '还行', '两年', '危险');
INSERT INTO `cust_dise_relation` VALUES (3, 5, 2, '严重', '十年', '病危');
INSERT INTO `cust_dise_relation` VALUES (7, 15, 3, '一般', '一年', '稳定');

-- ----------------------------
-- Table structure for custanduser
-- ----------------------------
DROP TABLE IF EXISTS `custanduser`;
CREATE TABLE `custanduser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NULL DEFAULT NULL,
  `docid` int(11) NULL DEFAULT NULL,
  `nurid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for disease
-- ----------------------------
DROP TABLE IF EXISTS `disease`;
CREATE TABLE `disease`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of disease
-- ----------------------------
INSERT INTO `disease` VALUES (1, '高血压');
INSERT INTO `disease` VALUES (2, '低血压');
INSERT INTO `disease` VALUES (3, '眩晕');
INSERT INTO `disease` VALUES (4, '糖尿病');
INSERT INTO `disease` VALUES (5, '高血脂');
INSERT INTO `disease` VALUES (6, '心脏病');
INSERT INTO `disease` VALUES (7, '肺炎');
INSERT INTO `disease` VALUES (8, '中风后遗症');
INSERT INTO `disease` VALUES (9, '支气管哮喘');
INSERT INTO `disease` VALUES (10, '阿尔兹海默症');
INSERT INTO `disease` VALUES (11, '慢性支气管炎');
INSERT INTO `disease` VALUES (12, '慢性消化道疾病');
INSERT INTO `disease` VALUES (13, '泌尿系统疾病');
INSERT INTO `disease` VALUES (14, '帕金森综合征');
INSERT INTO `disease` VALUES (15, '陈旧性骨折');
INSERT INTO `disease` VALUES (16, '骨关节疾病');
INSERT INTO `disease` VALUES (17, '眼科疾病');
INSERT INTO `disease` VALUES (18, '癌症');
INSERT INTO `disease` VALUES (19, '皮肤病');
INSERT INTO `disease` VALUES (20, '其他');

-- ----------------------------
-- Table structure for medication_record
-- ----------------------------
DROP TABLE IF EXISTS `medication_record`;
CREATE TABLE `medication_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) NULL DEFAULT NULL,
  `nur_id` int(11) NULL DEFAULT NULL,
  `medicine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药品名称',
  `dosage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用量',
  `condit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用药状态',
  `take_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服用时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cust_id`(`cust_id`) USING BTREE,
  INDEX `nur_id`(`nur_id`) USING BTREE,
  CONSTRAINT `medication_record_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `CUSTOMER` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `medication_record_ibfk_2` FOREIGN KEY (`nur_id`) REFERENCES `NURCONTENT` (`nurid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of medication_record
-- ----------------------------
INSERT INTO `medication_record` VALUES (1, 2, 2, '999感冒灵', '一包', '正常', '2021/12/7');
INSERT INTO `medication_record` VALUES (9, 2, 2, '皮炎平软管', '还行', '正常', '2021-12-07');
INSERT INTO `medication_record` VALUES (10, 2, 2, '维生素C', '两颗', '正常', '2021/12/7');

-- ----------------------------
-- Table structure for physical_exam
-- ----------------------------
DROP TABLE IF EXISTS `physical_exam`;
CREATE TABLE `physical_exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) NULL DEFAULT NULL,
  `cust_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `report` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体检报告',
  `assessment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康评估',
  `doctor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责医生',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注意事项',
  `exam_time` datetime(0) NULL DEFAULT NULL COMMENT '体检时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cust_id`(`cust_id`) USING BTREE,
  CONSTRAINT `physical_exam_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `CUSTOMER` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of physical_exam
-- ----------------------------
INSERT INTO `physical_exam` VALUES (3, 3, '小明', 'https://carecenter-1379.oss-cn-hangzhou.aliyuncs.com/2021-12-07/作文替换词.docx', '良好', '林医生', '血压较高', '2021-12-07 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
