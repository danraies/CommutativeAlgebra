package danraies.commutativealgebra;

abstract class UnitTest {
    private String testName = "Test";
    FactoryLogger log;
    private boolean logWasSet = false;
    private Element[] possibleCounterExample = null;
    private boolean testWasRun = false;
    private boolean testResult;
    private int intendedNumberOfChecks = 1;
    private int actualNumberOfChecks = 1;

    UnitTest(String testName, FactoryLogger log) {
        setTestName(testName);
        setLog(log);
    }
    
    abstract boolean runTest(ElementRandomizer r);

    final void run(ElementRandomizer r) {
        if (!logWasSet) {
            throw new RuntimeException(LOG_NOT_SET_MESSAGE);
        }
        log.announceAxiomCheck(testName, intendedNumberOfChecks);
        testResult = runTest(r);
        testWasRun = true;
        logResult();
    }

    final void setTestName(String testName) {
        this.testName = testName;
    }

    final void setLog(FactoryLogger log) {
        this.log = log;
        logWasSet = true;
    }

    final void setPossibleCounterExample(Element[] possibleCounterExample) {
        this.possibleCounterExample = possibleCounterExample;
    }

    final void setIntendedNumberOfChecks(int intendedNumberOfChecks) {
        this.intendedNumberOfChecks = intendedNumberOfChecks;
    }

    final int getIntendedNumberOfChecks() {
        return intendedNumberOfChecks;
    }

    final void setActualNumberOfChecks(int actualNumberOfChecks) {
        this.actualNumberOfChecks = actualNumberOfChecks;
    }

    final void logResultSummary() {
        if (!testWasRun) {
            throw new RuntimeException(TEST_WAS_NOT_RUN);
        }
        log.logResultSummary(testName, testResult);
        logPossibleCounterExample();
    }

    private void logResult() {
        if (!testWasRun) {
            throw new RuntimeException(TEST_WAS_NOT_RUN);
        }
        log.logAxiomResult(testName, testResult, actualNumberOfChecks);
        logPossibleCounterExample();
    }

    private void logPossibleCounterExample() {
        if ((!testResult) && (possibleCounterExample != null)) {
            log.logCounterExample(possibleCounterExample);
        }
    }

    private static String LOG_NOT_SET_MESSAGE = "You must set a log.";
    private static String TEST_WAS_NOT_RUN = "The test was not run.";
}
