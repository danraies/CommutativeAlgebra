package danraies.commutativealgebra;

public class IntegerExampleRingElement implements RingElement {
    private int value;

    public IntegerExampleRingElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof IntegerExampleRingElement)) {
            return false;
        }
        IntegerExampleRingElement i = (IntegerExampleRingElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public IntegerExampleRingElement addTo(CommutativeMonoidElement e) {
        IntegerExampleRingElement i = (IntegerExampleRingElement) e;
        return new IntegerExampleRingElement(value + i.value);
    }

    public IntegerExampleRingElement negative() {
        return new IntegerExampleRingElement(-1 * value);
    }

    public boolean isOne() {
        return (value == 1);
    }

    public IntegerExampleRingElement multiplyBy(RingElement e) {
        IntegerExampleRingElement i = (IntegerExampleRingElement) e;
        return new IntegerExampleRingElement(value * i.value);
    }
}
