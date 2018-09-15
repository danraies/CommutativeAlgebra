package danraies.commutativealgebra;

public interface RingElement extends AbelianGroupElement {
    public boolean isOne();
    public RingElement multiplyBy(RingElement e);
}
