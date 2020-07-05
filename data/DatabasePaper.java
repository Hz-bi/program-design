/*
    ���ݿ�������������(DatabasePaper.java)
    1.ʹ��ʱ����DatabasePaper.java��ȫ�����뵽RamdomInitTestPaper.java��
    2.ȥ��RamdomInitTestPaper()����ǰ��void
    3.�������ݿ⿪�� mysql�����Ϊ123456���޸�Դ�����е�Pass��
    4.�������ݿ�test ���´�ű�java��traffic
    5.��excel����������ݻ���sql�ļ����µ�.sql���ļ���test���ݿ���
    6.�Ҽ�AppWindow.java���м����������ݿ��������
    Update Latest:SQL��̬�л���ѯ������jdbc�����߶ȷ�װ���޸�.sql�ļ���ȡ�쳣����
 */
package data;
import java.util.*;
import java.sql.*;
public class DatabasePaper implements GiveTestPaper {
    TestPaper testPaper;                                                    //�Ծ�
    Problem[] problem;                                                      //������
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    static String sql;                                                      //SQL��ѯ��䣬��Ϊ���ݿ����������
    String[][] cell = new String[100][50];
    String subject;                                                         //��Ŀ����
    String choiceA;                                                         //ѡ��A
    String choiceB;                                                         //ѡ��B
    String choiceC;                                                         //ѡ��C
    String choiceD;                                                         //ѡ��D
    String answer;                                                          //��ȷ��
    String gallery;                                                         //ͼƬ
    public void RamdomInitTestPaper() {
        testPaper = new TestPaper();
    }

    @Override
    public TestPaper getTestPaper(String excelFileName, int amount) {
        try {
            randomGiveProblem(excelFileName,amount);                        //�������amount�����⣬��������randomGiveProblem����
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("������������ͣ��������");
            System.exit(0);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        testPaper.setProblem(problem);                                      //�Ծ������õ�һ��������problem
        return testPaper;                                                   //�����Ծ�
    }

    private void randomGiveProblem(String excelFileName,int amount) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("�����������ݿ�...");
        Connection conn = DriverManager.getConnection(URL, User, Pass);
        if (!conn.isClosed()) {
            System.out.println("�������ݿ�ɹ�!");
        }                                                                   //ʵ����Statement���󡢶�̬��ѯ���ݿ��
        Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        if(excelFileName.contains("��ͨ")){
            sql = "SELECT * FROM traffic";                                  //�л���Ӧ�� ���ı�������
        }
        if(excelFileName.contains("java")){
            sql = "SELECT * FROM java";                                     //�л���Ӧ�� ���ı�������
        }
        ResultSet rs = stmt.executeQuery(sql);
        int dataNum_col = rs.getMetaData().getColumnCount();                //���ݼ�¼������
        rs.last();
        int dataNum_row = rs.getRow();                                      //���ݼ�¼������
        rs.first();
        int index = 0;
        do{ //�����ݿ���Ŀ�洢������
            subject = rs.getString("��Ŀ����");
            choiceA = rs.getString("ѡ��A");
            choiceB = rs.getString("ѡ��B");
            choiceC = rs.getString("ѡ��C");
            choiceD = rs.getString("ѡ��D");
            answer = rs.getString("��ȷ��");
            gallery = rs.getString("ͼƬ");
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
        }while(rs.next());                                                  //do while���÷�װ
        problem = new Problem[dataNum_row];                                 //���ڴ�����������problem
        Random random = new Random();
        for (int i = 0; i < dataNum_row; i++) {
            problem[i] = new Problem();                                     //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
            int number = i+1;                                               //cell�ĵ�0�����������ݣ�������0��ʼ
            problem[i].setContent("��" + number + "��." + cell[i][0].trim());
            problem[i].setCorrectAnswer(cell[i][1].trim());
            problem[i].setGiveChoiceA(cell[i][2].trim());
            problem[i].setGiveChoiceB(cell[i][3].trim());
            problem[i].setGiveChoiceC(cell[i][4].trim());
            problem[i].setGiveChoiceD(cell[i][5].trim());
            String typeStr = cell[i][6].trim();                             //��������ͣ��жϻ�ѡ��
            if (typeStr.equalsIgnoreCase("p")) {                //ע�⣬��Ϊ������ͼ������typeStr�����֣�p,p#,x,x#:
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                problem[i].setImageName("�������/ͼ�����/havenot.jpg");
            }
            if (typeStr.equalsIgnoreCase("x")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                problem[i].setImageName("�������/ͼ�����/havenot.jpg");
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
//�ϵ�����ý��ּܣ���ɾ
//System.out.println("����"+rs.getMetaData().getColumnCount()+"������");
//System.out.println("���ݿ⹲��:"+dataNum_row+"������");
//System.out.println("���ݿ��ѯ�������:");
//System.out.println("��"+index+"��"+Arrays.toString(cell[index]));
//System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n��ȷ��:" + answer + " ͼƬ:" + gallery + "\n");
//System.out.println("��"+index+"��"+Arrays.toString(cell[index]));
//System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n��ȷ��:" + answer + " ͼƬ:" + gallery + "\n");
//System.out.println("iֵΪ:"+i);