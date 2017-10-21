package com.beancore.ui;

import com.beancore.config.Config;



public class MaxStartPanel {
//  蚂蚁的初始状态，true为左，false为右
	private Boolean[] initSetting= Config.INITSETTING;
	int a[]= Config.POSITION;
	private static int totalminTime=Integer.MAX_VALUE;  
    private static int totalmaxTime=-1;
    private Boolean[][] maxinitSetting=new Boolean[16][5];
    private Boolean[] mininitSetting=new Boolean[5];
    MainFrame mainFrame;

  
	public MaxStartPanel(MainFrame mainFrame){
		this.mainFrame=mainFrame;
		System.out.println("MaxStartPanel"); 
		Ant ants[]=new Ant[a.length];
		Ant.setANT_NUM(a.length);  
//        蚂蚁爬的方向的可能情况
        int numOfDirectionStates=1; 
        
//        计算所有的可能情况2^n种
        for(int i=0;i<a.length;i++){  
            numOfDirectionStates*=2;  
        }  
		
//      new出蚂蚁实例5只
      for(int i=0;i<a.length;i++){  
          ants[i]=new Ant(numOfDirectionStates);  
      }  
      
    //把蚂蚁所有可能的移动方向的情况存在 directionStates[32][5]里 。行代表第几种情况，列代表第几只蚂蚁的方向  
      boolean directionStates[][]=new boolean[numOfDirectionStates][a.length];  
      for(int i=0;i<numOfDirectionStates;i++){  
          for(int j=0;j<a.length;j++){ 
              directionStates[i][j]=( (i&(1<<j) )==0 );  
          }  
      }
    //进行暴力搜索，--就是对每一种蚂蚁移动情况 进行测试  
      for(int i=0;i<numOfDirectionStates;i++){  
          //先初始化 蚂蚁属性  
          for(int j=0;j<a.length;j++){  
              ants[j].setDown(false);  
              ants[j].setLeft(directionStates[i][j]);  
              ants[j].setPos(a[j]);  
          }  
          Ant.setNumOfDown(0);  
          Ant.setTime(0);  
            
          while(true){   
              Ant.timeMove();     //每循环一次，时间增加1  
                
              //每只蚂蚁走一步  
              for(int j=0;j<a.length;j++){  
                  ants[j].move(1);  
              }  
                
              //先让蚂蚁走一步，然后在考虑碰头与撞头的情况  
              for(int j=0;j<a.length-1;j++){  
                  if(!ants[j].isLeft() && ants[j+1].isLeft() &&  
                          !ants[j].isDown() && !ants[j+1].isDown()){  
                      //碰头  
                      if(ants[j].getPos()==ants[j+1].getPos()){  
                          System.out.println("发生碰头，掉头走");  
                          ants[j].turnRound();  
                          ants[j+1].turnRound();  
                      }  
                        
                      //撞头  
                      if(ants[j].getPos()>ants[j+1].getPos()){  
                          //这一步无效，并改变方向  
                          System.out.println("发生撞头，这步无效，回退一步，掉头走");  
                          ants[j].move(-1);  
                          ants[j+1].move(-1);  
                          ants[j].turnRound();  
                          ants[j+1].turnRound();  
                      }  
                  }  
              }  
                
              if(Ant.getNumOfDown()==a.length){  
                  //当所有蚂蚁全部掉下杆子时，跳出循环，执行下一种情况  
                  break;  
              }  
          }  
      }  
      int times[]=Ant.getTimes();
      totalminTime=Ant.getMinTime()/5;
      totalmaxTime=Ant.getMaxTime()/5;
      
      int maxcount=0; 
      int mincount=0; 
      for(int i=0;i<times.length;i++){  
          if( times[i]==Ant.getMinTime() ){ 
        	  System.out.println("times[i]==Ant.getMinTime() ");
              for(int j=0;j<ants.length;j++){
                      mininitSetting[mincount++]=directionStates[i][j];
              } 
              System.out.println();
          } 
          if( times[i]==Ant.getMaxTime() ){ 
        	  System.out.println("times[i]==Ant.getMaxTime()  ");
              for(int j=0;j<ants.length;j++){
            	  		  System.out.print(maxcount);
                      maxinitSetting[maxcount][j]=directionStates[i][j]; 
                      System.out.print(maxinitSetting[maxcount][j]);
              } 
              maxcount++;
              System.out.println();
          }
      } 
      
	}
	
