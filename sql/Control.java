/*
  ʹ�û�������:
*/
package sql;
import java.sql.*;
public class Control {
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("�����������ݿ�...");
        Connection conn=DriverManager.getConnection(URL,User,Pass);
        if(!conn.isClosed()){
            System.out.println("�������ݿ�ɹ�!");
        }
        //ִ�в�ѯ
        Statement stmt=(Statement)conn.createStatement(); //ʵ����Statement����
        String sql = "SELECT * FROM traffic";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("���ݿ��ѯ�������:");
        while (rs.next()){
            String subject = rs.getString("��Ŀ����");
            String choiceA = rs.getString("ѡ��A");
            String choiceB = rs.getString("ѡ��B");
            String choiceC = rs.getString("ѡ��C");
            String choiceD = rs.getString("ѡ��D");
            String answer  = rs.getString("��ȷ��");
            String gallery = rs.getString("ͼƬ");
            System.out.println(subject+"\n"+choiceA+" "+choiceB+" "+choiceC+" "+choiceD+"\n��ȷ��:"+answer+" ͼƬ:"+gallery);
        }
        System.out.println("���ݿ��ѯ����");
    }
}
