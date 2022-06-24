package Game;

public class Body {
	
	// You do not need to edit this file
	
	private int x;
	private int y;
	private Body next;
	boolean red;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Body getNext() {
		return next;
	}
	
	public boolean isRed() {
		return red;
	}
	
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setNext(Body next) {
		this.next=next;
	}
	
	public void setRed() {
		this.red=true;
	}
	
	public Body(int x, int y) {
		setPosition(x,y);
		red = false;
	}
}
