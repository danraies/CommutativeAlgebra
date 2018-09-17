package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.Random;

public class ExampleZZMonoidElementFactory extends CommutativeMonoidElementFactory {
    private Random r;
    
    public ExampleZZMonoidElementFactory(Random r) {
        this.r = r;
    }

    ExampleZZMonoidElement getRandom() {
        return new ExampleZZMonoidElement(r.nextInt());
    }

    ExampleZZMonoidElement getZero() {
        return new ExampleZZMonoidElement(0);
    }

    public static void main(String[] args) {
        Random r = new Random();
        ExampleZZMonoidElementFactory factory =
            new ExampleZZMonoidElementFactory(r);
        //factory.shouldIncludeVerboseOutput(true);
        //factory.setLog(System.out);
        //factory.setTotalNumberOfTests(5);
        factory.testAxioms();
    }
}
