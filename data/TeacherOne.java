package data;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class TeacherOne implements Teacher {
    public void giveTestPaparScore(TestPaper testPaper){
          int correctAmount=0;          //�ٷֱȼƷ�
          if(testPaper==null){
            JOptionPane.showMessageDialog
              (null,"û����","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            return;
          }
          Problem p[] = testPaper.getAllProblem();
          BufferedWriter br = null;
          try{
            br = new BufferedWriter(new FileWriter("docs/Test.txt"));
            Date date = new Date();
            DateFormat df2 = DateFormat.getDateTimeInstance();
            br.write("���Ծ���ʱ�䡿"+df2.format(date)+"\n");
            for(int j=0;j<p.length;j++){

                br.write(p[j].getContent()+"\n");
                br.write(p[j].getGiveChoiceA()+"\n");
                br.write(p[j].getGiveChoiceB()+"\n");
                if(p[j].getGiveChoiceC()!=""){   //�Ż��жϻ���
                    br.write(p[j].getGiveChoiceC()+"\n");
                }
                if(p[j].getGiveChoiceD()!=""){
                    br.write(p[j].getGiveChoiceD()+"\n"); //�Ż��ı�¼�����
                }
                br.write("��Ĵ�:"+p[j].getUserAnswer()+"\n");
                br.write("��ȷ��:"+p[j].getCorrectAnswer()+"\n");
                br.write("\n");
            }
              br.flush();//ѹ������
              br.close();//�رվ��
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        //System.out.println(Arrays.toString(paper));
          if(p==null||p.length==0){
            JOptionPane.showMessageDialog
              (null,"û����","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            return;
          }
          for(int i=0;i<p.length;i++){
              if(p[i].getCorrectAnswer().length()<=1){ //�Ƕ�ѡ����
                  boolean b = compare(p[i].getUserAnswer(),p[i].getCorrectAnswer());
                  if(b){
                      correctAmount++;
                  }
              }else{ //��ѡ�������
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
          String s ="����:"+p.length+"����."+
                    "��������"+correctAmount+"��,"+
                    "��ȷ�ʴ�Լ"+r+"%��"+"�����ѽ���,�Զ��˳���";
          JLabel mess = new JLabel(s);
          JOptionPane.showMessageDialog(null,mess,"�ɼ�",JOptionPane.PLAIN_MESSAGE );
          System.out.println("�����ѱ��棬���򼴽��˳�");
          try {
              Runtime.getRuntime().exec("Notepad docs/Test.txt");//cmd�����Զ��򿪿���
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