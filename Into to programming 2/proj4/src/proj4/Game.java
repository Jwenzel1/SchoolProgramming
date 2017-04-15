/**
 * This is the Game.java file posted in the Project4 project
 * description. It contains only the nextFight method. Students
 * must complete the remainder of the Game methods and add the
 * Game instance variables.
 */
package proj4;

import java.util.Vector;

// SMM, 11/13/12: I renamed Game_MethodOnly to Game before posting
public class Game implements GameInterface {

    /******************** instance variables ********************/
	private Vector<Ant> colony = new Vector<Ant>();
	private Vector<Zombie> horde = new Vector<Zombie>();
	private int food = 100;
	private int roundNumber = 1;
	private boolean gameOver = false;
	private static final int WINNING_ROUND = 6;

    /*************** methods declared in GameInterface ****************/
	
	 /**
     * Return the current round for the game.
     * @return 1 through 5, inclusive
     */
    public int getRoundNumber(){
    	return roundNumber;
    }

    /**
     * Return the amount of food the player's colony currently has.
     * @return food remaining
     */
    public int getFood(){
    	return food;
    }

    /**
     * Return a string that lists all of the ants in the player's colony.
     * The list is in order, and has newlines separating ants.
     * @return Multiline description of colony.
     */
    public String getColonyDesc(){
    	String colonyDesc = "";
    	for(int i = 0; i < colony.size(); i++){
    		colonyDesc += colony.get(i).getDesc() + " - remaining life: " + colony.get(i).getLife() + "\n";
    	}
    	return colonyDesc;
    }

    /**
     * Callback invoked when the player attempts to recruit an ant.
     * @param antType Type of ant to recruit
     * @return true if the player may recruit the ant, false if not.
     */
    public boolean recruitAnt(String antType){
    	//Check first to see if the player can recruit the army ant.
    	//I added this part to the method to specifically check for army ants because if i created one to check its cost it would 
    	//increment the number of army ants made giving army ants more power than they should have
    	if(antType.equals("Army Ant") && AntCost.ARMY_ANT.getCost() <= food){
    		colony.add(Ant.makeAnt(antType));
    		removeFood(colony.lastElement().getCost());
    		return true;
    	}
    	//if it isnt an army ant in line to be created
    	else if(antType.compareTo("Army Ant") != 0){
    		//create the ant and check to make sure its cost is less than or equal to the amount of available food
    		Ant antToAdd = Ant.makeAnt(antType);
    		if( antToAdd.getCost() <= food){
    			//if it is then add the ant and subtract the food
    			colony.add(antToAdd);
            	removeFood(colony.lastElement().getCost());
            	return true;
    		}
    	}
    	return false;
    }

    /**
     * Read and parse the Zombie String within a zombie file.
     * @param filename File containing Zombie String
     */
    public void readHordeFile(String filename){
    	String zombieString = "";
    	try{
    		java.io.FileReader file = new java.io.FileReader(filename);
        	java.io.BufferedReader buf = new java.io.BufferedReader(file);
	    	zombieString = buf.readLine();
	    	buf.close();
    	}
	    catch(Exception e){
	    	System.out.println("The file given is invalid. Please restart the program and try again.");
	    	System.exit(1);
	    }
    	generateHorde(zombieString);
    }

    /**
     * Return a string that lists all of the zombies in the current
     * invasion The list is in order, and has newlines separating
     * zombies.
     * @return Multiline description of horde.
     */
    public String getHordeDesc(){
    	String hordeDesc = "";
    	for(int i = 0; i < horde.size(); i++){
    		hordeDesc += horde.get(i).getDesc() + " - remaining life: " + horde.get(i).getLife() + "\n";
    	}
    	return hordeDesc;
    }


    /**
     * Determine if the invasion is over. If the invasion is over, all
     * remaining ants' health reset to full life.
     * @return true if there are no ants or no zombies remaining.
     */
    public boolean isInvasionOver(){
    	//check if the horde or the colony has no units left in it
    	if(horde.size() == 0 || colony.size() == 0){
    		//check if the colony size is greater than zero
    		if(colony.size() > 0){
    			for(int i = 0; i < colony.size(); i++){
    				//if it is heal all the ants
        			colony.elementAt(i).healAnt();
    			}
    		}
    		roundNumber++;
    		return true;
    	}
    	return false;
    }

    /**
     * Determine if the game is over or not.
     * @return true if game is over or not.
     */
    public boolean isGameOver(){
    	if(roundNumber == WINNING_ROUND){
    		return true;
    	}
    	else if(gameOver == true){
    		return true;
    	}
    	return false;
    }

    /**
     * Return a string that describe how the game ended.  If the
     * player lost, simply return "Game Over", otherwise return the
     * player's score.
     * @return Description of ending condition.
     */
    public String getEndingMessage(){
    	if(roundNumber == WINNING_ROUND){
    		return "You Win!!! Final Score: " + food;
    	}
    	return "Game Over!";
    }

