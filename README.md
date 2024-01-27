## Introduction to the Example Code

Take a look at the `RationalNumber` class in the **'com.amazon.ata.encapsulation'** Java package. It provides 
users the ability to add two rational numbers as well as display the number as a fraction or decimal value. The rational
number the class represents is always kept in reduced form.

(A rational number is a number that can be expressed as a fraction of two integers (e.g. 2/1 (or 2) and 99/100).
"Reduced form" means the numerator and denominator are the smallest integers they could be: e.g. 2/4
in "reduced form" would be 1/2)

The `RationalNumberTest` class, located in the
**'com.amazon.ata.encapsulation'** Java package of the tst/ directory, contains seven unit tests for the
RationalNumber class.

As you can see, the `RationalNumber` class is not well encapsulated. `RationalNumberTest`
utilizes the ability to directly access `RationalNumber`'s data fields. The access is not always malicious, however 
users of the class can cause weird behavior when they directly change the numerator or denominator, as the rational 
number will no longer be in reduced form. The test case `equals_withUpdatedReducedNonReducedRationals_returnsEqual`
provides an example of this.

The test case `updateDenominator_withZeroDenominator_throwsException` creates an invalid rational number where the
denominator is 0. Run the tests in IntelliJ by clicking the play button left of `public class RationalNumberTest`
near the top of the file. If you prefer to run tests in your terminal, use the command
`./gradlew -q clean :test`. You should see results similar to this (you may need to scroll a bit in the
terminal):

```text
      └─ JUnit Jupiter ✔
         └─ RationalNumberTest ✔
            ├─ constructor_withZeroDenominator_throwsException() ✔
            ├─ toString_onUpdatedRational_printsUpdatedValue() ✔
            ├─ plus_onTwoRationalsSumToLessThanOne_computesSum() ✔
            ├─ constructor_onNonReducedFormRational_reducesToReducedForm() ✔
            ├─ equals_withUpdatedReducedNonReducedRationals_returnsEqual() ✘ 1/2 and 2/4 should be equal. ==> expected: <true> but was: <false>
            ├─ update_withZeroDenominator_throwsException() ✘ You should not be able to edit an existing RationalNumber and make it invalid with a 0 value denominator. ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
            ├─ toString_onZero_printsZero() ✔
            ├─ toDecimalValue_onWholeNumber_printsWithDecimalZero() ✔
            ├─ plus_onTwoRationalsSumToGreaterThanOne_computesSum() ✔
            └─ equals_withMixedReducedNonReducedRationals_returnsEqual() ✔
```

The not encapsulated version of this class is causing two unit tests to fail, based on the report above.

## Implement encapsulation of `RationalNumber`

In this **try** you will make changes to the `RationalNumber` class so that the fields cannot be directly accessed or 
changed by another class.

### Encapsulate fields of `RationalNumber`

Provide access to the instance variables via getter methods and the ability to change them 
with a new void method, `update(int numerator, int denominator)`. Updating the values separately and reducing will yield 
confusing and unintended results, so instead only allow users to update the entire rational value using:
update(int numerator, int denominator)`. If a user wishes to only update the numerator (1/3 -> 2/3) they can write code
like this:

```java
       RationalNumber rational = new RationalNumber(1, 3);
       rational.update(2, rational.getDenominator());
```

**Note:** The `update()` method should ensure that the denominator is not 0, then call `reduce()` to make sure
that the number is in reduced form. If that makes it similar to the constructor, refactor to avoid duplicate code.

Finally, you should update the test code to utilize your new methods so that the test cases compile **and pass**.

### Create a copy constructor for `RationalNumber`

Create a new constructor `RationalNumber(RationalNumber original)` that returns a new `RationalNumber` instantiated
with the same numerator and denominator as the `original`.

**Note:** If the other constructors and the `update()` method all call `reduce()`, can an existing `RationalNumber`
ever be invalid? Will it always be in reduced form? Does the copy constructor need to check its argument's
denominator, or call reduce?

Add a new unit test, `constructor_existingRationalNumber_createsCopy`, to verify that the constructor creates a
new `RationalNumber` with the same value.

### Push your work

Now it's time to push your fixes! You can run the following Gradle command before committing to ensure
your try is in working order: `./gradlew -q clean :test`.

## Note on `assertThrows`

Some test cases use an assertion called **assertThrows**. We've seen this once before, but as a reminder:
this assertion ensures that an exception is thrown from a method call (what comes after the `() ->`).
We can use it to validate that when invalid inputs get passed to the method it blows up! The notation used
in this assertion is a little advanced and tricky, and we'll come back to it when we cover Java Lambda functions.

        Assertions.assertThrows(
            IllegalArgumentException.class, // #1 - Exception to throw
            () -> half.denominator = 0, // #2 - Thing to do
            "You should not be able to edit an existing " // #3 - Failure message
                + "RationalNumber and make it invalid with a 0 value denominator."
         );

The assertion has three parameters.
1. What exception you expect to be thrown by your code. 
2. Parameter two is the tricky code. There is the notation **() ->**, followed by a line of code, which does not end 
    with a semi-colon. For now, treat the **() ->** as just a little special magic that will tell the assertion to
    execute the line of code on the right hand side of the arrow. The line of code you put on the right hand side of the
    arrow is the code you want to blow up.
3. The message to display in case of a failure. 

The assertion above comes from the updateDenominator_withZeroDenominator_throwsException test method. Once you change
RationalNumber so that denominator cannot be accessed from the outside, this line of code won't compile any more!
Try swapping in new code to edit the denominator for: **half.denominator = 0**.
