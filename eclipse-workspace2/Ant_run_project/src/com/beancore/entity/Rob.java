package com.beancore.entity;

import com.beancore.config.Config;


/*判断蚂蚁移动距离,方向
设置蚂蚁编号
判断是否全部落下 isAllFall
相撞位置pos，蚂蚁转向
一只蚂蚁落下boolen isFall
蚂蚁的排序变动changeNumber
蚂蚁贴图
*/
public class Rob {
	private final int START = 1;
	private final int END = Config.ROBLENGTH;
	private int numOfAlive=Config.ANT_NUM;   //剩下了几只蚂蚁 
	private int ANT_NUM= Config.ANT_NUM;     //总共有几只蚂蚁  
    private int minTime=Integer.MAX_VALUE;  
    private int maxTime=-1;
//    private int pos[]=Config.POSITION;
//    Ant ants[];//存放实例蚂蚁
    
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
 
    
//    判断是否碰撞，true碰上
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
