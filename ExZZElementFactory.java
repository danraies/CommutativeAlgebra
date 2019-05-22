import danraies.commutativealgebra.*;
import java.util.Random;

final public class ExZZElementFactory extends CommutativeRingElementFactory {
    Random r = new Random();

    public ExZZElement getRandom() {
        long value = r.nextInt();
        return new ExZZElement(value);
    }

    public ExZZElement getZero() {
        return new ExZZElement(0L);
    }

    public ExZZElement getOne() {
        return new ExZZElement(1L);
    }

    public static void main(String[] args) {
        ExZZElementFactory f = new ExZZElementFactory();
        f.exampleTest();
    }
}
