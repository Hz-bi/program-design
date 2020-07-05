/*
 Navicat Premium Data Transfer

 Source Server         : Ichinose
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 05/07/2020 18:33:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for traffic
-- ----------------------------
DROP TABLE IF EXISTS `traffic`;
CREATE TABLE `traffic`  (
  `题目内容` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择D` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `正确答案` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `图片` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of traffic
-- ----------------------------
INSERT INTO `traffic` VALUES ('遇到这种情况的路口，以下做法正确的是什么？', 'A.沿左侧车道掉头', 'B.该路口不能掉头', 'C.选择中间车道掉头', 'D.在路口内掉头', 'B', 'x#x001.jpg');
INSERT INTO `traffic` VALUES ('这个标志是何含义？', 'A.无人看守铁路道口', 'B.有人看守铁路道口', 'C.立交式的铁路道口', 'D.多股铁路与道路相交', 'B', 'x#x002.jpg');
INSERT INTO `traffic` VALUES ('拼装的机动车只要认为安全就可以上路行驶。', 'A.正确', 'B.错误', NULL, NULL, 'B', 'p');
INSERT INTO `traffic` VALUES ('在道路与铁路道口遇到一个红灯亮时要尽快通过道口。', 'A.正确', 'B.错误', NULL, NULL, 'B', 'p#p001.jpg');
INSERT INTO `traffic` VALUES ('机动车发生碰撞时座椅安全带主要作用是什么？', 'A.减轻驾乘人员伤害', 'B.保护驾乘人员腰部', 'C.保护驾乘人员颈部', 'D.保护驾乘人员胸部', 'D', 'x');
INSERT INTO `traffic` VALUES ('机动车在高速公路上发生故障时，在来车方向50至100米处设置警告标志。', 'A.正确', 'B.错误', NULL, NULL, 'B', 'p');
INSERT INTO `traffic` VALUES ('下长坡连续使用行车制动会导致什么？', 'A.会缩短发动机寿命', 'B.增加驾驶人的劳动强度', 'C.会使制动器温度升高而使制动效果急剧下降', 'D.容易造成车辆倾翻', 'C', 'x');
INSERT INTO `traffic` VALUES ('季少华的真实名称是？', 'A.华可列夫斯基', 'B.华少北', 'C.季多芬', 'D.中国十华', 'D', 'x#erciyuan.png');

SET FOREIGN_KEY_CHECKS = 1;