	public Boolean[] getinitSetting() {
		return initSetting;
	}
	
	public void setmaxinitSetting() {
		this.initSetting=maxinitSetting[(int) (Math.random()*17)];
		Config.INITSETTING=this.initSetting;
		mainFrame.startGameAction();
		
	}
	
	public void setmininitSetting() {
		this.initSetting=mininitSetting;
		Config.INITSETTING=this.initSetting;
		mainFrame.startGameAction();
		
	}
}


class Ant{  
    public final static int LEFT_END=0;  
    public final static int RIGHT_END=0;  
    private static int numOfDown;   //掉下了几只蚂蚁  
    private static int time;  
    private static int ANT_NUM;     //总共有几只蚂蚁  
    private static int minTime=Integer.MAX_VALUE;  
    private static int maxTime=-1;  
      
    private static int times[];  
    private static int count;  
      
  
  
      
    private boolean left;       //蚂蚁移动方向  
    private int pos;        //蚂蚁位置  
    private boolean down;       //蚂蚁是否落下  
      
    public Ant(int a) {  
        // TODO Auto-generated constructor stub  
        times=new int[a];  
    }  
    
    public void move(int n){  
        //没有掉下杆子才移动  
        if(!down){  
            if(left){  
                pos-=n;  
            }else{  
                pos+=n;  
            }  
              
            if(pos<=0||pos>=300){  
                down=true;  
                numOfDown++;  
                if(numOfDown==ANT_NUM){  
                    System.out.println("蚂蚁全部掉下去了");  
                    if(time<minTime){  
                        minTime=time;  
                    }  
                    if(time>maxTime){  
                        maxTime=time;  
                    }  
                    times[count]=time;  
                    count++;  
                    System.out.println("用去时间为："+time);  
                    System.out.println("此时最小时间为:"+minTime);  
                    System.out.println("此时最大时间为:"+maxTime);  
                }  
            }  
        }  
    }  
      
    public static int[] getTimes() {  
        return times;  
    }  
      
    public static void setTimes(int[] times) {  
        Ant.times = times;  
    }  
      
    public static int getNumOfDown() {  
        return numOfDown;  
    }  
  
  
    public static void setNumOfDown(int numOfDown) {  
        Ant.numOfDown = numOfDown;  
    }  
  
  
    public static int getTime() {  
        return time;  
    }  
  
  
    public static void setTime(int time) {  
        Ant.time = time;  
    }  
      
    public static void timeMove() {  
        Ant.time++;  
    }  
      
    public static int getANT_NUM() {  
        return ANT_NUM;  
    }  
  
  
    public static void setANT_NUM(int aNT_NUM) {  
        ANT_NUM = aNT_NUM;  
    }  
  
  
    public static int getMinTime() {  
        return minTime;  
    }  
  
  
    public static void setMinTime(int minTime) {  
        Ant.minTime = minTime;  
    }  
  
  
    public static int getMaxTime() {  
        return maxTime;  
    }  
  
  
    public static void setMaxTime(int maxTime) {  
        Ant.maxTime = maxTime;  
    }  
  
  
    public boolean isLeft() {  
        return left;  
    }  
  
  
    public void setLeft(boolean left) {  
        this.left = left;  
    }  
  
  
    public int getPos() {  
        return pos;  
    }  
  
  
    public void setPos(int location) {  
        this.pos = location;  
    }  
  
  
    public boolean isDown() {  
        return down;  
    }  
  
  
    public void setDown(boolean down) {  
        this.down = down;  
    }  
      
    public void turnRound(){  
        //改变蚂蚁的方向  
        left=!left;  
    }  
      
}
