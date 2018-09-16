package danraies.commutativealgebra;

public class ExampleZZRingElement implements CommutativeRingElement {
    private int value;

    public ExampleZZRingElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExampleZZRingElement)) {
            return false;
        }
        ExampleZZRingElement i = (ExampleZZRingElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public ExampleZZRingElement addTo(CommutativeMonoidElement e) {
        ExampleZZRingElement i = (ExampleZZRingElement) e;
        return new ExampleZZRingElement(value + i.value);
    }

    public ExampleZZRingElement negative() {
        return new ExampleZZRingElement(-1 * value);
    }

    public boolean isOne() {
        return (value == 1);
    }

    public ExampleZZRingElement multiplyBy(CommutativeRingElement e) {
        ExampleZZRingElement i = (ExampleZZRingElement) e;
        return new ExampleZZRingElement(value * i.value);
    }
}
