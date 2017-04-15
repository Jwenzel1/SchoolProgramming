package lab3;

import java.util.Scanner;

public class FractionDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create a Scanner object on System.in
        Scanner scanner = new Scanner(System.in);
        int numerator = 1;
        int denominator = 1;
           
        //Use the Scanner object to get the numerator and denominator of the first fraction
        System.out.println("Enter the numerator of the first fraction ");
        numerator = scanner.nextInt();
        System.out.println("Enter the denominator of the first fraction ");
        denominator = scanner.nextInt();         
         
        //Create the first fraction object with input provided and print by invoking toString()
        Fraction firstFraction = new Fraction(numerator, denominator);
        System.out.println("The first fraction is " + firstFraction.toString());
         
        //Use the Scanner object to get the numerator and denominator of the second fraction
        System.out.println("Enter the numerator of the second fraction ");
        numerator = scanner.nextInt();
        System.out.println("Enter the denominator of the second fraction ");
        denominator = scanner.nextInt();
           
        //Create the second fraction object with input provided and print by invoking toString()
        Fraction secondFraction = new Fraction(numerator, denominator);
        System.out.println("The Second Fraction is " + secondFraction.toString());
 
           

          

        //Print the decimal value of the two fractions by invoking decimalValue()
        System.out.printf("The decimal value of the first fraction is %.2f\n",
                firstFraction.decimalValue());
        System.out.printf("The decimal value of the second fraction is %.2f\n",
                secondFraction.decimalValue());
  
          

        //Print the product of the fractions by invoking multiply()
        System.out.println("The product of the two fractions is " +
                firstFraction.multiply(secondFraction).toString());
         
        //Print whether or not the fractions are equivalent by invoking equals()
        System.out.println("Are these fractions equivalent? " +
                firstFraction.equals(secondFraction));


	}

}
