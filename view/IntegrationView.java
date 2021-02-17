package view;
import java.awt.BorderLayout;
import javax.swing.*;

public class IntegrationView extends JFrame{
    JTabbedPane tabbedPane; //用选项卡集成TestPaperView视图


    public IntegrationView(){
        tabbedPane= new JTabbedPane(JTabbedPane.NORTH);//卡在左侧
        tabbedPane.validate();
        add(tabbedPane,BorderLayout.CENTER); 
        setTitle("考试系统");
        setBounds(45,45,1280,760);//调整总框图分辨率和初始位置
        setIconImage(new ImageIcon("软件发布/图像管理/老三次元了.jpg").getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


    }
    public void addTestPaperView(String cardName,TestPaperView view){
        tabbedPane.add(cardName,view);
        validate();

    }
}
