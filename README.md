# Ant Run

蚂蚁爬杆离线小游戏
杆子上有5只蚂蚁，当蚂蚁爬到杆子的端点就会掉落。由于杆子太细，两只蚂蚁相撞是，它们不能交错通过，只能各自反向爬回去。对每只蚂蚁我们只知道它距离杆子左边的距离，但不知道他的朝向，请计算所有蚂蚁落下杆子的最短时间和最长时间
附加：给出自定义每只蚂蚁的朝向，给出全部蚂蚁落下的时间

## 项目展示
![all page](https://github.com/Ather1995/ant_run/blob/master/eclipse-workspace2/Ant_run_project/blob/ant_run.gif?raw=true)

## 首页动画效果
![befor page](https://github.com/Ather1995/ant_run/blob/master/eclipse-workspace2/Ant_run_project/blob/ani.png?raw=true)
首页是一个动画，同时给载入图片，资源一点时间

## 模式选择
![chosen page](https://github.com/Ather1995/ant_run/blob/master/eclipse-workspace2/Ant_run_project/blob/main.png?raw=true)
可以选择展示的模式

## 自选模式
![optional page](https://github.com/Ather1995/ant_run/blob/master/eclipse-workspace2/Ant_run_project/blob/optional.png?raw=true)
可以自定义蚂蚁的方向

### 技术亮点
1、求蚂蚁落下的最长最短时间
思路一：穷竭搜索算法，枚举所有蚂蚁的初始朝向组合，利用递归函数实现

进阶版：蚂蚁相遇后，当它们保持原样交错而过继续前进也不会有任何问题，可以认为每只蚂蚁都是独立运动的，所以只要求最长时间，只要求蚂蚁到杆子端点的最大距离。

2、加入计数器和声音效果，并且在蚂蚁相撞时也有碰撞声
另开一个线程，用于读取声音文件，并设置为循环播放

```
public SoundPlayer(String filePath) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	File file = new File(filePath);
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	clip = AudioSystem.getClip();
	clip.open(audioInputStream);
    }
```


```
public void loop() {
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
```

3、在开始的动画播放时间，进行图片资源的预先加载
```
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
		
	}
```