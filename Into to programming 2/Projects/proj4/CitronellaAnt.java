package proj4;

/**
 * This class models the Citronella Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class CitronellaAnt extends Ant{
	
	/**
     * Constructor - creates a new Citronella Ant instance
     * Preconditions: None
     * Postconditions: A Citronella Ant instance is created
     */
	public CitronellaAnt(){
		super("Citronella Ant", AntCost.CITRONELLA_ANT);
		super.setLife(20);
	}

	/**
     * Attacks the first zombie in the zombie vector
     * If a Citronella Ant dies it explodes dealing 2 damage to every unit
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
