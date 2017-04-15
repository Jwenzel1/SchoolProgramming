package proj4;

/**
 * This class models the Weaver Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class WeaverAnt extends Ant{
	
	/**
     * Constructor - creates a new Weaver Ant instance
     * Preconditions: None
     * Postconditions: A Weaver Ant instance is created
     */
	public WeaverAnt(){
		super("Weaver Ant", AntCost.WEAVER_ANT);
		super.setLife(10);
	}

	/**
     * Attacks the second zombie in the zombie vector
     * if there is no second zombie then this ant does no damage and is useless
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g) {
		try{
			Zombie z = g.getHorde().elementAt(1);
			z.takeDamage(15);
		}
		catch(Exception e){}
	}

}
