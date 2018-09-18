package danraies.commutativealgebra;

/**
 * <p>
 * Implement an <code>AbelianGroupElement</code> when you want to model an
 * abelian group.  Instances of this interface are meant to be elements of such a
 * group.  If <code>AElement</code> implements
 * <code>AbelianGroupElement</code> then instances of <code>AElement</code> are
 * meant to satisfy the following axioms:
 * </p>
 * <dl>
 * <dt>Closure</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>AElement</code> then <code>m1.addTo(m2)</code> is also an instance of
 *     <code>AElement</code>.</dd>
 * <dt>Commutivity</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>AElement</code> then 
 *     <code>m1.addTo(m2).equals(m2.addTo(m1))</code> returns 
 *     <code>true</code>.</dd>
 * <dt>Identity</dt>
 * <dd>There exists some object <code>zero</code> which is an instance of
 *     <code>AElement</code> such that <code>zero.isZero()<code> evaluates to
 *     <code>true</code>.  If <code>m</code> is any other instance of
 *     <code>AElement</code> then <code>m.addTo(zero).equals(m)</code> returns
 *     <code>true</code>.</dd>
 * <dt>Associativity</dt>
 * <dd>If <code>m1</code>, <code>m2</code>, and <code>m3</code> are all instances
 *     of <code>AElement</code>, if 
 *     <code>sum1 = m1.addTo(m2.addTo(m3))</code> and
 *     <code>sum2 = m1.addTo(m2).addTo(m3)</code> then it follows that
 *     <code>sum1.equals(sum2)</code> returns <code>true</code>.</dd>
 * <dt>Inverses</dt>
 * <dd>If <code>a</code> is an instance of <code>AElement</code> then there must
 *     exist an element <code>a.negative()</code> such that 
 *     <code>a.negative().addTo(a).isZero()</code> returns <code>true</code>.</dd>
 * </dl>
 * <p>
 * The interface doesn't have a way to check that these methods are coded correctly;
 * it is the programmers job to ensure that they satisfy the axioms.  However, by
 * writing a <code>AbelianGroupFactory</code> one can run some tests.
 * </p>
 *
 * @author Dan Raies
 */
public interface AbelianGroupElement extends CommutativeMonoidElement {
    /**
     * Elements of an abelian group are required to have negatives.  This method
     * implements negation.
     *
     * @return The negative of this <code>AbelianGroupElement</code>.
     */
    public AbelianGroupElement negative();
}
