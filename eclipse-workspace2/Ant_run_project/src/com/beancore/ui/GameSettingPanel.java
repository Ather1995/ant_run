package com.beancore.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.beancore.config.Config;
import com.beancore.util.Images;
import com.beancore.ui.MainFrame;

public class GameSettingPanel extends JFrame implements ItemListener{
	private static final long serialVersionUID = 1L;
	MainFrame mainFrame;
    private JPanel buttonPanel;
    private JPanel logoPanel;
    //蚂蚁右向走
    JRadioButton r1 = null;
    JRadioButton r2 = null;
    JRadioButton r3 = null;
    JRadioButton r4 = null;
    JRadioButton r5 = null;
    //蚂蚁左向走
    JRadioButton l1 = null;
    JRadioButton l2 = null;
    JRadioButton l3 = null;
    JRadioButton l4 = null;
    JRadioButton l5 = null;
    
    private JPanel OK_Exit_Panel;
    private JButton buttonOK;
    private JButton buttonExit;
    private JLabel logoLabel;
//    蚂蚁的初始状态，true为左，false为右
    private Boolean[] initSetting= Config.INITSETTING;
	
	public GameSettingPanel(MainFrame mainFrame) {
		this.initComponent();
		this.mainFrame=mainFrame;
	    }
	

