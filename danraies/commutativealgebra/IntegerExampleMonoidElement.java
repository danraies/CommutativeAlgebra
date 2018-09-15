package danraies.commutativealgebra;

public class IntegerExampleMonoidElement implements CommutativeMonoidElement {
    private int value;

    public IntegerExampleMonoidElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof IntegerExampleMonoidElement)) {
            return false;
        }
        IntegerExampleMonoidElement i = (IntegerExampleMonoidElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public IntegerExampleMonoidElement addTo(CommutativeMonoidElement e) {
        IntegerExampleMonoidElement i = (IntegerExampleMonoidElement) e;
        return new IntegerExampleMonoidElement(value + i.value);
    }
}
