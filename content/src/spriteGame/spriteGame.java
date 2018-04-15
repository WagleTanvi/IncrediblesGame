

package spriteGame;


import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import java.awt.Font;
import sun.audio.*;


public class spriteGame  extends JPanel implements KeyListener,Runnable, MouseListener,ActionListener
{
	private int x;
	private int y;
	private int eX;
	private int eY;

	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage guy;
	BufferedImage[] guys=new BufferedImage[6];
	BufferedImage girl;
	BufferedImage[] girls =new BufferedImage[6];
	BufferedImage boy;
	BufferedImage[] boys =new BufferedImage[6];
	BufferedImage elasticWoman;
	BufferedImage[] elasticWomans =new BufferedImage[6];
	BufferedImage backgroundOne;
	BufferedImage backgroundTwo;
	BufferedImage backgroundThree;
	BufferedImage backgroundFour;
	BufferedImage title;
	BufferedImage evilGuy;
	BufferedImage explosion;
	BufferedImage[] explosions =new BufferedImage[6];
	BufferedImage enemy;
	BufferedImage heart;
	BufferedImage one;
	BufferedImage two;
	BufferedImage three;
	BufferedImage j;
	File soundFile;
	BufferedImage four;
	boolean restart=false;
	boolean power = false;
	boolean explode = false;
	int manCount=0;
	int explodeCount = 0;
	int momCount = 0;
	int girlCount = 0;
	int boyCount = 0;
	int addEnemyCount = 0; 
	int savePos = 0;
	boolean menu = true;
	ArrayList<Enemy> man = new ArrayList<Enemy> ();
	ArrayList<Background> m = new ArrayList<Background> ();
	ArrayList<Background> w = new ArrayList<Background> ();
	ArrayList<Background> gr = new ArrayList<Background> ();
	ArrayList<Background> b = new ArrayList<Background> ();
	String person = "man";
	int score = 0;
	int lives = 5;
	boolean subtractLife = false;
	int count = -200;
	int backgroundX = 0;
	boolean instructions = false;
	int countMax = 0;
	int p1 = 0;
	int p2 = -500;
	int p3 = -500;;
	int p4 = -500;;
	int image = 1;
	BufferedImage back;
	Clip clip;
	int level = 1;
	int seconds = 70;
	boolean endScreen = false;
	int highScore = 0;
	String name = "";
	boolean intersectWoman = false;
	boolean intersectMan = false;
	boolean intersectGirl  = false;
	boolean intersectBoy = false;
	//HOW IT WORKS:
	 /* Click on the region where the character is located and press that particular key to activate the power
	  * SPACE BAR = Mr. Incredible
	  * A = Elastic Girl
	  * S = Violet
	  * D = Dash
	  */
	
