import danraies.commutativealgebra.*;

final public class ExZZElement implements CommutativeRingElement {
    private long value;

    public ExZZElement(long value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExZZElement)) {
            return false;
        }
        ExZZElement z = (ExZZElement) o;
        return (this.value == z.value);
    }

    public String toString() {
        return String.valueOf(value);
    }

    public boolean isZero() {
        return (value == (0L));
    }

    public ExZZElement addTo(CommutativeMonoidElement e) {
        ExZZElement z = (ExZZElement) e;
        return new ExZZElement(this.value + z.value);
    }

    public ExZZElement negative() {
        return new ExZZElement(this.value * (-1L));
    }

    public boolean isOne() {
        return (value == (1L));
    }

    public ExZZElement multiplyBy(CommutativeRingElement e) {
        ExZZElement z = (ExZZElement) e;
        return new ExZZElement(this.value * z.value);
    }
}
