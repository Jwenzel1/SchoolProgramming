package lab9;

public class Name implements Comparable{

	private String first;
	private String last;
	
	public Name(String first, String last){
		this.first = first;
		this.last = last;
	}
	
	public String toString(){
		return first + " " + last;
	}
		
	@Override
	public int compareTo(Object o) {
		Name otherName = (Name) o;
		if(otherName.last.compareTo(last) != 0){
			return otherName.last.compareTo(last);
		}
		return otherName.first.compareTo(first);
	}
}