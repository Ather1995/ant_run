package com.beancore.ui;

import com.beancore.config.Config;



public class MaxStartPanel {
//  ���ϵĳ�ʼ״̬��trueΪ��falseΪ��
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
//        �������ķ���Ŀ������
        int numOfDirectionStates=1; 
        
//        �������еĿ������2^n��
        for(int i=0;i<a.length;i++){  
            numOfDirectionStates*=2;  
        }  
		
//      new������ʵ��5ֻ
      for(int i=0;i<a.length;i++){  
          ants[i]=new Ant(numOfDirectionStates);  
      }  
      
    //���������п��ܵ��ƶ������������� directionStates[32][5]�� ���д���ڼ���������д���ڼ�ֻ���ϵķ���  
      boolean directionStates[][]=new boolean[numOfDirectionStates][a.length];  
      for(int i=0;i<numOfDirectionStates;i++){  
          for(int j=0;j<a.length;j++){ 
              directionStates[i][j]=( (i&(1<<j) )==0 );  
          }  
      }
    //���б���������--���Ƕ�ÿһ�������ƶ���� ���в���  
      for(int i=0;i<numOfDirectionStates;i++){  
          //�ȳ�ʼ�� ��������  
          for(int j=0;j<a.length;j++){  
              ants[j].setDown(false);  
              ants[j].setLeft(directionStates[i][j]);  
              ants[j].setPos(a[j]);  
          }  
          Ant.setNumOfDown(0);  
          Ant.setTime(0);  
            
          while(true){   
              Ant.timeMove();     //ÿѭ��һ�Σ�ʱ������1  
                
              //ÿֻ������һ��  
              for(int j=0;j<a.length;j++){  
                  ants[j].move(1);  
              }  
                
              //����������һ����Ȼ���ڿ�����ͷ��ײͷ�����  
              for(int j=0;j<a.length-1;j++){  
                  if(!ants[j].isLeft() && ants[j+1].isLeft() &&  
                          !ants[j].isDown() && !ants[j+1].isDown()){  
                      //��ͷ  
                      if(ants[j].getPos()==ants[j+1].getPos()){  
                          System.out.println("������ͷ����ͷ��");  
                          ants[j].turnRound();  
                          ants[j+1].turnRound();  
                      }  
                        
                      //ײͷ  
                      if(ants[j].getPos()>ants[j+1].getPos()){  
                          //��һ����Ч�����ı䷽��  
                          System.out.println("����ײͷ���ⲽ��Ч������һ������ͷ��");  
                          ants[j].move(-1);  
                          ants[j+1].move(-1);  
                          ants[j].turnRound();  
                          ants[j+1].turnRound();  
                      }  
                  }  
              }  
                
              if(Ant.getNumOfDown()==a.length){  
                  //����������ȫ�����¸���ʱ������ѭ����ִ����һ�����  
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
    private static int numOfDown;   //�����˼�ֻ����  
    private static int time;  
    private static int ANT_NUM;     //�ܹ��м�ֻ����  
    private static int minTime=Integer.MAX_VALUE;  
    private static int maxTime=-1;  
      
    private static int times[];  
    private static int count;  
      
  
  
      
    private boolean left;       //�����ƶ�����  
    private int pos;        //����λ��  
    private boolean down;       //�����Ƿ�����  
      
    public Ant(int a) {  
        // TODO Auto-generated constructor stub  
        times=new int[a];  
    }  
    
    public void move(int n){  
        //û�е��¸��Ӳ��ƶ�  
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
                    System.out.println("����ȫ������ȥ��");  
                    if(time<minTime){  
                        minTime=time;  
                    }  
                    if(time>maxTime){  
                        maxTime=time;  
                    }  
                    times[count]=time;  
                    count++;  
                    System.out.println("��ȥʱ��Ϊ��"+time);  
                    System.out.println("��ʱ��Сʱ��Ϊ:"+minTime);  
                    System.out.println("��ʱ���ʱ��Ϊ:"+maxTime);  
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
        //�ı����ϵķ���  
        left=!left;  
    }  
      
}
