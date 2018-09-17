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
    
    abstract CommutativeMonoidElement getRandom();
    abstract CommutativeMonoidElement getZero();

    void runAllAxiomTests() {
        testMonoidalAxioms();
    }

    void testMonoidalAxioms() {
        testZero();
        runAllAdditiveCommutivityTests();
        runAllAdditiveIdentityTests();
        runAllAdditiveAssociativityTests();
    }

    private void testZero() {
        logAxiomTestStart(ZERO_EQUALS_ZERO_TEST_NAME);
        logAxiomTestResult(ZERO_EQUALS_ZERO_TEST_NAME, getZero().isZero());
    }

    private boolean testAdditiveCommutivityAxiom(CommutativeMonoidElement e1,
                                                 CommutativeMonoidElement e2,
                                                 int testCounter) {
        logElementTestStart(testCounter);
        CommutativeMonoidElement sum1 = e1.addTo(e2);
        CommutativeMonoidElement sum2 = e2.addTo(e1);
        logVerbose(sumString(e1, e2));
        logVerbose(sumString(e2, e1));
        boolean pass = sum1.equals(sum2);
        logElementTestResult(pass,
                             PASS_ADDITIVE_COMMUTIVITY_STRING,
                             FAIL_ADDITIVE_COMMUTIVITY_STRING);
        return pass;
    }

    private void runAllAdditiveCommutivityTests() {
        logAxiomTestStart(ADDITIVE_COMMUTIVITY_TEST_NAME);
        int testCounter = 1;
        boolean passedSoFar = true;
        CommutativeMonoidElement e1 = getZero();
        CommutativeMonoidElement e2 = getZero();
        while ((passedSoFar) && (testCounter <= totalTests)) {
            e1 = getRandom();
            e2 = getRandom();
            passedSoFar = passedSoFar &&
                testAdditiveCommutivityAxiom(e1, e2, testCounter);
            testCounter++;
        }
        Element[] possibleCounterExample = {e1, e2};
        logAxiomTestResult(ADDITIVE_COMMUTIVITY_TEST_NAME,
                           passedSoFar,
                           possibleCounterExample);
    }

    private boolean testAdditiveIdentityAxiom(CommutativeMonoidElement e,
                                              int testCounter) {
        logElementTestStart(testCounter);
        CommutativeMonoidElement zero = getZero();
        CommutativeMonoidElement sum = e.addTo(zero);
        logVerbose(sumString(e, zero));
        boolean pass = sum.equals(e);
        logElementTestResult(pass,
                             PASS_ADDITIVE_IDENTITY_STRING,
                             FAIL_ADDITIVE_IDENTITY_STRING);
        return pass;
    }

    private void runAllAdditiveIdentityTests() {
        logAxiomTestStart(ADDITIVE_IDENTITY_TEST_NAME);
        int testCounter = 1;
        boolean passedSoFar = true;
        CommutativeMonoidElement e1 = getZero();
        while ((passedSoFar) && (testCounter <= totalTests)) {
            e1 = getRandom();
            passedSoFar = passedSoFar &&
                testAdditiveIdentityAxiom(e1, testCounter);
            testCounter++;
        }
        Element[] possibleCounterExample = {e1};
        logAxiomTestResult(ADDITIVE_IDENTITY_TEST_NAME,
                           passedSoFar,
                           possibleCounterExample);
    }

    private boolean testAdditiveAssociativityAxiom(CommutativeMonoidElement e1,
                                                   CommutativeMonoidElement e2,
                                                   CommutativeMonoidElement e3,
                                                   int testCounter) {
        logElementTestStart(testCounter);
        CommutativeMonoidElement leftSum = e1.addTo(e2).addTo(e3);
        CommutativeMonoidElement rightSum = e1.addTo(e2.addTo(e3));
        logVerbose(addLeftFirstString(e1, e2, e3));
        logVerbose(addRightFirstString(e1, e2, e3));
        boolean pass = leftSum.equals(rightSum);
        logElementTestResult(pass,
                             PASS_ADDITIVE_ASSOCIATIVITY_STRING,
                             FAIL_ADDITIVE_ASSOCIATIVITY_STRING);
        return pass;
    }

    private void runAllAdditiveAssociativityTests() {
        logAxiomTestStart(ADDITIVE_ASSOCIATIVITY_TEST_NAME);
        int testCounter = 1;
        boolean passedSoFar = true;
        CommutativeMonoidElement e1 = getZero();
        CommutativeMonoidElement e2 = getZero();
        CommutativeMonoidElement e3 = getZero();
        while ((passedSoFar) && (testCounter <= totalTests)) {
            e1 = getRandom();
            e2 = getRandom();
            e3 = getRandom();
            passedSoFar = passedSoFar &&
                testAdditiveAssociativityAxiom(e1, e2, e3, testCounter);
            testCounter++;
        }
        Element[] possibleCounterExample = {e1, e2, e3};
        logAxiomTestResult(ADDITIVE_ASSOCIATIVITY_TEST_NAME,
                           passedSoFar,
                           possibleCounterExample);
    }
}
