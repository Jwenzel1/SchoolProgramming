package lab4;

import java.util.Scanner;

public class RectangleDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		  Scanner scanner = new Scanner(System.in);
		  int x = 1;
		  int y = 1;
		 
		  System.out.print("Enter the coordinates of the upperLeft point (x y):");
		  x = scanner.nextInt();
		  y = scanner.nextInt();
		 
		  Point upperLeft = new Point(x, y);
		 
		   
		  System.out.print("Enter the coordinates of the lowerLeft point (x y):");
		  x = scanner.nextInt();
		  y = scanner.nextInt();
		 
		  Point lowerLeft = new Point(x, y);
		   
		   
		  System.out.print("Enter the coordinates of the lowerRight point (x y):");
		  x = scanner.nextInt();
		  y = scanner.nextInt();
		 
		  Point lowerRight = new Point(x, y);
		   
		   
		  System.out.print("Enter the coordinates of the upperRight point (x y):");
		  x = scanner.nextInt();
		  y = scanner.nextInt();
		 
		  Point upperRight = new Point(x, y);
		   
		  Rectangle rectangle = new Rectangle(upperLeft, lowerLeft, lowerRight, upperRight);
		 
		  System.out.printf("Length of rectangle : %.2f\n", rectangle.getLength());
		  System.out.printf("Width of rectangle : %.2f\n", rectangle.getWidth());
		  System.out.printf("Area of rectangle : %.2f\n", rectangle.getArea());
		  System.out.printf("Perimeter of rectangle : %.2f\n", rectangle.getPerimeter());
		
		 
		
		  //test the copy constructor
		  Rectangle rectangleNew = new Rectangle(rectangle);
		   
		  System.out.printf("Length of rectangleNew : %.2f\n", rectangleNew.getLength());
		  System.out.printf("Width of rectangleNew : %.2f\n", rectangleNew.getWidth());
		  System.out.printf("Area of rectangleNew : %.2f\n", rectangleNew.getArea());
		  System.out.printf("Perimeter of rectangleNew : %.2f\n", rectangleNew.getPerimeter());
		  
	}

}