	public spriteGame ()
	{
		frame=new JFrame();
		try {
	        BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"));
	        String line = reader.readLine();
	        while (line != null)                 
	        {
	            try {
	                int s = Integer.parseInt(line.trim());   
	                if (s > highScore)                       
	                { 
	                    highScore = s; 
	                }
	            } catch (NumberFormatException e1) {}
	            line = reader.readLine();
	        }
	        reader.close();

	    } catch (IOException ex) {
	        System.err.println("error");
	    }
		x=150;
		backgroundX = 0;
		y=260;
		eX = 600;
		eY = 260;
		gameOn=true;
		try {
			enemy = ImageIO.read(new File("omnitron.png"));
			AffineTransform tx = new AffineTransform();
		    tx.scale(0.24, 0.26);
		    AffineTransformOp op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    enemy= op.filter(enemy, null);
		    heart = ImageIO.read(new File("heart.png"));
		    tx.scale(2.2, 2.2);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    heart = op.filter(heart, null);
		    backgroundOne = ImageIO.read(new File("Green4.png"));
		    tx.scale(0.8, 0.5);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    backgroundOne = op.filter(backgroundOne, null);
		    backgroundTwo = ImageIO.read(new File("b2.png"));
		    tx.scale(1, 0.90);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    backgroundTwo = op.filter(backgroundTwo, null);
		    backgroundThree = ImageIO.read(new File("back3.png"));
		    tx.scale(1, 0.95);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    backgroundThree = op.filter(backgroundThree, null);
		    backgroundFour = ImageIO.read(new File("b4.png"));
		    tx.scale(2, 2.43);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    backgroundFour = op.filter(backgroundFour, null);
		    tx.scale(0.8, 0.4);
		    tx.scale(0.8, 0.8);
		    one = ImageIO.read(new File("incredibles1.jpg"));
		    two = ImageIO.read(new File("incredibles2.jpg"));
		    three = ImageIO.read(new File("incredibles3.jpg"));
		    four = ImageIO.read(new File("incredibles4.jpg"));
		    tx.scale(2, 7);
		    op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		    one = op.filter(one, null);
		    two = op.filter(two, null);
		    three = op.filter(three, null);
		    four = op.filter(four, null);
		    countMax = 500-one.getHeight();
		    title = ImageIO.read(new File("t.png"));
		    tx.scale(0.6,0.6);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    title = op.filter(title, null);
		    back = ImageIO.read(new File("the.png"));
		    tx.scale(0.5,0.5);
		    op = new AffineTransformOp(tx,
		        AffineTransformOp.TYPE_BILINEAR);
		    back = op.filter(back, null);
		    j = ImageIO.read(new File("jackjack.jpg"));
		    tx.scale(2,1.3);
		    op = new AffineTransformOp(tx,
			        AffineTransformOp.TYPE_BILINEAR);
		    j= op.filter(j, null);
		    for (int x= 0; x<6;x++) {
		    	 m.add(new Background(backgroundOne, backgroundX+x*backgroundOne.getWidth()-2*x, 320-backgroundOne.getHeight()));
		    }
		    for (int x= 0; x<3;x++) {
		    	 w.add(new Background(backgroundTwo, backgroundX+x*backgroundTwo.getWidth(), 200));
		    }
		    for (int x= 0; x<2;x++) {
		    	 gr.add(new Background(backgroundThree, backgroundX+x*backgroundThree.getWidth(), 140));
		    }
		    for (int x= 0; x<6;x++) {
		    	 b.add(new Background(backgroundFour, backgroundX+x*backgroundFour.getWidth()-2*x, 80));
		    }
		    
		    //man.add(new Enemy(eX,eY));
		    int p = (int) (Math.random() * 4) + 1;  
		    if (p == 2) { // woman
		    		man.add(new Enemy(eX-200,eY-60,"woman"));
		    		man.add(new Enemy(eX-100,eY-120, "girl"));
		    		man.add(new Enemy(eX,eY,"man"));
		    }
		    else if (p == 1) { // man 
		    		man.add(new Enemy(eX-200,eY, "man"));
		    		man.add(new Enemy(eX-100,eY-60, "woman"));
		    		man.add(new Enemy(eX,eY-180, "boy"));
		    }
		    else if (p == 3) { // girl 
		    		man.add(new Enemy(eX-200,eY-120, "girl"));
		    		man.add(new Enemy(eX-100,eY, "man"));
		    		man.add(new Enemy(eX,eY-60, "woman"));
		    }
		    else if (p == 4) { // boy
	    			man.add(new Enemy(eX-200,eY-180, "boy"));
	    			man.add(new Enemy(eX-100,eY, "man"));
	    			man.add(new Enemy(eX,eY-60, "woman"));
		    }
	
			guy = ImageIO.read(new File("sprite.png"));
			for(int x=0;x<6;x++)
				guys[x]=guy.getSubimage(x*52,0,52,50);
			
			girl = ImageIO.read(new File("image.png"));
			
			girls[0]=girl.getSubimage(0,0,48,46);
			girls[1]=girl.getSubimage(48,0,36,46);
			girls[2]=girl.getSubimage(84,0,24,46);
			girls[3]=girl.getSubimage(108,0,43,46);
			girls[4]=girl.getSubimage(157,0,26,46);
			girls[5]=girl.getSubimage(184,0,41,46);
			
			boy = ImageIO.read(new File("dashRunning.png"));
			boys[0]=boy.getSubimage(0,0,32,37);
			boys[1]=boy.getSubimage(30,0,26,37);
			boys[2]=boy.getSubimage(58,0,22,37);
			boys[3]=boy.getSubimage(80,0,30,37);
			boys[4]=boy.getSubimage(110,0,27,37);
			boys[5]=boy.getSubimage(140,0,23,37);
	
			elasticWoman = ImageIO.read(new File("elasticGirl.png"));
			elasticWomans[0]=elasticWoman.getSubimage(0,0,30,53);
			elasticWomans[1]=elasticWoman.getSubimage(30,0,32,53);
			elasticWomans[2]=elasticWoman.getSubimage(60,0,28,53);
			elasticWomans[3]=elasticWoman.getSubimage(88,0,26,53);
			elasticWomans[4]=elasticWoman.getSubimage(114,0,28,53);
			elasticWomans[5]=elasticWoman.getSubimage(142,0,32,53);
			explosion = ImageIO.read(new File("explosion.png"));
		    //System.out.println(explosion.getWidth() + " " + explosion.getHeight() );
			for(int x=0;x<6;x++)
				explosions[x]=explosion.getSubimage(x*50,0,50,40);
			//360/3200 pixels 
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		frame.addKeyListener(this);
		addMouseListener(this);
		frame.add(this);
		frame.setSize(800,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
		music();
	}
	public void intersect() {
		Rectangle manRect = new Rectangle(x,y,52,50);
		Rectangle womanRect = new Rectangle(x,y-60,29,53);
		Rectangle girlRect = new Rectangle(x,y-120,35,53);
		Rectangle boyRect = new Rectangle(x,y-160,35,37);
		for(int i = 0; i < man.size(); i++) {
			Enemy e = man.get(i);
			Rectangle r2 = new Rectangle(e.x,e.y,enemy.getWidth(),enemy.getHeight());
			if(manRect.intersects(r2) || womanRect.intersects(r2) || girlRect.intersects(r2) || boyRect.intersects(r2) ){
			    if (power && e.person.equals(person)) { // set when you click a key
			    		explode = true;
			    		savePos = i; // which enemy to explode
			    }
			    else {
			    		int d = x-guys[0].getWidth()+5;
			    		if (e.person.equals("man") && intersectMan == false && e.x <= d) {
			    			lives--;
			    			lifeSound();
			    			intersectMan = true;
			    			repaint();
			    		}
			    		if (e.person.equals("woman") && intersectWoman == false && e.x <= d) {
			    			lives--;
			    			intersectWoman = true;
			    			lifeSound();
			    			repaint();
			    		}
			    		if (e.person.equals("girl") && intersectGirl == false && e.x <= d) {
			    			lives--;
			    			intersectGirl = true;
			    			lifeSound();
			    			repaint();
			    		}
			    		if (e.person.equals("boy") && intersectBoy == false && e.x <= d) {
			    			lives--;
			    			intersectBoy = true;
			    			lifeSound();
			    			repaint();
			    		}
			    }
			}
			e.x -= 3;
		}
	}
	
	public void addEnemy() {
		for (int p = 0; p< man.size(); p++) {
			if (man.get(p).x < -50) {
				man.remove(p);
			}
			if (intersectMan == true && man.get(p).person.equals("man") && man.get(p).x < 70) {
				//lives--;
				intersectMan = false;
			}
			if (intersectWoman == true && man.get(p).person.equals("woman") && man.get(p).x < 70) {
				//lives--;
				intersectWoman = false;
			}
			if (intersectBoy == true && man.get(p).person.equals("boy") && man.get(p).x < 70) {
				//lives--;
				intersectBoy = false;
			}
			if (intersectGirl == true && man.get(p).person.equals("girl") && man.get(p).x < 70) {
				//lives--;
				intersectGirl = false;
			}
		}
		if (addEnemyCount > seconds) {
			int person = (int) (Math.random() * 4) + 1;  // change to four 
		    if (person == 2) { // woman
		    		man.add(new Enemy(eX,eY-60,"woman"));
		    }
		    else if (person == 1) { // man 
		    		man.add(new Enemy(eX,eY, "man"));
		    }
		    else if (person == 3) { // girl 
		    		man.add(new Enemy(eX,eY-120, "girl"));
		    }
		    else if (person == 4) { // boy 
	    			man.add(new Enemy(eX,eY-180, "boy"));
		    }
		    addEnemyCount = 0;
		}
		//System.out.println(System.currentTimeMillis());
	}
	public void music(){
		if (menu == true) {
			soundFile = new File("first.wav");
		}
		else if (menu == false) {
			clip.stop();
			soundFile = new File("second.wav");
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class,format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		}
		catch(Exception e) {System.out.println("error");}
	}
	public void lifeSound(){
		soundFile = new File("lostLife.wav");
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class,format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			clip.start();			
		}
		catch(Exception e) {System.out.println("error");}
	}
	public void findHighScore() {
		/*try {
	        BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"));
	        String line = reader.readLine();
	        while (line != null)                 
	        {
	            try {
	                int s = Integer.parseInt(line.trim());   
	                if (score > s)                       
	                { 
	                    highScore = score; 
	                }
	            } catch (NumberFormatException e1) {}
	            line = reader.readLine();
	        }
	        reader.close();

	    } catch (IOException ex) {
	        System.err.println("error");
	    }*/
		
		try {
	        BufferedWriter output = new BufferedWriter(new FileWriter("highscores.txt", true));
	        output.newLine();
	        output.append("" + score);
	        output.close();

	    } catch (IOException ex1) {}
		if (highScore == 0) {
			highScore = score;
		}
	}
	public void run()
	{
		/*Logic
		 * Check if anything intersected?
		 * if user pressed the key, set animation at that position based on the person, remove omnitron 
		 * if user did not press the key then remove life of only that person and flicker animate
		 * 
		 * 
		 * 
		 * 
		 */
		while(true)
		{
			if (menu) {
				if (!instructions) {
					count= count + 20;
					repaint();
					if (count > countMax) {
						count = -200;
						if (image == 5) {
							repaint();
							try{t.sleep(15);} 
							catch(InterruptedException e){}
							instructions = true;
						}
						image++;
					}
					repaint();
				}
				else{
					/*if (name.equals("")) {
						name = JOptionPane.showInputDialog(frame, "Enter your username:", JOptionPane.QUESTION_MESSAGE);
					}*/
				}
				repaint();
			}
			else if(gameOn){
				intersect();
				//lives = 0;
				if (score == 3) {
					//lives = 0;
					level = 2;
					seconds = 40;
				}
				else if (score == 10) {
					level = 3;
					//lives = 0;
					seconds = 20;
				}
				if (explode) {
					explodeCount++;
					if(explodeCount>=6) {
						explode = false;
						explodeCount = 0;
						Enemy e = man.get(savePos);
						man.remove(savePos);
						savePos = 0;
						score++;
					}
				}
				if (lives == 0) {
					repaint();
					try{t.sleep(15);} 
					catch(InterruptedException e){}
					gameOn = false; 
					endScreen = true;
					findHighScore();
				}
				
				addEnemy();
				addEnemyCount++;
			
				for (int d=0; d<m.size(); d++) {
					m.get(d).x = m.get(d).x - 2;
				}
				for (int d=0; d<w.size(); d++) {
					w.get(d).x = w.get(d).x - 2;
				}
				for (int d=0; d<gr.size(); d++) {
					gr.get(d).x = gr.get(d).x - 2;
				}
				for (int d=0; d<b.size(); d++) {
					b.get(d).x = b.get(d).x - 2;
				}
				if (m.get(0).x < -backgroundOne.getWidth()+5) {
		    			m.remove(0);
				}
				if (m.get(m.size()-1).x + backgroundOne.getWidth() <= 800) {
					//System.out.println(m.get(m.size()-1).x);
					m.add(new Background(backgroundOne, 800-10, 320-backgroundOne.getHeight()));
				}
				if (w.get(0).x < -backgroundTwo.getWidth()) {
	    				w.remove(0);
				}
				if (w.get(w.size()-1).x + backgroundTwo.getWidth() <= 800) {
					w.add(new Background(backgroundTwo, 800-10, 200));

				}
				if (gr.get(0).x < -backgroundThree.getWidth()) {
    					gr.remove(0);
				}
				if (gr.get(gr.size()-1).x + backgroundThree.getWidth() <= 800) {
					gr.add(new Background(backgroundThree, 800-10, 140));

				}
				if (b.get(0).x < -backgroundFour.getWidth()) {
					b.remove(0);
				}
				if (b.get(b.size()-1).x + backgroundFour.getWidth() <= 800) {
					b.add(new Background(backgroundFour, 800-10, 80));
	
				}
			
				
				manCount++;
				momCount++;
				girlCount++;
				boyCount++;
				if (!power) { // normal animation for all the characters 
					y = 260;
					if (manCount>=6) 
						manCount=0;
					if (momCount >= 6) 
						momCount = 0;
					if (girlCount >= 6) 
						girlCount = 0;
					if (boyCount >= 6) 
						boyCount = 0;
				}
				if (power) { // if you pressed the key (handles the animation for each character)
					if (person.equals("man")) {
						animateMan();
						if (momCount >= 6) {momCount = 0;}
						if (girlCount >= 6) girlCount = 0;
						if (boyCount >= 6) boyCount = 0;
					}
					if (person.equals("woman")) {
						animateWoman();
						if (manCount>=6) {manCount=0;}
						if (girlCount >= 6) girlCount = 0;
						if (boyCount >= 6) boyCount = 0;
					}
					if (person.equals("girl")) {
						animateGirl();
						if (manCount>=6) {manCount=0;}
						if (momCount >= 6) {momCount = 0;}
						if (boyCount >= 6) boyCount = 0;
					}
					if (person.equals("boy")) {
						animateBoy();
						if (manCount>=6) {manCount=0;}
						if (momCount >= 6) {momCount = 0;}
						if (girlCount >= 6) girlCount = 0;
					// add the animation for the children 
					}
				}
				repaint();
			}
			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			try{t.sleep(60);} 
			catch(InterruptedException e){}
		}
	}
	public void animateMan() {
		if (manCount == 5) {
			y = y - 12;
		}
		if(manCount>=6) {
			manCount=0;
			y = y + 12;
			power = false;
			try{guy = ImageIO.read(new File("sprite.png"));}
			catch (IOException e) {System.out.println("error");}
			for(int x=0;x<6;x++)
				guys[x]=guy.getSubimage(x*52,0,52,50);
		}
	}
	
	public void animateWoman() {
		if(momCount>=6) {
			momCount=0;
			power = false;
			try{ elasticWoman = ImageIO.read(new File("elasticGirl.png"));}
			catch (IOException e) {System.out.println("error");}
			elasticWomans[0]=elasticWoman.getSubimage(0,0,30,53);
			elasticWomans[1]=elasticWoman.getSubimage(30,0,32,53);
			elasticWomans[2]=elasticWoman.getSubimage(60,0,28,53);
			elasticWomans[3]=elasticWoman.getSubimage(88,0,26,53);
			elasticWomans[4]=elasticWoman.getSubimage(114,0,28,53);
			elasticWomans[5]=elasticWoman.getSubimage(142,0,32,53);
		}
	}
	
	public void animateGirl() {
		if(girlCount>=6) {
			girlCount=0;
			power = false;
			try{ girl = ImageIO.read(new File("invisibleGirl.png"));}
			catch (IOException e) {System.out.println("error");}
			girls[0]=girl.getSubimage(0,0,46,53);
			girls[1]=girl.getSubimage(46,0,35,53);
			girls[2]=girl.getSubimage(80,0,26,53);
			girls[3]=girl.getSubimage(106,0,44,53);
			girls[4]=girl.getSubimage(149,0,34,53);
			girls[5]=girl.getSubimage(183,0,28,53);
		}
	}
	
	public void animateBoy() {
		if(boyCount>=6) {
			boyCount=0;
			power = false;
			try{ boy = ImageIO.read(new File("dashRunning.png"));}
			catch (IOException e) {System.out.println("error");}
			boys[0]=boy.getSubimage(0,0,32,37);
			boys[1]=boy.getSubimage(30,0,26,37);
			boys[2]=boy.getSubimage(58,0,22,37);
			boys[3]=boy.getSubimage(80,0,30,37);
			boys[4]=boy.getSubimage(110,0,27,37);
			boys[5]=boy.getSubimage(140,0,23,37);
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if (menu) {
			if (!instructions) {
				//g2d.setColor(Color.gray);
				Color color1 = Color.RED;
		        Color color2 = Color.ORANGE;
		        GradientPaint gp = new GradientPaint(0, 0, color1, 0, getWidth(), color2);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.fillRect(0,0,800,600);
				switch (image) {
					case 1: p1 = count; 
						break;
					case 2: p2 = count;
						break;
					case 3: p3 = count;
						break;
					case 4: p4 = count;
						break;
				}
				g2d.drawImage(one,100,p1, null);
				g2d.drawImage(two,100+one.getWidth()+10,p2, null);
				g2d.drawImage(three,100+2*(one.getWidth()+10),p3, null);
				g2d.drawImage(four,100+3*(one.getWidth()+10),p4, null);
				if (image >= 5) {
					g2d.drawImage(title,200,0, null);
				}
			}
			else {
				int w = getWidth();
		        int h = getHeight();
		        Color color1 = Color.RED;
		        Color color2 = Color.ORANGE;
		        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
		        g2d.drawImage(back,210,0,null);
		        g2d.setFont(new Font("Chalkboard", Font.BOLD, 15));
		        g2d.setColor(Color.BLACK);
		        g2d.drawString("All the Incredibles family got split up trying to escape Syndrome.", 160, 300);
				g2d.drawString("The each have to use their powers to defeat the Omnitrons in their way.", 140, 325);
				g2d.drawString("Click the person to select the incredibles player.", 220, 350);
				g2d.drawString("And then press the particular key to activate their superpower.", 160, 375);
				g2d.drawString("SPACEBAR = Mr. Incredible      A = ElasticGirl      S= Violet     D= Dash.", 110, 400);
				g2d.drawString("There are three levels. After defeating a certain number of omnitrons you level up!", 100, 425);
				g2d.setFont(new Font("Chalkboard", Font.BOLD, 20));
				g2d.drawString("Press Enter to continue", 290, 450);
			}
		}
		if (!menu){
		
			/*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String []fontFamilies = ge.getAvailableFontFamilyNames();
			for (int x =0;x <fontFamilies.length; x++) {
				System.out.println(fontFamilies[x]);
			}*/
			
			g2d.setFont(new Font("Chalkboard", Font.BOLD, 40));
			g2d.drawString("THE INCREDIBLES", 220, 50);
			g2d.setFont(new Font("Noteworthy", Font.BOLD, 20));
			if (person.equals("girl")) {
				g2d.drawString("Player Selected: Violet", 270, 350);
			}
			if (person.equals("man")) {
				g2d.drawString("Player Selected: Mr. Incredible", 270, 350);
			}
			if (person.equals("woman")) {
				g2d.drawString("Player Selected: Elastigirl", 270, 350);
			}
			if (person.equals("boy")) {
				g2d.drawString("Player Selected: Dash", 270, 350);
			}
			g2d.drawString("Score: "+score, 0, 380);
			g2d.setFont(new Font("Impact", Font.BOLD, 30));
			g2d.drawString("Level: "+level, 350, 400);
			for (int f=0; f<m.size(); f++) {
				g2d.drawImage(m.get(f).b,m.get(f).x, m.get(f).y, backgroundOne.getWidth(), backgroundOne.getHeight(),null );
			}
			
			for (int f=0; f<w.size(); f++) {
				g2d.drawImage(w.get(f).b,w.get(f).x, w.get(f).y, backgroundTwo.getWidth(), backgroundTwo.getHeight(),null );
			}
			for (int f=0; f<gr.size(); f++) {
				g2d.drawImage(gr.get(f).b,gr.get(f).x, gr.get(f).y, backgroundThree.getWidth(), backgroundThree.getHeight(),null );
			}
			for (int f=0; f<b.size(); f++) {
				g2d.drawImage(b.get(f).b,b.get(f).x, b.get(f).y, backgroundFour.getWidth(), backgroundFour.getHeight(),null );
			}
	
			
			for (int p = 0; p < lives; p++)
				g2d.drawImage(heart,740-p*heart.getWidth(),360,null);
			
			g2d.setColor(Color.BLACK);
			for (int p=0; p< man.size(); p++) {
				if (p == savePos && explode){
					g2d.drawImage(explosions[explodeCount],man.get(p).x,man.get(p).y,null);
				}
				else {
					g2d.drawImage(enemy,man.get(p).x,man.get(p).y,null);	
				}
			}
			g2d.drawImage(guys[manCount],x,y,null);
			g2d.drawImage(elasticWomans[momCount],x,y-60,null);
			g2d.drawImage(girls[girlCount],x,y-120,null);
			g2d.drawImage(boys[boyCount],x,y-160,null);
			g2d.setFont(new Font("Chalkboard", Font.BOLD, 13));
	        g2d.setColor(Color.BLACK);
			g2d.drawString("SPACEBAR = Mr. Incredibles' Superpower", 130, 420);
			g2d.drawString("A = ElasticGirl's Superpower", 410, 420);
			g2d.drawString("S = for Violet's Superpower", 130, 460);
			g2d.drawString("D = Dash's Superpower", 410, 460);
		}
		if (endScreen) {
			int w = getWidth();
	        int h = getHeight();
	        Color color1 = Color.RED;
	        Color color2 = Color.ORANGE;
	        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
			g2d.setColor(Color.black);
			//g2d.fillRect(0,0,800,600);
			g2d.drawImage(j,200,90,null);
			g2d.setFont(new Font("monospace", Font.BOLD, 20));
			g2d.drawString("Score: " + score, 320, 430);
			g2d.setFont(new Font("monospace", Font.BOLD, 28));
			if (highScore == score){
				g2d.drawString("Congrats you matched the highscore!", 160, 60);
				
			}
			if (score > highScore) {
				g2d.drawString("Congrats you made a new highscore!", 160, 60);
				
			}
			if (score < highScore) {
				g2d.drawString("Sorry you failed to beat the highscore of " + highScore, 140, 60);
				
			}
			g2d.setFont(new Font("monospace", Font.BOLD, 20));
			g2d.drawString("Press R to restart", 300, 470);
			
		}

	}
	public void keyPressed(KeyEvent key)
	{
		//System.out.println(key.getKeyCode());
		if(key.getKeyCode()==10) {
			menu = false;
			music();
		}
		if(key.getKeyCode()==32 && person.equals("man"))
		{
			try{guy = ImageIO.read(new File("punchIncredible.png"));}
			catch (IOException e) {System.out.println("error");}
			guys[0]=guy.getSubimage(0,14,46,57);
			guys[1]=guy.getSubimage(46,14,61,57);
			guys[2]=guy.getSubimage(110,20,51,50);
			guys[3]=guy.getSubimage(161,20,51,50);
			guys[4]=guy.getSubimage(161+51,20,51,50);
			guys[5]=guy.getSubimage(161+2*51,4,51,64);
			manCount = 0;
			power = true;
			person = "man";
		}
		if(key.getKeyCode()==65 && person.equals("woman"))
		{
			try{elasticWoman = ImageIO.read(new File("blownElastic.png"));}
			catch (IOException e) {System.out.println("error");}
			elasticWomans[0]=elasticWoman.getSubimage(0,0,39,50);
			elasticWomans[1]=elasticWoman.getSubimage(39,0,52,46);
			elasticWomans[2]=elasticWoman.getSubimage(90,0,69,55);
			elasticWomans[3]=elasticWoman.getSubimage(159,0,69,55);
			elasticWomans[4]=elasticWoman.getSubimage(363,0,50,50);
			elasticWomans[5]=elasticWoman.getSubimage(413,0,41,51);
			momCount = 0;
			person = "woman";
			power = true;
		}
		if(key.getKeyCode()==83 && person.equals("girl"))
		{
			try{girl = ImageIO.read(new File("purpleGirl.png"));}
			catch (IOException e) {System.out.println("error");}
			girls[0]=girl.getSubimage(0,0,26,50);
			girls[1]=girl.getSubimage(26,0,26,50);
			girls[2]=girl.getSubimage(52,0,39,50);
			girls[3]=girl.getSubimage(91,0,34,50);
			girls[4]=girl.getSubimage(125,0,30,50);
			girls[5]=girl.getSubimage(155,0,33,50);
			girlCount = 0;
			person = "girl";
			power = true;
		}
		if(key.getKeyCode()==68 && person.equals("boy"))
		{
			try{boy = ImageIO.read(new File("dashPower.png"));}
			catch (IOException e) {System.out.println("error");}
			boys[5]=boys[1];
			boys[0]=boys[0];
			boys[1] = boy.getSubimage(0,0,49,38);
			boys[3]=boy.getSubimage(49,0,36,38);
			boys[2]=boy.getSubimage(85,0,36,38);
			boys[4]=boy.getSubimage(126,0,36,38);
			boyCount = 0;
			person = "boy";
			power = true;
		}
		if(key.getKeyCode()==82) {
			if (score > highScore) {
				highScore = score;
			}
			power = false;
			explode = false;
			manCount=0;
			explodeCount = 0;
			momCount = 0;
			girlCount = 0;
			boyCount = 0;
			addEnemyCount = 0; 
			savePos = 0;
			lives = 5;
			subtractLife = false;
			count = -200;
			backgroundX = 0;
			instructions = false;
			countMax = 0;
			level = 1;
			seconds = 70;
			restart=true;
			gameOn = true;
			endScreen = false;
			score = 0;
			person = "man";
			m.clear();
			w.clear();
			gr.clear();
			b.clear();
			man.clear();
			  for (int x= 0; x<6;x++) {
			    	 m.add(new Background(backgroundOne, backgroundX+x*backgroundOne.getWidth()-2*x, 320-backgroundOne.getHeight()));
			    }
			    for (int x= 0; x<3;x++) {
			    	 w.add(new Background(backgroundTwo, backgroundX+x*backgroundTwo.getWidth(), 200));
			    }
			    for (int x= 0; x<2;x++) {
			    	 gr.add(new Background(backgroundThree, backgroundX+x*backgroundThree.getWidth(), 140));
			    }
			    for (int x= 0; x<6;x++) {
			    	 b.add(new Background(backgroundFour, backgroundX+x*backgroundFour.getWidth()-2*x, 80));
			    }
			    
			    
			    //man.add(new Enemy(eX,eY));
			    int p = (int) (Math.random() * 4) + 1;  // change to four 
			    if (p == 2) { // woman
			    		man.add(new Enemy(eX-200,eY-60,"woman"));
			    		man.add(new Enemy(eX-100,eY-120, "girl"));
			    		man.add(new Enemy(eX,eY,"man"));
			    }
			    else if (p == 1) { // man 
			    		man.add(new Enemy(eX-200,eY, "man"));
			    		man.add(new Enemy(eX-100,eY-60, "woman"));
			    		man.add(new Enemy(eX,eY-180, "boy"));
			    }
			    else if (p == 3) { // girl 
			    		man.add(new Enemy(eX-200,eY-120, "girl"));
			    		man.add(new Enemy(eX-100,eY, "man"));
			    		man.add(new Enemy(eX,eY-60, "woman"));
			    }
			    else if (p == 4) { // boy
		    			man.add(new Enemy(eX-200,eY-180, "boy"));
		    			man.add(new Enemy(eX-100,eY, "man"));
		    			man.add(new Enemy(eX,eY-60, "woman"));
			    }
			
		}
	}
	public void keyReleased(KeyEvent key)
	{
	}
	public void keyTyped(KeyEvent key)
	{
	}
	public static void main(String args[])
	{
		spriteGame app=new spriteGame();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int Mx = e.getX();
		int My = e.getY();
		//person="";
			if (My > 260 && My < 310) {
				person = "man";
			}
			else if (My > 200 && My < 260) {
				person = "woman";
				
			}
			else if (My > 140 && My < 200) {
				person = "girl";
			}
			else if (My > 80 && My < 140) {
				person = "boy";
			}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}


