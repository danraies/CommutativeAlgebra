import danraies.commutativealgebra.*;

public class ExNNElement {
    private long value;

    public ExNNElement(long value) {
        if (value < 0) {
            throw new RuntimeException("Natural Numbers must be nonnegative");
        }
        this.value = value;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExNNElement)) {
            return false;
        }
        ExNNElement n = (ExNNElement) o;
        return (this.value == n.value);
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean isZero() {
        return (value == (0L));
    }

    public ExNNElement addTo(CommutativeMonoidElement e) {
        ExNNElement n;
        try {
            n = (ExNNElement) e;
        } catch (Exception ex) {
            throw new RuntimeException("These elements cannot be added", ex);
        }
        return new ExNNElement(this.value + n.value);
    }
}
