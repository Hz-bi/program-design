package data;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
public class GiveJTP implements GiveTestPaper{                  //Java���Գ�������
    TestPaper testPaper ;//�Ծ�
    File fileExcel;
    InputStream in = null;
    Problem [] problem; //����Ծ��һ���⣨problem�ĵ�Ԫ���һ�����⼴һ��Problem����
    Workbook wb = null;  //��װExcel,Workbook��jxl���е���
    Sheet sheet = null;  //��װExcel�е�sheet��Sheet��jxl���е���
    LinkedList<Integer> list;     //��ȡ����ʱ��
    public GiveJTP(){
        testPaper = new TestPaper();
        list = new LinkedList<Integer>();
    }
    public TestPaper getTestPaper(String excelFileName,int amount) {
        int c=0;                                                            //0ΪĬ��ֵ�����������ѡ��
        //����0��������˳��ѡ��
        //С��0��������3�ı���ѡ��
        boolean b =setExcel(excelFileName);  //�����û��������ĵ��ӱ��
        if(b) {
            try {
                GiveProblem_2(amount,c);//�������amount�����⣬��������GiveProblem_1����
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
    private void GiveProblem_2(int amount,int choi){
        if(wb==null) {
            JOptionPane.showMessageDialog
                    (null, "û��Ԥ�����Excel", "��Ϣ�Ի���", JOptionPane.WARNING_MESSAGE);
            return;
        }
        sheet = wb.getSheet(0);//�õ�Excel�еĵ�һ��sheet��������0��ʼ��
        int rowsAmount = sheet.getRows();     //�õ�sheet��������
        //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
        int realAmount = Math.min(amount,rowsAmount-1);//ʵ�ʳ�ȡ����������
        problem = new Problem[realAmount];              //���ڴ�����������problem
        if(choi==0) {
            list.clear();
            for(int i=0;i<rowsAmount-1;i++){  //��1��rowsAmount-1���ַ�������
                list.add(i+1);
            }
            Random random=new Random();
            for(int i=0;i<problem.length;i++) {
                int m = random.nextInt(list.size());//[0,list.size())��һ�������
                int index =list.remove(m);//ɾ��list�ĵ�m���ڵ㣬ͬʱ�ýڵ�����
                Cell [] cell = sheet.getRow(index); //����sheet�еĵ�index��
                //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
                //cell�ĵ�0�����������ݣ�������0��ʼ
                problem[i] = new Problem();
                Question(problem[i],i,cell);
            }
        }
        else if(choi<0) {
            list.clear();
            for (int i = 1; i <= rowsAmount - 1; i++) {  //��1��rowsAmount-1��3�ı�����������
                if (i % 3 == 0)
                    list.add(i);
            }
            for (int i = 0; i < problem.length; i++) {
                int index = list.remove(0);//ɾ��list�ĵ�0���ڵ㣬ͬʱ�ýڵ�����
                Cell[] cell = sheet.getRow(index);
                //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
                //cell�ĵ�0�����������ݣ�������0��ʼ
                problem[i] = new Problem();
                Question(problem[i],i,cell);

            }
        }
        else{
            for(int i=0;i<problem.length;i++) {
                Cell [] cell = sheet.getRow(i+1); //����sheet�еĵ�index��
                //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
                //cell�ĵ�0�����������ݣ�������0��ʼ
                problem[i] = new Problem();
                Question(problem[i],i,cell);
            }
        }
    }
    private void Question(Problem problem,int i, Cell[] cell){
        int number=i+1;
        problem.setContent("��"+number+"��."+cell[0].getContents());//���������
        problem.setCorrectAnswer(cell[1].getContents().trim());//����Ĵ�
        problem.setGiveChoiceA(cell[2].getContents().trim());  //�����Aѡ��
        problem.setGiveChoiceB(cell[3].getContents().trim());  //�����Bѡ��
        problem.setGiveChoiceC(cell[4].getContents().trim());  //�����Cѡ��
        problem.setGiveChoiceD(cell[5].getContents().trim());  //�����D��
        String typeStr = cell[6].getContents().trim();//��������ͣ��жϻ�ѡ��
        //ע�⣬��Ϊ������ͼ������typeStr�����֣�p,p#,x,x#:
        if(typeStr.equalsIgnoreCase("p")){
            problem.setIsJudge(true);
            problem.setIsChoice(false);
            problem.setImageName("�������/ͼ�����/havenot.jpg");
        }
        if(typeStr.equalsIgnoreCase("x")) {
            problem.setIsJudge(false);
            problem.setIsChoice(true);
            problem.setImageName("�������/ͼ�����/havenot.jpg");
        }
        if(typeStr.startsWith("p#")||typeStr.startsWith("P#")) {
            problem.setIsJudge(true);
            problem.setIsChoice(false);
            String imageName = typeStr.substring(typeStr.indexOf("#")+1);
            problem.setImageName(imageName);
        }
        if(typeStr.startsWith("x#")||typeStr.startsWith("X#")) {
            problem.setIsJudge(false);
            problem.setIsChoice(true);
            String imageName = typeStr.substring(typeStr.indexOf("#")+1);
            problem.setImageName(imageName);
        }
    }
}
