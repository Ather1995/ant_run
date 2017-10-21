package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
//import com.beancore.entity.Score;
import com.beancore.ui.MainFrame.StartGameActionClass;
//import com.beancore.util.FileUtil;
import com.beancore.util.Images;
import com.beancore.util.SoundPlayer;

public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	游戏加载界面
	private GameLoadingPanel gameLoadingPanel;
//	游戏界面
	private GamePlayingPanel gamePlayingPanel;
//	弹出菜单
	private PopupMenuPanel popupMenuPanel;
////	排名
//    private Top10ScorePanel popupScorePanel;
//    帮助文档
    private HelpDialog helpDialog;
    
    private MaxStartPanel maxStartPanel;
    
    private GameSettingPanel gameSettingAction;
    
    private SoundPlayer achievementSoundPlayer;


    public MainFrame() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
    	this.loadImage();
    	this.initSoundPlayer();
    	this.initComponents();
    	this.setBackgroundImage();
    	
   
        }
	private void setBackgroundImage() {
		// TODO Auto-generated method stub
		ImageIcon bgImgIcon = new ImageIcon(Images.GAME_LOADING__BACKGROUND_IMG);
		JLabel bgLabel = new JLabel(bgImgIcon);
		this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
		bgLabel.setBounds(0, 0, bgImgIcon.getIconWidth(), bgImgIcon.getIconHeight());
		((JPanel) this.getContentPane()).setOpaque(false);
		
	}
	private void initComponents() {
		// TODO Auto-generated method stub
		this.setTitle("Ant Run");
		this.setIconImage(new ImageIcon(Config.LOGO_IMG).getImage());
		this.setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width - Config.MAIN_FRAME_WIDTH) / 2, (d.height - Config.MAIN_FRAME_HEIGHT) / 2,
			Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initSoundPlayer() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		achievementSoundPlayer = new SoundPlayer(Config.ACHIEVEMENT_AUDIO);
	    }
	private void loadImage() throws IOException {
		// TODO Auto-generated method stub
		Images.GAME_LOADING_IMG1=Toolkit.getDefaultToolkit().getImage("images//"+"timg_load"+".gif");
		Images.GAME_LOADING__BACKGROUND_IMG=Toolkit.getDefaultToolkit().getImage("images//"+"load_background"+".png");
		Images.GAME_LOADING_TEXT_IMG=Toolkit.getDefaultToolkit().getImage("images//"+"load_text"+".png");
		Images.MY_Ant_IMG=Toolkit.getDefaultToolkit().getImage("images//"+"menu_ant"+".gif");
		Images.ANT_LEFT=Toolkit.getDefaultToolkit().getImage("images//"+"run_left"+".gif");
		Images.ANT_RIGHT=Toolkit.getDefaultToolkit().getImage("images//"+"run_right"+".gif");
		Images.GAME_PLAYING_ROB=Toolkit.getDefaultToolkit().getImage("images//"+"rob"+".png");
		Images.INIT_SETTING=Toolkit.getDefaultToolkit().getImage("images//"+"init_setting"+".gif");
		
		Images.ANTIMAGE00=Toolkit.getDefaultToolkit().getImage("images//"+"ant1"+".png");
		Images.ANTIMAGE01=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant1"+".png");
		Images.ANTIMAGE10=Toolkit.getDefaultToolkit().getImage("images//"+"ant2"+".png");
		Images.ANTIMAGE11=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant2"+".png");
		Images.ANTIMAGE20=Toolkit.getDefaultToolkit().getImage("images//"+"ant1"+".png");
		Images.ANTIMAGE21=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant1"+".png");
		Images.ANTIMAGE30=Toolkit.getDefaultToolkit().getImage("images//"+"ant2"+".png");
		Images.ANTIMAGE31=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant2"+".png");
		Images.ANTIMAGE40=Toolkit.getDefaultToolkit().getImage("images//"+"ant1"+".png");
		Images.ANTIMAGE41=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant1"+".png");
		
//		Images.ANTIMAGE20=Toolkit.getDefaultToolkit().getImage("images//"+"ant3"+".tiff");
//		Images.ANTIMAGE21=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant3"+".tiff");
//		Images.ANTIMAGE30=Toolkit.getDefaultToolkit().getImage("images//"+"ant4"+".tiff");
//		Images.ANTIMAGE31=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant4"+".tiff");
//		Images.ANTIMAGE40=Toolkit.getDefaultToolkit().getImage("images//"+"ant5"+".tiff");
//		Images.ANTIMAGE41=Toolkit.getDefaultToolkit().getImage("images//"+"anti_ant5"+".tiff");
	}
	
