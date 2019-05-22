package danraies.commutativealgebra;

final class AxiomTestMultiplicativeAssociativity extends AxiomTest {
    private static String TEST_NAME = "Multiplicative Associativity";

    AxiomTestMultiplicativeAssociativity(int numberOfTests, FactoryLogger log) {
        super(3, numberOfTests, TEST_NAME, log);
    }

    boolean testElements(Element[] list) {
        CommutativeRingElement e1 = (CommutativeRingElement) list[0];
        CommutativeRingElement e2 = (CommutativeRingElement) list[0];
        CommutativeRingElement e3 = (CommutativeRingElement) list[0];
        CommutativeRingElement product1 = e1.multiplyBy(e2.multiplyBy(e3));
        CommutativeRingElement product2 = e1.multiplyBy(e2).multiplyBy(e3);
        return product1.equals(product2);
    }
}
