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

 Date: 04/07/2020 23:47:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for java
-- ----------------------------
DROP TABLE IF EXISTS `java`;
CREATE TABLE `java`  (
  `题目内容` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择A` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择B` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择C` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择D` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `正确答案` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `图片` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of java
-- ----------------------------
INSERT INTO `java` VALUES ('对于下列Hello类，哪个叙述是正确的？class Hello {\r\n     Hello(int m){\r\n     }\r\n     int Hello() {\r\n         return 20;\r\n     }\r\n     hello() {\r\n     }\r\n}', 'A．Hello类有2个构造方法', 'B．Hello类的int Hello()方法是错误的方法', 'C．Hello类没有构造方法。', 'D．Hello无法通过编译，因为其中的hello方法的方法头是错误的（没有类型）。', 'D', 'x#java001.jpg');
INSERT INTO `java` VALUES ('对于下列Dog类，哪个叙述是错误的？class Dog {\r\n     Dog(int m){ \r\n     }\r\n     Dog(double  m){ \r\n     }\r\n     int Dog(int m){ \r\n        return 23;\r\n     }\r\n     void Dog(double m){\r\n     }\r\n}', 'A．Dog(int m)与Dog(double m)是互为重载的构造方法。', 'B．int Dog(int m)与void Dog(double m)是互为重载的非构造方法。', 'C．Dog类只有两个构造方法，而且没有无参数的构造方法。', 'D．Dog类有3个构造方法。', 'C', 'x#java002.jpg');
INSERT INTO `java` VALUES ('下列E类的类体中哪些【代码】是错误的。class E {\r\n  int x;               //【代码1】\r\n  long y = x;         //【代码2】\r\n  public void f(int n) {\r\n     int m;           //【代码3】 \r\n     int t = n+m;    //【代码4】   \r\n  } \r\n}', 'A.【代码1】是错误的', 'B.【代码2】是错误的', 'C.【代码3】是错误的', 'D.【代码4】是错误的', 'D', 'x#java003.jpg');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？', 'A．成员变量的名字不可以和局部变量的名字相同。', 'B．方法的参数的名字可以和方法中声明的局部变量的名字相同。', 'C．成员变量没有默认值。', 'D．局部变量没有默认值。', 'D', 'x');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？\r\nA．子类继承父类的构造方法\r\nB．abstract类的子类必须是非abstract类\r\nC．子类继承的方法只能操作子类继承和隐藏的成员变量\r\nD．子类重写或新增的方法也能直接操作被子类隐藏的成员变量', '', NULL, NULL, NULL, 'C', 'x');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？\r\nA．final 类可以有子类\r\nB．abstract类中只可以有abstract方法\r\nC．abstract类中可以有非abstract方法，但该方法不可以用final修饰\r\nD．不可以同时用final和abstract修饰同一个方法', NULL, NULL, NULL, NULL, 'D', 'x');
INSERT INTO `java` VALUES ('下列程序中注释的哪两个代码（A，B，C，D）是错误的（无法通过编译）？class Father {\r\n   private int money =12;\r\n   float height;\r\n   int seeMoney(){\r\n      return money ;           //A\r\n   } \r\n}\r\nclass Son extends Father {\r\n   int height;\r\n   int lookMoney() {\r\n      int m = seeMoney();    //B\r\n      return m;             \r\n   }\r\n}\r\nclass E {\r\n   public static void main(String args[]) {\r\n      Son erzi = new Son();\r\n      erzi.money = 300;      //C\r\n      erzi.height = 1.78F;  //D\r\n   }\r\n}', NULL, NULL, NULL, NULL, 'D', 'x#java004.jpg');

SET FOREIGN_KEY_CHECKS = 1;
