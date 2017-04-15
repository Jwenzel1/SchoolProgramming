package lab9;

public class Patron implements Comparable{
	
	private Name name;
	private int roomNumber;
	
	public Patron(String first, String last, int roomNumber){
		this.name = new Name(first, last);
		this.roomNumber = roomNumber;
	}
	
	public String toString(){
		return name.toString() + ": " + roomNumber;
	}
	@Override
	public int compareTo(Object o) {
		Patron person = (Patron) o;
		if(person.name.compareTo(name) != 0){
			return person.name.compareTo(name);
		}
		Integer thisNumber = (Integer) roomNumber;
		Integer otherNumber = (Integer) person.roomNumber;
		return thisNumber.compareTo(otherNumber);
	}

}
