package Game;

import java.util.ArrayList;

public class Snake {
	private Body head; // the head of the Snake
	// The horizontal direction of the Snake
	private int dirX; // 1 (right), -1 (left) or 0
	// The vertical direction of the Snake
	private int dirY; // 1 (down), -1 (up) or 0
	
	// DO NOT EDIT BELOW
	public Body getHead() {
		return head;
	}
	public void setDir(int dirX,int dirY) {
		this.dirX=dirX;
		this.dirY=dirY;
	}
	// DO NOT EDIT ABOVE
	
	
	/**
	 * getSize (2 marks)
	 * 
	 * @return number of Body objects within the Snake
	 * 
	 */
	public int getSize() {
	Body current=head;
	int count=0;
	while(current!=null) {current=current.getNext(); count++;} //count the number of body objects
		return count; 
	}
	
	/**
	 * getSnakeBody (4 marks)
	 * 
	 * This method is used to draw the Snake within Client.java.
	 * 
	 * @return an array which contains all the snake's Body objects.
	 * 
	 */
	public Body[] getSnakeBody(){
		// To be completed
	Body[]b=new Body[getSize()];
	Body current=head;
	int i=0;
	while(current!=null) {b[i]=current; current=current.getNext(); i++;} //array contains all snake body objects
	return b;
	}
	
	/**
	 * Snake(int,int) (4 marks)
	 * 
	 * @param w - the width of the screen
	 * @param h - the height of the screen
	 * 
	 * This constructor must set the head of the Snake to be
	 * a new Body object positioned in the center of the screen.
	 * 
	 * Three more Body Objects must be added below the head of the Snake
	 * 
	 * The snake's direction must also be set so that it moves upwards. (Look at setDir above)
	 * 
	 * (This is so that when the Game starts the Snake consists of 4 parts in total)
	 * 
	 * Hint: your solution should be around 6 lines long including a loop. 
	 * 
	 */
	public Snake(int w, int h) {
	setDir(0,-1); //set direction that the snake move upwards
	head = new Body(w/2, h/2);
	Body current = head;
	int i = 20;
	while (current != null) {
	current.setNext(new Body(w/2,h/2+i));
	current = current.getNext();
	i+=20;
	if(i>60) {break;}}	//add 3 more body objects below the head
	}
	
	/**
	 * move (10 marks)
	 * 
	 * This method must move the snake.
	 * 
	 * The head of the snake must move 20 pixels according to dirX and dirY.
	 * 
	 * The Body object after head must take the old co-ordinates of the head.
	 * 
	 * This process must then be repeated.
	 * 
	 * That is, the co-ordinates of the next Body object must be set to the old 
	 * co-ordinates of the previous Body object.
	 * 
	 * Hint: you only need 1 loop here.
	 */
	public void move() {
		// To be completed
		int x=head.getX(); int y=head.getY();
		x+=20*dirX; 
		y+=20*dirY;
		Body add=new Body(x,y);
		add.setNext(head);
		head=add; //add head to new coordinates
		Body current=head;
		while(current.getNext().getNext()!=null) {
		current=current.getNext();}
		current.setNext(null); //remove the last body object
	}
	
	/**
	 * grow(int) (10 marks)
	 * 
	 * @param size - the number of Body objects to add to the snake.
	 * 
	 * This method should add Body objects to the end of the snake.
	 * 
	 * The co-ordinates of the new Body object should match the co-ordinates
	 * of the last Body object in the snake.
	 * 
	 */
	public void grow(int size) {
		// To be completed
	Body current=head;
	while(current.getNext()!=null) {
		current=current.getNext();} //move to the last object
	while(current!=null&&size>0) {
	int x=current.getX(); int y=current.getY(); 
	x-=dirX*20;
	y-=dirY*20;
	Body a=new Body(x,y); //make new object after the last object
	a.setNext(current.getNext()); 
	current.setNext(a); //set new one to be the last object
	current=current.getNext();
	size--;} //number of Body objects to add
		
	}
	
	/**
	 * shrink (2 marks)
	 * 
	 * This method should reduce the length of the snake by 1 Body object.
	 * 
	 * The length of the snake should only be reduced if the size of the snake
	 * is greater than 3.
	 * 
	 */
	public void shrink() {
		// To be completed
		if(getSize()>3) { 
		head=head.getNext();} //remove 1 body object
		
	}
	
