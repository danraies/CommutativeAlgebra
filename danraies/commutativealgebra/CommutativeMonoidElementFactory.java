package danraies.commutativealgebra;
import java.util.ArrayList;
import java.io.PrintStream;

public abstract class CommutativeMonoidElementFactory extends ElementFactory {
    public abstract CommutativeMonoidElement getRandom();
    public abstract CommutativeMonoidElement getZero();

    void addAllTests() {
        addCommutativeMonoidTestsToRun();
    }

    final void addCommutativeMonoidTestsToRun() {
        addTestToRun(new ZeroEqualsZeroTest(getZero(), log));
        addTestToRun(new AxiomTestAdditiveCommutivity(totalTests, log));
        addTestToRun(new AxiomTestAdditiveAssociativity(totalTests, log));
    }
}
