package proj5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;


public class Project5 {

	private static PrintWriter out;
	private static BufferedReader reader;
	private static ArrayList<String> commands = new ArrayList<String>();
	
	/**
	 * Driver for Project5
	 * Class Invariants:
	 *   - None
	 * @version 12/10/13
	 * @author Joseph Wenzel <jwenzel1@umbc.edu>
	 * @project CMSC 202 - Fall 2013 - Project 5
	 * @section 06
	 */
	public static void main(String[] args) {
		try{
			if(args.length != 6){
				throw new CommandLineArgsException();
			}
		}
		catch(CommandLineArgsException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Train crazyTrain = new Train(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		
		try {
			reader = new BufferedReader(new FileReader(args[4]));
			out = new PrintWriter(new File(args[5]));
			String currentLine = null;
			while((currentLine = reader.readLine()) != null){
				commands.add(currentLine);
			}	
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File given in command line args not found. Now Exiting.");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("An IOException occurred. Now Exiting.");
			System.exit(1);
		}
	
		for(int i = 0; i < commands.size(); i++){
			if(commands.get(i).equals("PRINT")){
				out.println(crazyTrain.print());
			}
			else if(commands.get(i).equals("ARRIVE")){
				try{
					out.println(crazyTrain.arrive());
				}
				catch(TrainMovingException e){
					out.println(e.getMessage());
				}
			}
			else if(commands.get(i).equals("DEPART")){
				try{
					out.println(crazyTrain.depart(commands.get(i+1)));
				}
				catch(TrainMovingException e){
					out.println(e.getMessage());
				}
			}
			else if(commands.get(i).equals("ADDCAR")){
				try {
					out.println(crazyTrain.addCar(commands.get(i+1), Integer.parseInt(commands.get(i+2))));
				}  
				catch (TrainMovingException e) {
					out.println(e.getMessage());
				}
			}
			else if(commands.get(i).equals("SPEEDUP")){
				try {
					out.println(crazyTrain.speedUp(Integer.parseInt(commands.get(i+1))));
				} 
				catch (TrainSpeedException e) {
					out.println(e.getMessage());
				}
			}
			else if(commands.get(i).equals("SLOWDOWN")){
				try {
					out.println(crazyTrain.slowDown(Integer.parseInt(commands.get(i+1))));
				} 
				catch (TrainSpeedException e) {
					out.println(e.getMessage());
				}
			}
			//NEEDS ATTENTION
			//****************************************************************************************
			else if(commands.get(i).equals("REMOVECAR")){
				out.println(crazyTrain.removeCar(Integer.parseInt(commands.get(i+1))));
			}

			else if(commands.get(i).equals("LOAD")){
				try{
					if(commands.get(i+1).equals("PERSON")){
						out.println(crazyTrain.load(Integer.parseInt(commands.get(i+2)), new Person(commands.get(i+3), commands.get(i+4), Integer.parseInt(commands.get(i+5)))));
						
					}
					if(commands.get(i+1).equals("CARGO")){
						out.println(crazyTrain.load(Integer.parseInt(commands.get(i+2)), new Cargo(commands.get(i+3), Integer.parseInt(commands.get(i+4)), Integer.parseInt(commands.get(i+5)), Integer.parseInt(commands.get(i+6)), Integer.parseInt(commands.get(i+7)))));
					}
				}
				catch(TrainMovingException e){
					out.println(e.getMessage());
				}
				catch(BoxcarException e){
					if(e.getMessage() == null){
						out.println("LOAD\n	ITEM ALREADY EXISTS IN BOXCAR");
					}
					else{
						out.println(e.getMessage());
					}
				}
			}
			else if(commands.get(i).equals("UNLOAD")){
				try{
					out.println(crazyTrain.unload(Integer.parseInt(commands.get(i+1)), commands.get(i+2)));
				}
				catch(BoxcarException e){
					if(e.getMessage() == null){
						out.println("UNLOAD\n	ERROR: ITEM DOESNT EXIST");
					}
				}
			}
		
			if(commands.get(i).equals("QUIT")){
				out.println("QUIT\nQuitting...");
				out.close();
				System.exit(0);
			}
		}
		System.out.println("Command file did not have a QUIT at the end. Logfile was generated without a quit at the end.");
		out.close();
	}
}


