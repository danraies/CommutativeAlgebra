import danraies.commutativealgebra.*;
import java.util.Random;

final public class ExQQElementFactory extends FieldElementFactory {
    private static int MAX_LONG = 20;
    Random r = new Random();

    public ExQQElement getRandom() {
        long numerator = r.nextInt(2 * MAX_LONG) - MAX_LONG;
        long denominator = r.nextInt(MAX_LONG - 1) + 1;
        return new ExQQElement(numerator, denominator);
    }

    public ExQQElement getZero() {
        return new ExQQElement(0L, 1L);
    }

    public ExQQElement getOne() {
        return new ExQQElement(1L, 1L);
    }

    public static void main(String[] args) {
        ExQQElementFactory f = new ExQQElementFactory();
        f.exampleTest();
    }
}
