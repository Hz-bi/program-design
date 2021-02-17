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

 Date: 05/07/2020 23:36:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for java
-- ----------------------------
DROP TABLE IF EXISTS `java`;
CREATE TABLE `java`  (
  `题目内容` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `正确答案` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `选择项目3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `题目类型` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of java
-- ----------------------------
INSERT INTO `java` VALUES ('对于下列Hello类，哪个叙述是正确的？class Hello {\n     Hello(int m){\n     }\n     int Hello() {\n         return 20;\n     }\n     hello() {\n     }\n}\n', 'D', 'A．Hello类有2个构造方法', 'B．Hello类的int Hello()方法是错误的方法', 'C．Hello类没有构造方法。', 'D．Hello无法通过编译，因为其中的hello方法的方法头是错误的（没有类型）。', 'x#java001.jpg');
INSERT INTO `java` VALUES ('对于下列Dog类，哪个叙述是错误的？class Dog {\n     Dog(int m){ \n     }\n     Dog(double  m){ \n     }\n     int Dog(int m){ \n        return 23;\n     }\n     void Dog(double m){\n     }\n}\n', 'C', 'A．Dog(int m)与Dog(double m)是互为重载的构造方法。', 'B．int Dog(int m)与void Dog(double m)是互为重载的非构造方法。', 'C．Dog类只有两个构造方法，而且没有无参数的构造方法。', 'D．Dog类有3个构造方法。', 'x#java002.jpg');
INSERT INTO `java` VALUES ('下列E类的类体中哪些【代码】是错误的。class E {\n  int x;               //【代码1】\n  long y = x;         //【代码2】\n  public void f(int n) {\n     int m;           //【代码3】 \n     int t = n+m;    //【代码4】   \n  } \n}\n', 'D', 'A.【代码1】是错误的', 'B.【代码2】是错误的', 'C.【代码3】是错误的', 'D.【代码4】是错误的', 'x#java003.jpg');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？', 'D', 'A．成员变量的名字不可以和局部变量的名字相同。', 'B．方法的参数的名字可以和方法中声明的局部变量的名字相同。', 'C．成员变量没有默认值。', 'D．局部变量没有默认值。', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？\nA．子类继承父类的构造方法\nB．abstract类的子类必须是非abstract类\nC．子类继承的方法只能操作子类继承和隐藏的成员变量\nD．子类重写或新增的方法也能直接操作被子类隐藏的成员变量\n', 'C', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下列哪个叙述是正确的？\nA．final 类可以有子类\nB．abstract类中只可以有abstract方法\nC．abstract类中可以有非abstract方法，但该方法不可以用final修饰\nD．不可以同时用final和abstract修饰同一个方法\n', 'D', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下列程序中注释的哪两个代码（A，B，C，D）是错误的（无法通过编译）？class Father {\n   private int money =12;\n   float height;\n   int seeMoney(){\n      return money ;           //A\n   } \n}\nclass Son extends Father {\n   int height;\n   int lookMoney() {\n      int m = seeMoney();    //B\n      return m;             \n   }\n}\nclass E {\n   public static void main(String args[]) {\n      Son erzi = new Son();\n      erzi.money = 300;      //C\n      erzi.height = 1.78F;  //D\n   }\n}\n', 'D', 'A', 'B', 'C', 'D', 'x#java004.jpg');
INSERT INTO `java` VALUES ('下列选项中关于Java中封装的说法错误的是在Java中,下面对于构造函数的描述正确的是\n A 类必须显示定义构造函数\n B  构造函数的返回类型是void\n C 构造函数和类有相同的名称,并且不能带任何参数\n D  一个类可以定义多个构造函数', 'D', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下面选项中，（  ）是Java 关键字。\n\nA、then\n\nB、continue\n\nC、java\n\nD、PUBLIC', 'B', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下面语句中，正确的是（  ）。\n\nA、boolean b=”true”;\n\nB、double x=2.5f;\n\nC、char c=”A”;\n\nD、float y=0.8d;', 'B', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('在Java 中，用package 语句说明一个包时，该包的层次结构必须是（  ）。\n\nA、与文件目录的层次相同\n\nB、与文件的结构相同\n\nC、与文件类型相同\n\nD、与文件大小相同', 'A', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下面关于数组的说法，错误的是（  ）。\n\nA、数组是最简单的复合数据类型，是一系列数据的集合\n\nB、声明数组时，必须分配内存\n\nC、数组的元素可以是值（基本数据类型）、对象或其他数组\n\nD、一个数组中的所有值都必须是相同的类型', 'B', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('下面关于方法的说法，错误的是（   ）。\n\nA、Java 中的方法参数传递时传值调用，而不是地址调用\n\nB、方法体是对方法的实现，包括变量声明和Java 的合法语句\n\nC、如果程序定义了一个或多个构造方法，在创建对象时，也可以用系统自\n\n动生成空的构造方法\n\nD、类的私有方法不能被其子类直接访问', 'C', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('在Java 中，用package 语句说明一个包时，该包的层次结构必须是（  ）。\n\nA、与文件目录的层次相同\n\nB、与文件的结构相同\n\nC、与文件类型相同\n\nD、与文件大小相同', 'A', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('在字节流中，可以使用文件名作为参数的类有（  ）。\n\nA、DataInputStream\n\nB、BufferedReader\n\nC、FileInputStream\n\nD、FileReader', 'C', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('假设有一个Java 源程序文件，它只定义了一个具有public 属性的类\n\nHello，那么编译该文件的命令是“javac Hello” 。', 'B', 'A.对', 'B.错', NULL, NULL, 'p#havenot.jpg');
INSERT INTO `java` VALUES ('switch 语句中可以没有default 子句。', 'A', 'A.对', 'B.错', NULL, NULL, 'p#havenot.jpg');
INSERT INTO `java` VALUES ('下面能让线程停止执行的有（多选）( )\n\nA. sleep();\n\nB. stop();\n\nC. notify();\n\nD. synchronized();\n', 'ABD', 'A', 'B', 'C', 'D', 'x#havenot.jpg');
INSERT INTO `java` VALUES ('不能用来修饰interface的有（）(多选)\n\nA．private\n\nB．public\n\nC．protected\n\nD．Static', 'ACD', 'A', 'B', 'C', 'D', 'x#havenot.jpg');

SET FOREIGN_KEY_CHECKS = 1;
