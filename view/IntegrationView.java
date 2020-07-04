package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
public class IntegrationView extends JFrame{
    JTabbedPane tabbedPane; //用选项卡集成TestPaperView视图
    public IntegrationView(){
        tabbedPane= new JTabbedPane(JTabbedPane.LEFT);//卡在左侧 
        tabbedPane.validate();
        add(tabbedPane,BorderLayout.CENTER); 
        setBounds(100,100,1280,760);//调整总框图分辨率和初始位置
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void addTestPaperView(String cardName,TestPaperView view){
       tabbedPane.add(cardName,view);
       validate();
    }
}