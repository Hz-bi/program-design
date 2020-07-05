package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import data.*;
import data.TestPaper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class TestPaperView extends JPanel implements ActionListener{
   TestPaper testPaper;      //����ͼ��Ҫ��ʾ���Ծ�
   public Teacher teacher ;  //������ʦ
   public JTextArea showContent;    //��ʾ��������

   public ImageJPanel showImage;    //��ʾ�����ͼ��
   public JRadioButton choiceA;
   public JRadioButton choiceB;
   public JRadioButton choiceC;
   public JRadioButton choiceD;//��ʾѡ������
   public JButton nextProblem,previousProblem;  //ѡ����һ�⣬��һ��İ�ť
   public JButton aProblemSubmit;  //ȷ��ĳ����Ļش��ѡ��
   public JButton viewAnswer;    //�鿴��
   public JButton renewJButton   ;  //���¿�ʼ��
   public JButton submit;  //����
   HandleTestPaper handleTestPaper; //������testPaper�Ծ��е�����
   public int  totalTime = 0;      //������ʱ����λ�룩
   public int  usedTime  = totalTime;
   public int  usedTime_minute = 0;  //ʣ��ʱ��(����)
   public int  usedTime_second = 0; //ʣ��ʱ��(��)
   public javax.swing.Timer time;          //���Լ�ʱ��
   public JLabel showUsedTime   ;          //��ʾ��ʱ
   JLabel testName ;                       //��ʾ��������
   JFrame f1 = new JFrame("ʱ�侯��") ; //����һ������
   JFrame f2 = new JFrame("��ǰ��֪") ; //����һ������
   public TestPaperView() {
      time = new Timer(1000,this);//delay:60*1000 ÿ��1���Ӽ�ʱһ�Σ�����ActionEvent����������Ϊ�������
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
      showContent.setToolTipText("���������ͼ����ͼ�ϵ������ɵ����ۿ�");
      //showContent.setForeground(Color.white);
      showContent.setWrapStyleWord(true);
      showContent.setLineWrap(true); //�����Զ�
      showContent.setFont(new Font("����",Font.BOLD,18));
      choiceA = new JRadioButton("A");
      choiceB = new JRadioButton("B");
      choiceC = new JRadioButton("C");
      choiceD = new JRadioButton("D");
      ButtonGroup group = new ButtonGroup(); //��ťѡ���飬�����İ�ťֻ�ܵ�ѡ
      choiceA.setVisible(false);
      choiceB.setVisible(false);
      choiceC.setVisible(false);
      choiceD.setVisible(false);
      nextProblem = new JButton("��һ��Ŀ");
      nextProblem.setBorderPainted(false);


      previousProblem = new JButton("��һ��Ŀ");
      previousProblem.setBorderPainted(false);

      aProblemSubmit = new JButton("ȷ��");
      aProblemSubmit.setBorderPainted(false);
      aProblemSubmit.setVisible(false);
      viewAnswer = new JButton("�鿴��");
      viewAnswer.setVisible(false);
      viewAnswer.setBorderPainted(false);
      renewJButton = new JButton("����һ��");
      renewJButton.setBorderPainted(false);
      renewJButton.setVisible(false);
      submit = new JButton("����");
      submit.setBorderPainted(false);
      submit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));

      JPanel pNorth = new JPanel();
      pNorth.setBackground(Color.white) ;
      showUsedTime = new JLabel();
      testName = new JLabel();
      testName.setFont(new Font("����",Font.BOLD,18));
      Point p = new Point(500,500) ;  // ָ���������ʾλ��
      f1.setVisible(false);
      f1.setSize(400, 400);//���úÿ��
      f1.setLocationRelativeTo(null);//���������ʾ
      f1.setBackground(Color.RED) ;    // ���������óɰ�ɫ
      JLabel label1 = new JLabel("����ʱ�仹ʣ5����");
      label1.setHorizontalAlignment(0);
      JPanel ppWest = new JPanel();
      ppWest.add(testName);
      add(ppWest,BorderLayout.WEST);
      JPanel ppEest = new JPanel();
      pNorth.add(renewJButton);
      pNorth.add(showUsedTime);
      pNorth.add(submit);
      this.add(pNorth,BorderLayout.NORTH);
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
      group.add(choiceD); //��ѡ��ťȫ�����밴ť�飬ȷ��ֻ�е�ѡ
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
      showUsedTime.setText("����ʣ��ʱ��:"+usedTime_minute+"��"+usedTime_second+"��");
      if(usedTime == 300){
         f1.setVisible(true);
      }
      if(usedTime == 0){
          time.stop();
          showUsedTime.setText("�뽻��");
          nextProblem.setVisible(false); 
          previousProblem.setVisible(false); 
      }
      usedTime--;
   }
   public void setTestName(String name){
      testName.setText("��"+name+"��");
   }
   public void setTotalTime(int n) {
      totalTime = n;
      usedTime = n;
      usedTime_minute = usedTime/60;
      usedTime_second = n-usedTime_minute*60;
      showUsedTime.setText("����ʣ��ʱ��:"+usedTime_minute+"��"+usedTime_second+"��");
   }
}
