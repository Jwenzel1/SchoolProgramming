package proj4;

/**
 * This class models the Army Ant used in the Game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class ArmyAnt extends Ant{
	
	private static int armyAntsCreated = 0;
	
	/**
     * Constructor - creates a new Army Ant instance and increments the number of army ants made by 1
     * Preconditions: None
     * Postconditions: A Army Ant instance is created
     */
	public ArmyAnt(){
		super("Army Ant", AntCost.ARMY_ANT);
		super.setLife(30);
		armyAntsCreated++;
	}
	
	/**
     * Attacks the first zombie in the zombie vector.
     * Damage is increased based on how many Army ants have been created throughout the game
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g){
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10+(armyAntsCreated*5));
	}

}
