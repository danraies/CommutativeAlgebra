package danraies.commutativealgebra;

public class QMonoidElement implements CommutativeMonoidElement {
    long numerator;
    long denominator;

    public QMonoidElement(long aNumerator, long aDenominator) {
        if (aDenominator == 0) {
            throw new RuntimeException("Denominator cannot be zero.");
        } else if (aNumerator == 0) {
            numerator = 0;
            denominator = 1;
        } else {
            long gcd = gcd(aNumerator, aDenominator);
            numerator = aNumerator/gcd;
            denominator = aDenominator/gcd;
        }
        if (denominator < 0) {
            denominator = (-1L)*denominator;
            numerator = (-1L)*numerator;
        }
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof QMonoidElement)) {
            return false;
        }
        QMonoidElement q = (QMonoidElement) o;
        return (numerator == q.numerator);
    }

    public boolean isZero() {
        return (numerator == 0L);
    }

    public QMonoidElement addTo(CommutativeMonoidElement e) {
        QMonoidElement q = (QMonoidElement) e;
        long aNumerator = q.numerator * denominator + q.denominator * numerator;
        long aDenominator = denominator * q.denominator;
        return new QMonoidElement(aNumerator, aDenominator);
    }
    
    private static long gcd(long aNum1, long aNum2) {
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
}
