package proj4;

/**
 * This class models the Carpenter Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class CarpenterAnt extends Ant{
	
	/**
     * Constructor - creates a new Carpenter Ant instance
     * Preconditions: None
     * Postconditions: A Carpenter Ant instance is created
     */
	public CarpenterAnt(){
		super("Carpenter Ant", AntCost.CARPENTER_ANT);
		super.setLife(10);
	}

	/**
     * Attacks the first zombie in the zombie vector
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g) {
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
	}
}
