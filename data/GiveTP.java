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
public class GiveTP implements GiveTestPaper{                  //抽取题号为3的倍数的题目
    TestPaper testPaper ;//试卷
    File fileExcel;
    InputStream in = null;
    Problem [] problem; //组成试卷的一套题（problem的单元存放一道试题即一个Problem对象）
    Workbook wb = null;  //封装Excel,Workbook是jxl包中的类
    Sheet sheet = null;  //封装Excel中的sheet，Sheet是jxl包中的类
    LinkedList<Integer> list;     //抽取试题时用
    public GiveTP(){
        testPaper = new TestPaper();
        list = new LinkedList<Integer>();
    }
    public TestPaper getTestPaper(String excelFileName,int amount) {
        int c=0;                                                            //0为默认值————随机选题
                                                                            //1————顺序选题
                                                                            //2————3的倍数选题
        boolean b =setExcel(excelFileName);  //设置用户存放试题的电子表格
        if(b) {
            try {
                c=GiveChoice();
                GiveProblem_2(amount,c);//随机给出amount道试题，见类后面的GiveProblem_2方法
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("试题必须有类型，请检查题库");
                System.exit(0);
            }
            testPaper.setProblem(problem);//试卷上设置的一套试题是problem
            return testPaper;         //返回试卷
        }
        else {
            JOptionPane.showMessageDialog
                    (null,"没有预备题库","消息对话框",JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    private boolean setExcel(String excelFileName){
        boolean b =true;
        try {
            fileExcel =new File(excelFileName);
            in =new FileInputStream(fileExcel);
            testPaper.setProblemSource(fileExcel.getAbsolutePath());//试卷设置题库来源
        }
        catch(IOException exp){
            JOptionPane.showMessageDialog
                    (null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
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
    private int GiveChoice(){
        int c=0;
        Random r=new Random();
        c=r.nextInt(4);
        switch(c){
            case 0:
                String s0 ="考试即将开始，您的题目排列为：随机";
                JLabel mess0 = new JLabel(s0);
                JOptionPane.showMessageDialog(null,mess0,"题目排列",JOptionPane.PLAIN_MESSAGE );
                break;
            case 1:
                String s1 ="考试即将开始，您的题目排列为：顺序";
                JLabel mess1 = new JLabel(s1);
                JOptionPane.showMessageDialog(null,mess1,"题目排列",JOptionPane.PLAIN_MESSAGE );
                break;
            case 2:
                String s ="考试即将开始，您的题目排列为：3的倍数";
                JLabel mess2 = new JLabel(s);
                JOptionPane.showMessageDialog(null,mess2,"题目排列",JOptionPane.PLAIN_MESSAGE );
        }
        return c;
    }
    private void GiveProblem_2(int amount,int choi){
        if(wb==null) {
            JOptionPane.showMessageDialog
                    (null, "没有预备题库Excel", "消息对话框", JOptionPane.WARNING_MESSAGE);
            return;
        }
        sheet = wb.getSheet(0);//得到Excel中的第一个sheet（索引从0开始）
        int rowsAmount = sheet.getRows();     //得到sheet的总行数
        //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
        int realAmount = Math.min(amount,rowsAmount-1);//实际抽取的试题数量
        problem = new Problem[realAmount];              //用于存放试题的数组problem
        if(choi==0) {
            list.clear();
            for(int i=0;i<rowsAmount-1;i++){  //将1至rowsAmount-1数字放入链表
                list.add(i+1);
            }
            Random random=new Random();
            for(int i=0;i<problem.length;i++) {
                int m = random.nextInt(list.size());//[0,list.size())中一个随机数
                int index =list.remove(m);//删除list的第m个节点，同时得节点数字
                Cell [] cell = sheet.getRow(index); //返回sheet中的第index行
                //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
                //cell的第0列是试题内容，索引从0开始
                problem[i] = new Problem();
                Question(problem[i],i,cell);
            }
        }
        else if(choi==2) {
            list.clear();
            for (int i = 1; i <= rowsAmount - 1; i++) {  //将1至rowsAmount-1中3的倍数放入链表
                if (i % 3 == 0)
                    list.add(i);
            }
            for (int i = 0; i < problem.length; i++) {
                int index = list.remove(0);//删除list的第0个节点，同时得节点数字
                Cell[] cell = sheet.getRow(index);
                //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
                //cell的第0列是试题内容，索引从0开始
                problem[i] = new Problem();
                Question(problem[i],i,cell);

            }
        }
        else if(choi==1){
            for(int i=0;i<problem.length;i++) {
                Cell [] cell = sheet.getRow(i+1); //返回sheet中的第index行
                //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
                //cell的第0列是试题内容，索引从0开始
                problem[i] = new Problem();
                Question(problem[i],i,cell);
            }
        }
    }
    private void Question(Problem problem,int i, Cell[] cell){
        int number=i+1;
        problem.setContent("第"+number+"题."+cell[0].getContents());//试题的内容
        problem.setCorrectAnswer(cell[1].getContents().trim());//试题的答案
        problem.setGiveChoiceA(cell[2].getContents().trim());  //试题的A选择
        problem.setGiveChoiceB(cell[3].getContents().trim());  //试题的B选择
        problem.setGiveChoiceC(cell[4].getContents().trim());  //试题的C选择
        problem.setGiveChoiceD(cell[5].getContents().trim());  //试题的D答案
        String typeStr = cell[6].getContents().trim();//试题的类型（判断或选择）
        //注意，因为试题有图像，所以typeStr有四种：p,p#,x,x#:
        if(typeStr.equalsIgnoreCase("p")){
            problem.setIsJudge(true);
            problem.setIsChoice(false);
            problem.setImageName("软件发布/图像管理/havenot.jpg");
        }
        if(typeStr.equalsIgnoreCase("x")) {
            problem.setIsJudge(false);
            problem.setIsChoice(true);
            problem.setImageName("软件发布/图像管理/havenot.jpg");
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