	private void initComponent() {
		// TODO Auto-generated method stub
		setLocationRelativeTo(null); // 让窗体居中显示 
        setTitle("蚂蚁方向设置");// 设置窗口中标题栏的文字 
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width - Config.XGameSettingPanel) / 2, (d.height - Config.YGameSettingPanel) / 2,
			Config.XGameSettingPanel, Config.YGameSettingPanel);
        setSize(Config.XGameSettingPanel, Config.YGameSettingPanel);// 设置窗体的大小
        setResizable(false);// 设置窗体是否可以调整大小，参数为布尔值 
        setVisible(true);// 设置窗体是否可以调整大小，参数为布尔值  
        setDefaultCloseOperation(HIDE_ON_CLOSE);// 用户点击窗口隐藏 
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(3,1));
        
        this.logoLabel = new JLabel();
        this.logoLabel.setIcon(new ImageIcon(Images.INIT_SETTING));
		
        logoPanel = new JPanel();
        logoPanel.setOpaque(false);
    		logoPanel.add(logoLabel);
    		
    		OK_Exit_Panel = new JPanel();
    		BoxLayout boxLayout1 = new BoxLayout(OK_Exit_Panel, BoxLayout.X_AXIS);
    		buttonExit= new JButton("取消");
    		buttonOK= new JButton("确定");
    		OK_Exit_Panel.setOpaque(false);
    		OK_Exit_Panel.setLayout(boxLayout1);
    		OK_Exit_Panel.add(buttonExit);
    		OK_Exit_Panel.add(buttonOK);
    		buttonExit.addActionListener(new ActionListener()
            {
    			@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("按钮执行关闭窗口的功能");
					setVisible(false);
					
				}
    		      });
    		buttonOK.addActionListener(new ActionListener()
            {
    			@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("按钮执行ok");
					setVisible(false);
					setinitSetting();
					mainFrame.startGameAction();
				}
    		      });
    		
    		GridLayout gridLayout = new GridLayout(5, 1, 50, 10);
    		buttonPanel = new JPanel();
    		buttonPanel.setOpaque(false);
    		buttonPanel.setLayout(gridLayout);
    		
    		Dimension d1 = new Dimension(20, 300);
    		buttonPanel.setSize(d1);
    		buttonPanel.setPreferredSize(d1);
    		
    		JPanel p1 = new JPanel();
    		p1.setLayout(new GridLayout(1,2));
    		p1.setBorder(BorderFactory.createTitledBorder("蚂蚁A")); 
    		ButtonGroup JRadioB1=new ButtonGroup();
    		l1 = new JRadioButton("左",true);
    		r1 = new JRadioButton("右");
    		JRadioB1.add(l1);
    		JRadioB1.add(r1);
    		p1.add(l1);
    		p1.add(r1);
    		l1.addItemListener(this);
    		r1.addItemListener(this);
    		
    		JPanel p2 = new JPanel();
    		p2.setLayout(new GridLayout(1,2));
    		p2.setBorder(BorderFactory.createTitledBorder("蚂蚁B")); 
    		ButtonGroup JRadioB2=new ButtonGroup();
    		l2 = new JRadioButton("左",true);
    		r2 = new JRadioButton("右");
    		JRadioB2.add(l2);
    		JRadioB2.add(r2);
    		p2.add(l2);
    		p2.add(r2);
    		l2.addItemListener(this);
    		r2.addItemListener(this);
    		
    		JPanel p3 = new JPanel();
    		p3.setLayout(new GridLayout(1,2));
    		p3.setBorder(BorderFactory.createTitledBorder("蚂蚁C")); 
    		ButtonGroup JRadioB3=new ButtonGroup();
    		l3 = new JRadioButton("左",true);
    		r3 = new JRadioButton("右");
    		JRadioB3.add(l3);
    		JRadioB3.add(r3);
    		p3.add(l3);
    		p3.add(r3);
    		l3.addItemListener(this);
    		r3.addItemListener(this);
    		
    		JPanel p4 = new JPanel();
    		p4.setLayout(new GridLayout(1,2));
    		p4.setBorder(BorderFactory.createTitledBorder("蚂蚁D")); 
    		ButtonGroup JRadioB4=new ButtonGroup();
    		l4 = new JRadioButton("左",true);
    		r4 = new JRadioButton("右");
    		JRadioB4.add(l4);
    		JRadioB4.add(r4);
    		p4.add(l4);
    		p4.add(r4);
    		l4.addItemListener(this);
    		r4.addItemListener(this);
    		
    		
    		JPanel p5 = new JPanel();
    		p5.setLayout(new GridLayout(1,2));
    		p5.setBorder(BorderFactory.createTitledBorder("蚂蚁E")); 
    		ButtonGroup JRadioB5=new ButtonGroup();
    		l5 = new JRadioButton("左",true);
    		r5 = new JRadioButton("右");
    		JRadioB5.add(l5);
    		JRadioB5.add(r5);
    		p5.add(l5);
    		p5.add(r5);
    		l5.addItemListener(this);
    		r5.addItemListener(this);
    		
    		
    		buttonPanel.add(p1);
    		buttonPanel.add(p2);
    		buttonPanel.add(p3);
    		buttonPanel.add(p4);
    		buttonPanel.add(p5);
    		
    		
    		Container c = this.getContentPane();
    		c.removeAll();
    		this.repaint();
    		
    		BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
    		c.setLayout(boxLayout);
    		c.add(Box.createVerticalGlue());
    		c.add(logoPanel);
    		c.add(buttonPanel);
    		c.add(OK_Exit_Panel);
    		c.add(Box.createVerticalGlue());

	}
	
	public Boolean[] getinitSetting() {
		return initSetting;
	}
	
	public void setinitSetting() {
		Config.INITSETTING=this.initSetting;
	}
		

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == e.SELECTED) {
			if(e.getSource() == r1){
				initSetting[0]=false;
	        }
			if(e.getSource() == l1){
				initSetting[0]=true;
	        }
			if(e.getSource() == r2){
				initSetting[1]=false;
	        }
			if(e.getSource() == l2){
				initSetting[1]=true;
	        }
			if(e.getSource() == r3){
				initSetting[2]=false;
	        }
			if(e.getSource() == l3){
				initSetting[2]=true;
	        }
			if(e.getSource() == r4){
				initSetting[3]=false;
	        }
			if(e.getSource() == l4){
				initSetting[3]=true;
	        }
			if(e.getSource() == r5){
				initSetting[4]=false;
	        }
			if(e.getSource() == l5){
				initSetting[4]=true;
	        }
			
		}
		
	}

}
