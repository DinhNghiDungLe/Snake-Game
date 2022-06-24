package Game;

import java.util.ArrayList;
import processing.core.*; 
 
public class Client extends PApplet {
	
	Game game;
	String name;
	Button sort,retry,easy,medium,hard;
	boolean sD;
	
	public void setup() {
		background(200);
		noStroke();
		rectMode(CENTER);
		textAlign(CENTER);
		game = new Game(width,height);
		frameRate(60);
		name = "";
		retry = new Button(width/2-100,height-80,120,60,"Retry");
		sort = new Button(width/2+100,height-80,120,60,"Sort score");
		easy=new Button(width/3-120,height-80,120,60,"Easy"); //Easy
		medium=new Button(2*width/3-120,height-80,120,60,"Medium"); //Medium
		hard=new Button(width-120,height-80,120,60,"Hard"); //Hard
		sD=false;
	}

	public void draw() {
		
		drawScreen();
		int page = game.getPage();

		if(page==0) {
			// Draw start page
			fill(255);
			textSize(42);
			text("N0d3Sn4k3",width/2,height/2-30);
			textSize(24);
			text("Select a difficulty",width/2,height/2+30);
			//Easy level
			stroke(255);
			strokeWeight(2);
			fill(0x2ebcbc);
			if(easy.onButton(mouseX,mouseY)) {
				fill(106,200,200);
			}
			rect(easy.getX(),easy.getY(),easy.getWidth(),easy.getHeight());		
			fill(0x2ebcbc);
			//Medium level
			if(medium.onButton(mouseX,mouseY)) {
				fill(106,200,200);
			}
			rect(medium.getX(),easy.getY(),medium.getWidth(),medium.getHeight());
			fill(0x2ebcbc);
			//Hard level
			if(hard.onButton(mouseX,mouseY)) {
				fill(106,200,200);
			}
			rect(hard.getX(),medium.getY(),hard.getWidth(),hard.getHeight());
			fill(255);
			text(medium.getText(),medium.getX(),medium.getY()+10);
			text(easy.getText(),easy.getX(),easy.getY()+10);
			text(hard.getText(),hard.getX(),hard.getY()+10);
			//
		}else if (page==1){
			drawGame();
		}else if(page==2) {
			drawSnake();
			textSize(42);
			text("Game Over",width/2,height/2-30);
			textSize(24);
			fill(0,112,106);
			text("Type your name then click anywhere",width/2,height/2+30);
			fill(255);
			text(name,width/2,height/2+90);
		}else {
			drawScores();
		}
		
	}
	
	public void drawScreen() {
		// Draw background
		background(0x2ebcbc);
		// Draw borders
		stroke(0, 158, 154);
		strokeWeight(20);
		line(0,0,width,0);
		line(0,0,0,height);
		line(0,height,width,height);
		line(width,0,width,height);
	}
	
	public void drawGame() {
		// Draw apples
		ArrayList<Apple> apples = game.getApples();
		noStroke();
		if(apples==null||game.getSnake()==null) {
			fill(255);
			text("You need to finish startGame and Game in Game.java",width/2,height/2);
			return;
		}
		for(Apple a : apples) {
			if(a.getEffect()>0&&a.getEffect()!=10) {
				fill(0,112,106);
			}else if(a.getEffect()==10) { //draw BLUE APPLES
				fill(0,0,255);}
			else {
				fill(219,61,59);
			}
			ellipse(a.getX(),a.getY(),20,20);
		}
		// Draw snake
		drawSnake();
		// Draw score
		textSize(24);
		fill(255);
		text("Score: "+game.getCurrScore(),width/2,40);
		// Step
		game.step(width, height);
	}
	public void drawSnake() {
		// Draw snake
		Body[] parts = game.getSnake().getSnakeBody();
		if(parts == null) {
			fill(255);
			text("You need to finish getSnakeBody Snake.java",width/2,height/2);
		}else {
			noStroke();
			for(int i = 0; i < parts.length; i++) {
				fill(0, 144, 140);
				if(i==0) {
					fill(0,112,106);
				}
				if(parts[i].isRed()) {
					fill(219,61,59);
				}
				rect(parts[i].getX(),parts[i].getY(),18,18);
			}
		}
	}
	
	public void drawScores() {
		textSize(42);
		fill(255);
		text("Leaderboard",width/2,100);
		
		ArrayList<Score> scores = game.getScores();
		
		if(scores == null) {
			fill(255);
			text("You need to finish Game in Game.java",width/2,height/2);
			return;
		}
		
		textSize(20);
		int y = 150;
		text("Player",width/2-100,y);
		text("Score",width/2+100,y);
		for(int i = 0; i < 5&&i<scores.size(); i++) {
			y+=50;
			Score s = scores.get(i);
			text(s.getName(),width/2-100,y);
			text(s.getScore(),width/2+100,y);
		}
		
		stroke(255);
		strokeWeight(2);
		fill(0x2ebcbc);
		if(sort.onButton(mouseX,mouseY)) {
			fill(106,200,200);
		}
		rect(sort.getX(),sort.getY(),sort.getWidth(),sort.getHeight());		
		fill(0x2ebcbc);
		if(retry.onButton(mouseX,mouseY)) {
			fill(106,200,200);
		}
		rect(retry.getX(),sort.getY(),retry.getWidth(),retry.getHeight());
		fill(255);
		text(retry.getText(),retry.getX(),retry.getY()+10);
		text(sort.getText(),sort.getX(),sort.getY()+10);
	}
	
	public void mouseClicked() {
		if(game.getPage()==0) {
			if(easy.onButton(mouseX,mouseY)) {frameRate(10);} //default speed in easy level
			if(medium.onButton(mouseX,mouseY)) {frameRate(20);}//two times faster in medium
			if(hard.onButton(mouseX,mouseY)) {frameRate(30);}//three times faster in hard
			game.setPage(1);
		}else if(game.getPage()==2) {
			frameRate(60);
			game.addScore(name);
			game.setPage(3);
			name = "";
			sD=false;
		}else if(game.getPage()==3) {
			if(sort.onButton(mouseX,mouseY)) {
				sD=!sD;
				game.sortScores(sD);
			}
			if(retry.onButton(mouseX,mouseY)) {
				game.startGame(width,height);
			}
		}
	}
	public void keyPressed() {
		if(game.getPage()==1) {
			if(keyCode==UP) {
				game.getSnake().setDir(0,-1);
			}else if(keyCode==DOWN) {
				game.getSnake().setDir(0,1);
			}else if(keyCode==LEFT) {
				game.getSnake().setDir(-1,0);
			}else if(keyCode==RIGHT) {
				game.getSnake().setDir(1,0);
			}
		}else if(game.getPage()==2) {
			if(key!=CODED)
				name+=key;
		} 
	}
	public void settings() {  
		// Enter your screen size below
		size(800, 600); 
	}
	public static void main(String[] args) {
		PApplet.main("Game.Client"); // The "Game.Client" should match your project
	}
}
