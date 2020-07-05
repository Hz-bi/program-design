package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import data.*;
import data.TestPaper;

public class TestPaperView extends JPanel implements ActionListener{
   TestPaper testPaper;      //本视图需要显示的试卷
   public Teacher teacher ;  //批卷老师
   public JTextArea showContent;    //显示试题内容
   public ImageJPanel showImage;    //显示试题的图像
   public JRadioButton choiceA;
   public JRadioButton choiceB;
   public JRadioButton choiceC;
   public JRadioButton choiceD;//显示选项内容
   public JButton nextProblem,previousProblem;  //选择上一题，下一题的按钮
   public JButton aProblemSubmit;  //确认某道题的回答或选择
   public JButton viewAnswer;    //查看答案
   public JButton renewJButton   ;  //重新开始；
   public JButton submit;  //交卷
   HandleTestPaper handleTestPaper; //负责处理testPaper试卷中的数据
   public int  totalTime = 0;      //考试用时（单位秒）
   public int  usedTime  = totalTime;
   public int  usedTime_minute = 0;  //剩余时间(分钟)
   public int  usedTime_second = 0; //剩余时间(秒)
   public javax.swing.Timer time;          //考试计时器
   public JLabel showUsedTime   ;          //显示用时
   JLabel testName ;                       //显示考试名称
   JFrame f = new JFrame("时间警告") ; //创建一个窗体
   public TestPaperView() {
      time = new Timer(1000,this);//delay:60*1000 每隔1分钟计时一次（触发ActionEvent）本容器作为其监视器
      initView();
      registerListener();
   } 
   public void setTeacher(Teacher teacher){
      this.teacher = teacher;
   }
   public void initView() {
      setLayout(new BorderLayout()); 
      showImage = new ImageJPanel();
      showContent = new JTextArea(12,12);
      showContent.setToolTipText("如果题中有图像，在图上单机鼠标可调整观看");
      showContent.setForeground(Color.blue);
      showContent.setWrapStyleWord(true);
      showContent.setLineWrap(true); //回行自动
      showContent.setFont(new Font("宋体",Font.BOLD,18));
      choiceA = new JRadioButton("A");
      choiceB = new JRadioButton("B");
      choiceC = new JRadioButton("C");
      choiceD = new JRadioButton("D");
      ButtonGroup group = new ButtonGroup(); //按钮选项组，加入后的按钮只能单选
      choiceA.setVisible(false);
      choiceB.setVisible(false);
      choiceC.setVisible(false);
      choiceD.setVisible(false);
      nextProblem = new JButton("下一题目");
      previousProblem = new JButton("上一题目");
      aProblemSubmit = new JButton("确认"); 
      aProblemSubmit.setVisible(false);
      viewAnswer = new JButton("查看答案");
      viewAnswer.setVisible(false);
      renewJButton = new JButton("再来一次");
      renewJButton.setVisible(false);
      submit = new JButton("交卷");  
      submit.setBorderPainted(false);
      submit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
      JPanel pNorth = new JPanel();
      pNorth.setBackground(Color.cyan) ;
      showUsedTime = new JLabel();
      testName = new JLabel();
      testName.setFont(new Font("楷体",Font.BOLD,18));
      f.setVisible(false);
      f.setSize(400, 400);//设置好宽高
      f.setLocationRelativeTo(null);//窗体居中显示
      f.setBackground(Color.RED) ;    // 将背景设置成白色
      Point p = new Point(500,500) ;  // 指定组件的显示位置
      JLabel label1 = new JLabel("考试时间还剩5分钟");
      label1.setHorizontalAlignment(0);
      f.add(label1);
      pNorth.add(testName);
      pNorth.add(new JLabel("单击下一题或上一题按钮开始考试")); 
      pNorth.add(submit);
      pNorth.add(renewJButton);
      pNorth.add(showUsedTime); 
      add(pNorth,BorderLayout.NORTH);
      JPanel pCenter = new JPanel();
      pCenter.setLayout(new GridLayout(1,2));
      pCenter.add(new JScrollPane(showContent));
      pCenter.add(showImage);
      add(pCenter,BorderLayout.CENTER);
      JPanel pSouth = new JPanel();
      pSouth.setLayout(new GridLayout(2,1));
      JPanel oneInPSouth = new JPanel();
      JPanel twoInPSouth = new JPanel();
      oneInPSouth.setBackground(Color.green) ;
      oneInPSouth.setBackground(Color.pink) ;
      group.add(choiceA);
      group.add(choiceB);
      group.add(choiceC);
      group.add(choiceD); //单选按钮全部加入按钮组，确保只有单选
      oneInPSouth.add(choiceA);
      oneInPSouth.add(choiceB);
      oneInPSouth.add(choiceC);
      oneInPSouth.add(choiceD);
      oneInPSouth.add(aProblemSubmit);
      oneInPSouth.add(viewAnswer);
      twoInPSouth.add(nextProblem);
      twoInPSouth.add(previousProblem);
      pSouth.add(oneInPSouth);
      pSouth.add(twoInPSouth);          
      add(pSouth,BorderLayout.SOUTH);
      validate();
   }
   public void registerListener(){
      handleTestPaper = new HandleTestPaper();
      nextProblem.addActionListener(handleTestPaper);
      previousProblem.addActionListener(handleTestPaper);
      aProblemSubmit.addActionListener(handleTestPaper);
      viewAnswer.addActionListener(handleTestPaper);
      submit.addActionListener(handleTestPaper);
      renewJButton.addActionListener(handleTestPaper);
      handleTestPaper.setView(this);
   }
   public void setTestPaper(TestPaper testPaper) {
      this.testPaper = testPaper;
      handleTestPaper.setTestPaper(testPaper);
   }

   public void actionPerformed(ActionEvent e){
      usedTime_minute = usedTime/60;
      usedTime_second = usedTime-usedTime_minute*60;
      showUsedTime.setText("考试剩余时间:"+usedTime_minute+"分"+usedTime_second+"秒");
      if(usedTime == 300){
         f.setVisible(true);
      }
      if(usedTime == 0){
          time.stop();
          showUsedTime.setText("请交卷");
          nextProblem.setVisible(false); 
          previousProblem.setVisible(false); 
      }
      usedTime--;
   }
   public void setTestName(String name){
      testName.setText("【"+name+"】");
   }
   public void setTotalTime(int n) {
      totalTime = n;
      usedTime = n;
      usedTime_minute = usedTime/60;
      usedTime_second = n-usedTime_minute*60;
      showUsedTime.setText("考试剩余时间:"+usedTime_minute+"分"+usedTime_second+"秒");
   }
}
