package lab4;

public class Point {
	private int pointX;
	private int pointY;
	public Point(int x, int y){
		pointX = x;
		pointY = y;
	}
	public Point(Point original){
		pointX = original.pointX;
		pointY = original.pointY;
	}
	public int getX(){
		return pointX;
	}
	public int getY(){
		return pointY;
	}
	public void setX(int x){
		pointX = x;
	}
	public void setY(int y){
		pointY = y;
	}
	public double distance(Point otherPoint){
		double xDistance = Math.pow(pointX-otherPoint.pointX,2);
		double yDistance = Math.pow(pointY-otherPoint.pointY,2);
		double distance = Math.sqrt(xDistance+yDistance);
		return distance;
	}
}
