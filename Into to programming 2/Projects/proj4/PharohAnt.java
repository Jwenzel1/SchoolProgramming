package proj4;

/**
 * This class models the Pharoh Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class PharohAnt extends Ant{
	
	/**
     * Constructor - creates a new Pharoh Ant instance
     * Preconditions: None
     * Postconditions: A Pharoh Ant instance is created
     */
	public PharohAnt(){
		super("Phaorh Ant", AntCost.PHAROH_ANT);
		super.setLife(10);
	}

	/**
     * Attacks the first zombie in the zombie vector
     * If the zombie is Gigantic then deal 30 damage instead of 10
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g) {
		Zombie z = g.getHorde().elementAt(0);
		if(z instanceof Gigantic){
			z.takeDamage(30);
		}
		else{
			z.takeDamage(10);
		}
	}
}


