package danraies.commutativealgebra;

public class IntegerExampleGroupElement implements AbelianGroupElement {
    private int value;

    public IntegerExampleGroupElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof IntegerExampleGroupElement)) {
            return false;
        }
        IntegerExampleGroupElement i = (IntegerExampleGroupElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public IntegerExampleGroupElement addTo(CommutativeMonoidElement e) {
        IntegerExampleGroupElement i = (IntegerExampleGroupElement) e;
        return new IntegerExampleGroupElement(value + i.value);
    }

    public IntegerExampleGroupElement negative() {
        return new IntegerExampleGroupElement(-1 * value);
    }
}
