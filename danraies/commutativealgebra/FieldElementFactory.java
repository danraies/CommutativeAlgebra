package danraies.commutativealgebra;
import java.util.ArrayList;
import java.io.PrintStream;

public abstract class FieldElementFactory extends CommutativeRingElementFactory {
    public abstract FieldElement getRandom();
    public abstract FieldElement getZero();
    public abstract FieldElement getOne();

    void addAllTests() {
        addCommutativeMonoidTestsToRun();
        addAbelianGroupTestsToRun();
        addCommutativeRingTestsToRun();
        addFieldTestsToRun();
    }

    final void addFieldTestsToRun() {
        addTestToRun(new AxiomTestMultiplicativeInverse(totalTests, log));
    }
}
