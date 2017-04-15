package lab10;

public class Student{
	
	String name;
	Integer id;
	
	public Student(String name, Integer id) throws IllegalArgumentException{
		if(name == null || id == null){
			throw new IllegalArgumentException("dude you suck");
		}
		this.name = name;
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
}