    /**
     * Return an array of all types of ants that may be recruited.
     * This array will be used to construct the recruitment buttons
     * during Recruit phase.
     */
    public String[] getAntTypes(){
    	String[] typesOfAnts = {"Army Ant", "Bullet Ant", "Carpenter Ant", "Citronella Ant", "Fire Ant", "LeafCutter Ant", 
    			"Pharoh Ant", "Sugar Ant", "Theif Ant", "Weaver Ant"};
    	return typesOfAnts;
    }

    /**
     * Return the cost to recruit a particular ant.
     * @param antType Type of ant to recruit.
     * @return Food cost to recruit.
     */
    public int getAntCost(String antType){
    	if(antType.equals("Army Ant")){
    		return AntCost.ARMY_ANT.getCost();
    	}
    	else if(antType.equals("Citronella Ant")){
    		return AntCost.CITRONELLA_ANT.getCost();
    	}
    	else if(antType.equals("LeafCutter Ant")){
    		return AntCost.LEAFCUTTER_ANT.getCost();
    	}
    	else if(antType.equals("Bullet Ant")){
    		return AntCost.BULLET_ANT.getCost();
    	}
    	else if(antType.equals("Carpenter Ant")){
    		return AntCost.CARPENTER_ANT.getCost();
    	}
    	else if(antType.equals("Fire Ant")){
    		return AntCost.FIRE_ANT.getCost();
    	}
    	else if(antType.equals("Pharoh Ant")){
    		return AntCost.PHAROH_ANT.getCost();
    	}
    	else if(antType.equals("Sugar Ant")){
    		return AntCost.SUGAR_ANT.getCost();
    	}
    	else if(antType.equals("Theif Ant")){
    		return AntCost.THEIF_ANT.getCost();
    	}
    	else if(antType.equals("Weaver Ant")){
    		return AntCost.WEAVER_ANT.getCost();
    	}
    	return 0;
    }

    public void nextFight() {
		Ant a = colony.elementAt(0);
		a.attack(this);
	
		Zombie z = horde.elementAt(0);
		if ((a instanceof LeafcutterAnt) && (z.getLife() <= 0)) {
		    // leafcutters have first strike, so opposing zombie gets no attack
		}
		else {
		    z.attack(this);
		}
	
		// reap all things dead
		boolean keepReaping = true;
		while (keepReaping) {
		    keepReaping = false;
		    for (int i = 0; i < colony.size(); ) {
			a = colony.elementAt(i);
			if (a.getLife() > 0) {
			    i++;
			}
			else {
			    colony.remove(i);
			    if (a instanceof CitronellaAnt) {
				for (Ant a2 : colony) {
				    a2.takeDamage(2);
				}
				for (Zombie z2: horde) {
				    z2.takeDamage(2);
				}
			    }
			    keepReaping = true;
			}
		    }
	
		    for (int i = 0; i < horde.size(); ) {
			z = horde.elementAt(i);
			if (z.getLife() > 0) {
			    i++;
			}
			else {
			    horde.remove(i);
			    food += z.getReward();
			}
		    }
		}
		if (colony.size() == 0 && horde.size() > 0) {
		    gameOver = true;
		}
    }

	
    /******************** other methods ********************/
    /**
     * Gets the Zombie vector containing all the zombies
     * @return the Zombie vector
     */
    public Vector<Zombie> getHorde(){
    	return horde;
    }
    
    /**
     * Gets the Ant vector containing all the ants
     * @return the Ant vector
     */
    public Vector<Ant> getColony(){
    	return colony;
    }
    
    /**
     * Adds food to the players total food stash
     * @param Food to add to the stash
     */
    public void removeFood(int number){
    	if(food != 0){
    		food -= number;
    	}
    }
    
    /**
     * Remove food from the players total food stash
     * @param Food to subtract from the stash
     */
    public void addFood(int number){
    	food += number;
    }
    
    /**
     * Creates and adds the zombies to the zombie vector
     * @param zombieString - the parsed string given from the horde file
     */
    private void generateHorde(String zombieString){
    	for(int i = 0; i < zombieString.length(); i++){
    		String numberCheck = Character.toString(zombieString.charAt(i));
    		//check to see if the string calls for more of the previous zombie to be made and added
    		if(numberCheck.equals("1") || numberCheck.equals("2") || numberCheck.equals("3") || numberCheck.equals("4") || 
    				numberCheck.equals("5") || numberCheck.equals("6") || numberCheck.equals("7") || numberCheck.equals("8") || numberCheck.equals("9")){
    			//make the line into a number if it does require more zombies be added
    			int number = Integer.parseInt(numberCheck);
    			//add that many zombies
    			for(int j = 0; j < number; j++){
    				horde.add(Zombie.makeZombie(zombieString.charAt(i-1)));
    			}
    		}
    		else{
    			horde.add(Zombie.makeZombie(zombieString.charAt(i)));
    		}
    	}
    }
}