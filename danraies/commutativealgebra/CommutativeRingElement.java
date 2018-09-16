package danraies.commutativealgebra;

public interface CommutativeRingElement extends AbelianGroupElement {
    public boolean isOne();
    public CommutativeRingElement multiplyBy(CommutativeRingElement e);
}
