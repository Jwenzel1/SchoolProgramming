package lab11;

public class Book implements Media{
	
	String id;
	String author;
	String title;
	int yearPublished;

	public Book(String id, String author, String title, int yearPublished){
		this.id = id;
		this.author = author;
		this.title = title;
		this.yearPublished = yearPublished;
	}

	public <T> Comparable<T> getId() {
		return (Comparable<T>) id;
	}

	public String getCreator() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return yearPublished;
	}
}
