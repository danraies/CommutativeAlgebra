package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * <p>
 * Instances of children of this class are meant to be used to test the axioms
 * in an algebraic structure.  If one makes a class called <code>XElement</code>
 * which is meant to mimic the elements in an algebraic structure, then one can
 * make a class called <code>XElementFactory</code> which is capable of testing
 * the axioms that <code>XElement</code> objects are meant to satisfy.
 * </p>
 * <p>
 * For example, suppose <code>XElement</code> directly extends
 * <code>CommutativeRingElement</code>.  Then <code>XElementFactory</code> should
 * directly extend <code>CommutativeRingElementFactory</code>.  By implementing the
 * appropriate methods one can then run an arbitrary number of tests designed to
 * check the axioms required by a commutative ring.
 * </p>
 * <p>
 * There are two methods of importance to one who is trying to use one of these
 * factory classes for testing their algebraic structure.
 * </p>
 * <dl>
 * <dt>{@link #getRandom() getRandom}</dt>
 * <dd>When one implements an <code>XElementFactory</code>, it is this method that
 *     determines everything in the tests.  It should return a randomly generated
 *     <code>XElement</code>.  The methods used during the tests will use this
 *     method repeatedly to generate the elements that are tested.</dd>
 * <dt>{@link #testAxioms() testAxioms}</dt>
 * <dd>This method runs the test.  When one implements an
 *     <code>XElementFactory</code> class, running this method on an instance of
 *     that class will perform the tests.  By default, output is sent to
 *     <code>System.out</code>.</dd>
 * </dl>
 */
public abstract class ElementFactory {
    //////////////////////////////////////////////////
    // Private Instance Variables
    //////////////////////////////////////////////////

    /** This instance keeps track of whether testing should log verbose output
        or not.  In this case "verbose output" means the details of the individual
        element tests that are run. */
    private boolean logVerboseOutput = true;

    /** This instance keeps track of the <code>PrintStream</code> object to which
        all logs are written.  By default this is <code>System.out</code> but it
        can be changed with the {@link #setLog(PrintStream) setLog} method. */
    private PrintStream log = System.out;

    /** This is the total number of element-wise tests that will be run when
        testing each individual axiom. */
    int totalTests = 100;

    /** This is an instance variable for internal use in the package.  It contains
        a list of objects, each of which represents the result of the test of
        an axiom. */
    private ArrayList<TestResult> testResults = new ArrayList<TestResult>();
    
    //////////////////////////////////////////////////
    // Abstract Methods
    //////////////////////////////////////////////////

    /**
     * This is the method that a user must implement when creating a factory class
     * for testing purposes.  It should return a randomly generated instance of
     * the precise class that you're trying to test.
     * 
     * @return A randomly generated instance of the class 
     */
    abstract Element getRandom();

    /**
     * 
     */
    abstract void runAllAxiomTests();

    //////////////////////////////////////////////////
    // Public Methods
    //////////////////////////////////////////////////

    public void testAxioms() {
        logStartOfTesting();
        runAllAxiomTests();
        logTestingSummary();
    }

    public void shouldIncludeVerboseOutput(boolean logVerboseOutput) {
        this.logVerboseOutput = logVerboseOutput;
    }

    public void setLog(PrintStream log) {
        this.log = log;
    }

    public void setTotalNumberOfTests(int totalTests) {
        this.totalTests = totalTests;
    }
    
    //////////////////////////////////////////////////
    // Logging Methods with Default Access
    //////////////////////////////////////////////////
    
    void logVerbose(String message) {
        if (logVerboseOutput) {
            log(VERBOSE_INDENT + message);
        }
    }

    // An "axiom test" is considered the test of one entire axiom.
    // Axiom tests involve a series of repeated, randomized element
    // tests.
    
    void logAxiomTestStart(String testName) {
        logHeadline(TEST_HEADER_PREFIX + testName);
    }

    void logAxiomTestResult(String testName, boolean didItPass) {
        TestResult result = new TestResult(testName, didItPass);
        logTestResult(result);
        testResults.add(result);
    }

    void logAxiomTestResult(String testName,
                            boolean didItPass,
                            Element[] counterExampleElementsList) {
        TestResult result = new TestResult(testName, didItPass);
        result.setCounterExample(counterExampleElementsList);
        logTestResult(result);
        testResults.add(result);
    }

    // An "element test" is considered to be the testing of a specific
    // collection of elements against one axiom.

    void logElementTestStart(int testCounter) {
        String heading = TEST_NUMBER_HEADING + testCounter;
        String underline = repeatForLength(TEST_UNDERLINE_CHARACTER,
                                           heading.length());
        logVerbose(heading);
        logVerbose(underline);
    }

    void logElementTestResult(boolean didItPass,
                              String passMessage,
                              String failMessage) {
        if (didItPass) {
            logVerbose(passMessage);
        } else {
            logVerbose(failMessage);
        }
        logVerbose("");
    }
    
    //////////////////////////////////////////////////
    // Static Methods for use in subclasses
    //////////////////////////////////////////////////
    
    static String sumString(CommutativeMonoidElement e1,
                                    CommutativeMonoidElement e2) {
        return (e1 + " + " + e2 + " = " + e1.addTo(e2));
    }

    static String productString(CommutativeRingElement e1,
                                        CommutativeRingElement e2) {
        return (e1 + " * " + e2 + " = " + e1.multiplyBy(e2));
    }

    static String addLeftFirstString(CommutativeMonoidElement e1,
                                             CommutativeMonoidElement e2,
                                             CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2).addTo(e3);
        return ("(" + e1 + " + " + e2 + ") + " + e3 + " = " + sum);
    }

    static String addRightFirstString(CommutativeMonoidElement e1,
                                              CommutativeMonoidElement e2,
                                              CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2.addTo(e3));
        return (e1 + " + (" + e2 + " + " + e3 + ") = " + sum);
    }

    static String multiplyLeftFirstString(CommutativeMonoidElement e1,
                                                  CommutativeMonoidElement e2,
                                                  CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2).addTo(e3);
        return ("(" + e1 + " * " + e2 + ") * " + e3 + " = " + sum);
    }

    static String multiplyRightFirstString(CommutativeMonoidElement e1,
                                                   CommutativeMonoidElement e2,
                                                   CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2.addTo(e3));
        return (e1 + " * (" + e2 + " * " + e3 + ") = " + sum);
    }
    
    //////////////////////////////////////////////////
    // Private Methods
    //////////////////////////////////////////////////

    private void log(String message) {
        log.println(message);
    }

    private void logSkip() {
        log(NEWLINE);
    }

    private void logStartOfTesting() {
        logBigHeadline(START_ALL_TEST_MESSAGE_STRING + totalTests);
    }

    private void logTestingSummary() {
        logBigHeadline(SUMMARY_OF_RESULTS_STRING);
        boolean overallResult = true;
        for (int i = 0; i < testResults.size(); i++) {
            overallResult = overallResult && testResults.get(i).passed();
            log(testResults.get(i).testResultLogEntry());
        }
        logSkip();
        if (overallResult) {
            log(ALL_TESTS_PASSED_MESSAGE_START +
                totalTests +
                ALL_TESTS_PASSED_MESSAGE_END);
        } else {
            log(TESTS_FAILED_MESSAGE);
        }
    }

    private void logHeadline(String message) {
        log(makeWideHeadline(message));
    }

    private void logBigHeadline(String message) {
        String headline = makeWideHeadline(message);
        String horizontalRule = repeatForLength(HEADER_SPACING_CHARACTER,
                                                headline.length());
        log(horizontalRule);
        log(headline);
        log(horizontalRule);
        logSkip();
    }

    private void logTestResult(TestResult result) {
        log(result.testResultLogEntry());
        logSkip();
    }

    private static String makeWideHeadline(String message) {
        return HEADER_INDENTATION_STRING + WHITESPACE +
               message +
               WHITESPACE + HEADER_INDENTATION_STRING;
    }

    private static String repeatForLength(String character, int length) {
        String line = "";
        for (int i = 0; i < length; i++) {
            line = line + character;
        }
        return line;
    }
    
    //////////////////////////////////////////////////
    // Private Static Strings (for logging)
    //////////////////////////////////////////////////
    
    private static String WHITESPACE = " ";
    private static String NEWLINE = "\n";
    private static String VERBOSE_INDENT = "||";
    private static String TEST_NUMBER_HEADING = "Test Number ";
    private static String TEST_UNDERLINE_CHARACTER = "-";
    private static String HEADER_SPACING_CHARACTER = "*";
    private static String HEADER_INDENTATION_STRING =
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER;
    private static String START_ALL_TEST_MESSAGE_STRING =
        "TESTING AXIOMS" +
        WHITESPACE + HEADER_SPACING_CHARACTER + WHITESPACE +
        "Total Tests:" + WHITESPACE;
    private static String SUMMARY_OF_RESULTS_STRING =
        WHITESPACE + "SUMMARY OF RESULTS" + WHITESPACE;
    private static String ALL_TESTS_PASSED_MESSAGE_START =
        "All tests have passed.  Each axiom was tested with randomly generated\n" +
        "elements a total of ";
    private static String ALL_TESTS_PASSED_MESSAGE_END =
        " times and the axioms held in all of those\n" +
        "tests.  This is not a guarantee that all the axioms hold in general;\n" +
        "all we can say is that the tests did not find an example where they\n" +
        "fail to hold.";
    private static String TESTS_FAILED_MESSAGE =
        "One or more of the tests failed.  See the previous entries in the log\n" +
        "for more details on which axioms failed.";
    private static String TEST_HEADER_PREFIX = "Running" + WHITESPACE;
    
    //////////////////////////////////////////////////
    // A private inner class used for bookkeeping.
    //////////////////////////////////////////////////

    class TestResult {
        private String testName;
        private boolean passed;
        private String counterExample;
    
        TestResult(String testName, boolean passed) {
            this.testName = testName;
            this.passed = passed;
            counterExample = null;
        }
    
        boolean passed() {
            return passed;
        }
    
        void setCounterExample(Element[] list) {
            counterExample = "";
            for (int i = 0; i < list.length; i++) {
                counterExample = counterExample + list[i];
                if (i < (list.length - 1)) {
                    counterExample = counterExample + ", ";
                }
            }
        }
    
        String testResultLogEntry() {
            String logEntry = "";
            if (passed) {
                logEntry = "(---PASSED---) " + testName;
            } else {
                logEntry = "(***FAILED***) " + testName;
                if (counterExample != null) {
                    logEntry = logEntry +
                        "\n\t[Counter Example :: " + counterExample + "]"    ;
                }
            }
            return logEntry;
        }
    }
}
