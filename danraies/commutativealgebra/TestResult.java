package danraies.commutativealgebra;

/**
 * This is a private class used to log and print results of axiom tests.
 * If you'd like the non-verbose entries in the log to be printed differently,
 * this is where you might want to change some things.
 */
class TestResult {
    /** The name of the test. */
    private String testName;
    
    /** The result of the test.  This should be true if the test passed and
        false if it fails. */
    private boolean passed;
    
    /** The string describing a counterexample to the test (if provided). */
    private String counterExample;
    
    /**
     * Constructs a <code>TestResult</code> object representing...the result
     * of a test.
     *
     * @param testName A name for the test which this object represents.
     * @param passed <code>true</code> if the test passed and <code>false</code>
     *        if the test failed.
     * @param possibleCounterExample A list of elements which represents a counter
     *        example to the test if the test fails.  If the test passes then this
     *        parameter is ignored.
     */
    TestResult(String testName, boolean passed, Element[] possibleCounterExample) {
        this.testName = testName;
        this.passed = passed;
        counterExample = "";
        for (int i = 0; i < possibleCounterExample.length; i++) {
            counterExample = counterExample + possibleCounterExample[i];
            if (i < (possibleCounterExample.length - 1)) {
                counterExample = counterExample + ", ";
            }
        }
    }
    
    /**
     * Returns the result of the test.
     *
     * @return <code>true</code> if the test passed and <code>false</code> if
     *         the test failed.
     */
    boolean passed() {
        return passed;
    }

    /**
     * Returns a log entry for this test.
     *
     * @return A string which is meant to be printed in the log for this
     *         test result.
     */
    String testResultLogEntry() {
        String logEntry = "";
        if (passed) {
            logEntry = "(---PASSED---) " + testName;
        } else {
            logEntry = "(***FAILED***) " + testName +
                "\n\t[Counter Example :: " + counterExample + "]"    ;
        }
        return logEntry;
    }
}
