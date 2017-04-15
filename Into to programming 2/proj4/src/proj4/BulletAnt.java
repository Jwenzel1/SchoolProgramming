package proj4;

/**
 * This class models the Bullet Ant used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public class BulletAnt extends Ant{
	
	/**
     * Constructor - creates a new Bullet Ant instance
     * Preconditions: None
     * Postconditions: A Bullet Ant instance is created
     */
	public BulletAnt(){
		super("Bullet Ant", AntCost.BULLET_ANT);
		super.setLife(1);
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
		z.takeDamage(25);
	}
}