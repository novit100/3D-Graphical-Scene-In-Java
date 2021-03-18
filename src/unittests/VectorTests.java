/**the tests of Vector class
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import static primitives.Util.*;

import primitives.Vector;

/**
 * 
 * @author Ronni & Nov  
 *
 */

public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1,1,1);
		Vector v2 = new Vector(-1,-1,1);
		Vector v3 = new Vector(-1,-1,-1);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("wrong length result for Add function ", new Vector(0,0,2), v1.add(v2));
		
		  // =============== Boundary Values Tests ==================
        // 
        try {
            v1.add(v3);
            fail("The Add function was supposed to throw an exception but didn't");
        } catch (Exception e) {} ////////we shall check later on the exception 
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(1,3,3);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("Substract() wrong result length", new Vector(0,-1,0), v1.substract(v2));
		
		  // =============== Boundary Values Tests ==================
        // 
        try {
            v1.substract(v1);
            fail("Substract() should throw an exception, but it failed");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(1, 1, 1);
		// ============ Equivalence Partitions Tests ==============
	    assertEquals("Scale() failed to multiply the Vector by the given scale",new Vector(-2, -2, -2), v1.scale(-2));
	    // =============== Boundary Values Tests ==================
        // 
        try {
            v1.scale(0);
            fail("Scale() should throw an exception, but it failed");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        //  we used orthogonal vectors taken to simplify ,Tests that the length of the cross-product does the job well:)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 1, 1);
		Vector v2 = new Vector(3, 3, 1);
		Vector v3 = new Vector(0, -3, 9);
		// ============ Equivalence Partitions Tests ==============
		assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) -7));
		assertTrue("ERROR: dotProduct() wrong value for orthogonal vectors",isZero(v3.dotProduct(v2)));
		// =============== Boundary Values Tests ==================
		try {
			assertTrue("dotProduct() wrong result length",isZero(new Vector(0,0,0).dotProduct(v1)));
			fail("dotProduct() should throw an exception, but it failed");
		    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#lenghtSquared()}.
	 */
	@Test
	public void testLenghtSquared() {
		Vector v1 = new Vector (1, 3, 9);
		// ============ Equivalence Partitions Tests ==============
		assertTrue("LengthSquared() wrong result length",isZero(v1.lengthSquared() - 91));
		 // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("LengthSquared() wrong result length",isZero(new Vector(0,0,0).lengthSquared()));
		fail("LengthSquared() should throw an exception, but it failed");
	    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		Vector v1= new Vector(5,3,1);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("ERROR: length() wrong value", v1.length(),Math.sqrt(35),0.0001);
		 // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Length() wrong result length",isZero(new Vector(0,0,0).length()));
		fail("Length() should throw an exception, but it failed");
	    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v1 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
	    assertEquals(v1.normalize().length(), 1,0.0001);
	    // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Normalize() wrong result length",isZero(new Vector(0,0,0).normalize().length()));
		fail("Normalize() should throw an exception, but it failed");
	    } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v1 = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============
	    assertEquals(v1.normalized().length(), 1,0.0001);
	    // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Normalized() wrong result length",isZero(new Vector(0,0,0).normalized().length()));
		fail("Normalized() should throw an exception, but it failed");
	    } catch (Exception e) {}
	}

	
}
