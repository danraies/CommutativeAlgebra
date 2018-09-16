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

    public CommutativeMonoidElementFactory() {
        super();
    }

    public CommutativeMonoidElementFactory(boolean logVerboseOutput,
                                           PrintStream log) {
        super(logVerboseOutput, log);
    }
    
    abstract CommutativeMonoidElement getRandom();
    abstract CommutativeMonoidElement getZero();

    public boolean testAxioms(int totalTests) {
        logHeading(totalTests);
        ArrayList<TestResult> results = new ArrayList<TestResult>();
        testMonoidalAxioms(results, totalTests);
        return logSummary(results, totalTests);
    }

    void testMonoidalAxioms(ArrayList<TestResult> resultsSoFar,
                            int totalTests) {
        resultsSoFar.add(testZero());
        resultsSoFar.add(runAllAdditiveCommutivityTests(totalTests));
        resultsSoFar.add(runAllAdditiveIdentityTests(totalTests));
        resultsSoFar.add(runAllAdditiveAssociativityTests(totalTests));
    }

    private TestResult testZero() {
        logTestStart(ZERO_EQUALS_ZERO_TEST_NAME);
        TestResult result =
            new TestResult(ZERO_EQUALS_ZERO_TEST_NAME, getZero().isZero());
        log(result);
        return result;
    }

    private boolean testAdditiveCommutivityAxiom(CommutativeMonoidElement e1,
                                                 CommutativeMonoidElement e2,
                                                 int testCounter) {
        logVerboseTestCounter(testCounter);
        CommutativeMonoidElement sum1 = e1.addTo(e2);
        CommutativeMonoidElement sum2 = e2.addTo(e1);
        logVerbose(sumString(e1, e2));
        logVerbose(sumString(e2, e1));
        boolean pass = sum1.equals(sum2);
        logVerboseTestResult(pass,
                             PASS_ADDITIVE_COMMUTIVITY_STRING,
                             FAIL_ADDITIVE_COMMUTIVITY_STRING);
        return pass;
    }

    private TestResult runAllAdditiveCommutivityTests(int totalTests) {
        logTestStart(ADDITIVE_COMMUTIVITY_TEST_NAME);
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
        TestResult result =
            new TestResult(ADDITIVE_COMMUTIVITY_TEST_NAME, passedSoFar);
        if (!passedSoFar) {
            Element[] list = {e1, e2};
            result.setCounterExample(list);
        }
        log(result);
        return result;
    }

    private boolean testAdditiveIdentityAxiom(CommutativeMonoidElement e,
                                              int testCounter) {
        logVerboseTestCounter(testCounter);
        CommutativeMonoidElement zero = getZero();
        CommutativeMonoidElement sum = e.addTo(zero);
        logVerbose(sumString(e, zero));
        boolean pass = sum.equals(e);
        logVerboseTestResult(pass,
                             PASS_ADDITIVE_IDENTITY_STRING,
                             FAIL_ADDITIVE_IDENTITY_STRING);
        return pass;
    }

    private TestResult runAllAdditiveIdentityTests(int totalTests) {
        logTestStart(ADDITIVE_IDENTITY_TEST_NAME);
        int testCounter = 1;
        boolean passedSoFar = true;
        CommutativeMonoidElement e1 = getZero();
        while ((passedSoFar) && (testCounter <= totalTests)) {
            e1 = getRandom();
            passedSoFar = passedSoFar &&
                testAdditiveIdentityAxiom(e1, testCounter);
            testCounter++;
        }
        TestResult result =
            new TestResult(ADDITIVE_IDENTITY_TEST_NAME, passedSoFar);
        if (!passedSoFar) {
            result.setCounterExample(e1.toString());
        }
        log(result);
        return result;
    }

    private boolean testAdditiveAssociativityAxiom(CommutativeMonoidElement e1,
                                                   CommutativeMonoidElement e2,
                                                   CommutativeMonoidElement e3,
                                                   int testCounter) {
        logVerboseTestCounter(testCounter);
        CommutativeMonoidElement leftSum = e1.addTo(e2).addTo(e3);
        CommutativeMonoidElement rightSum = e1.addTo(e2.addTo(e3));
        logVerbose(addLeftFirstString(e1, e2, e3));
        logVerbose(addRightFirstString(e1, e2, e3));
        boolean pass = leftSum.equals(rightSum);
        logVerboseTestResult(pass,
                             PASS_ADDITIVE_ASSOCIATIVITY_STRING,
                             FAIL_ADDITIVE_ASSOCIATIVITY_STRING);
        return pass;
    }

    private TestResult runAllAdditiveAssociativityTests(int totalTests) {
        logTestStart(ADDITIVE_ASSOCIATIVITY_TEST_NAME);
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
        TestResult result =
            new TestResult(ADDITIVE_ASSOCIATIVITY_TEST_NAME, passedSoFar);
        if (!passedSoFar) {
            Element[] list = {e1, e2, e3};
            result.setCounterExample(list);
        }
        log(result);
        return result;
    }
}
