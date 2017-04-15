package lab10;

import java.util.Scanner;
import java.io.*;

public class StudentDriver {
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) 
	{		
		Scanner fileScanner = null;
		Student students[];
		int numStudents = 0;

		//Step a: Perform number of arguments check 
		if (args.length != 1)
		{
			/* Throw an appropriate exception here. */
		}
		
		
		try
		{
            /*Step b: Assign the file's input stream to the 
            Scanner object. 
            */
			FileInputStream inStream = new FileInputStream(args[0]);
			fileScanner = new Scanner(inStream);
		 }
		
		catch (FileNotFoundException e)
		{
            System.out.println("File not found " 
                                    + e.getMessage());
            System.exit(-1);
        }		

        /*Step c: Read the number of students from the file
         and store in numStudents 
        */
		numStudents = fileScanner.nextInt();

        System.out.println("The number of students is: " + numStudents);
    
        /*Step d: Allocate memory for students array based on 
        numStudents
        */	
        students = new Student[numStudents];

        int i = 0;
    
        while (fileScanner.hasNextLine())
        {
            /*Step e: Read from the file line by line using the 
            Scanner object and store the values read in the 
            students array.
            */
        	String name = fileScanner.next();
        	int id = fileScanner.nextInt();
            // code to read the tuple [name] [id] from file 
        	Student testStudent = new Student(name, id);
//        	try{
//        		for(i = 0; i < students.length; i++){
//        			if(students[i].getId() != null){
//        			if(testStudent.getId() == students[i].getId()){
//        				throw new DuplicateIDException(testStudent.getName() + "'s ID number, "+ testStudent.getId() + ", is not unique and cannot be assigned.");
//        			}
//        			}
//        				
//        		}
        		students[i] = new Student(name, id);
                i++;
//        	}
//        	catch(DuplicateIDException e){
//        		e.getMessage();
//        	}
        }
	
        
    
        /* Step f: close the file reader as you are done 
        reading the file
        */
        fileScanner.close();
    
        /*Step g: Print out the information read from the 
        file which is stored in students array in the form
        Student name = [name] and id = [id]
        */
        for(i = 0; i<students.length; i++){
        	System.out.printf("Student name = %s and id = %d\n", students[i].getName(), students[i].getId());
        }
	}	
}
