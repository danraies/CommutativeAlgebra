package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.ArrayList;

public abstract class ElementFactory {
    private static String VERBOSE_INDENT = "||";
    private static String PASS_INDICATOR_MESSAGE = "(___passed___)";
    private static String FAIL_INDICATOR_MESSAGE = "(***failed***)";
    private static String TEST_NUMBER_HEADING = "Test Number ";
    private static String TEST_UNDERLINE_CHARACTER = "-";
    private static String BIG_HEADER_CHARACTER = "*";
    private static String STARTING_TEST_STRING =
        "TESTING AXIOMS " + BIG_HEADER_CHARACTER + " Total Tests: ";
    private static String SUMMARY_OF_RESULTS_STRING =
        "SUMMARY OF RESULTS";
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
    private static String TEST_HEADER_START = "*** Running ";
    private static String TEST_HEADER_END = " ***";
    
    private final boolean logVerboseOutput;
    private final PrintStream log;

    public ElementFactory() {
        logVerboseOutput = true;
        log = System.out;
    }
    
    public ElementFactory(boolean logVerboseOutput,
                          PrintStream log) {
        this.logVerboseOutput = logVerboseOutput;
        this.log = log;
    }

    void log(String message) {
        log.println(message);
    }

    void log(TestResult result) {
        log(result.testResultLogEntry());
        logSkip();
    }

    void logVerbose(String message) {
        if (logVerboseOutput) {
            log(VERBOSE_INDENT + message);
        }
    }

    void logSkip() {
        log("\n");
    }

    void logTestStart(String testName) {
        log(TEST_HEADER_START + testName + TEST_HEADER_END);
    }

    void logVerboseTestResult(boolean didItPass,
                              String passMessage,
                              String failMessage) {
        if (didItPass) {
            logVerbose(passMessage);
        } else {
            logVerbose(failMessage);
        }
        logVerbose("");
    }

    void logHeading(int totalTests) {
        String headLine =
            BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER
            + " " + STARTING_TEST_STRING + totalTests + " " +
            BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER;
        String horizontalRule = repeatForLength(BIG_HEADER_CHARACTER,
                                                headLine.length());
        log(horizontalRule);
        log(headLine);
        log(horizontalRule);
        logSkip();
    }

    boolean logSummary(ArrayList<TestResult> results, int totalTests) {
        String summaryLine = 
            BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER
            + " " + SUMMARY_OF_RESULTS_STRING + " " +
            BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER + BIG_HEADER_CHARACTER;
        String horizontalRule = repeatForLength(BIG_HEADER_CHARACTER,
                                                summaryLine.length());
        log(horizontalRule);
        log(summaryLine);
        log(horizontalRule);
        boolean overallResult = true;
        for (int i = 0; i < results.size(); i++) {
            overallResult = overallResult && results.get(i).passed();
            log(results.get(i).testResultLogEntry());
        }
        logSkip();
        if (overallResult) {
            log(ALL_TESTS_PASSED_MESSAGE_START +
                totalTests +
                ALL_TESTS_PASSED_MESSAGE_END);
        } else {
            log(TESTS_FAILED_MESSAGE);
        }
        return overallResult;
    }

    void logVerboseTestCounter(int testCounter) {
        String heading = TEST_NUMBER_HEADING + testCounter;
        String underline = repeatForLength(TEST_UNDERLINE_CHARACTER,
                                           heading.length());
        logVerbose(heading);
        logVerbose(underline);
    }
    
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

    private static String repeatForLength(String character, int length) {
        String line = "";
        for (int i = 0; i < length; i++) {
            line = line + character;
        }
        return line;
    }
}
