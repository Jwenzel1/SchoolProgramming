package lab7;

public class Residence {
	private int numOfRooms;
	private int numOfExternalWalls;
	private boolean hasWasher;
	
	public Residence(int numOfRooms, int numOfExternalWalls, boolean hasWasher){
		this.numOfRooms = numOfRooms;
		this.numOfExternalWalls = numOfExternalWalls;
		this.hasWasher = hasWasher;
	}

	protected boolean hasWasher(){
		return hasWasher;
	}
	
	public double propertyValue(){
		return numOfRooms*10000;
	}
	public int numWindows(){
		return numOfExternalWalls*2;
	}
	public String toString(){
		String outString = "";
		outString += "Number of Rooms: " + numOfRooms;
		outString += "\nNumber of Walls: " + numOfExternalWalls;
		outString += "\nWasher: " + hasWasher();
		outString += "\nNumber of Windows: " + numWindows(); 
		outString += "\nProperty Value: " + propertyValue();
		return outString;
	}
}
