package lab6;
 
/**
 * The Person object is an object that maintains a Person's first
 * and last name.
 *
 * @author Ryan
 *
 */
public class Person {
    private String lastName;
    private String firstName;
     
    public Person(String lastName, String firstName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
     
    /**
     * CompareTo will compare two Person objects based on their
     * last name and then first name in ascending order.
     * @return 0 if first and last name are the same
     * -1 if this Person proceeds the other Person
     * 1 if this Person succeeds the other Person
     */
    public int compare(Person other){
        // get the result of comparing the last name
        int result = this.lastName.compareTo(other.lastName);
        if (result > 0){
        	result = 1;
        }
        else if(result < 0){
        	result = -1;
        }
        switch(result){
            case -1:
            	return result;
            case 1:
                return result;
            default:
                return this.firstName.compareTo(other.firstName);
        }
    }
     
    public String toString(){
        return String.format("%s %s", firstName, lastName);
    }
     
}