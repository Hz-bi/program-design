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
           //���ñ����Խ��ı䴰�ڱ߿���ʽ����
           BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
           org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
       }
       catch(Exception e)
       {
           //TODO exception
       }
       String testName="";
       IntegrationView integrationView = new IntegrationView();
       GiveTestPaper initTestPaper = new GiveTP(); //������ʼ�Ծ����
       TestPaper testPaper= initTestPaper.getTestPaper("�������/���/��ͨ����.xls",5);   //�õ���5����Ŀ���Ծ�
       TestPaperView testView = new TestPaperView();
       testView.setTestPaper(testPaper);        //�����Ծ�
       testView.setTeacher(new TeacherOne()); //�����ľ���ʦ
       testName = "��ͨ��ѵ��";
       testView.setTestName(testName);
       testView.setTotalTime(900);//����ʱ��15����,900
       integrationView.addTestPaperView(testName,testView);
       initTestPaper = new GiveJTP(); //������ʼ�Ծ����
       testPaper= initTestPaper.getTestPaper("�������/���/java����.xls",6);
       testView = new TestPaperView();
       testView.setTestPaper(testPaper);        
       testView.setTeacher(new TeacherOne());
       testName = "Javaѵ��"; 
       testView.setTestName(testName);
       testView.setTotalTime(600);//����ʱ��10���ӣ�600
       integrationView.addTestPaperView(testName,testView);
   }
}
