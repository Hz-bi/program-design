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

 Date: 05/07/2020 23:36:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for traffic
-- ----------------------------
DROP TABLE IF EXISTS `traffic`;
CREATE TABLE `traffic`  (
  `题目内容` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `正确答案` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `题目类型` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of traffic
-- ----------------------------
INSERT INTO `traffic` VALUES ('遇到这种情况的路口，以下做法正确的是什么？', 'B', 'A.沿左侧车道掉头', 'B.该路口不能掉头', 'C.选择中间车道掉头', 'D.在路口内掉头', 'x#x001.jpg');
INSERT INTO `traffic` VALUES ('这个标志是何含义？', 'B', 'A.无人看守铁路道口', 'B.有人看守铁路道口', 'C.立交式的铁路道口', 'D.多股铁路与道路相交', 'x#x002.jpg');
INSERT INTO `traffic` VALUES ('拼装的机动车只要认为安全就可以上路行驶。', 'B', 'A.正确', 'B.错误', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('在道路与铁路道口遇到一个红灯亮时要尽快通过道口。', 'B', 'A.正确', 'B.错误', NULL, NULL, 'p#p001.jpg');
INSERT INTO `traffic` VALUES ('机动车发生碰撞时座椅安全带主要作用是什么？', 'D', 'A.减轻驾乘人员伤害', 'B.保护驾乘人员腰部', 'C.保护驾乘人员颈部', 'D.保护驾乘人员胸部', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('机动车在高速公路上发生故障时，在来车方向50至100米处设置警告标志。', 'B', 'A.正确', 'B.错误', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('下长坡连续使用行车制动会导致什么？', 'C', 'A.会缩短发动机寿命', 'B.增加驾驶人的劳动强度', 'C.会使制动器温度升高而使制动效果急剧下降', 'D.容易造成车辆倾翻', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('李某驾驶一辆大客车，乘载21人(核载35人)，行驶途中察觉制动装置有异常但未处理，行至双岛海湾大桥时时速为50公里(该路段限速40公里)，因制动失灵坠入海中，造成13人死亡、8人受伤。李某的主要违法行为是什么?(多选)', 'AD', 'A.超速行驶', 'B.疲劳驾驶', 'C.客车超员', 'D.驾驶具有安全隐患的机动车', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('机动车驾驶人因违反交通运输管理法规，而发生重大事故，致人重伤、死亡的处3年以上有期徒刑或者拘役。', 'B', 'A.对 ', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('掉头时，应观察是否有禁止掉头的标志，严禁在不准掉头的的区域掉头。', 'A', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('车辆驶近人行横道时，应_________。', 'D', 'A.加速通过', 'B.立即停车', 'C.鸣喇叭示意行人让道 ', 'D.先注意观察行人、非机动车动态，再通过', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('吴某驾驶一辆大客车，乘载33人(核载22人)，行至163县道7公里加300米处时，机动车失控坠入山沟，造成10人死亡、21人受伤。事后经酒精检测，吴某血液酒精含量为26毫克/百毫升。吴某的主要违法行为是什么?（多选）', 'BD', 'A.超速行驶', 'B.客车超员', 'C.疲劳驾驶', 'D.酒后驾驶', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('醉酒后驾驶机动车的，公安机关交通管理部门可以扣留驾驶证。', 'A', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('高速公路安全距离确认路段，可供驾驶人确认在每小时______速度时的安全距离。', 'B', 'A.120公里 ', 'B.100公里 ', 'C.90公里', 'D.60公里', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('行车中当车辆前轮爆胎已发生转向时，驾驶人应双手紧握转向盘，尽力控制车辆直线行驶。', 'A', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('通过铁路道口时，不得超车。', 'A', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('行车中遇到对向来车占道行驶，应__________。', 'B', 'A.紧靠道路中心行驶', 'B.主动给对方让行 ', 'C.用大灯警示对方', 'D.逼对方靠右行驶', 'x#timg.jpg');
INSERT INTO `traffic` VALUES ('仪表板上的“ ”灯亮，是提醒驾驶人座椅位置调整不当。', 'B', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');
INSERT INTO `traffic` VALUES ('对制动失效的被牵引车，可以用软连接装置牵引。', 'B', 'A.对', 'B.错', NULL, NULL, 'p#timg.jpg');

SET FOREIGN_KEY_CHECKS = 1;
