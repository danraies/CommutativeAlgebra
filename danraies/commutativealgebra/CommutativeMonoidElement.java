package danraies.commutativealgebra;

/**
 * <p>
 * Implement <code>CommutativeMonoidElement</code> when you want to model a
 * commutative monoid.  Instances of this interface are meant to be elements
 * of such a monoid.  If <code>MElement</code> implements 
 * <code>CommutativeMonoidElement</code> then instances of <code>MElement</code>
 * are meant to satisfy the following axioms:
 * </p>
 * <dl>
 * <dt>Closure</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>MElement</code> then <code>m1.addTo(m2)</code> is also an instance of
 *     <code>MElement</code>.</dd>
 * <dt>Commutivity</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>MElement</code> then 
 *     <code>m1.addTo(m2).equals(m2.addTo(m1))</code> returns 
 *     <code>true</code>.</dd>
 * <dt>Identity</dt>
 * <dd>There exists some object <code>zero</code> which is an instance of
 *     <code>MElement</code> such that <code>zero.isZero()<code> evaluates to
 *     <code>true</code>.  If <code>m</code> is any other instance of
 *     <code>MElement</code> then <code>m.addTo(zero).equals(m)</code> returns
 *     <code>true</code>.</dd>
 * <dt>Associativity</dt>
 * <dd>If <code>m1</code>, <code>m2</code>, and <code>m3</code> are all instances
 *     of <code>MElement</code>, if 
 *     <code>sum1 = m1.addTo(m2.addTo(m3))</code> and
 *     <code>sum2 = m1.addTo(m2).addTo(m3)</code> then it follows that
 *     <code>sum1.equals(sum2)</code> returns <code>true</code>.</dd>
 * </dl>
 * <p>
 * The interface doesn't have a way to check that these methods are coded correctly;
 * it is the programmers job to ensure that they satisfy the axioms.  However, by
 * writing a <code>CommutativeMonoidFactory</code> one can run some tests.
 * </p>
 *
 * @author Dan Raies
 */
public interface CommutativeMonoidElement extends Element {
    /**
     * Commutative monoids are required to have a zero element, i.e. an additive
     * identity.  This method is used to recognize if a given instance is equal
     * to zero.  Be careful about type checking when implementing this method.
     *
     * @return This method should return <code>true</code> if this element is
     *         zero and <code>false</code> if not.
     */
    public boolean isZero();

    /**
     * Commutative monoids are required to have an addition operation.  This
     * method implements the operation.
     *
     * @param e Any other instance of <code>CommutativeMonoidElement</code>.
     * @return The sum of <code>this</code> and <code>e</code>.
     */
    public CommutativeMonoidElement addTo(CommutativeMonoidElement e);
}
