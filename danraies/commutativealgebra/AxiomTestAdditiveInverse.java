package danraies.commutativealgebra;

final class AxiomTestAdditiveInverse extends AxiomTest {
    private static String TEST_NAME = "Additive Inverse";
    
    AxiomTestAdditiveInverse(int numberOfTests, FactoryLogger log) {
        super(1, numberOfTests, TEST_NAME, log);
    }

    boolean testElements(Element[] list) {
        AbelianGroupElement g = (AbelianGroupElement) list[0];
        CommutativeMonoidElement possibleZero1 = g.negative().addTo(g);
        CommutativeMonoidElement possibleZero2 = g.addTo(g.negative());
        return possibleZero1.isZero() && possibleZero2.isZero();
    }
}
