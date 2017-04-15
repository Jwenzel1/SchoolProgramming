package lab8;

import processing.core.*;

//The following line will not compile until you make the shapes package
import shapes.*;

public class ShapeDriver extends PApplet {
	
	private Shape[] shapeArray;
	private static final long serialVersionUID = 1L;

	public void setup()
	{
		size (200, 200);
		smooth();

		// your code here
		shapeArray = new Shape[4];
		shapeArray[0] = new Rectangle(25,25,50,50,this);
		shapeArray[1] = new Rectangle(125,125,50,50,this);
		shapeArray[2] = new Circle(150, 50, 50, this);
		shapeArray[3] = new Circle(50, 150, 50, this);
		
		
	}

	public void draw()
	{
		background (127);

		// your code here
		for(int i = 0; i < shapeArray.length; i++){
			fill(i * 50);
			shapeArray[i].draw();
		}
	}

	public static void main(String[] args) {
		PApplet.main(args);
		//Instantiate an object of the driver class.
		//Call the setup method on the above object.
		//Call the draw method on the above object.
	}

}
