package proj4;

/**
 * This abstract class is the base class for all Ants used in the game.java class
 * Class Invariants:
 *   - None
 * @version 11/23/13
 * @author Joseph Wenzel <jwenzel1@umbc.edu>
 * @project CMSC 202 - Fall 2013 - Project 4
 * @section 06
 */
public abstract class Ant {
	
	private AntCost cost;
	private int fullHealth;
	private int life;
	private String desc;
	
	/**
     * Constructor -  Used as a super constructor for all the other ants
     * Preconditions: None
     * Postconditions: An Ant instance is created
     */
	protected Ant(String desc, AntCost cost){
		this.desc = desc;
		this.cost = cost;
	}
	
	/**
     * Creates an ant based on the string description passed in 
     * Preconditions: None
     * Postconditions: None
     * @param The description of the ant given in a string
     * @return The created Ant
     */
	public static Ant makeAnt(String desc) {
	    if(desc.equals("Army Ant")){ 
	    	return new ArmyAnt();
	    }
	    else if(desc.equals("Citronella Ant")){ 
	    	return new CitronellaAnt();
	    }
	    else if(desc.equals("LeafCutter Ant")){ 
	    	return new LeafcutterAnt();
	    }
	    else if(desc.equals("Bullet Ant")){
	    	return new BulletAnt();
	    }
	    else if(desc.equals("Carpenter Ant")){
	    	return new CarpenterAnt();
	    }
	    else if(desc.equals("Fire Ant")){ 
	    	return new FireAnt();
	    }
	    else if(desc.equals("Pharoh Ant")){ 
	    	return new PharohAnt();
	    }
	    else if(desc.equals("Sugar Ant")){
	    	return new SugarAnt();
	    }
	    else if(desc.equals("Theif Ant")){
	    	return new TheifAnt();
	    }
	    else if(desc.equals("Weaver Ant")){
	    	return new WeaverAnt();
		}
		return null;
	 }
	
	 /**
     * Gets food cost of the ant
     * Preconditions: None
     * Postconditions: None
     * @return The food cost of the ant
     */
	public int getCost(){
		return cost.getCost();
	}
	
	 /**
     * Gets the life of the ant
     * Preconditions: None
     * Postconditions: None
     * @return The life of the ant
     */
	public int getLife() {
		return life;
	}

	 /**
     * Gets the name of the ant
     * Preconditions: None
     * Postconditions: None
     * @return String name of the ant
     */
	public String getDesc() {
		return desc;
	}
	
	 /**
     * Subtracts the damage the ant is taking from its health
     * Preconditions: None
     * Postconditions: None
     * @param Damage the ant is taking
     * @param Zombie giving the damage
     * @return None
     */
	public void takeDamage(int damage, Zombie z){
		life -= damage;
	}
	
	/**
     * Subtracts the damage the ant is taking from its health
     * Preconditions: None
     * Postconditions: None
     * @param Damage the ant is taking
     * @return None
     */
	public void takeDamage(int damage){
		life -= damage;
	}

	public abstract void attack(Game g);
	
	/**
     * Sets the health of the ant
     * Preconditions: None
     * Postconditions: None
     * @param Amount to set life at
     * @return None
     */
	public void setLife(int amount){
		life = amount;
		fullHealth = amount;
	}
	
	/**
     * Heals the ant at the end of each round
     * Preconditions: None
     * Postconditions: None
     * @return None
     */
	public void healAnt(){
		life = fullHealth;
	}
	
}
