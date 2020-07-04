/*
    ���ݿ�������������(DatabasePaper.java)
    ʹ��ʱ����DatabasePaper.java��ȫ�����뵽RamdomInitTestPaper.java��
    ȥ����20�е�public void RamdomInitTestPaper()�е�void
    �������ݿ⿪�� mysql�����Ϊ123456
    �������ݿ�test ���´�ű�java��traffic����excel�����������
    �Ҽ�AppWindow.java���м���
 */
package data;
import java.util.Arrays;
import java.util.*;
import java.sql.*;
public class DatabasePaper implements GiveTestPaper {   //����������Ծ�����
    TestPaper testPaper;//�Ծ�
    Problem[] problem; //����Ծ��һ���⣨problem�ĵ�Ԫ���һ�����⼴һ��Problem����
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    static final String User = "root";
    static final String Pass = "123456";
    String[][] cell = new String[100][50];//������ݿ��ȡ�������Ϣ,100��50������
    public void RamdomInitTestPaper() {
        testPaper = new TestPaper();
    }

    //�����ú���
    @Override
    public TestPaper getTestPaper(String excelFileName, int amount) {
        try {
            randomGiveProblem(amount);//�������amount�����⣬��������randomGiveProblem����
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("������������ͣ��������");
            System.exit(0);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        testPaper.setProblem(problem);//�Ծ������õ�һ��������problem
        return testPaper;         //�����Ծ�
    }

    //��������ת�ƺ���
    private void randomGiveProblem(int amount) throws ClassNotFoundException, SQLException { //�������amount������,����problem������
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("�����������ݿ�...");
        Connection conn = DriverManager.getConnection(URL, User, Pass);
        if (!conn.isClosed()) {
            System.out.println("�������ݿ�ɹ�!");
        }
        //ִ�в�ѯ
        Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //ʵ����Statement����
        String sql = "SELECT * FROM traffic";//�л���Ӧ�� ���ı�������
        ResultSet rs = stmt.executeQuery(sql);
        int dataNum_col = rs.getMetaData().getColumnCount();//����
        //System.out.println("����"+rs.getMetaData().getColumnCount()+"������");
        rs.last();
        int dataNum_row = rs.getRow();//����
        //System.out.println("���ݿ⹲��:"+dataNum_row+"������");
        rs.first();
        //System.out.println("���ݿ��ѯ�������:");
        int index = 0;
        String subject = rs.getString("��Ŀ����");
        String choiceA = rs.getString("ѡ��A");
        String choiceB = rs.getString("ѡ��B");
        String choiceC = rs.getString("ѡ��C");
        String choiceD = rs.getString("ѡ��D");
        String answer = rs.getString("��ȷ��");
        String gallery = rs.getString("ͼƬ");
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
        //System.out.println("��"+index+"��"+Arrays.toString(cell[index]));
        //System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n��ȷ��:" + answer + " ͼƬ:" + gallery + "\n");
        index++;
        while (rs.next()) {
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
            //System.out.println("��"+index+"��"+Arrays.toString(cell[index]));
            //System.out.println(subject + "\n" + choiceA + "\n" + choiceB + "\n" + choiceC + "\n" + choiceD + "\n��ȷ��:" + answer + " ͼƬ:" + gallery + "\n");
            index++;
        }
        //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
        problem = new Problem[dataNum_row];              //���ڴ�����������problem
        Random random = new Random();
        for (int i = 0; i < dataNum_row; i++) {
            //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
            //cell�ĵ�0�����������ݣ�������0��ʼ
            //System.out.println("iֵΪ:"+i);
            problem[i] = new Problem();
            int number = i+1;
            problem[i].setContent("��" + number + "��." + cell[i][0].trim());//���������
            problem[i].setCorrectAnswer(cell[i][1].trim());//����Ĵ�
            problem[i].setGiveChoiceA(cell[i][2].trim());  //�����Aѡ��
            problem[i].setGiveChoiceB(cell[i][3].trim());  //�����Bѡ��
            problem[i].setGiveChoiceC(cell[i][4].trim());  //�����Cѡ��
            problem[i].setGiveChoiceD(cell[i][5].trim());  //�����D��
            String typeStr = cell[i][6].trim();//��������ͣ��жϻ�ѡ��
            //ע�⣬��Ϊ������ͼ������typeStr�����֣�p,p#,x,x#:
            if (typeStr.equalsIgnoreCase("p")) {
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
