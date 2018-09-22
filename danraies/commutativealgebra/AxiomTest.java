package danraies.commutativealgebra;

abstract class AxiomTest extends UnitTest {
    private boolean elementsRequiredWasSet = false;
    private int elementsRequired;

    AxiomTest(int elementsRequired,
              String testName,
              int numberOfTests,
              FactoryLogger log) {
        super(testName, numberOfTests, log);
        setNumberOfElementsRequired(elementsRequired);
    }

    void setNumberOfElementsRequired(int elementsRequired) {
        this.elementsRequired = elementsRequired;
        elementsRequiredWasSet = true;
    }
    
    abstract boolean testElements(Element[] list);

    private boolean safelyTestElements(Element[] list) {
        if (list.length != elementsRequired) {
            throw new RuntimeException(WRONG_NUMBER_OF_ELEMENTS);
        }
        return testElements(list);
    }

    TestResult runTest(ElementFactory factory) {
        if (!elementsRequiredWasSet) {
            throw new RuntimeException(VARIABLES_NOT_SET_MESSAGE);
        }
        int testCounter = 1;
        boolean passedSoFar = true;
        Element[] listOfElements = new Element[elementsRequired];
        while ((passedSoFar) && (testCounter <= numberOfTests)) {
            for (int i = 0; i < listOfElements.length; i++) {
                listOfElements[i] = factory.getRandom();
            }
            boolean passedThisCheck = safelyTestElements(listOfElements);
            log.logIndividualCheck(testCounter, listOfElements, passedThisCheck);
            passedSoFar = passedSoFar && passedThisCheck;
            testCounter++;
        }
        TestResult result = new TestResult(testName, passedSoFar, listOfElements);
        return result;
    }

    private static String VARIABLES_NOT_SET_MESSAGE =
        "Either the log, the name of the test, or the number of elements required" +
        "was not set.";

    private static String WRONG_NUMBER_OF_ELEMENTS =
        "The wrong number of elements was used for this axiom.";
}
