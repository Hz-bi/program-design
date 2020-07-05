/*
    数据库连接用组卷代码(DatabasePaper.java)
    1.使用时复制DatabasePaper.java的全部代码到RamdomInitTestPaper.java中
    2.去除RamdomInitTestPaper()方法前的void
    3.保持数据库开启 mysql密码改为123456或修改源代码中的Pass项
    4.建立数据库test 旗下存放表java和traffic
    5.由excel导入具体数据或导入sql文件夹下的.sql表文件到test数据库中
    6.右键AppWindow.java运行即可启动数据库题库连接
    Update Latest:SQL动态切换查询表名、jdbc操作高度封装、修复.sql文件读取异常问题
 */
package data;
import java.util.*;
import java.sql.*;
public class DatabasePaper implements GiveTestPaper {
    TestPaper testPaper;                                                    //试卷
    Problem[] problem;                                                      //试题组
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    static String sql;                                                      //SQL查询语句，下为数据库题库存放数组
    String[][] cell = new String[100][50];
    String subject;                                                         //题目内容
    String choiceA;                                                         //选择A
    String choiceB;                                                         //选择B
    String choiceC;                                                         //选择C
    String choiceD;                                                         //选择D
    String answer;                                                          //正确答案
    String gallery;                                                         //图片
    public void RamdomInitTestPaper() {
        testPaper = new TestPaper();
    }

    @Override
    public TestPaper getTestPaper(String excelFileName, int amount) {
        try {
            randomGiveProblem(excelFileName,amount);                        //随机给出amount道试题，见类后面的randomGiveProblem方法
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("试题必须有类型，请检查题库");
            System.exit(0);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        testPaper.setProblem(problem);                                      //试卷上设置的一套试题是problem
        return testPaper;                                                   //返回试卷
    }

    private void randomGiveProblem(String excelFileName,int amount) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("正在连接数据库...");
        Connection conn = DriverManager.getConnection(URL, User, Pass);
        if (!conn.isClosed()) {
            System.out.println("连接数据库成功!");
        }                                                                   //实例化Statement对象、动态查询数据库表
        Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if(excelFileName.contains("交通")){
            sql = "SELECT * FROM traffic";                                  //切换对应表 更改表名即可
        }
        if(excelFileName.contains("java")){
            sql = "SELECT * FROM java";                                     //切换对应表 更改表名即可
        }
        ResultSet rs = stmt.executeQuery(sql);
        int dataNum_col = rs.getMetaData().getColumnCount();                //数据记录总列数
        rs.last();
        int dataNum_row = rs.getRow();                                      //数据记录总行数
        rs.first();
        int index = 0;
        do{ //将数据库题目存储至数组
            subject = rs.getString("题目内容");
            choiceA = rs.getString("选择A");
            choiceB = rs.getString("选择B");
            choiceC = rs.getString("选择C");
            choiceD = rs.getString("选择D");
            answer = rs.getString("正确答案");
            gallery = rs.getString("图片");
            if (choiceA == null) choiceA = "";
            if (choiceB == null) choiceB = "";
            if (choiceC == null) choiceC = "";
            if (choiceD == null) choiceD = "";
            cell[index][0]=subject;
            cell[index][1]=answer;
            cell[index][2]=choiceA;
            cell[index][3]=choiceB;
            cell[index][4]=choiceC;
            cell[index][5]=choiceD;
            cell[index][6]=gallery;
            index++;
        }while(rs.next());                                                  //do while复用封装
        problem = new Problem[dataNum_row];                                 //用于存放试题的数组problem
        Random random = new Random();
        for (int i = 0; i < dataNum_row; i++) {
            problem[i] = new Problem();                                     //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
            int number = i+1;                                               //cell的第0列是试题内容，索引从0开始
            problem[i].setContent("第" + number + "题." + cell[i][0].trim());
            problem[i].setCorrectAnswer(cell[i][1].trim());
            problem[i].setGiveChoiceA(cell[i][2].trim());
            problem[i].setGiveChoiceB(cell[i][3].trim());
            problem[i].setGiveChoiceC(cell[i][4].trim());
            problem[i].setGiveChoiceD(cell[i][5].trim());
            String typeStr = cell[i][6].trim();                             //试题的类型（判断或选择）
            if (typeStr.equalsIgnoreCase("p")) {                //注意，因为试题有图像，所以typeStr有四种：p,p#,x,x#:
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                problem[i].setImageName("软件发布/图像管理/havenot.jpg");
            }
            if (typeStr.equalsIgnoreCase("x")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                problem[i].setImageName("软件发布/图像管理/havenot.jpg");
            }
            if (typeStr.startsWith("p#") || typeStr.startsWith("P#")) {
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                String imageName = typeStr.substring(typeStr.indexOf("#") + 1);
                problem[i].setImageName(imageName);
            }
            if (typeStr.startsWith("x#") || typeStr.startsWith("X#")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                String imageName = typeStr.substring(typeStr.indexOf("#") + 1);
                problem[i].setImageName(imageName);
            }
        }
    }
}
//断点测试用脚手架，勿删
//System.out.println("共计"+rs.getMetaData().getColumnCount()+"列数据");
//System.out.println("数据库共有:"+dataNum_row+"行数据");
//System.out.println("数据库查询结果如下:");
//System.out.println("第"+index+"行"+Arrays.toString(cell[index]));
//System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n正确答案:" + answer + " 图片:" + gallery + "\n");
//System.out.println("第"+index+"行"+Arrays.toString(cell[index]));
//System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n正确答案:" + answer + " 图片:" + gallery + "\n");
//System.out.println("i值为:"+i);