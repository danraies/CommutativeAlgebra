package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.ArrayList;

final class FactoryLogger {
    boolean showVerboseOutput = true;
    PrintStream stream = System.out;

    void shouldShowVerboseOutput(boolean showVerboseOutput) {
        this.showVerboseOutput = showVerboseOutput;
    }

    void setPrintStream(PrintStream stream) {
        this.stream = stream;
    }
    
    void announceStart() {
        logBigHeadLine(LOG_START_MESSAGE);
    }

    void announceEnd() {
        logSkip();
        logBigHeadLine(LOG_SUMMARY_MESSAGE);
    }

    void announceAxiomCheck(String testName, int intendedNumberOfChecks) {
        logSkip();
        String[] heading = {TEST_NAME_PREFIX + testName};
        log(addIndents(heading));
        log(TESTING_STARTED_MESSAGE);
        log(INTENDED_NUMBER_PREFIX + intendedNumberOfChecks);
    }

    void logResultSummary(String testName, boolean testResult) {
        String lineToLog = testName + TEST_RESULT_SEPERATOR + WHITESPACE;
        if (testResult) {
            lineToLog = lineToLog + TEST_PASSED_MESSAGE;
        } else {
            lineToLog = lineToLog + TEST_FAILED_MESSAGE;
        }
        log(lineToLog);
    }

    void logAxiomResult(String testName,
                        boolean testResult,
                        int actualNumberOfChecks) {
        log(TESTING_COMPLETE_MESSAGE);
        log(ACTUAL_CHECKS_PREFIX + actualNumberOfChecks);
        logResultSummary(testName, testResult);
    }

    void logCounterExample(Element[] listOfElements) {
        log(COUNTER_EXAMPLE_PREFIX + formatElements(listOfElements));
    }

    void logIndividualCheck(int testCounter,
                            Element[] listOfElements,
                            boolean passedThisCheck) {
        String titleLine = INDIVIDUAL_CHECK_PREFIX + testCounter;
        String underline = repeatForLength(TEST_UNDERLINE_CHARACTER,
                                           titleLine.length());
        logVerbose(titleLine);
        logVerbose(underline);
        logVerbose(ELEMENTS_TESTED_PREFIX + formatElements(listOfElements));
        String passOrFailLine = THIS_TEST_HAS;
        if (passedThisCheck) {
            passOrFailLine = passOrFailLine + PASSED;
        } else {
            passOrFailLine = passOrFailLine + FAILED;
        }
        logVerbose(passOrFailLine);
        logVerbose("");
    }

    void close() {
        stream.close();
    }
    
    //////////////////////////////////////////////////
    // Private methods
    //////////////////////////////////////////////////

    private void log(String message) {
        stream.println(message);
    }

    private void log(String[] messages) {
        for (int i = 0; i < messages.length; i++) {
            log(messages[i]);
        }
    }

    private void logSkip() {
        log(NEWLINE);
    }

    /**
     * Verbose logs are only printed if logVerboseOutput is set to
     * <code>true</code>.  This is meant to be used to print the details of the
     * individual element tests.
     *
     * @param message Some message to log.
     */
    private void logVerbose(String message) {
        if (showVerboseOutput) {
            log(VERBOSE_INDENT + message);
        }
    }

    private void logBigHeadLine(String[] messages) {
        String[] formattedMessages = addIndents(messages);
        String rule = repeatForLength(HEADER_SPACING_CHARACTER,
                                      formattedMessages[0].length());
        log(rule);
        log(formattedMessages);
        log(rule);
    }

    private void logBigHeadLine(String message) {
        String[] messages = {message};
        logBigHeadLine(messages);
    }

    private String formatElements(Element[] elements) {
        String returnValue = "";
        for (int i = 0; i < elements.length; i++) {
            returnValue = returnValue + elements[i];
            if (i < (elements.length - 1)) {
                returnValue = returnValue + ELEMENT_DELIMITER + WHITESPACE;
            }
        }
        return returnValue;
    }

    private String[] addIndents(String[] messages) {
        int longestMessage = 0;
        for (int i = 0; i < messages.length; i++) {
            if (messages[i].length() > longestMessage) {
                longestMessage = messages[i].length();
            }
        }
        String line;
        String[] formattedMessages = new String[messages.length];
        for (int i = 0; i < messages.length; i++) {
            line = LEFT_INDENT + messages[i];
            if (messages[i].length() < longestMessage) {
                String whitespace =
                    repeatForLength(WHITESPACE,
                                    longestMessage - messages[i].length());
                line = line + whitespace;
            }
            line = line + RIGHT_INDENT;
            formattedMessages[i] = line;
        }
        return formattedMessages;
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
    // Private Strings
    //////////////////////////////////////////////////

    private static String WHITESPACE = " ";
    private static String NEWLINE = "\n";
    private static String VERBOSE_INDENT = "||";
    private static String ELEMENT_DELIMITER = ",";
    private static String TEST_UNDERLINE_CHARACTER = "-";
    private static String HEADER_SPACING_CHARACTER = "*";
    private static String TEST_RESULT_SEPERATOR = ":";
    private static String LEFT_INDENT =
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER +
        WHITESPACE;
    private static String RIGHT_INDENT =
        WHITESPACE +
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER +
        HEADER_SPACING_CHARACTER;
    private static String TEST_NAME_PREFIX = "Test: ";
    private static String PASSED = "passed";
    private static String FAILED = "FAILED";
    private static String TEST_PASSED_MESSAGE = "(___" + PASSED + "___)";
    private static String TEST_FAILED_MESSAGE = "(***" + FAILED + "***)";
    private static String COUNTER_EXAMPLE_PREFIX = "\t\tCounter Example --> ";
    private static String INDIVIDUAL_CHECK_PREFIX = "Test Number ";
    private static String ELEMENTS_TESTED_PREFIX = "Elements Tested: ";
    private static String THIS_TEST_HAS = "This test has ";
    private static String TESTING_STARTED_MESSAGE = "Testing Started";
    private static String TESTING_COMPLETE_MESSAGE = "Testing Complete";
    private static String INTENDED_NUMBER_PREFIX = "Intended number of checks: ";
    private static String ACTUAL_CHECKS_PREFIX = "Number of checks ran: ";
    private static String[] LOG_START_MESSAGE = {"Logging Starting",
                                                 " Testing Axioms"};
    private static String[] LOG_SUMMARY_MESSAGE = {" Testing Complete",
                                                   "Summary of Results"};
}
