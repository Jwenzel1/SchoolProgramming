package shapes;
import processing.core.*;

public class Circle extends Shape{
	int radius;
	
	public Circle(int x,int y,int radius,PApplet canvas){
		super(x,y,canvas);
		this.radius = radius;
	}
	public void draw(){
		super.getCanvas().ellipse(super.getX(), super.getY(), radius, radius);
	}
}
