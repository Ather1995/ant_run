package com.beancore.entity;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.beancore.config.Config;

public class Ant {
	private boolean direction;
//	木杆上的位置
	private int position;
//	在木杆上是第几只
	private int number=-1;
//	掉下的时间
	private int falltime=-1; 
	private boolean isAlive=true;
	private final int START = 1;
	private final int END = Config.ROBLENGTH;// 长度做死了..
	private Image antImage;
	
	public Ant(int number,int pos,Boolean isLeft) {  
        // TODO Auto-generated constructor stub  
        initAnt(number,pos,isLeft);
    }

	public void initAnt(int number, int pos, Boolean isLeft) {
		// TODO Auto-generated method stub
		this.setNumber(number);
		this.setPosition(pos);
		this.setDirection(isLeft);
	}

	public void setNumber(int number) {
		this.number=number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setImage(ImageIcon antImgList) {
		this.antImage=antImgList.getImage();
	}
	
	public Image getImage() {
		return this.antImage;
	}
	
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	public boolean isLeft() {  
        return direction;  
    }

	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {  
        return position;  
    }
	
	public void setTime(int falltime) {
		this.falltime = falltime;
	}
	
	public int getTime() {  
        return falltime;  
    } 

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Boolean getAlive() {
		if(this.position<=0||this.position>=Config.ROBWIDTH)
		{
			setAlive(false);
		}
		return isAlive;
	}

	public void move(int n) {
		if (isAlive) {
			if (direction) {
				position -= n;
			} else {
				position += n;
			}
			if (position < START) {
				isAlive = false;
			}
			if (position > END) {
				isAlive = false;
			}
		}
	}

	public void copy(Ant ant) {
		// TODO Auto-generated method stub
		this.direction=ant.isLeft();
		this.position=ant.getPosition();
		this.falltime=ant.getTime();
		this.isAlive=ant.getAlive();
		this.number=ant.getNumber();
		this.antImage=ant.getImage();
	}

}
