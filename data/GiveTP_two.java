package data;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import jxl.*;
public class GiveTP_two implements GiveTestPaper{                  //抽取题号为3的倍数的题目
    TestPaper testPaper ;//试卷
    File fileExcel;
    InputStream in = null;
    Problem [] problem; //组成试卷的一套题（problem的单元存放一道试题即一个Problem对象）
    Workbook wb = null;  //封装Excel,Workbook是jxl包中的类
    Sheet sheet = null;  //封装Excel中的sheet，Sheet是jxl包中的类
    LinkedList<Integer> list;     //抽取试题时用
    public GiveTP_two(){
        testPaper = new TestPaper();
        list = new LinkedList<Integer>();
    }
    public TestPaper getTestPaper(String excelFileName,int amount) {
        boolean b =setExcel(excelFileName);  //设置用户存放试题的电子表格
        if(b) {
            try {
                GiveProblem_2(amount);//随机给出amount道试题，见类后面的GiveProblem_1方法
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
    private void GiveProblem_2(int amount){
        list.clear();
        if(wb==null) {
            JOptionPane.showMessageDialog
                    (null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        sheet = wb.getSheet(0);//得到Excel中的第一个sheet（索引从0开始）
        int rowsAmount = sheet.getRows();     //得到sheet的总行数
        //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
        int realAmount = Math.min(amount,rowsAmount-1);//实际抽取的试题数量
        problem = new Problem[realAmount];              //用于存放试题的数组problem
        for(int i=1;i<=rowsAmount-1;i++){  //将1至rowsAmount-1中3的倍数放入链表
            if(i%3==0)
            list.add(i);
        }
        for(int i=0;i<problem.length;i++) {
            int index =list.remove(0);//删除list的第0个节点，同时得节点数字
            Cell [] cell = sheet.getRow(index);
            //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
            //cell的第0列是试题内容，索引从0开始
            problem[i] = new Problem();
            int number = i+1;
            problem[i].setContent("Question"+number+":"+cell[0].getContents());//试题的内容
            problem[i].setCorrectAnswer(cell[1].getContents().trim());//试题的答案
            problem[i].setGiveChoiceA(cell[2].getContents().trim());  //试题的A选择
            problem[i].setGiveChoiceB(cell[3].getContents().trim());  //试题的B选择
            problem[i].setGiveChoiceC(cell[4].getContents().trim());  //试题的C选择
            problem[i].setGiveChoiceD(cell[5].getContents().trim());  //试题的D答案
            String typeStr = cell[6].getContents().trim();//试题的类型（判断或选择）
            //注意，因为试题有图像，所以typeStr有四种：p,p#,x,x#:
            if(typeStr.equalsIgnoreCase("p")){
                problem[i].setIsJudge(true);
                problem[i].setIsChoice(false);
                problem[i].setImageName("软件发布/图像管理/havenot.jpg");
            }
            if(typeStr.equalsIgnoreCase("x")) {
                problem[i].setIsJudge(false);
                problem[i].setIsChoice(true);
                problem[i].setImageName("软件发布/图像管理/havenot.jpg");
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
