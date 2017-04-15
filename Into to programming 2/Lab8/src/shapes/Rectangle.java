package shapes;
import processing.core.*;

public class Rectangle extends Shape{
	int width;
	int height;
	
	public Rectangle(int x, int y, int width, int height, PApplet canvas){
		super(x,y,canvas);
		this.width = width;
		this.height = height;
	}
	public void draw(){
		super.getCanvas().rect(super.getX(),super.getY(),width, height);
	}
}
