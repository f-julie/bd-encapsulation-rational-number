package com.amazon.ata.encapsulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RationalNumberTest {

    @Test
    public void toString_onZero_printsZero() {
        //GIVEN
        RationalNumber zero = new RationalNumber(0, 100);
        String zeroString = "0/1";

        //WHEN
        String toStringOutput = zero.toString();

        //THEN
        assertEquals(zeroString, toStringOutput, "Expected 0/100 to print as 0/1");
    }

    @Test
    public void toString_onUpdatedRational_printsUpdatedValue() {
        //GIVEN
        RationalNumber zero = new RationalNumber(0, 1);
        zero.numerator = 1;
        zero.denominator = 1;
        String oneString = "1/1";

        //WHEN
        String toStringOutput = zero.toString();

        //THEN
        assertEquals(oneString, toStringOutput, "Expected 1 to print as 1/1");
    }

    @Test
    public void constructor_onNonReducedFormRational_reducesToReducedForm() {
        //WHEN
        RationalNumber half = new RationalNumber(2, 4);

        //THEN
        assertEquals(1, half.numerator, "Expected 2/4 to reduce to 1/2");
        assertEquals(2, half.denominator, "Expected 2/4 to reduce to 1/2");
    }

    @Test
    public void toDecimalValue_onWholeNumber_printsWithDecimalZero() {
        //GIVEN
        RationalNumber one = new RationalNumber(5, 5);
        double oneDecimal = 1.0;

        //WHEN
        double decimalOutput = one.toDecimalValue();

        //THEN
        assertTrue(Double.compare(oneDecimal, decimalOutput) == 0,
                   "Expected 1/1 to have decimal value of 1.0");
    }

    @Test
    public void plus_onTwoRationalsSumToLessThanOne_computesSum() {
        //GIVEN
        RationalNumber half = new RationalNumber(1, 2);
        RationalNumber third = new RationalNumber(1, 3);
        RationalNumber expectedSum = new RationalNumber(5, 6);

        //WHEN
        RationalNumber outputSum = half.plus(third);

        //THEN
        assertEquals(expectedSum, outputSum, "Expected 1/2 + 1/3 = 5/6");
    }

    @Test
    public void plus_onTwoRationalsSumToGreaterThanOne_computesSum() {
        //GIVEN
        RationalNumber half = new RationalNumber(1, 2);
        RationalNumber twoThirds = new RationalNumber(2, 3);
        RationalNumber expectedSum = new RationalNumber(7, 6);

        //WHEN
        RationalNumber outputSum = half.plus(twoThirds);

        //THEN
        assertEquals(expectedSum, outputSum, "Expected 1/2 + 2/3 = 7/6");
    }

    @Test
    public void constructor_withZeroDenominator_throwsException() {
        //WHEN & THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> new RationalNumber(1, 0),
                                "The constructor should not allow an illegal denominator value of 0.");
    }

    @Test
    public void update_withZeroDenominator_throwsException() {
        //GIVEN
        RationalNumber half = new RationalNumber(1, 2);

        //WHEN & THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> half.denominator = 0, "You should not "
            + "be able to edit an existing RationalNumber and make it invalid with a 0 value denominator.");
    }

    @Test
    public void equals_withMixedReducedNonReducedRationals_returnsEqual() {
        //GIVEN
        RationalNumber half = new RationalNumber(1, 2);
        RationalNumber half2 = new RationalNumber(2, 4);

        //WHEN
        boolean equal = half.equals(half2);

        //THEN
        assertTrue(equal, "1/2 and 2/4 should be equal.");
    }

    @Test
    public void equals_withUpdatedReducedNonReducedRationals_returnsEqual() {
        //GIVEN
        RationalNumber half = new RationalNumber(1, 2);
        RationalNumber half2 = new RationalNumber(1, 2);
        half2.numerator = 2;
        half2.denominator = 4;

        //WHEN
        boolean equal = half.equals(half2);

        //THEN
        assertTrue(equal, "1/2 and 2/4 should be equal.");
    }
}
