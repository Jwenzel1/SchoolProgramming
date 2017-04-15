package proj4;

/**
* @version 4/29/14
* @author Joseph Wenzel <jwenzel1@umbc.edu>
* CMSC 341 - Spring 2014 - Project 4
* Section 02
*/

import java.util.Vector;
import java.math.BigInteger;

public class Hashtable<K, V> {
	
	private static int primeNumber = 5;
	private Vector<Vector<V>> table = new Vector<Vector<V>>(primeNumber);
	public int slots = table.capacity();
	public int thingsInHash = 0;
	private double loadFactor = (double)thingsInHash / (double)table.capacity();
	private int collisions = 0;
	public int timesrehashed = 0;
			
	/**
	 * creates a hashtable and populates it with vectors for chaining
	 */
	public Hashtable(){
		for(int i = 0; i < primeNumber; i++){
			table.add(new Vector<V>());
		}
	}

	/**
	 * checks if the hashtable has a key in it
	 * @param key
	 * @return true if it contains it
	 */
	public boolean containsKey(K key){
		Vector<V> chain = table.get(hashKey(key));
		for(int i = 0; i < chain.size(); i++){
			if(chain.get(i).hashCode() == key.hashCode()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * gets the value of the key out fo the hash table
	 * @param key
	 * @return value
	 */
	public V get(K key){
		Vector<V> chain = table.get(hashKey(key));
		for(int i = 0; i < chain.size(); i++){
			if(chain.get(i).hashCode() == key.hashCode()){
				return chain.get(i);
			}
		}
		return null;
	}
	
	//getters and setters****
	public int getPositionInHashtable(K key){
		return hashKey(key);
	}
	
	public int numCollisions(){
		return collisions;
	}
	
	public int numEntries(){
		return thingsInHash;
	}
	
	public int numSlots(){
		return table.capacity();
	}
	
	public double getLoadFactor() {
		return loadFactor;
	}
	
	//******
	
	/**
	 * puts the key value pair in the hashtable
	 * @param key
	 * @param value
	 */
	public void put(K key, V value){
		int index = hashKey(key);
		loadFactor = (double)thingsInHash / (double)table.capacity();
		if(!containsKey(key)){
			thingsInHash++;
			table.get(index).add(value);
			if(table.get(index).size() > 1){
				collisions++;
			}
			loadFactor = (double)thingsInHash / (double)table.capacity();
		}
		else{
			thingsInHash++;
			Vector<V> chain = table.get(hashKey(key));
			for(int i = 0; i < chain.size(); i++){
				if(chain.get(i).hashCode() == key.hashCode()){
					((TicTacToe) chain.get(i)).incrementTimesSeen();
					if(((TicTacToe)value).getWins() > 0){
						((TicTacToe) chain.get(i)).incrementWins();
					}
					else if(((TicTacToe)value).getLoses() > 0){
						((TicTacToe) chain.get(i)).incrementLosses();
					}
					else if(((TicTacToe)value).getDraws() > 0){
						((TicTacToe) chain.get(i)).incrementDraws();
					}
				}
			}
		}
		if(loadFactor > .75){
			thingsInHash = 0;
			collisions = 0;
//			System.out.println(loadFactor);
			timesrehashed++;
			primeNumber = getBiggerPrime(primeNumber);
			Hashtable<K, V> newTable = new Hashtable<K, V>();
			for(int i = 0; i < table.size(); i++){
				for(int j = 0; j < table.get(i).size(); j++){
					newTable.put(((K)table.get(i).get(j)), table.get(i).get(j));
				}
			}
			thingsInHash = newTable.thingsInHash;
			collisions = newTable.collisions;
			table = newTable.table;
			loadFactor = (double)thingsInHash / (double)table.capacity();
		}
	}
	
	private int hashKey(K key){
		return key.hashCode() % slots;
	}
	
	private int getBiggerPrime(int b){
		BigInteger prime = new BigInteger("" + b);
		prime = prime.nextProbablePrime();
		primeNumber = prime.intValue();
		return primeNumber;
	}
	
	/**
	 * returns a string representation of the hashTable
	 */
	public String toString(){
		String string = "";
		for(int i = 0; i < primeNumber; i++){
			string += "This is bucket number " + i + "\n";
			for(int j = 0; j < table.get(i).size(); j++){
				string += table.get(i).get(j).toString()/* + ((TicTacToe) table.get(i).get(j)).getWins() + ((TicTacToe) table.get(i).get(j)).getLoses() + ((TicTacToe) table.get(i).get(j)).getDraws()*/;
			}
		}
		return string;
	}
	
}
