package danraies.commutativealgebra;

/**
 * <p>
 * Implement a <code>FieldElement</code> when you want to model a
 * field.  Instances of this interface are meant to be elements of such a
 * field.  If <code>FElement</code> implements
 * <code>FieldElement</code> then instances of <code>FElement</code> are
 * meant to satisfy the following axioms:
 * </p>
 * <dl>
 * <dt>Additive Closure</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>FElement</code> then <code>m1.addTo(m2)</code> is also an instance of
 *     <code>FElement</code>.</dd>
 * <dt>Additive Commutivity</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>FElement</code> then 
 *     <code>m1.addTo(m2).equals(m2.addTo(m1))</code> returns 
 *     <code>true</code>.</dd>
 * <dt>Additive Identity</dt>
 * <dd>There exists some object <code>zero</code> which is an instance of
 *     <code>FElement</code> such that <code>zero.isZero()<code> evaluates to
 *     <code>true</code>.  If <code>m</code> is any other instance of
 *     <code>FElement</code> then <code>m.addTo(zero).equals(m)</code> returns
 *     <code>true</code>.</dd>
 * <dt>Additive Associativity</dt>
 * <dd>If <code>m1</code>, <code>m2</code>, and <code>m3</code> are all instances
 *     of <code>FElement</code>, if 
 *     <code>sum1 = m1.addTo(m2.addTo(m3))</code> and
 *     <code>sum2 = m1.addTo(m2).addTo(m3)</code> then it follows that
 *     <code>sum1.equals(sum2)</code> returns <code>true</code>.</dd>
 * <dt>Additive Inverses</dt>
 * <dd>If <code>a</code> is an instance of <code>FElement</code> then there must
 *     exist an element <code>a.negative()</code> such that 
 *     <code>a.negative().addTo(a).isZero()</code> returns <code>true</code>.</dd>
 * <dt>Multiplicative Closure</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>FElement</code> then <code>m1.multiplyBy(m2)</code> is also an
 *     instance of <code>FElement</code>.</dd>
 * <dt>Multiplicative Commutivity</dt>
 * <dd>If <code>m1</code> and <code>m2</code> are both instances of
 *     <code>FElement</code> then 
 *     <code>m1.multiplyBy(m2).equals(m2.multiplyBy(m1))</code> returns 
 *     <code>true</code>.</dd>
 * <dt>Multiplicative Identity</dt>
 * <dd>There exists some object <code>one</code> which is an instance of
 *     <code>FElement</code> such that <code>one.isOne()<code> evaluates to
 *     <code>true</code>.  If <code>m</code> is any other instance of
 *     <code>FElement</code> then <code>m.multiplyBy(one).equals(m)</code> returns
 *     <code>true</code>.</dd>
 * <dt>Multiplicative Inverses</dt>
 * <dd>If <code>f</code> is an instance of <code>FElement</code> then there must
 *     exist an element <code>a.inverse()</code> such that 
 *     <code>a.inverse().multiplyBy(a).isOne()</code> returns 
 *     <code>true</code>.</dd>
 * </dl>
 * <p>
 * The interface doesn't have a way to check that these methods are coded correctly;
 * it is the programmers job to ensure that they satisfy the axioms.  However, by
 * writing a <code>FieldFactory</code> one can run some tests.
 * </p>
 *
 * @author Dan Raies
 */
public interface FieldElement extends CommutativeRingElement {
    /**
     * Elements of an field are required to have inverses.  This method
     * implements inversion.
     *
     * @return The negative of this <code>FieldElement</code>.
     */
    public FieldElement inverse();
}
