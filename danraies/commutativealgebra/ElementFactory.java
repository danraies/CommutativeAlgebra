package danraies.commutativealgebra;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * <p>
 * Instances of children of this class are meant to be used to test the axioms
 * in an algebraic structure.  If one makes a class called <code>XElement</code>
 * which is meant to mimic the elements in an algebraic structure, then one can
 * make a class called <code>XElementFactory</code> which is capable of testing
 * the axioms that <code>XElement</code> objects are meant to satisfy.
 * </p>
 * <p>
 * For example, suppose <code>XElement</code> directly extends
 * <code>CommutativeRingElement</code>.  Then <code>XElementFactory</code> should
 * directly extend <code>CommutativeRingElementFactory</code>.  By implementing the
 * appropriate methods one can then run an arbitrary number of tests designed to
 * check the axioms required by a commutative ring.
 * </p>
 * <p>
 * There are two methods of importance to one who is trying to use one of these
 * factory classes for testing their algebraic structure.
 * </p>
 * <dl>
 * <dt>{@link #getRandom() getRandom}</dt>
 * <dd>When one implements an <code>XElementFactory</code>, it is this method that
 *     determines everything in the tests.  It should return a randomly generated
 *     <code>XElement</code>.  The methods used during the tests will use this
 *     method repeatedly to generate the elements that are tested.</dd>
 * <dt>{@link #testAxioms() testAxioms}</dt>
 * <dd>This method runs the test.  When one implements an
 *     <code>XElementFactory</code> class, running this method on an instance of
 *     that class will perform the tests.  By default, output is sent to
 *     <code>System.out</code>.</dd>
 * </dl>
 */
public abstract class ElementFactory {
    //////////////////////////////////////////////////
    // Private Instance Variables
    //////////////////////////////////////////////////

    /** This instance keeps track of the <code>FactoryLogger</code> object to which
        all logs are written. */
    FactoryLogger log = new FactoryLogger();

    /** This is the total number of element-wise tests that will be run when
        testing each individual axiom. */
    int totalTests = 100;

    /** This list holds all of the tests that are going to be run. */
    private ArrayList<UnitTest> testsToRun = new ArrayList<UnitTest>();
    
    //////////////////////////////////////////////////
    // Abstract Methods
    //////////////////////////////////////////////////

    /**
     * This is the method that a user must implement when creating a factory class
     * for testing purposes.  It should return a randomly generated instance of
     * the precise class that you're trying to test.
     * 
     * @return A randomly generated instance of the class 
     */
    public abstract Element getRandom();

    abstract void addAllTests();

    //////////////////////////////////////////////////
    // Public Methods
    //////////////////////////////////////////////////

    /**
     * This method tests the axioms of the structure that you're trying to analyze.
     * The intention is that one implements a class <code>XElement</code> that is
     * a subclass of <code>Element</code>.  Then one also implements a class
     * <code>XElementFactory</code> that is a subclass of
     * <code>ElementFactory</code> and the implementation of the
     * <code>getRandom</code> method always returns an instance of
     * <code>XElement</code>.  The instances of <code>XElement</code> are supposed
     * to satisfy some axioms and the <code>testAxioms</code> method runs a
     * number of tests which check those axioms.  Of course, passing a finite number
     * of tests does not guarantee that all instances of <code>XElement</code>
     * satisfy the axioms (that burden is on the coder) but a sufficiently large
     * number of tests can increase confidence.
     */
    public void testAxioms() {
        log.announceStart();
        for (int i = 0; i < testsToRun.size(); i++) {
            testsToRun.get(i).run(this);
        }
        log.logEnd();
    }

    /**
     * This method is used by abstract subclasses to add tests that should be run.
     *
     * @param aTest Some test that should be added to the list.
     */
    public void addTestToRun(UnitTest aTest) {
        testsToRun.add(aTest);
    }

    /**
     * Running the <code>testAxioms</code> method writes the results of the test
     * to a log.  (The log is a <code>PrintStream</code> object which is
     * <code>System.out</code> by default.  The <code>setLog</code> method can be
     * used to change the object to which the log is written.)  At times one may
     * wish to see only a summary of the tests and at other times one may wish to
     * see the details of every individual test.  Passing a value of
     * <code>true</code> to this method shows all of the details and passing a value
     * of <code>false</code> shows only the summary.  By default, the value is
     * <code>true</code>.
     *
     * @param logVerboseOutput A boolean value which determines if the details of
     *        testing are printed in the log.  Pass a value of <code>true</code>
     *        to log the details and a value of <code>false</code> to surpress them.
     *        By default this value is <code>true</code>.
     */
    public void shouldIncludeVerboseOutput(boolean logVerboseOutput) {
        log.shouldShowVerboseOutput(logVerboseOutput);
    }

    /**
     * The <code>testAxioms</code> method runs a series of tests and writes the
     * results to a <code>PrintStream</code> object.  By default, that object is
     * <code>System.out</code>.  This element is used to change it.
     *
     * @param log The <code>PrintStream</code> object to which the results of the
     *        <code>testAxioms</code> method are written.  By default this is
     *        <code>System.out</code>.
     */
    public void setLog(PrintStream stream) {
        log.setPrintStream(stream);
    }

    /**
     * By default the <code>testAxioms</code> method will run one hundred tests
     * for each axiom that is is checking.  Use this method to change that number.
     *
     * @param totalTests The number of tests that the <code>testAxioms</code> method
     *        runs when checking each of the axioms that it is designed to check.
     */
    public void setTotalNumberOfTests(int totalTests) {
        this.totalTests = totalTests;
    }
}
