package data;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import jxl.*;
public class GiveTP_two implements GiveTestPaper{                  //��ȡ���Ϊ3�ı�������Ŀ
    TestPaper testPaper ;//�Ծ�
    File fileExcel;
    InputStream in = null;
    Problem [] problem; //����Ծ��һ���⣨problem�ĵ�Ԫ���һ�����⼴һ��Problem����
    Workbook wb = null;  //��װExcel,Workbook��jxl���е���
    Sheet sheet = null;  //��װExcel�е�sheet��Sheet��jxl���е���
    LinkedList<Integer> list;     //��ȡ����ʱ��
    public GiveTP_two(){
        testPaper = new TestPaper();
        list = new LinkedList<Integer>();
    }
    public TestPaper getTestPaper(String excelFileName,int amount) {
        boolean b =setExcel(excelFileName);  //�����û��������ĵ��ӱ��
        if(b) {
            try {
                GiveProblem_2(amount);//�������amount�����⣬��������GiveProblem_1����
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("������������ͣ��������");
                System.exit(0);
            }
            testPaper.setProblem(problem);//�Ծ������õ�һ��������problem
            return testPaper;         //�����Ծ�
        }
        else {
            JOptionPane.showMessageDialog
                    (null,"û��Ԥ�����","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    private boolean setExcel(String excelFileName){
        boolean b =true;
        try {
            fileExcel =new File(excelFileName);
            in =new FileInputStream(fileExcel);
            testPaper.setProblemSource(fileExcel.getAbsolutePath());//�Ծ����������Դ
        }
        catch(IOException exp){
            JOptionPane.showMessageDialog
                    (null,"û��Ԥ�����Excel","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            b = false;
        }
        try {
            wb=Workbook.getWorkbook(in);
            in.close();
        }
        catch(Exception exp){
            b = false;
        }
        return b;
    }
    private void GiveProblem_2(int amount){
        list.clear();
        if(wb==null) {
            JOptionPane.showMessageDialog
                    (null,"û��Ԥ�����Excel","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        sheet = wb.getSheet(0);//�õ�Excel�еĵ�һ��sheet��������0��ʼ��
        int rowsAmount = sheet.getRows();     //�õ�sheet��������
        //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
        int realAmount = Math.min(amount,rowsAmount-1);//ʵ�ʳ�ȡ����������
        problem = new Problem[realAmount];              //���ڴ�����������problem
        for(int i=1;i<=rowsAmount-1;i++){  //��1��rowsAmount-1��3�ı�����������
            if(i%3==0)
            list.add(i);
        }
        for(int i=0;i<problem.length;i++) {
            int index =list.remove(0);//ɾ��list�ĵ�0���ڵ㣬ͬʱ�ýڵ�����
            Cell [] cell = sheet.getRow(index);
            //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
            //cell�ĵ�0�����������ݣ�������0��ʼ
            problem[i] = new Problem();
            int number = i+1;
            problem[i].setContent("Question"+number+":"+cell[0].getContents());//���������
            problem[i].setCorrectAnswer(cell[1].getContents().trim());//����Ĵ�
            problem[i].setGiveChoiceA(cell[2].getContents().trim());  //�����Aѡ��
            problem[i].setGiveChoiceB(cell[3].getContents().trim());  //�����Bѡ��
            problem[i].setGiveChoiceC(cell[4].getContents().trim());  //�����Cѡ��
            problem[i].setGiveChoiceD(cell[5].getContents().trim());  //�����D��
            String typeStr = cell[6].getContents().trim();//��������ͣ��жϻ�ѡ��
            //ע�⣬��Ϊ������ͼ������typeStr�����֣�p,p#,x,x#:
            if(typeStr.equalsIgnoreCase("p")){
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                problem[i].setImageName("�������/ͼ�����/havenot.jpg");
            }
            if(typeStr.equalsIgnoreCase("x")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                problem[i].setImageName("�������/ͼ�����/havenot.jpg");
            }
            if(typeStr.startsWith("p#")||typeStr.startsWith("P#")) {
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                String imageName = typeStr.substring(typeStr.indexOf("#")+1);
                problem[i].setImageName(imageName);
            }
            if(typeStr.startsWith("x#")||typeStr.startsWith("X#")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                String imageName = typeStr.substring(typeStr.indexOf("#")+1);
                problem[i].setImageName(imageName);
            }
        }
    }
}
