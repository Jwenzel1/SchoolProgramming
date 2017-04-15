package lab7;

public class Apartment extends Residence {
	
	private int story;
	
	public Apartment(int story, boolean washer){
		super(4,4,washer);
		this.story = story;
	}
	
	public double propertyValue(){
		if(super.hasWasher() == true){
			return super.propertyValue()+100;
		}
		return super.propertyValue();
	}
	
	public int getStory(){
		return story;
	}
	public String toString(){
		return super.toString() + "\nStory: " + story;
	}
}
