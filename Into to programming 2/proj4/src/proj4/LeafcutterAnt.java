package proj4;

/**
 * This class models the Leafcutter Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class LeafcutterAnt extends Ant{
	
	/**
     * Constructor - creates a new Leafcutter Ant instance
     * Preconditions: None
     * Postconditions: A Leafcutter Ant instance is created
     */
	public LeafcutterAnt(){
		super("LeafCutter Ant", AntCost.LEAFCUTTER_ANT);
		super.setLife(10);
	}

	/**
     * Attacks the first zombie in the zombie vector.
     * The leafcutter ant as first strike. If it kills a zombie then it takes no damage from that zombie
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
