package danraies.commutativealgebra;

final class AxiomTestMultiplicativeInverse extends AxiomTest {
    private static String TEST_NAME = "Multiplicative Inverse";

    AxiomTestMultiplicativeInverse(int numberOfTests, FactoryLogger log) {
        super(1, numberOfTests, TEST_NAME, log);
    }

    boolean testElements(Element[] list) {
        FieldElement x = (FieldElement) list[0];
        if (x.isZero()) {
            return true;
        }
        CommutativeRingElement possibleOne1 = x.inverse().multiplyBy(x);
        CommutativeRingElement possibleOne2 = x.multiplyBy(x.inverse());
        return possibleOne1.isOne() && possibleOne2.isOne();
    }
}
