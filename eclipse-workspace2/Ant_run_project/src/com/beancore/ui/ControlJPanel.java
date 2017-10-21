package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
import com.beancore.util.SoundPlayer;

public class ControlJPanel extends JPanel implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	private static final String OPENMUSIC = "OPENMUSIC"; 
	private static final String STOPMUSIC = "STOPMUSIC"; 
	public static final String STARTMOVE = "STARTMOVE"; 
	public static final String STOPMOVE = "STOPMOVE"; 
	private JLabel text= null;
	private GameButton musicButton;
	public GameButton startButton;
	private int count=0;
	public boolean on=true;
	private Thread thread;
	private SoundPlayer musicSoundPlayer;
//	Timer timer;
	final long timeInterval = 1000; 
//	private GameButton startButton;
//	private GameButton stop_pauseButton;
	public ControlJPanel(GamePlayingPanel gamePlayingPanel) { 
        // new 对象  
		startButton = new GameButton("暂停");
		
		this.setBorder(BorderFactory.createTitledBorder("~SETTING AREA~"));
		this.text=new JLabel("time:"+count+"s");
		JPanel timePanel = new JPanel();
		timePanel.setOpaque(false);
		timePanel.add(text);
		musicButton= new GameButton("音乐关");
		
		GridLayout gridLayout = new GridLayout(2,1,0,10);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(gridLayout);
		
		buttonPanel.add(startButton);
		buttonPanel.add(musicButton);
		
		Dimension d = new Dimension(Config.CONTROL_BUTTON_PANEL_WIDTH, Config.CONTROL_BUTTON_PANEL_HEIGHT);
		buttonPanel.setSize(d);
		buttonPanel.setPreferredSize(d);
		timePanel.setSize(20,20);
//		timePanel.setPreferredSize(100,100);
		
		Dimension d2 = new Dimension(Config.CONTROLJPANAL_WIDTH, Config.CONTROLJPANAL_HEIGHT);
		this.setSize(d2);
		this.setPreferredSize(d2);
        
        if (thread == null || !thread.isAlive())
    		thread = new Thread(this);
    		thread.start();
    		
    		BorderLayout mainLayout = new BorderLayout();
    		mainLayout.setVgap(20);
//    		mainLayout.setHgap(200);
    		JPanel mainPanel = new JPanel();

    		mainPanel.setOpaque(false);
    		mainPanel.setLayout(mainLayout);
		
    		mainPanel.add(timePanel, BorderLayout.NORTH);
    		mainPanel.add(buttonPanel);
//		this.add(startButton);
//		this.add(musicButton);
    		this.setOpaque(false);
    		this.add(mainPanel);
		
		musicButton.addActionListener(this);
		startButton.addActionListener(gamePlayingPanel);
		try {
		    this.musicSoundPlayer = new SoundPlayer(Config.GAME_MUSIC_AUDIO);
		    this.musicSoundPlayer.play();
		    this.musicSoundPlayer.loop();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
//		this.setLayout(new BorderLayout(5,5));
//		this.setOpaque(false);
//		this.add(text,BorderLayout.NORTH);
//		this.add(text,BorderLayout.NORTH);
//		this.add(mainJPanel);
		
		
		
//		timer.schedule(new TimerTask(){  
//            public void run(){  
//            	text.setText("time:"+count+"s"); 
//            	count++;
//            }  
//        },0,1000);
		

		musicButton.setActionCommand(STOPMUSIC);
		startButton.setActionCommand(STOPMOVE);
		this.setVisible(true);
		
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCmd = e.getActionCommand();
		if (actionCmd.equals(OPENMUSIC)) {
			musicButton.setText("音乐关");
			this.musicSoundPlayer.play();
			musicButton.setActionCommand(STOPMUSIC);
		    System.out.println("OPENMUSIC");
		}else if(actionCmd.equals(STOPMUSIC)) {
			musicButton.setText("音乐开");
			this.musicSoundPlayer.stop();
			musicButton.setActionCommand(OPENMUSIC);
		    System.out.println("STOPMUSIC");
		}
		
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while (on) {
			try {
				text.setText("time:"+count+"s"); 
            		count+=2;
				Thread.sleep(timeInterval);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}

	} 
    
    public void StartTime(){
    	if (thread == null || !thread.isAlive())
			thread = new Thread(this);
			thread.start();
		this.on=true;
	}
    
    public void StopTime(){
		this.on=false;
	}
	
	public int getTime(){
		return count;
	}

}
