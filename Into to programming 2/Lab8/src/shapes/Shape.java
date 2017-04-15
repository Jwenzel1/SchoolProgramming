package shapes;
import processing.core.*;

public abstract class Shape {
	private int x;
	private int y;
	private PApplet canvas;
	
	public Shape(int x, int y, PApplet canvas){
		this.x = x;
		this.y = y;
		this.canvas = canvas;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the canvas
	 */
	public PApplet getCanvas() {
		return canvas;
	}

	public abstract void draw();
}
