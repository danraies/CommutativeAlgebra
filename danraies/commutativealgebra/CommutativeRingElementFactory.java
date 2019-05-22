package danraies.commutativealgebra;
import java.util.ArrayList;
import java.io.PrintStream;

public abstract class CommutativeRingElementFactory extends AbelianGroupElementFactory {
    public abstract CommutativeRingElement getRandom();
    public abstract CommutativeRingElement getZero();
    public abstract CommutativeRingElement getOne();

    void addAllTests() {
        addCommutativeMonoidTestsToRun();
        addAbelianGroupTestsToRun();
        addCommutativeRingTestsToRun();
    }

    final void addCommutativeRingTestsToRun() {
        addTestToRun(new OneEqualsOneTest(getOne(), log));
        addTestToRun(new AxiomTestMultiplicativeCommutivity(totalTests, log));
        addTestToRun(new AxiomTestMultiplicativeAssociativity(totalTests, log));
    }
}
