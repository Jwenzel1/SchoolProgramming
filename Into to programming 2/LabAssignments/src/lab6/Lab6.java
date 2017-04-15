package lab6;
 
public class Lab6 {
     
    // this is insertion sort
    // precondition - all items in the array must be non-null Comparable objects
    // postcondition - all items will be sorted in ascending order
    public static void insertionSort( Person [ ] personArray )
    {
        for( int i = 0; i < personArray.length; i++ )
        {
            Person tmp = personArray[ i ];
            int j = i;
 
            while(j > 0 && tmp.compare( personArray[ j - 1 ] ) < 0){
                personArray[ j ] = personArray[ j - 1 ];
                j--;
            }
            personArray[ j ] = tmp;
        }
    }
     
     
    public static void main(String[] args){
        System.out.println("Hello and Good Luck with your Debugging Exercise");
         
        // initialize an array of comparable objects
        Person[] array = new Person[5];
        array[0] = new Person("Edison", "Denise");
        array[1] = new Person("Clarkson", "Happy");
        array[2] = new Person("Edison","Thomas");
        array[3] = new Person("Guy", "Some");
        array[4] = new Person("Allen", "Woody");
         
        Lab6.insertionSort(array);
        for(Person p: array){
            System.out.println(p);
        }
    }
}