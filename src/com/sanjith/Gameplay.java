package com.sanjith;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean  left = false;
	private boolean  right = false;
	private boolean  up = false;
	private boolean  down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lengthOfSnake = 3;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeImage;
	
	private int[] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,
		350,375,400,425,450,475,500,525,550,575,600,625,650,
		675,700,725,750,775,800,825,850};
	private int[] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625};
	private ImageIcon enemyimage;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int score = 0;
	
	private int moves = 0;
	
	private ImageIcon titleImage;
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		if(moves == 0) {
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		// TITLE IMAGE BORDER
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		// TITLE IMAGE
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		// BORDER FOR GAMEPLAY
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// BG FOR GAMEPLAY
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 780, 30);
		
		g.drawString("Length: " + lengthOfSnake, 780, 50);
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int a = 0; a<lengthOfSnake; a++) {
			if(a==0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a==0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a==0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			if(a==0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
			
			if(a!=0) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
			}
		}
		
		enemyimage = new ImageIcon("enemy.png");
		
		if((enemyXpos[xpos] == snakeXlength[0]) && (enemyYpos[ypos] == snakeYlength[0])) {
			lengthOfSnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			score+=1;
		}
		
		enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
		
		for(int b=1; b<lengthOfSnake; b++) {
			if(snakeXlength[b] == snakeXlength[0] && snakeYlength[b]==snakeYlength[0]) {
				left=false;
				right=false;
				up=false;
				down=false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("GAME OVER", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 30));
				g.drawString("Space to RESTART", 350, 340);
			}
		}
		
		g.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
			moves = 0;
			score = 0;
			lengthOfSnake = 3;
			left=false;
			right=false;
			up=false;
			down=false;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			if(!left)
				right = true;
			else
				left = true;
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			if(!right)
				left = true;
			else
				right = true;
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			if(!down)
				up = true;
			else
				down = true;
			left = false;
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			if(!up)
				down = true;
			else
				up = true;
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right) {
			for(int r=lengthOfSnake-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=lengthOfSnake; r>=0; r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r] + 25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r]>850) {
					snakeXlength[r] = 25;
				}
			}
			repaint();
		}
		if(left) {
			for(int r=lengthOfSnake-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for(int r=lengthOfSnake; r>=0; r--) {
				if(r==0) {
					snakeXlength[r] = snakeXlength[r] - 25;
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				if(snakeXlength[r]<25) {
					snakeXlength[r] = 850;
				}
			}
			repaint();
		}
		if(up) {
			for(int r=lengthOfSnake-1; r>=0; r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=lengthOfSnake; r>=0; r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r] - 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r]<75) {
					snakeYlength[r] = 625;
				}
			}
			repaint();
		}
		if(down) {
			for(int r=lengthOfSnake-1; r>=0; r--) {
				snakeXlength[r+1] = snakeXlength[r];
			}
			for(int r=lengthOfSnake; r>=0; r--) {
				if(r==0) {
					snakeYlength[r] = snakeYlength[r] + 25;
				}
				else {
					snakeYlength[r] = snakeYlength[r-1];
				}
				if(snakeYlength[r]>625) {
					snakeYlength[r] = 75;
				}
			}
			repaint();
		}
	}
}
