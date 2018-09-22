package danraies.commutativealgebra;

class ZeroEqualsZeroTest extends UnitTest {
    CommutativeMonoidElement possibleZero;
    
    ZeroEqualsZeroTest(CommutativeMonoidElement possibleZero,
                       int numberOfTests,
                       FactoryLogger log) {
        super(TEST_NAME, numberOfTests, log);
        this.possibleZero = possibleZero;
    }
    
    TestResult runTest(ElementFactory factory) {
        // factory is not needed for this one.
        Element[] possibleCounterExample = {possibleZero};
        return new TestResult(TEST_NAME,
                              possibleZero.isZero(),
                              possibleCounterExample);
    }

    private static String TEST_NAME = "Zero Equals Zero";
}
