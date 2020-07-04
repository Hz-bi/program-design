/*
  使用环境依赖:
*/
package sql;
import java.sql.*;
public class Control {
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("正在连接数据库...");
        Connection conn=DriverManager.getConnection(URL,User,Pass);
        if(!conn.isClosed()){
            System.out.println("连接数据库成功!");
        }
        //执行查询
        Statement stmt=(Statement)conn.createStatement(); //实例化Statement对象
        String sql = "SELECT * FROM traffic";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("数据库查询结果如下:");
        while (rs.next()){
            String subject = rs.getString("题目内容");
            String choiceA = rs.getString("选择A");
            String choiceB = rs.getString("选择B");
            String choiceC = rs.getString("选择C");
            String choiceD = rs.getString("选择D");
            String answer  = rs.getString("正确答案");
            String gallery = rs.getString("图片");
            System.out.println(subject+"\n"+choiceA+" "+choiceB+" "+choiceC+" "+choiceD+"\n正确答案:"+answer+" 图片:"+gallery);
        }
        System.out.println("数据库查询结束");
    }
}
