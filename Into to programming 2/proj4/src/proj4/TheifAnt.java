package proj4;

/**
 * This class models the Theif Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class TheifAnt extends Ant{
	
	/**
     * Constructor - creates a new Theif Ant instance
     * Preconditions: None
     * Postconditions: A Theif Ant instance is created
     */
	public TheifAnt(){
		super("Theif Ant", AntCost.THEIF_ANT);
		super.setLife(25);
	}

	/**
	 * This class is empty since a theif ant has no direct attack.
	 * It is only here to fulfill the abstract class requirement in Ant.java
	 */
	public void attack(Game g) {}

	/**
     * A theif ant does not attack it only takes damage and then 
     * reflects half the damage back at the attacking zombie
     * Preconditions: None
     * Postconditions: None
     * @param The amount of damage the ant is taking
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void takeDamage(int damage, Zombie z){
		super.takeDamage(damage);
		z.takeDamage(damage/2);
	}
}
