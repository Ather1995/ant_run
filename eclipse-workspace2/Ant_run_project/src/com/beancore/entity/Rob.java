package com.beancore.entity;

import com.beancore.config.Config;


/*�ж������ƶ�����,����
�������ϱ��
�ж��Ƿ�ȫ������ isAllFall
��ײλ��pos������ת��
һֻ��������boolen isFall
���ϵ�����䶯changeNumber
������ͼ
*/
public class Rob {
	private final int START = 1;
	private final int END = Config.ROBLENGTH;
	private int numOfAlive=Config.ANT_NUM;   //ʣ���˼�ֻ���� 
	private int ANT_NUM= Config.ANT_NUM;     //�ܹ��м�ֻ����  
    private int minTime=Integer.MAX_VALUE;  
    private int maxTime=-1;
//    private int pos[]=Config.POSITION;
//    Ant ants[];//���ʵ������
    
//    public Rob(Boolean isLeft[]) {  
//        // TODO Auto-generated constructor stub  
//         for(int i=0;i<ANT_NUM;i++) {
//        	 ants[i]=new Ant(i,pos[i],isLeft[i]);
//         }
//    }
    public int getNumOfAlive() {  
        return numOfAlive;  
    } 
  
    public void setNumOfAlive(int numOfAlive) {  
        this.numOfAlive = numOfAlive;  
    }
    
    public Boolean isAllFall() {
    	if(numOfAlive==0) {
    		return true;
    	}else{
    		return false;
    	}
    }
 
    
//    �ж��Ƿ���ײ��true����
    public Boolean isColide(Ant leftAnt,Ant rightAnt) {
    	if(leftAnt.getAlive()&&rightAnt.getAlive()&&leftAnt.getPosition()>=rightAnt.getPosition())
    		return true;
    	return false;    	
    }
    
    public void setminTime(int fallTime) {  
        if(fallTime<this.minTime)  {
        	this.minTime=fallTime;
        }
    }
    
    public int getminTime() {  
        return minTime;
    }
    
    public void setmaxTime(int fallTime) {  
        if(fallTime>this.minTime)  {
        	this.maxTime=fallTime;
        }
    }
    
    public int getmaxTime() {  
        return maxTime;
    }
    
}
