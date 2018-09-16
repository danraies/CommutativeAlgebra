package danraies.commutativealgebra;

public class ExampleZZGroupElement implements AbelianGroupElement {
    private int value;

    public ExampleZZGroupElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExampleZZGroupElement)) {
            return false;
        }
        ExampleZZGroupElement i = (ExampleZZGroupElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public ExampleZZGroupElement addTo(CommutativeMonoidElement e) {
        ExampleZZGroupElement i = (ExampleZZGroupElement) e;
        return new ExampleZZGroupElement(value + i.value);
    }

    public ExampleZZGroupElement negative() {
        return new ExampleZZGroupElement(-1 * value);
    }
}
