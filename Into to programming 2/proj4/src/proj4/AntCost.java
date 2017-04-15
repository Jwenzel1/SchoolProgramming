/**The AntCost enum encapsulates the costs of each ant*/

package proj4;

public enum AntCost {
	
	/**
	 * The Cost of the Army Ant
	 */
	ARMY_ANT(35),
	
	/**
	 * The Cost of the Bullet Ant
	 */
	BULLET_ANT(10),
	
	/**
	 * The Cost of the Carpenter Ant
	 */
	CARPENTER_ANT(10),
	
	/**
	 * The Cost of the Citronella Ant
	 */
	CITRONELLA_ANT(25),
	
	/**
	 * The Cost of the Fire Ant
	 */
	FIRE_ANT(15),
	
	/**
	 * The Cost of the Leafcutter Ant
	 */
	LEAFCUTTER_ANT(20),
	
	/**
	 * The Cost of the Pharoh Ant
	 */
	PHAROH_ANT(15),
	
	/**
	 * The Cost of the Sugar Ant
	 */
	SUGAR_ANT(20),
	
	/**
	 * The Cost of the Theif Ant
	 */
	THEIF_ANT(15),
	
	/**
	 * The Cost of the Weaver Ant
	 */
	WEAVER_ANT(20);
	
	/**
	 * The Cost of the ant
	 */
	private int cost;
	
	/**
	 * Internal constructor used to create <code>AntCost</code> enums
	 * 
	 * @param cost
	 *            the number value of the cost of the ant
	 */
	private AntCost(int cost){
		this.cost = cost;
	}
	
	/**
	 * Returns the value of the cost.
	 */
	public int getCost(){
		return cost;
	}

}
