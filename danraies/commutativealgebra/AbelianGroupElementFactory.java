package danraies.commutativealgebra;
import java.util.ArrayList;
import java.io.PrintStream;

public abstract class AbelianGroupElementFactory extends CommutativeMonoidElementFactory {
    public abstract AbelianGroupElement getRandom();
    public abstract AbelianGroupElement getZero();

    void addAllTests() {
        addCommutativeMonoidTestsToRun();
        addAbelianGroupTestsToRun();
    }

    final void addAbelianGroupTestsToRun() {
        addTestToRun(new AxiomTestAdditiveInverse(totalTests, log));
    }
}
