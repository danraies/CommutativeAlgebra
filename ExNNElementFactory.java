import danraies.commutativealgebra.*;
import java.util.Random;

final public class ExNNElementFactory extends CommutativeMonoidElementFactory {
    Random r = new Random();
    
    public ExNNElement getRandom() {
        long value = r.nextInt();
        if (value < 0) {
            value = (-1L) * value;
        }
        return new ExNNElement(value);
    }

    public ExNNElement getZero() {
        return new ExNNElement(0L);
    }

    public static void main(String[] args) {
        ExNNElementFactory f = new ExNNElementFactory();
        f.exampleTest();
    }
}
