package danraies.commutativealgebra;

public interface CommutativeMonoidElement extends Element {
    public boolean isZero();
    public CommutativeMonoidElement addTo(CommutativeMonoidElement e);
}
