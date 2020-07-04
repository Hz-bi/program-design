package ch5.gui;

import ch5.data.GiveTestPaper;
import ch5.data.RamdomInitTestPaper;
import ch5.data.TestPaper;
import ch5.data.TeacherOne;
import ch5.view.TestPaperView;
import ch5.view.IntegrationView;

public class AppWindow {
	public static void main(String[] args) {
		String testName = "";
		IntegrationView integrationView = new IntegrationView();
		GiveTestPaper initTestPaper = new RamdomInitTestPaper(); // 创建初始试卷对象
		TestPaper testPaper = initTestPaper.getTestPaper("C:\Users\10119\Desktop\程序设计综合实践参考代码\ch5\软件发布\题库\交通理论.xls", 5); // 得到有5个题目的试卷
		TestPaperView testView = new TestPaperView();
		testView.setTestPaper(testPaper); // 设置试卷
		testView.setTeacher(new TeacherOne()); // 设置阅卷老师
		testName = "交通法训练";
		testView.setTestName(testName);
		testView.setTotalTime(15);// 考试时间15分钟
		integrationView.addTestPaperView(testName, testView);
		initTestPaper = new RamdomInitTestPaper(); // 创建初始试卷对象
		testPaper = initTestPaper.getTestPaper("C:\Users\10119\Desktop\程序设计综合实践参考代码\ch5\软件发布\题库\Java基础.xls", 6);
		testView = new TestPaperView();
		testView.setTestPaper(testPaper);
		testView.setTeacher(new TeacherOne());
		testName = "Java训练";
		testView.setTestName(testName);
		testView.setTotalTime(10);
		integrationView.addTestPaperView(testName, testView);
	}
}