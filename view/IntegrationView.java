package view;
import java.awt.BorderLayout;
import javax.swing.*;

public class IntegrationView extends JFrame{
    JTabbedPane tabbedPane; //��ѡ�����TestPaperView��ͼ


    public IntegrationView(){
        tabbedPane= new JTabbedPane(JTabbedPane.NORTH);//�������
        tabbedPane.validate();
        add(tabbedPane,BorderLayout.CENTER); 
        setTitle("����ϵͳ");
        setBounds(45,45,1280,760);//�����ܿ�ͼ�ֱ��ʺͳ�ʼλ��
        setIconImage(new ImageIcon("�������/ͼ�����/������Ԫ��.jpg").getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


    }
    public void addTestPaperView(String cardName,TestPaperView view){
        tabbedPane.add(cardName,view);
        validate();

    }
}
