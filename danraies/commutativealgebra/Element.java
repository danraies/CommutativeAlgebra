package danraies.commutativealgebra;

/**
 * <p>
 * This is an interface which is meant to be implemented when instances of a class
 * represet elements in a set.  For the purposes of this project, one should not
 * normally extend <code>Element</code> directly.  It is more useful to implement
 * one of the other interfaces in this package.
 * </p>
 * <p>
 * The idea in this package is that if you want to model a set X which has some
 * algebraic structure then you create a class called <code>XElement</code> which
 * implements an interface that best describes the structure.
 * </p>
 * <p>
 * In general, it is best to implement the interface with the most structure.  For
 * example, if you want to study the integers as an abelian group with this package
 * you might be able to write a class called <code>IntegerElement</code> which
 * implements <code>AbelianGroupElement</code>.  However, the integers have the
 * structure of a ring so it is going to be more effective to implement the
 * <code>RingElement</code> interface.  You can still use all the functionality
 * of the <code>AbelianGroup</code> interface and the computations available to it
 * but if, in the future, you decide that you want to use the ring structure as well
 * then you don't need to write a new class.
 * </p>
 *
 * @author Dan Raies
 */
public interface Element {
    /**
     * The <code>Element</code> interface requires an override of the
     * <code>toString()</code> method from the <code>Object</code> class.
     * 
     * @return A string which represents this <code>Element</code> object.
     */
    public String toString();

    /**
     * The <code>Element</code> interface requires an override of the
     * <code>equals(Object)</code> method from the <code>Object</code> class.
     *
     * @param o Any <code>Object</code> object.
     * @return This should return true if they're equal as elements in the algebraic
     *         structure that you're trying to model.
     */
    public boolean equals(Object o);
}
