package com.beancore.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.beancore.config.Config;
import com.beancore.util.Images;

public class GameLoadingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private Image gameLoadingTextImg;
    private JLabel gameLoadingAntLabel;
    private JLabel gameLoadingTextLabel;

    public GameLoadingPanel() {
	this.createLoadingPanel();
    }

	private void createLoadingPanel() {
		// TODO Auto-generated method stub
		this.gameLoadingTextImg = Images.GAME_LOADING_TEXT_IMG;
		
		gameLoadingAntLabel = new JLabel();
		gameLoadingAntLabel.setOpaque(false);
		gameLoadingTextLabel = new JLabel(new ImageIcon(this.gameLoadingTextImg));
		gameLoadingTextLabel.setOpaque(false);
		BorderLayout mainLayout = new BorderLayout();
		mainLayout.setVgap(25);
		
		FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER);
		JPanel panel1 = new JPanel();
		panel1.setLayout(flowLayout1);
		panel1.add(gameLoadingAntLabel);
		panel1.setOpaque(false);
		
		FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER);
		JPanel panel2 = new JPanel();
		panel2.setLayout(flowLayout2);
		panel2.add(gameLoadingTextLabel);
		panel2.setOpaque(false);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(panel1, BorderLayout.NORTH);
		mainPanel.add(panel2, BorderLayout.CENTER);
		this.setOpaque(false);
		this.add(mainPanel);
	}
	
	
	public void loadingGame() {
		int times = 3;
		for (int i = 0; i < times; i++) {
			this.gameLoadingAntLabel.setIcon(new ImageIcon(Images.GAME_LOADING_IMG1));
		    try {
			Thread.sleep(Config.GAME_LOADING_INTERVAL);
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		}
	    }
	
}
