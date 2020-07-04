/*
    数据库连接用组卷代码(DatabasePaper.java)
    使用时复制DatabasePaper.java的全部代码到RamdomInitTestPaper.java中
    去除第20行的public void RamdomInitTestPaper()中的void
    保持数据库开启 mysql密码改为123456
    建立数据库test 旗下存放表java和traffic，由excel导入具体数据
    右键AppWindow.java运行即可
 */
package data;
import java.util.Arrays;
import java.util.*;
import java.sql.*;
public class DatabasePaper implements GiveTestPaper {   //将试题放入试卷（出卷）
    TestPaper testPaper;//试卷
    Problem[] problem; //组成试卷的一套题（problem的单元存放一道试题即一个Problem对象）
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    String[][] cell = new String[100][50];//存放数据库读取的题库信息,100行50列容量
    public void RamdomInitTestPaper() {
        testPaper = new TestPaper();
    }

    //组卷调用函数
    @Override
    public TestPaper getTestPaper(String excelFileName, int amount) {
        try {
            randomGiveProblem(amount);//随机给出amount道试题，见类后面的randomGiveProblem方法
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("试题必须有类型，请检查题库");
            System.exit(0);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        testPaper.setProblem(problem);//试卷上设置的一套试题是problem
        return testPaper;         //返回试卷
    }

    //出卷及数据转移函数
    private void randomGiveProblem(int amount) throws ClassNotFoundException, SQLException { //随机给出amount道试题,放入problem数组中
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("正在连接数据库...");
        Connection conn = DriverManager.getConnection(URL, User, Pass);
        if (!conn.isClosed()) {
            System.out.println("连接数据库成功!");
        }
        //执行查询
        Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //实例化Statement对象
        String sql = "SELECT * FROM traffic";//切换对应表 更改表名即可
        ResultSet rs = stmt.executeQuery(sql);
        int dataNum_col = rs.getMetaData().getColumnCount();//列数
        //System.out.println("共计"+rs.getMetaData().getColumnCount()+"列数据");
        rs.last();
        int dataNum_row = rs.getRow();//行数
        //System.out.println("数据库共有:"+dataNum_row+"行数据");
        rs.first();
        //System.out.println("数据库查询结果如下:");
        int index = 0;
        String subject = rs.getString("题目内容");
        String choiceA = rs.getString("选择A");
        String choiceB = rs.getString("选择B");
        String choiceC = rs.getString("选择C");
        String choiceD = rs.getString("选择D");
        String answer = rs.getString("正确答案");
        String gallery = rs.getString("图片");
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
        //System.out.println("第"+index+"行"+Arrays.toString(cell[index]));
        //System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n正确答案:" + answer + " 图片:" + gallery + "\n");
        index++;
        while (rs.next()) {
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
            //System.out.println("第"+index+"行"+Arrays.toString(cell[index]));
            //System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n正确答案:" + answer + " 图片:" + gallery + "\n");
            index++;
        }
        //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
        problem = new Problem[dataNum_row];              //用于存放试题的数组problem
        Random random = new Random();
        for (int i = 0; i < dataNum_row; i++) {
            //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
            //cell的第0列是试题内容，索引从0开始
            //System.out.println("i值为:"+i);
            problem[i] = new Problem();
            int number = i+1;
            problem[i].setContent("第" + number + "题." + cell[i][0].trim());//试题的内容
            problem[i].setCorrectAnswer(cell[i][1].trim());//试题的答案
            problem[i].setGiveChoiceA(cell[i][2].trim());  //试题的A选择
            problem[i].setGiveChoiceB(cell[i][3].trim());  //试题的B选择
            problem[i].setGiveChoiceC(cell[i][4].trim());  //试题的C选择
            problem[i].setGiveChoiceD(cell[i][5].trim());  //试题的D答案
            String typeStr = cell[i][6].trim();//试题的类型（判断或选择）
            //注意，因为试题有图像，所以typeStr有四种：p,p#,x,x#:
            if (typeStr.equalsIgnoreCase("p")) {
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
