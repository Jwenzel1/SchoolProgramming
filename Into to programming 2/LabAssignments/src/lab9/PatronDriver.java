package lab9;

import java.util.Arrays;


public class PatronDriver{
	public static void main (String[] args)
	{		
		Patron[] patrons = {
			new Patron ("Don", "Miner", 671),
			new Patron ("Max", "Morawski", 202),
			new Patron ("Justin", "Raabe", 341),
			new Patron("Don", "Miner", 201),
			new Patron("Eric", "Sillers", 13),
			new Patron("Andrew", "Sillers", 11)
		};
		
		Arrays.sort(patrons);

		for(int i = 0; i < patrons.length; i++) {
		    System.out.println(patrons[i]);
		}
	}
}
