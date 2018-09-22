package danraies.commutativealgebra;

class AxiomTestAdditiveCommutivity extends AxiomTest {
    private static String TEST_NAME = "Additive Commutivity";

    AxiomTestAdditiveCommutivity(int numberOfTests, FactoryLogger log) {
        super(2, TEST_NAME, numberOfTests, log);
    }

    boolean testElements(Element[] list) {
        CommutativeMonoidElement e1 = (CommutativeMonoidElement) list[0];
        CommutativeMonoidElement e2 = (CommutativeMonoidElement) list[1];
        CommutativeMonoidElement sum1 = e1.addTo(e2);
        CommutativeMonoidElement sum2 = e2.addTo(e1);
        return e1.equals(e2);
    }
}
