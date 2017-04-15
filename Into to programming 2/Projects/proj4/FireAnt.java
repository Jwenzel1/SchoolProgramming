package proj4;

/**
 * This class models the Fire Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class FireAnt extends Ant{
	
	/**
     * Constructor - creates a new Fire Ant instance
     * Preconditions: None
     * Postconditions: A Fire Ant instance is created
     */
	public FireAnt(){
		super("Fire Ant", AntCost.FIRE_ANT);
		super.setLife(20);
	}

	/**
     * Attacks the first zombie in the zombie vector
     * If the zombie is Flammable then deal 20 damage instead of 10
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g) {
		Zombie z = g.getHorde().elementAt(0);
		if(z instanceof Flammable){
			z.takeDamage(20);
		}
		else{
			z.takeDamage(10);
		}
	}
}
