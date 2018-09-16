package danraies.commutativealgebra;

public class ExampleZZMonoidElement implements CommutativeMonoidElement {
    private int value;

    public ExampleZZMonoidElement(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExampleZZMonoidElement)) {
            return false;
        }
        ExampleZZMonoidElement i = (ExampleZZMonoidElement) o;
        return (value == i.value);
    }

    public boolean isZero() {
        return (value == 0L);
    }

    public ExampleZZMonoidElement addTo(CommutativeMonoidElement e) {
        ExampleZZMonoidElement i = (ExampleZZMonoidElement) e;
        return new ExampleZZMonoidElement(value + i.value);
    }
}