//加载游戏界面	
	public void loadGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Container c = this.getContentPane();
		c.removeAll();
		this.repaint();
		if (this.gameLoadingPanel == null) {
		    this.gameLoadingPanel = new GameLoadingPanel();
		}

		BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(boxLayout);
		c.add(Box.createVerticalGlue());
		c.add(this.gameLoadingPanel);
		c.add(Box.createVerticalGlue());
		this.gameLoadingPanel.loadingGame();

		this.menuGame();
	    }
	
//	菜单界面，6个按钮
	private void menuGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {		
		popupMenuPanel();
		   
	}
	private void popupMenuPanel() {
		// TODO Auto-generated method stub

		Container c = this.getContentPane();
		c.removeAll();
		this.repaint();
		if (this.popupMenuPanel == null) {
		    this.popupMenuPanel = new PopupMenuPanel(this);
		}
		BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(boxLayout);
		c.add(Box.createVerticalGlue());
		c.add(this.popupMenuPanel);
		c.add(Box.createVerticalGlue());
		this.validate();
	    
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals(PopupMenuPanel.MAX)) {
		    startMaxAction();
		}
		else if (actionCmd.equals(PopupMenuPanel.MIN)) {
		    startMinAction();
		} 
		else if (actionCmd.equals(PopupMenuPanel.START_GAME_BUTTON)) {
//		    startGameAction();
			gameSettingAction();
		} else if (actionCmd.equals(PopupMenuPanel.EXIT_GAME_BUTTON)) {
		    exitGameAction();
		    System.out.println("exitGameAction");
		} else if (actionCmd.equals(PopupMenuPanel.HELP_BUTTON)) {
		    helpAction();
		} 
	}
	private void gameSettingAction() {
		// TODO Auto-generated method stub
		if (this.gameSettingAction == null) {
	    this.gameSettingAction = new GameSettingPanel(this);
	}
	this.gameSettingAction.setVisible(true);		
	}
	private void startMinAction() {
		// TODO Auto-generated method stub
		if (this.maxStartPanel == null) {
		    this.maxStartPanel = new MaxStartPanel(this);
		}
		this.maxStartPanel.setmininitSetting();
		
	}
	private void startMaxAction() {
		// TODO Auto-generated method stub
		if (this.maxStartPanel == null) {
		    this.maxStartPanel = new MaxStartPanel(this);
		}
		this.maxStartPanel.setmaxinitSetting();
		
	}
	private void helpAction() {
		// TODO Auto-generated method stub
		if (this.helpDialog == null) {
		    this.helpDialog = new HelpDialog();
		}
		this.helpDialog.setVisible(true);
	}
	private void exitGameAction() {
		// TODO Auto-generated method stub
		System.exit(0); 
	}

	void startGameAction() {
		// TODO Auto-generated method stub
		new Thread(new StartGameActionClass()).start();
		System.out.println("startGameAction");
	}
//	跳到GamePlayingPanel，开始游戏，gamePlayingPanel.startGame()
	private void StartGame() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		// TODO Auto-generated method stub
		Container c = this.getContentPane();
		c.removeAll();
		this.repaint();
		System.out.println("StartGame");
		BoxLayout boxLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(boxLayout);
		if (this.gamePlayingPanel == null) {
			System.out.println("mainStartGame");
			this.gamePlayingPanel = new GamePlayingPanel(this);
		}
		c.add(this.gamePlayingPanel);
		this.validate();
//		this.gamePlayingPanel.startGame();
    }
		
	class StartGameActionClass implements Runnable {

		@Override
		public void run() {
		    try {
//		    	开始游戏啦
		    	System.out.println("StartGameActionClass");
		    	StartGame();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	}	
}
