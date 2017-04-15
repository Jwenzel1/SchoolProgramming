package lab3;

public class Fraction {
	/**
	 * An int representing the numerator of the fraction.
	 */
	private int numerator;
	 
	/**
	 * An int representing the denominator of the fraction.
	 */
	private int denominator;
	
	public Fraction( int n, int d){
		numerator = n;
		denominator = d;
	}
	public int getNumerator(){
		return numerator;
	}
	public void setNumerator(int n){
		numerator = n;
	}
	public String toString(){
		String str = "";
		str += str + numerator + "/" + denominator;
		return str;
	}
	public double decimalValue(){
		double dnum = (double)numerator;
		double dden = (double)denominator;
		double value = dnum/dden;
		return value;
	}
	public Fraction multiply(Fraction otherFraction){
		int newNum = this.numerator * otherFraction.numerator;
		int newDen = this.denominator * otherFraction.denominator;
		Fraction f = new Fraction(newNum,newDen);
		return f;
	}
	public boolean equals(Fraction secondFraction){
		boolean Ncheck = this.numerator == secondFraction.numerator;
		boolean Dcheck = this.denominator == secondFraction.denominator;
		return Ncheck&&Dcheck;
//		if (Ncheck && Dcheck){
//			boolean valid = true;
//			return valid;
//		}
//		return false
	}
}
