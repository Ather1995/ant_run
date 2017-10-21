package com.beancore.ui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.beancore.config.Config;
import com.beancore.entity.Ant;
import com.beancore.entity.Rob;
import com.beancore.util.Images;
import com.beancore.util.SoundPlayer;

public class CanvasPanel extends JPanel implements Runnable {
	private int time= 100;//刷新时间
	private int x=4;//每次移动速度
	private Thread thread;
	private boolean play = true;//图片是否移动
	private Image rob;
	private ImageIcon[] antImgList;
	private int m=0;
	private int ANT_NUM=Config.ANT_NUM;
	private int numOfAlive=Config.ANT_NUM;
	private Image ant[]=new Image[ANT_NUM];
//	实时变化的蚂蚁排位
	private Ant antState[]=new Ant[ANT_NUM];
	private Rob myRob;
	private ControlJPanel controlJPanel;

	MainFrame mainFrame;
	
	private SoundPlayer boomPlayer;
	
	private Boolean[] initSetting= Config.INITSETTING;
	private int[] initPosition=Config.POSITION;
	GamePlayingPanel gamePlayingPanel;
	GameLoadingPanel gameLoadingPanel;
	
	
	public CanvasPanel(GamePlayingPanel gamePlayingPanel, MainFrame mainFrame) {
		super();
		myRob= new Rob();
//		图片数组
		this.antImgList = new ImageIcon[2*Config.ANT_NUM];
		this.antImgList[0] = new ImageIcon(Images.ANTIMAGE00);
		this.antImgList[1] = new ImageIcon(Images.ANTIMAGE01);
		this.antImgList[2] = new ImageIcon(Images.ANTIMAGE10);
		this.antImgList[3] = new ImageIcon(Images.ANTIMAGE11);
		this.antImgList[4] = new ImageIcon(Images.ANTIMAGE20);
		this.antImgList[5] = new ImageIcon(Images.ANTIMAGE21);
		this.antImgList[6] = new ImageIcon(Images.ANTIMAGE30);
		this.antImgList[7] = new ImageIcon(Images.ANTIMAGE31);
		this.antImgList[8] = new ImageIcon(Images.ANTIMAGE40);
		this.antImgList[9] = new ImageIcon(Images.ANTIMAGE41);
		
		try {
		    this.boomPlayer = new SoundPlayer(Config.BOOM_AUDIO);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		initAnts(ANT_NUM,initPosition,initSetting);
		this.initSetting=Config.INITSETTING;
		rob=Images.GAME_PLAYING_ROB;
		

		this.mainFrame=mainFrame;
		
		initGamePlayingPanel(gamePlayingPanel);
		play = true;
		if (thread == null || !thread.isAlive())
		thread = new Thread(this);
		thread.start();
		}
	private void initGamePlayingPanel(GamePlayingPanel gamePlayingPanel2) {
		// TODO Auto-generated method stub
		this.gamePlayingPanel=gamePlayingPanel2;
		this.setBorder(BorderFactory.createTitledBorder("~PLAY AREA~"));
	}
	private void initAnts(int ANT_NUM, int[] initPosition,Boolean[] initSetting) {
		// TODO Auto-generated method stub
		for(int i=0;i<ANT_NUM;i++) {
//			匹配像素位置的值
			initPosition[i]=2*initPosition[i];
		}
		for(int i=0;i<ANT_NUM;i++) {
//			初始化响应蚂蚁的信息
			antState[i]=new Ant(i, initPosition[i],initSetting[i]);
			if(initSetting[i]) {
				antState[i].setImage(antImgList[2*i]);
				System.out.println("initSetting[i]true"+(2*i));
			}
			else {
				System.out.println("initSetting[i]false"+(2*i+1));
				antState[i].setImage(antImgList[2*i+1]);
			}
		}
	}
	
	
	private int getTime() {
		return gamePlayingPanel.getcount();
	}
	
	private void stopTime() {
		gamePlayingPanel.stopcount();;
	}
	
	
	public void pauseGame() {
		this.play=false;
	}
	
	public void startGame() {
		if (thread == null || !thread.isAlive())
			thread = new Thread(this);
			thread.start();
		this.play=true;
	}
	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawImage(rob, Config.XROBPOS, Config.YROBPOS, Config.ROBWIDTH, Config.ROBHEIGHT, this);
	
	

	for(int i=0;i<numOfAlive;i++) {
//		如果第一只到达0位置或右边到达600位置
		//两只蚂蚁相撞
		if((i!=numOfAlive-1)&&(antState[i].isLeft()==false)&&(antState[i+1].isLeft()==true)) {
			if(myRob.isColide(antState[i], antState[i+1])) {
				this.boomPlayer.play();
				System.out.println("boom");
				antState[i].setDirection(true);
				System.out.println(i+"******"+antState[i].isLeft());
				antState[i+1].setDirection(false);
				System.out.println((i+1)+"******"+antState[i+1].isLeft());
				if(numOfAlive%2==1) {
				antState[i].setImage(antImgList[i*2]);
				antState[i+1].setImage(antImgList[(i+1)*2+1]);
				}
				else {
				antState[i].setImage(antImgList[(i+1)*2]);
				antState[i+1].setImage(antImgList[(i+2)*2+1]);	
				}
				g.drawImage(antState[i].getImage(), antState[i].getPosition(), Config.YROBPOS, this);
				g.drawImage(antState[i+1].getImage(), antState[i+1].getPosition(), Config.YROBPOS, this);
			}
		}
		if(i==0&&antState[i].getPosition()<=0) {
			antState[0].setTime(this.getTime());
			myRob.setmaxTime(antState[i].getTime());
			myRob.setminTime(antState[i].getTime());
			myRob.setNumOfAlive(--numOfAlive);
			if(myRob.isAllFall()==true) {
				play=false;
				this.stopTime();
				Object[] options = {"朕知道了！退下"};
				int response=JOptionPane.showOptionDialog(this, "最后一只蚂蚁掉落用时："+(myRob.getmaxTime()-2)+"第一只蚂蚁掉落用时：："+(myRob.getminTime()-2), "Game Over!",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0)
				{ 
					System.exit(0);
				}
			}
			
			//更新现有蚂蚁的排位antState[]
			for(int j=0;j<numOfAlive;j++) {
				antState[j].copy(antState[j+1]);
				
				antState[j].setNumber(j);
				if(antState[j].isLeft()) {
					antState[j].setPosition(antState[j].getPosition()-x);
				}
				else {
					antState[j].setPosition(antState[j].getPosition()+x);
				}
			}
		}else if((i==numOfAlive-1)&&antState[i].getPosition()>=600) {
			antState[i].setTime(this.getTime());
			myRob.setmaxTime(antState[i].getTime());
			myRob.setminTime(antState[i].getTime());
			myRob.setNumOfAlive(--numOfAlive);
			if(myRob.isAllFall()==true) {
				play=false;
				this.stopTime();
				Object[] options = {"朕知道了！退下"};
				int response=JOptionPane.showOptionDialog(this, "最后一只蚂蚁掉落用时："+(myRob.getmaxTime()-2)+"第一只蚂蚁掉落用时：："+(myRob.getminTime()-2), "Game Over!",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0)
				{ 
					System.exit(0);
				}
			}
			//右边一只落下不用更新排位
		}
		else if(antState[i].isLeft()==true){
			
			g.drawImage(antState[i].getImage(), antState[i].getPosition()-x, Config.YROBPOS, this);
			antState[i].setPosition(antState[i].getPosition()-x);//更新位置
			antState[i].setAlive(antState[i].getAlive());//更新是否到头了
		}
		else{
			g.drawImage(antState[i].getImage(), antState[i].getPosition()+x, Config.YROBPOS, this);
			antState[i].setPosition(antState[i].getPosition()+x);//更新位置
			antState[i].setAlive(antState[i].getAlive());//更新是否到头了			
		}
		
		
	}
	if(numOfAlive==0)
	{
		play=false;
	}
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (play) {
			try {
				Thread.sleep(time);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}

	}

}
