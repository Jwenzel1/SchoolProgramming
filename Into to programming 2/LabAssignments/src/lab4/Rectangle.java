package lab4;

public class Rectangle {
	
	private Point upperLeft, lowerLeft, lowerRight, upperRight;
	
	public Rectangle (Point upperLeft, Point lowerLeft, Point lowerRight, Point upperRight){
		this.upperLeft = upperLeft;
		this.lowerLeft = lowerLeft;
		this.lowerRight = lowerRight;
		this.upperRight = upperRight;
		checkRectangle();
	}
	
	public Rectangle(Rectangle original){
		this.upperLeft = new Point(original.upperLeft);
		this.lowerLeft = new Point(original.lowerLeft);
		this.lowerRight = new Point(original.lowerRight);
		this.upperRight = new Point(original.upperRight);
	}
	
	public double getLength(){
		double firstDistance = upperLeft.distance(lowerLeft);
		double secondDistance = upperLeft.distance(upperRight);
		if(firstDistance > secondDistance){
			return firstDistance;
		}
		return secondDistance;
	}
	
	public double getWidth(){
		double firstDistance = upperLeft.distance(lowerLeft);
		double secondDistance = upperLeft.distance(upperRight);
		if(firstDistance < secondDistance){
			return firstDistance;
		}
		return secondDistance;
	}
	
	public double getArea(){
		double area = this.getLength()*this.getWidth();
		return area;
	}
	
	public double getPerimeter(){
		double perimeter = (this.getLength()*2)+(this.getWidth()*2);
		return perimeter;
	}
	
	private void checkRectangle(){
		//check the long sides
		double firstLongSide = upperLeft.distance(upperRight);
		double secondLongSide = lowerLeft.distance(lowerRight);
		if (firstLongSide != secondLongSide){
			System.out.print("This is not a valid rectangle.");
			System.exit(0);	
		}
		double firstShortSide = upperLeft.distance(lowerLeft);
		double secondShortSide = upperRight.distance(lowerRight);
		if (firstShortSide != secondShortSide){
			System.out.print("This is not a valid rectangle.");
			System.exit(0);
		}
	}
}
