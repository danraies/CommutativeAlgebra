import danraies.commutativealgebra.*;

final public class ExQQElement implements FieldElement {
    private long numerator;
    private long denominator;

    /**
     * This is the default constructor but should not be used.  It always
     * returns the rational number 1/1.
     */
    private ExQQElement() {
        numerator = 1L;
        denominator = 1L;
    }

    /**
     * Creats a rational number by specifying the numerator and denominator.
     * The fraction is automatically reduced by the constructor.
     * One can use any non-zero integer for the denominator when calling this
     * constructor, but the constructor will force the denominator to be 
     * positive in the end.  If one calls this constructor with a zero for
     * the denominator then a runtime exception will be thrown.
     *
     * @param aNumerator The numerator of the rational number.
     * @param aDenominator The denominator of the rational number.
     *        An exception is thrown if this is zero.
     */
    public ExQQElement(long aNumerator, long aDenominator) {
        if (aDenominator == 0) {
            // An exception is thrown if the denominator is zero.
            throw new RuntimeException(zeroDenominatorMessage);
        } else if (aNumerator == 0) {
            // If the numerator is zero we make the denominator 1.
            numerator = 0;
            denominator = 1;
        } else {
            // If the number is non-zero the fraction is reduced.
            long gcd = gcd(aNumerator, aDenominator);
            numerator = aNumerator/gcd;
            denominator = aDenominator/gcd;
        }
        if (denominator < 0) {
            // This forces the denominator to be positive.
            denominator = (-1L)*denominator;
            numerator = (-1L)*numerator;
        }
    }

    /**
     * Overrides the <code>toString()</code> method.
     *
     * @return The string "numerator/denominator" is returned.  Since the
     *         denominator is guaranteed to be positive this won't
     *         result in any ugly negative signs after a slash.
     */
    public String toString() {
        return numerator + SLASH_CHAR + denominator;
    }

    /**
     * Overrides the <code>equals()</code> method.  Returns <code>true</code>
     * if the numerator and denominator are both equal.  This works because 
     * the constructor reduces fractions and forces denominators to be
     * positive.
     *
     * @param o An <code>Object</code> with which to compare.
     * @return Returns <code>true</code> if <code>o</code> and
     *         <code>this</code> are the same rational number.
     */
    public boolean equals(Object o) {
        boolean returnValue;

        if (o == this) {
            returnValue = true;
        } else if (!(o instanceof ExQQElement)) {
            returnValue = false;
        } else {
            ExQQElement r = (ExQQElement) o;
            returnValue = (r.numerator == this.numerator)
                && (r.denominator == this.denominator);
        }

        return returnValue;
    }

    /**
     * Checks if this number is zero.
     *
     * @return Returns <code>true</code> if zero, <code>false</code>
     *         if non-zero.
     */
    public boolean isZero() {
        return (numerator == 0L);
    }

    /**
     * Checks if this number is one.
     *
     * @return Returns <code>true</code> if one, <code>false</code>
     *         otherwise.
     */
    public boolean isOne() {
        return (numerator == 1L) && (denominator == 1L);
    }

    /**
     * Gets the additive inverse of <code>this</code>.
     *
     * @return Returns the <code>ExQQElement</code> given by
     *         <code>-1 * this</code>.
     */
    public ExQQElement negative() {
        return new ExQQElement((-1L * numerator), denominator);
    }

    /**
     * Gets the multiplicative inverse of <code>this</code>.
     *
     * @return Returns the <code>ExQQElement</code> given by
     *         <code>1 / this</code>.
     */
    public ExQQElement inverse() {
        return new ExQQElement(denominator, numerator);
    }

    public ExQQElement addTo(CommutativeMonoidElement r) {
        ExQQElement q = (ExQQElement) r;
        long lcm = lcm(denominator, q.denominator);
        long aNumerator;
        try {
            aNumerator = Math.addExact(
                Math.multiplyExact(numerator, (lcm / denominator)),
                Math.multiplyExact(q.numerator, (lcm / q.denominator)));
        } catch (ArithmeticException e) {
            throw new RuntimeException(longOverflowMessage, e);
        }
        return new ExQQElement(aNumerator, lcm);
    }

    public ExQQElement multiplyBy(CommutativeRingElement r) {
        ExQQElement q = (ExQQElement) r;
        long num1 = numerator;
        long num2 = q.numerator;
        long den1 = denominator;
        long den2 = q.denominator;
        
        long somefactors = gcd(num1, den2);
        num1 = num1/somefactors;
        den2 = den2/somefactors;
        somefactors = gcd(num2, den1);
        num2 = num2/somefactors;
        den1 = den1/somefactors;
        
        long aNumerator;
        long aDenominator;
        try {
            aNumerator = Math.multiplyExact(num1, num2);
            aDenominator = Math.multiplyExact(den1, den2);
        } catch (ArithmeticException e) {
            throw new RuntimeException(longOverflowMessage, e);
        }
        return new ExQQElement(aNumerator, aDenominator);
    }

    /**
     * Returns the greatest common divisor of two <code>long</code> integers.
     * It uses the Euclidean Algorithm for the computation.
     *
     * @param aNum1 Any <code>long</code> integer.
     * @param aNum2 Any <code>long</code> integer.
     * @return The greatest common divisor of the two parameters.  This will 
     *         always be nonnegative.
     */
    private static long gcd(long aNum1, long aNum2) {
        // This forces all quantities involved to be positive.
        long a = aNum1;
        if (a < 0) {
            a = (-1L) * a;
        }
        long b = aNum2;
        if (b < 0) {
            b = (-1L) * b;
        }
        
        // This executes the Euclidean Algorithm.
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Returns the least common multiple of two <code>long</code> integers.
     * For the purposes of this method, the least common multiple of any
     * number and zero is considered to be undefined.  There are two
     * instances in which this method can throw a runtime exception:
     * <ol>
     * <li>When one the parameters is zero.  For the purposes of this method,
     *     we consider the least common multiple of two numbers to be
     *     undefined when one of th numbers is zero.</li>
     * <li>When there is loss of precision.  The least common multiple of two
     *     numbers is often larger than the number itself.  If the result of
     *     this method is too large to be contained in a <code>long</code>
     *     integer then there is loss of precision.</li>
     * </ol>
     * 
     * @param aNum1 Any nonzero <code>long</code> integer.
     * @param aNum2 Any nonzero <code>long</code> integer.
     * @return The least common multiple of the two parameters.  This will
     *         always be nonnegative.
     */
    private static long lcm(long aNum1, long aNum2) {
        // An exception is thrown if aNum1 or aNum2 are zero.
        if ((aNum1 == 0L) || (aNum2 == 0L)) {
            throw new RuntimeException(illegalLcmMessage);
        }
        
        // The best algorithm that I know for the least common multiple is
        //     (aNum1 * aNum2) / gcd(aNum1, aNum2)
        // The slight modification below is done to avoid overflow.  If
        // overflow is unavoidable then Math.multiplyExact() will throw
        // an exception.
        long lcm;
        try {
            lcm = Math.multiplyExact((aNum1 / gcd(aNum1, aNum2)), aNum2);
        } catch (ArithmeticException e) {
            throw new RuntimeException(longOverflowMessage, e);
        }
        return lcm;
    }
    
    private static final String zeroDenominatorMessage
        = "Denominator was set to be zero.";
    private static final String longOverflowMessage
        = "This computation resulted in an integer that is not a long.";
    private static final String illegalLcmMessage
        = "The LCM of a number with 0 is considered undefined.";
    public static final String SLASH_CHAR = "/";
}
