package com.amazon.ata.encapsulation;

/**
 * Represents a rational number, always kept in the most reduced form. By definition a rational number is a number that
 * can be in the form n/d where n and d are integers and d is not equal to zero.
 */
public class RationalNumber {

    public int numerator;
    public int denominator;

    /**
     * Constructs a new rational number representing 0. Numerator = 0, Denominator = 1.
     */
    public RationalNumber() {
        this.numerator = 0;
        this.denominator = 1;
    }

    /**
     * Constructs a new rational number with Numerator = numerator, Denominator = denominator.
     *
     * @param numerator   the rational number's numerator (the one on top)
     * @param denominator the rational number's denominator (the one on the bottom)
     */
    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be 0.");
        }
        this.numerator = numerator;
        this.denominator = denominator;

        reduce();
    }

    /**
     * Calculates the rational number as a decimal value.
     *
     * @return the decimal value of the rational number.
     */
    public double toDecimalValue() {
        return (double) numerator / denominator;
    }

    /**
     * Adds the passed Rational Number to this Rational Number and returns a new Rational Number in reduced form.
     *
     * @param b - the number to be added to the current rational number.
     * @return a new rational number that is the result of addition.
     */
    public RationalNumber plus(RationalNumber b) {
        int num = (this.numerator * b.denominator) + (b.numerator * this.denominator);
        int denom = this.denominator * b.denominator;
        return new RationalNumber(num, denom);
    }

    @Override
    public boolean equals(Object obj) {
        // Check that obj is not null and is a RationalNumber
        if (!(obj instanceof RationalNumber)) {
            return false;
        }

        // Cast it as a RationalNumber so we can use its methods and fields
        final RationalNumber other = (RationalNumber) obj;

        return this.denominator == other.denominator && this.numerator == other.numerator;
    }

    /**
     * Converts the rational number to the typical way fractions are viewed "numerator/denominator".
     * 0 is represented as "0/1" and 1 is represented as "1/1".
     *
     * @return the string representation of the rational number.
     */
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    // Euclidean algorithm to computing the GCD (Greatest Common Divisor)
    // https://en.wikipedia.org/wiki/Euclidean_algorithm
    private int findGreatestCommonDivisor(int valueA, int valueB) {
        // Copy the initial values
        int reducedA = valueA;
        int reducedB = valueB;
        // Reduce the values until one them is 0
        while (reducedA != 0 && reducedB != 0) {
            int storeB = reducedB;
            reducedB = reducedA % reducedB;
            reducedA = storeB;
        }
        // one has to be 0, so return the non-zero value
        return reducedA + reducedB;
    }

    private void reduce() {
        int gcd = findGreatestCommonDivisor(numerator, denominator);
        numerator = numerator / gcd;
        denominator = denominator / gcd;

        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }
    }
}
