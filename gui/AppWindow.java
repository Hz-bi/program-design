package gui;
import data.GiveTestPaper;
import data.GiveTP;
import data.GiveJTP;
import data.TestPaper;
import data.TeacherOne;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import view.TestPaperView;
import view.IntegrationView;
public class AppWindow {
   public static void main(String []args) {
       try
       {
           //设置本属性将改变窗口边框样式定义
           BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
           org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
       }
       catch(Exception e)
       {
           //TODO exception
       }
       String testName="";
       IntegrationView integrationView = new IntegrationView();
       GiveTestPaper initTestPaper = new GiveTP(); //创建初始试卷对象
       TestPaper testPaper= initTestPaper.getTestPaper("软件发布/题库/交通理论.xls",5);   //得到有5个题目的试卷
       TestPaperView testView = new TestPaperView();
       testView.setTestPaper(testPaper);        //设置试卷
       testView.setTeacher(new TeacherOne()); //设置阅卷老师
       testName = "交通法训练";
       testView.setTestName(testName);
       testView.setTotalTime(900);//考试时间15分钟,900
       integrationView.addTestPaperView(testName,testView);
       initTestPaper = new GiveJTP(); //创建初始试卷对象
       testPaper= initTestPaper.getTestPaper("软件发布/题库/java基础.xls",6);
       testView = new TestPaperView();
       testView.setTestPaper(testPaper);        
       testView.setTeacher(new TeacherOne());
       testName = "Java训练"; 
       testView.setTestName(testName);
       testView.setTotalTime(600);//考试时间10分钟，600
       integrationView.addTestPaperView(testName,testView);
   }
}
