package danraies.commutativealgebra;

final class AxiomTestMultiplicativeCommutivity extends AxiomTest {
    private static String TEST_NAME = "Multiplicative Commutivity";

    AxiomTestMultiplicativeCommutivity(int numberOfTests, FactoryLogger log) {
        super(2, numberOfTests, TEST_NAME, log);
    }

    boolean testElements(Element[] list) {
        CommutativeRingElement e1 = (CommutativeRingElement) list[0];
        CommutativeRingElement e2 = (CommutativeRingElement) list[1];
        CommutativeRingElement product1 = e1.multiplyBy(e2);
        CommutativeRingElement product2 = e2.multiplyBy(e1);
        return product1.equals(product2);
    }
}
