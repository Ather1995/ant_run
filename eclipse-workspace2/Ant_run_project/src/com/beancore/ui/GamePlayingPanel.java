package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePlayingPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPanel ControlJPanel;
//	private JTextField text; 
//	private GameButton startButton;
//	private GameButton stop_pauseButton;
	private ControlJPanel controlJPanel;
	private CanvasPanel mainJPanel;
	
	public GamePlayingPanel(MainFrame mainFrame) {
		this.initComponents(mainFrame);
	    }

	private void initComponents(MainFrame mainFrame) {
		// TODO Auto-generated method stub
		
		controlJPanel=new ControlJPanel(this);
		if (mainJPanel == null) {
			mainJPanel = new CanvasPanel(this,mainFrame);
			}
		mainJPanel.setOpaque(false);
		
		this.setLayout(new BorderLayout(5,5));
		this.setOpaque(false);
		this.add(controlJPanel,BorderLayout.NORTH);
		this.add(mainJPanel);
		System.out.println("GamePlayingPanel");
		
	}
//	获得时间
	public int getcount() {
		return this.controlJPanel.getTime();
	}
//	停止时钟
	public void stopcount() {
		this.controlJPanel.StopTime();
	}
	
	public void startcount() {
		this.controlJPanel.StartTime();
	}
	
	
	//获取游戏中心界面
//	protected JPanel getPanel() {
//			if (mainJPanel == null) {
//				mainJPanel = new CanvasPanel(this);
//				}
//				return mainJPanel;
//		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if(actionCmd.equals(ControlJPanel.STARTMOVE)) {
			controlJPanel.startButton.setText("暂停");
			mainJPanel.startGame();
			controlJPanel.StartTime();
			controlJPanel.startButton.setActionCommand(ControlJPanel.STOPMOVE);
		    System.out.println("暂停");
		}else if(actionCmd.equals(ControlJPanel.STOPMOVE)) {
			controlJPanel.startButton.setText("开始");
			controlJPanel.StopTime();
			mainJPanel.pauseGame();
			controlJPanel.startButton.setActionCommand(ControlJPanel.STARTMOVE);
		    System.out.println("开始");
		}
	}



}
