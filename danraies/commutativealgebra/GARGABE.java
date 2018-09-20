
    
    //////////////////////////////////////////////////
    // Static Methods for use in subclasses
    //////////////////////////////////////////////////

    /**
     * This formats a nice string for the sum of two elements.
     *
     * @param e1 Any <code>CommutativeMonoidElement</code>.
     * @param e2 Any <code>CommutativeMonoidElement</code>.
     * @return A formatted string showing the sum of the parameters.
     */
    static String sumString(CommutativeMonoidElement e1,
                            CommutativeMonoidElement e2) {
        return (e1 + " + " + e2 + " = " + e1.addTo(e2));
    }

    /**
     * This formats a nice string for the product of two elements.
     *
     * @param e1 Any <code>CommutativeRingElement</code>.
     * @param e2 Any <code>CommutativeRingElement</code>.
     * @return A formatted string showing the product of the parameters.
     */
    static String productString(CommutativeRingElement e1,
                                CommutativeRingElement e2) {
        return (e1 + " * " + e2 + " = " + e1.multiplyBy(e2));
    }

    /**
     * This formats a nice string for the sum of two elements which is associated
     * so that the left sum is performed first.
     *
     * @param e1 Any <code>CommutativeMonoidElement</code>.
     * @param e2 Any <code>CommutativeMonoidElement</code>.
     * @param e3 Any <code>CommutativeMonoidElement</code>.
     * @return A formatted string showing the sum of the parameters which is
     *         associated so that the left sum is performed first.
     */
    static String addLeftFirstString(CommutativeMonoidElement e1,
                                     CommutativeMonoidElement e2,
                                     CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2).addTo(e3);
        return ("(" + e1 + " + " + e2 + ") + " + e3 + " = " + sum);
    }

    /**
     * This formats a nice string for the sum of two elements which is associated
     * so that the right sum is performed first.
     *
     * @param e1 Any <code>CommutativeMonoidElement</code>.
     * @param e2 Any <code>CommutativeMonoidElement</code>.
     * @param e3 Any <code>CommutativeMonoidElement</code>.
     * @return A formatted string showing the sum of the parameters which is
     *         associated so that the right sum is performed first.
     */
    static String addRightFirstString(CommutativeMonoidElement e1,
                                      CommutativeMonoidElement e2,
                                      CommutativeMonoidElement e3) {
        CommutativeMonoidElement sum = e1.addTo(e2.addTo(e3));
        return (e1 + " + (" + e2 + " + " + e3 + ") = " + sum);
    }

    /**
     * This formats a nice string for the product of two elements which is 
     * associated so that the left product is performed first.
     *
     * @param e1 Any <code>CommutativeRingElement</code>.
     * @param e2 Any <code>CommutativeRingElement</code>.
     * @param e3 Any <code>CommutativeRingElement</code>.
     * @return A formatted string showing the product of the parameters which is
     *         associated so that the left product is performed first.
     */
    static String multiplyLeftFirstString(CommutativeRingElement e1,
                                          CommutativeRingElement e2,
                                          CommutativeRingElement e3) {
        CommutativeMonoidElement product = e1.multiplyBy(e2).multiplyBy(e3);
        return ("(" + e1 + " * " + e2 + ") * " + e3 + " = " + product);
    }

    /**
     * This formats a nice string for the product of two elements which is 
     * associated so that the right product is performed first.
     *
     * @param e1 Any <code>CommutativeRingElement</code>.
     * @param e2 Any <code>CommutativeRingElement</code>.
     * @param e3 Any <code>CommutativeRingElement</code>.
     * @return A formatted string showing the product of the parameters which is
     *         associated so that the right product is performed first.
     */
    static String multiplyRightFirstString(CommutativeRingElement e1,
                                           CommutativeRingElement e2,
                                           CommutativeRingElement e3) {
        CommutativeMonoidElement product = e1.multiplyBy(e2.multiplyBy(e3));
        return (e1 + " * (" + e2 + " * " + e3 + ") = " + product);
    }
    
    //////////////////////////////////////////////////
    // Logging Methods with Default Access
    //////////////////////////////////////////////////

    /**
     * Verbose logs are only printed if logVerboseOutput is set to
     * <code>true</code>.  This is meant to be used to print the details of the
     * individual element tests.
     *
     * @param message Some message to log.
     */
    void logVerbose(String message) {
        if (logVerboseOutput) {
            log(VERBOSE_INDENT + message);
        }
    }

    // An "axiom test" is considered the test of one entire axiom.
    // Axiom tests involve a series of repeated, randomized element
    // tests.

    /**
     * This method logs the beginning of the series of tests designed to check a
     * single axiom.
     *
     * @param testName The name of the test that is about to be run.
     */
    void logAxiomTestStart(String testName) {
        logHeadline(TEST_HEADER_PREFIX + testName);
    }

    /**
     * This method logs the result of the series of tests designed to check a single
     * axiom.
     * 
     * @param testName The name of the test that was run.
     * @param testName The result of the test.  This should be <code>true</code>
     *        if the test passed and <code>false</code> if it failed.
     */
    void logAxiomTestResult(String testName, boolean didItPass) {
        TestResult result = new TestResult(testName, didItPass);
        logTestResult(result);
        testResults.add(result);
    }

    /**
     * This method logs the result of the series of tests designed to check a single
     * axiom.  This method allows the user to pass a counterexample in the form of
     * a list of <code>Element</code> objects.  If the axiom that is being tested
     * fails then the list provided by <code>counterExampleElementsList</code>
     * should contain elements for which the axiom doesn't hold.  Note that this
     * method works even when the test passes.  The list passed to
     * <code>counterExampleElementsList</code> is used only in the event that
     * <code>didItPass</code> is <code>false</code>.
     *
     * It may seem counterintuitive, but this is the way that works best in the
     * package.  The way that the routines in my code work, a series of tests are
     * run in a loop which exits if either (i) the loop runs a maximum number of
     * times and never fails or (ii) the loop finds a set of elements which causes
     * the axiom to fail.  Once the loop exits we can then pass the result of the
     * test, the name of the test, and the last set of elements that the loop used
     * to this method.  If the test passed then the list of elements is meaningless
     * and is never used but if the thest fails then the list of elements is a
     * working counterexample.
     * 
     * @param testName The name of the test that was run.
     * @param didItPass The result of the test.  This should be <code>true</code>
     *        if the test passed and <code>false</code> if it failed.
     * @param counterExampleElementsList 
     */
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

    /**
     * Logs the start of a test of a single set of elements against an axiom. The
     * message will only show if verbose output is shown.
     *
     * @param testCounter An axiom test runs a series of consecutive tests.  This
     *        parameter reflects the number of the individual test.
     */
    void logElementTestStart(int testCounter) {
        String heading = TEST_NUMBER_HEADING + testCounter;
        String underline = repeatForLength(TEST_UNDERLINE_CHARACTER,
                                           heading.length());
        logVerbose(heading);
        logVerbose(underline);
    }
    
    /**
     * Logs the result of a test of a single set of elements against an axiom.  The
     * message will only show if verbose output is shown.  This method is used to
     * log both a pass or a fail; this prevents a lot of gross if-then statements.
     *
     * @param didItPass The result of the test.  Pass <code>true</code> to this
     *        parameter if the test passes and pass <code>false</code> if it fails.
     * @param passMessage The message to log if the test passes.
     * @param failMessage The message to log if the test fails.
     */
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
    // Private Methods
    //////////////////////////////////////////////////

    /**
     * Writes a message to the log.
     *
     * @param message Any message.
     */
    private void log(String message) {
        log.println(message);
    }

    /**
     * Writes a large skip in the log.
     */
    private void logSkip() {
        log(NEWLINE);
    }

    /**
     * Writes a message at the beginning of the log to indicate that the log starts.
     */
    private void logStartOfTesting() {
        logBigHeadline(START_ALL_TEST_MESSAGE_STRING + totalTests);
    }

    /**
     * Writes a summary at the end of the log.
     */
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

    /**
     * Logs a big, pretty title.
     * 
     * @param message The message that gets printed in the title.
     */
    private void logBigHeadline(String message) {
        String headline = makeWideHeadline(message);
        String horizontalRule = repeatForLength(HEADER_SPACING_CHARACTER,
                                                headline.length());
        log(horizontalRule);
        log(headline);
        log(horizontalRule);
        logSkip();
    }

    /**
     * Writes a smaller, pretty title.  This is for something like a subsection.
     */
    private void logHeadline(String message) {
        log(makeWideHeadline(message));
    }

    /**
     * Logs the result of an axiom test.  The <code>TestResult</code> class is a
     * private, internal class which holds the title, result, and potentially a
     * counterexample to the axiom.
     *
     * @param result The result of an axiom test.
     */
    private void logTestResult(TestResult result) {
        log(result.testResultLogEntry());
        logSkip();
    }

    /**
     * Makes a nice, pretty headline out of a message.
     *
     * @param message Any <code>String</code> to format.
     * @return The parameter formatted as a headline.
     */
    private static String makeWideHeadline(String message) {
        return HEADER_INDENTATION_STRING + WHITESPACE +
               message +
               WHITESPACE + HEADER_INDENTATION_STRING;
    }
    
    /**
     * Repeats a string a desired number of times.
     *
     * @param character The string to repeat.  In practice, this is a single
     *        character but it works fine with any string.
     * @param length The number of times to repeat the other parameter.  In 
     *        practice this is the length of the returned string because the
     *        other parameter is usually a single character.  However, the
     *        length parameter dictates how many times to repeat the string.
     * @return The given string repeated the given number of times.
     */
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

    /** The whitespace character. */
    private static String WHITESPACE = " ";

    /** The newline character. */
    private static String NEWLINE = "\n";

    /** The string used to indent verbose log entries. */
    private static String VERBOSE_INDENT = "||";

    /** The string used when heading a log entry for a test number. */
    private static String TEST_NUMBER_HEADING = "Test Number ";

    /** The character used to make an underline for test number headings. */
    private static String TEST_UNDERLINE_CHARACTER = "-";

    /** The character used to make pretty headings. */
    private static String HEADER_SPACING_CHARACTER = "*";

    /** The string used on the left and right edge of headings. */
    private static String HEADER_INDENTATION_STRING =
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER;

    /** The string used to announce the start of the log. */
    private static String START_ALL_TEST_MESSAGE_STRING =
        "TESTING AXIOMS" +
        WHITESPACE + HEADER_SPACING_CHARACTER + WHITESPACE +
        "Total Tests:" + WHITESPACE;

    /** The string used to announce the summary of the results at the end of
        the log.*/
    private static String SUMMARY_OF_RESULTS_STRING =
        WHITESPACE + "SUMMARY OF RESULTS" + WHITESPACE;

    /** The first half of a message used to announce that all tests have passed. */
    private static String ALL_TESTS_PASSED_MESSAGE_START =
        "All tests have passed.  Each axiom was tested with randomly generated\n" +
        "elements a total of ";

    /** The second half of a message used to announce that all tests have passed. */
    private static String ALL_TESTS_PASSED_MESSAGE_END =
        " times and the axioms held in all of those\n" +
        "tests.  This is not a guarantee that all the axioms hold in general;\n" +
        "all we can say is that the tests did not find an example where they\n" +
        "fail to hold.";

    /** The message used if the tests have failed. */
    private static String TESTS_FAILED_MESSAGE =
        "One or more of the tests failed.  See the previous entries in the log\n" +
        "for more details on which axioms failed.";

    /** The string used in the message announcing that a single element test is
        starting.*/
    private static String TEST_HEADER_PREFIX = "Running" + WHITESPACE;
    
    //////////////////////////////////////////////////
    // A private inner class used for bookkeeping.
    //////////////////////////////////////////////////
