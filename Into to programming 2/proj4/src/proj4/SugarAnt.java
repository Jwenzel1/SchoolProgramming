package proj4;

/**
 * This class models the Sugar Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class SugarAnt extends Ant{
	
	/**
     * Constructor - creates a new Sugar Ant instance
     * Preconditions: None
     * Postconditions: A Sugar Ant instance is created
     */
	public SugarAnt(){
		super("Sugar Ant", AntCost.SUGAR_ANT);
		super.setLife(20);
	}

	/**
     * Attacks the first zombie in the zombie vector
     * Adds 5 additional to the players total if the zombie is killed by this ant
     * Preconditions: None
     * Postconditions: None
     * @param The game where the zombie vector is stored
     * @return None
     */
	public void attack(Game g) {
		Zombie z = g.getHorde().elementAt(0);
		z.takeDamage(10);
		if(z.getLife() <= 0){
			g.addFood(5);
		}
	}
	

}
