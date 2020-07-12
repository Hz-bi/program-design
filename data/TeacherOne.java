package data;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class TeacherOne implements Teacher {
    public void giveTestPaparScore(TestPaper testPaper){
          int correctAmount=0;          //百分比计分
          if(testPaper==null){
            JOptionPane.showMessageDialog
              (null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
          }
          Problem p[] = testPaper.getAllProblem();
          BufferedWriter br = null;
          try{
            br = new BufferedWriter(new FileWriter("docs/Test.txt"));
            Date date = new Date();
            DateFormat df2 = DateFormat.getDateTimeInstance();
            br.write("【试卷创建时间】"+df2.format(date)+"\n");
            for(int j=0;j<p.length;j++){

                br.write(p[j].getContent()+"\n");
                br.write(p[j].getGiveChoiceA()+"\n");
                br.write(p[j].getGiveChoiceB()+"\n");
                if(p[j].getGiveChoiceC()!=""){   //优化判断机制
                    br.write(p[j].getGiveChoiceC()+"\n");
                }
                if(p[j].getGiveChoiceD()!=""){
                    br.write(p[j].getGiveChoiceD()+"\n"); //优化文本录入机制
                }
                br.write("你的答案:"+p[j].getUserAnswer()+"\n");
                br.write("正确答案:"+p[j].getCorrectAnswer()+"\n");
                br.write("\n");
            }
              br.flush();//压入内容
              br.close();//关闭句柄
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        //System.out.println(Arrays.toString(paper));
          if(p==null||p.length==0){
            JOptionPane.showMessageDialog
              (null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
          }
          for(int i=0;i<p.length;i++){
              if(p[i].getCorrectAnswer().length()<=1){ //非多选处理
                  boolean b = compare(p[i].getUserAnswer(),p[i].getCorrectAnswer());
                  if(b){
                      correctAmount++;
                  }
              }else{ //多选处理机制
                  String[] correctAnswer = p[i].getCorrectAnswer().split("&");
                  int count=0;
                  for(String s:correctAnswer){
                      System.out.println(s);
                  }
                  for(int j=0;j<correctAnswer.length;j++){
                      if(p[i].getUserAnswer().contains(correctAnswer[j])){
                          count++;
                      }
                  }
                  if(count==correctAnswer.length){
                      correctAmount++;
                  }
              }
          } 
          double result = (double)correctAmount/(double)p.length;
          int r =(int)(result*100);
          String s ="共有:"+p.length+"道题."+
                    "您做对了"+correctAmount+"题,"+
                    "正确率大约"+r+"%。"+"考试已结束,自动退出。";
          JLabel mess = new JLabel(s);
          JOptionPane.showMessageDialog(null,mess,"成绩",JOptionPane.PLAIN_MESSAGE );
          System.out.println("考卷已保存，程序即将退出");
          try {
              Runtime.getRuntime().exec("Notepad docs/Test.txt");//cmd调用自动打开考卷
          } catch (IOException e) {
              e.printStackTrace();
          }
          System.exit(0);
    }
    private boolean compare(String s,String t) {
        boolean isTrue = true;
        for(int i=0;i<s.length();i++) {
           String temp = ""+s.charAt(i);
           if(!(t.contains(temp)))
             isTrue = false;
        }
        for(int i=0;i<t.length();i++) {
           String temp = ""+t.charAt(i);
           if(!(s.contains(temp)))
             isTrue = false;
        }
        return isTrue;
    }
}