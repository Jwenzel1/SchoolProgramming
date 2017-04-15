/**
 * The Rank enum encapsulates each of the 13 ranks of a standard deck
 * of 52 playing cards. Using this enum assures that all code refers
 * to the ranks in the same manner, i.e. Rank.TWO, ..., Rank.QUEEN,
 * Rank.KING, Rank.ACE, with no possibility of conflicting representations.
 * This enum is provided for your use. You must not change it. 
 * 
 * @author Susan M. Mitchell (Created: 10 Nov 2007)
 * @author Daniel J. Hood (Modified: 23 Mar 2011)
 * @author Susan M. Mitchell (Modified: 07 Oct 2012)
 * @author Susan M. Mitchell (Modified: 10 Oct 2012)
 */

package proj2;

public enum Rank {
	
	/**
	 * The rank ACE
	 */
	ACE('a', "Ace", 14),

	/**
	 * The rank TWO (2)
	 */
	TWO('2', "Two", 2),
	
	/**
	 * The rank THREE (3)
	 */
	THREE('3', "Three", 3),
	
	/**
	 * The rank FOUR (4)
	 */
	FOUR('4', "Four", 4),
	
	/**
	 * The rank FIVE (5)
	 */
	FIVE('5', "Five", 5),
	
	/**
	 * The rank SIX (6)
	 */
	SIX('6', "Six", 6),
	
	/**
	 * The rank SEVEN (7)
	 */
	SEVEN('7', "Seven", 7),
	
	/**
	 * The rank EIGHT (8)
	 */
	EIGHT('8', "Eight", 8),
	
	/**
	 * The rank NINE (9)
	 */
	NINE('9', "Nine", 9),
	
	/**
	 * The rank TEN (10)
	 */
	TEN('t', "Ten", 10),
	
	/**
	 * The rank JACK
	 */
	JACK('j', "Jack", 11),
	
	/**
	 * The rank QUEEN
	 */
	QUEEN('q', "Queen", 12),
	
	/**
	 * The rank KING
	 */
	KING('k', "King", 13);
	
	/**
	 * Single character representation of the rank
	 */
	private final char symbol;
	
	/**
	 * String representation of the rank
	 */
	private final String name;

	/**
	 * Value of the rank
	 */
	private final int value;

	/**
	 * Internal constructor used to create <code>Rank</code> enums
	 * 
	 * @param symbol
	 *            the character representation of the rank
	 * @param name
	 *            the string representation of the rank
	 */
	private Rank(char symbol, String name, int value) {
		this.symbol = symbol;
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns the symbol for the rank (e.g. ACE is 'a').
	 * 
	 * @return the symbol
	 */
	public char getSymbol() {
		return this.symbol;
	}

	/**
	 * Returns the name for the suit (e.g. ACE is "Ace").
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the value of the suit.
	 */

	public int getValue() {
		return this.value;
	}
}
