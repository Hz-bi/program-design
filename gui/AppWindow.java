package gui;
import data.GiveTestPaper;
import data.RamdomInitTestPaper;
import data.TestPaper;
import data.TeacherOne;
import view.TestPaperView;
import view.IntegrationView;
public class AppWindow {
   public static void main(String []args) {
       String testName="";
       IntegrationView integrationView = new IntegrationView();
       GiveTestPaper initTestPaper = new RamdomInitTestPaper(); //创建初始试卷对象
       TestPaper testPaper= 
       initTestPaper.getTestPaper("软件发布/题库/交通理论.xls",5);   //得到有5个题目的试卷
       TestPaperView testView = new TestPaperView();
       testView.setTestPaper(testPaper);        //设置试卷
       testView.setTeacher(new TeacherOne()); //设置阅卷老师
       testName = "交通法训练";
       testView.setTestName(testName);
       testView.setTotalTime(900);//考试时间15分钟,900秒
       integrationView.addTestPaperView(testName,testView);
       initTestPaper = new RamdomInitTestPaper(); //创建初始试卷对象
       testPaper= initTestPaper.getTestPaper("软件发布/题库/java基础.xls",6);
       testView = new TestPaperView();
       testView.setTestPaper(testPaper);        
       testView.setTeacher(new TeacherOne());
       testName = "Java训练"; 
       testView.setTestName(testName);
       testView.setTotalTime(10);
       integrationView.addTestPaperView(testName,testView);
   }
}