package danraies.commutativealgebra;

final class ZeroEqualsZeroTest extends UnitTest {
    CommutativeMonoidElement possibleZero;
    
    ZeroEqualsZeroTest(CommutativeMonoidElement possibleZero,
                       FactoryLogger log) {
        super(TEST_NAME, log);
        this.possibleZero = possibleZero;
    }
    
    boolean runTest(ElementRandomizer r) {
        // r is not needed for this one.
        Element[] possibleCounterExample = {possibleZero};
        setPossibleCounterExample(possibleCounterExample);
        return possibleZero.isZero();
    }

    private static String TEST_NAME = "Zero Equals Zero";
}
