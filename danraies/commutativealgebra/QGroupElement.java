package danraies.commutativealgebra;

public class QGroupElement extends QMonoidElement implements AbelianGroupElement {
    public QGroupElement(long aNumerator, long aDenominator) {
        super(aNumerator, aDenominator);
    }
    
    public QGroupElement negative() {
        return new QGroupElement((-1L) * numerator, denominator);
    }
}
