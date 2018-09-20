package danraies.commutativealgebra;

abstract class AxiomTest {
    private boolean elementsRequiredWasSet = false;
    private int elementsRequired;
    private boolean testNameWasSet = false;
    private String testName;
    private boolean logWasSet = false;
    private FactoryLogger log;

    AxiomTest(int elementsRequired, String testName, FactoryLogger log) {
        setNumberOfElementsRequired(elementsRequired);
        setTestName(testName);
        setLog(log);
    }

    void setNumberOfElementsRequired(int elementsRequired) {
        this.elementsRequired = elementsRequired;
        elementsRequiredWasSet = true;
    }

    void setTestName(String testName) {
        this.testName = testName;
        testNameWasSet = true;
    }

    void setLog(FactoryLogger log) {
        this.log = log;
        logWasSet = true;
    }
    
    abstract boolean testElements(Element[] list);

    TestResult runTests(int numberOfTests, ElementFactory factory) {
        if ((!elementsRequiredWasSet) || (!testNameWasSet) || (!logWasSet)) {
            throw new RuntimeException(VARIABLES_NOT_SET_MESSAGE);
        }
        log.announceAxiomCheck(testName);
        int testCounter = 1;
        boolean passedSoFar = true;
        Element[] listOfElements = new Element[elementsRequired];
        while ((passedSoFar) && (testCounter <= numberOfTests)) {
            for (int i = 0; i < listOfElements.length; i++) {
                listOfElements[i] = factory.getRandom();
            }
            boolean passedThisCheck = testElements(listOfElements);
            log.logIndividualCheck(testCounter, listOfElements, passedThisCheck);
            passedSoFar = passedSoFar && passedThisCheck;
            testCounter++;
        }
        return new TestResult(testName, passedSoFar, listOfElements);
    }

    private static String VARIABLES_NOT_SET_MESSAGE =
        "Either the log, the name of the test, or the number of elements required" +
        "was not set.";
}
