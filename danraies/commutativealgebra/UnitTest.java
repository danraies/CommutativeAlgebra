package danraies.commutativealgebra;

abstract class UnitTest {
    String testName = "Test";
    int numberOfTests = 100;
    FactoryLogger log;
    private boolean logWasSet = false;

    UnitTest(String testName, int numberOfTests, FactoryLogger log) {
        setTestName(testName);
        setNumberOfTestsToRun(numberOfTests);
        setLog(log);
    }
    
    abstract TestResult runTest(ElementFactory factory);

    void setTestName(String testName) {
        this.testName = testName;
    }

    void setNumberOfTestsToRun(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    void setLog(FactoryLogger log) {
        this.log = log;
        logWasSet = true;
    }

    TestResult run(ElementFactory factory) {
        if (!logWasSet) {
            throw new RuntimeException(LOG_NOT_SET_MESSAGE);
        }
        log.announceAxiomCheck(testName);
        TestResult result = runTest(factory);
        log.logAxiomResult(result);
        return result;
    }

    private static String LOG_NOT_SET_MESSAGE = "You must set a log.";
}
