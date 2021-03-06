package danraies.commutativealgebra;

final class AxiomTestAdditiveAssociativity extends AxiomTest {
    private static String TEST_NAME = "Additive Associativity";

    AxiomTestAdditiveAssociativity(int numberOfTests, FactoryLogger log) {
        super(3, numberOfTests, TEST_NAME, log);
    }

    boolean testElements(Element[] list) {
        CommutativeMonoidElement e1 = (CommutativeMonoidElement) list[0];
        CommutativeMonoidElement e2 = (CommutativeMonoidElement) list[1];
        CommutativeMonoidElement e3 = (CommutativeMonoidElement) list[2];
        CommutativeMonoidElement sum1 = e1.addTo(e2.addTo(e3));
        CommutativeMonoidElement sum2 = e1.addTo(e2).addTo(e3);
        return sum1.equals(sum2);
    }
}
