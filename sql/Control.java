/*
  使用环境依赖:MySQL8.0 Navicat Premium12.0
  驱动依赖:mysql-connector-java-8.0.20.jar
  Control.java第二次修改:修改部分换行符、设置null字符串为空、添加部分关键注释
*/
package sql;
import java.sql.*;
public class Control {
    //必须确保使用含有test数据库才能正常加载 用户名默认root 密码更改为个人数据库设置的密码
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
        String sql = "SELECT * FROM traffic";//切换对应表 更改表名即可
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
            if(choiceA==null)choiceA="";
            if(choiceB==null)choiceB="";
            if(choiceC==null)choiceC="";
            if(choiceD==null)choiceD="";
            System.out.println(subject+"\n"+choiceA+"\n"+choiceB+"\n"+choiceC+"\n"+choiceD+"\n正确答案:"+answer+" 图片:"+gallery+"\n");
        }
        System.out.println("数据库查询结束");
    }
}
