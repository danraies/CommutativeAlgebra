package danraies.commutativealgebra;

final class OneEqualsOneTest extends UnitTest {
    CommutativeRingElement possibleOne;

    OneEqualsOneTest(CommutativeRingElement possibleOne,
                     FactoryLogger log) {
        super(TEST_NAME, log);
        this.possibleOne = possibleOne;
    }

    boolean runTest(ElementRandomizer r) {
        // r is not needed for this one.
        Element[] possibleCounterExample = {possibleOne};
        setPossibleCounterExample(possibleCounterExample);
        return possibleOne.isOne();
    }

    private static String TEST_NAME = "One Equals One";
}