	/**
	 * hitSelf (4 marks)
	 * 
	 * @return true if the snake's head hits any other part of its body.
	 * 		   false otherwise.
	 */
	public boolean hitSelf() {
		// To be completed
	int x=head.getX();
	int y=head.getY();
	Body current=head.getNext();
	while(current!=null) {if(current.getX()==x&&current.getY()==y) {return true;} //check if the head hits other parts
	current=current.getNext();}
	return false;
	}
	
	/**
	 * hitWalls (2 marks)
	 * 
	 * @param w - the width of the screen
	 * @param h - the height of the screen
	 * 
	 * @return true if the snake's head hits or passes the edges of the screen.
	 * 		   false otherwise.
	 * 
	 */
	public boolean hitWalls(int w, int h) {
		// To be completed
	int x=head.getX();
	int y=head.getY();
	if(x<=20||x>=w-20||y<=20||y>=h-20) {return true;} //check if the snake's head hits or passes the edges of the screen
		return false;
	}
	
	/**
	 * eat(Apple) (4 marks)
	 * 
	 * @param a - the Apple to check collision with
	 * 
	 * This method should check to see whether the head of the snake collides
	 * with the parameter Apple.
	 * 
	 * If there is a collision then either the shrink or grow methods must be called 
	 * depending on the effect of the Apple.
	 * 
	 * @return true if the head of the snake collides with the Apple.
	 * 		   false otherwise.
	 * 
	 */
	public boolean eat(Apple a) {
		// To be completed
	//distance between the head of the snake and the Apple
	double distance=Math.sqrt(((a.getX()-head.getX())*(a.getX()-head.getX()))+((a.getY()-head.getY())*(a.getY()-head.getY())));
	if(distance<=0) {grow(a.getEffect()); if(a.getEffect()==-1) {shrink();} return true;} //check if it collides
		return false;
	}
	
	/**
	 * allRed(Body) (5 marks)
	 * 
	 * YOUR SOLUTION TO THIS METHOD MUST BE RECURSIVE
	 * 
	 * @param current - A body Object which represents the current location within the Snake
	 * 
	 * This method should set every Body object from current onwards to red.
	 * 
	 * Hint: read Body.java and look at how allRed is used in Game.java (gameOver).
	 * 
	 */
	public void allRed(Body current) {
		// To be completed
	if(current==null) {return;} //stop when there is no object to check
	if(current.isRed()==false) {current.setRed();} //turn object color to red
	allRed(current.getNext()); //move to the next object
	}
	
	/**
	 * DIFFICULT QUESTION (leave to the end)
	 * 
	 * getFreePositions(int,int) (10 marks)
	 * 
	 * @param w - the width of the screen
	 * @param h - the height of the screen
	 * 
	 * @return a two dimensional array which contains the co-ordinates in which the 
	 * 		   Snake is NOT located.
	 * 
	 * The first dimension must correspond to an individual set of co-ordinates.
	 * 
	 * The second dimension must correspond to the x and y co-ordinates.
	 * 
	 * Example:
	 * 
	 * int[][] coords = getFreePositions(w,h);
	 * 
	 * coords[1][0] must correspond to the x co-ordinate of the first point
	 * coords[1][1] must correspond to the y co-ordinate of the first point
	 * 
	 * coords[3][1] must correspond to the y co-ordinate of the third point
	 * 
	 */
	public int[][] getFreePositions(int w, int h) {
		// To be completed
		Body current=head;
		
		int[]arrX=new int [(((w-20)-20)/20)+1]; //array store all possible x-coordinates
		arrX[0]=20;
		for(int i=1;i<arrX.length;i++) {arrX[i]=(i+1)*20;} //fill the array
		int []arrY=new int [(((h-20)-20)/20)+1]; //array store all possible y-coordinates
		arrY[0]=20;
		for(int i=1;i<arrY.length;i++) {arrY[i]=(i+1)*20;} //fill the array
		int count=0;
		ArrayList<Integer>idxRemove=new ArrayList<Integer>();
		for(int i=0;i<arrX.length;i++) { boolean check=false; for(int j=0;j<arrY.length;j++) {
			while(current!=null) {if(current.getX()==arrX[i]&&current.getY()==arrY[j]) {j++; check=true; current=current.getNext();}}}
			if(check) {idxRemove.add(count);}
			count++;} //count the number of free positions
		int [][]coords= new int[count][2];
		current=head;
		// convert to 2D array
		for(int i=0;i<arrX.length;i++) {for(int j=0;j<arrY.length;j++){
		if(current.getX()==arrX[i]&&current.getY()==arrY[j]) {j++; current=current.getNext();} 
		coords[i][0]=arrX[i]; coords[j][1]=arrY[j];}}	
		return coords;
	}

}
