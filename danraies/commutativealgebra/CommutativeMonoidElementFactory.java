package danraies.commutativealgebra;
import java.util.ArrayList;
import java.io.PrintStream;

public abstract class CommutativeMonoidElementFactory extends ElementFactory {
    private static String PASS_ADDITIVE_IDENTITY_STRING =
        "This element is unchanged when added to zero.";
    private static String FAIL_ADDITIVE_IDENTITY_STRING =
        "This element is NOT unchanged when added to zero.";
    private static String PASS_ADDITIVE_COMMUTIVITY_STRING =
        "These elements commute additively.";
    private static String FAIL_ADDITIVE_COMMUTIVITY_STRING =
        "These elements do NOT commute additively.";
    private static String PASS_ADDITIVE_ASSOCIATIVITY_STRING =
        "These elements associate additively";
    private static String FAIL_ADDITIVE_ASSOCIATIVITY_STRING =
        "These elements do NOT associate additively";
    private static String ZERO_EQUALS_ZERO_TEST_NAME =
        "Zero Equals Zero Test";
    private static String ADDITIVE_COMMUTIVITY_TEST_NAME =
        "Additive Commutivity Test";
    private static String ADDITIVE_IDENTITY_TEST_NAME =
        "Additive Identity Test";
    private static String ADDITIVE_ASSOCIATIVITY_TEST_NAME =
        "Additive Associativity Test";
    
    public abstract CommutativeMonoidElement getRandom();
    public abstract CommutativeMonoidElement getZero();

    void addAllTests() {
        addCommutativeMonoidTestsToRun();
    }

    void addCommutativeMonoidTestsToRun() {
        addTestToRun(new ZeroEqualsZeroTest(getZero(),
                                            totalTests,
                                            log));
        addTestToRun(new AxiomTestAdditiveCommutivity(totalTests, log));
    }
}
