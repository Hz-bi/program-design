/*
  ʹ�û�������:MySQL8.0 Navicat Premium12.0
  ��������:mysql-connector-java-8.0.20.jar
  Control.java�ڶ����޸�:�޸Ĳ��ֻ��з�������null�ַ���Ϊ�ա���Ӳ��ֹؼ�ע��
*/
package sql;
import java.sql.*;
public class Control {
    //����ȷ��ʹ�ú���test���ݿ������������ �û���Ĭ��root �������Ϊ�������ݿ����õ�����
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
        String sql = "SELECT * FROM traffic";//�л���Ӧ�� ���ı�������
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
            if(choiceA==null)choiceA="";
            if(choiceB==null)choiceB="";
            if(choiceC==null)choiceC="";
            if(choiceD==null)choiceD="";
            System.out.println(subject+"\n"+choiceA+"\n"+choiceB+"\n"+choiceC+"\n"+choiceD+"\n��ȷ��:"+answer+" ͼƬ:"+gallery+"\n");
        }
        System.out.println("���ݿ��ѯ����");
    }
}
