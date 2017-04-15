package lab7;

public class ResidenceDriver 
{
	public static void main(String[] args) 
        {
        System.out.println("House:");
			//make a House object
			//use toString to print the values related to this house
        House house = new House();
        System.out.println(house);

		System.out.println("\nApartment:");
			//make a new Apartment object on the 2nd floor that has a washer
			//use toString to print the values related to this apartment
		Apartment apartment = new Apartment(2, true);
		System.out.println(apartment);

		System.out.println("\nTent:");
		  	//make a new Tent object
		  	//use toString to print the values related to this tent
		Tent tent = new Tent();
		System.out.println(tent);
	}

}
