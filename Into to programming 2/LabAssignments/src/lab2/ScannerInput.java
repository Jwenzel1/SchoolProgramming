/**
 * 
 */
package lab2;
import java.util.Scanner;

/**
 * @author jwenzel1
 *
 */
public class ScannerInput {
	static Scanner input = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.print("Enter Name (First Last): ");
		String firstName = input.next();
		String lastName = input.next();
		
		System.out.print("Enter age: ");
		int age = input.nextInt();
		input.nextLine();
		
		System.out.print("Enter campus ID: ");
		String id = input.next();
		
		System.out.print("Enter GPA: ");
		double gpa = input.nextFloat();
		
		System.out.println("\nYour input:");
		System.out.printf("%s, %s\n", lastName, firstName);
		System.out.printf("%s is %d\n", firstName, age);
		System.out.printf("%s's GPA is %.2f\n", firstName, gpa);
		System.out.printf("%s's CampusID is %s\n", firstName, id);

	}

}
