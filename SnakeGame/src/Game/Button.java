package Game;

public class Button {
	
	// You do not need to edit this file
	
	float x,y,w,h;
	String text;
	
	public float getX() {
		return x;
	}
	public float getY() {
		return  y;
	}
	public float getWidth() {
		return w;
	}
	public float getHeight() {
		return h;
	}
	public String getText() {
		return text;
	}
	
	public void setPosition(float x,float y) {
		this.x=x;
		this.y=y;
	}
	public void setSize(float w, float h) {
		this.w = w;
		this.h = h;
	}
	public void setText(String text) {
		this.text=text;
	}
	public Button(float x, float y, float w, float h,String text) {
		setPosition(x,y);
		setSize(w,h);
		setText(text);
	}
	public boolean onButton(int x, int y) {
		if(x > this.x-w/2 && x < (this.x + w/2) && y > this.y - h/2 && y < (this.y + h/2)) {
				return true;
		}
		return false;
	}

}
