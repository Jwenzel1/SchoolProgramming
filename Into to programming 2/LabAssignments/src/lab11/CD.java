package lab11;

public class CD implements Media{
	
	Integer id;
	String artist;
	String title;
	int year;
	
	public CD(Integer id, String artist, String title, int year){
		this.id = id;
		this.artist = artist;
		this.title = title;
		this.year = year;
	}

	public <T> Comparable<T> getId() {
		return (Comparable<T>) id;
	}

	public String getCreator() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

}
