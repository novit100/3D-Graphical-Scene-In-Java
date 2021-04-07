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
		// ============ Equivalence Partitions Tests ==============

		Vector v1 = new Vector(1,1,1);
		Vector v2 = new Vector(-1,-1,1);
		Vector v3 = new Vector(-1,-1,-1);
		assertEquals("Add() wrong result length", new Vector(0,0,2), v1.add(v2));//check if the add works well (not the zero vector)
		
		  // =============== Boundary Values Tests ==================
        // 
        try {
            v1.add(v3);//zero vector
            fail("Add() should throw an exception, but it failed");
        } catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(1,3,3);
		
		assertEquals("Substract() wrong result length", new Vector(0,-1,0), v1.substract(v2));//regular case
		
		  // =============== Boundary Values Tests ==================
        // 
        try {
            v1.substract(v1);//zero
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
	    assertEquals(new Vector(-2, -2, -2), v1.scale(-2));//regular case
	    // =============== Boundary Values Tests ==================
        // 
        try {
            v1.scale(0);//multiple by 0- cannot get the zero vector
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
        Vector vr = v1.crossProduct(v3);//vr=(0,3,-2)

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);//in cross product, the length of the third vector(normal) must be equal to the mult of the 2 length

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));//in cross product, the third vector must be a normal to the plane of the others
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));//in cross product, the third vector must be a normal to the plane of the others

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);//if the vector are on the same direction, the cross product must be 0
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
		assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) -7));//check regular case
		assertTrue("ERROR: dotProduct() wrong value for orthogonal vectors",isZero(v3.dotProduct(v2)));//check the orthogonal case- if the result is 0
		// =============== Boundary Values Tests ==================
		try {
			assertTrue("dotProduct() wrong result length",isZero(new Vector(0,0,0).dotProduct(v1)));//in that case, the ctor of vector will throw an exception- the zero vector
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
		assertTrue("LengthSquared() wrong result length",isZero(v1.lengthSquared() - 91));//regular case
		 // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("LengthSquared() wrong result length",isZero(new Vector(0,0,0).lengthSquared()));//in that case, the ctor of vector will throw an exception- the zero vector
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
		assertEquals("ERROR: length() wrong value", v1.length(),Math.sqrt(35),0.0001);//regular case
		 // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Length() wrong result length",isZero(new Vector(0,0,0).length()));//in that case, the ctor of vector will throw an exception- the zero vector
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
	    assertEquals(v1.normalize().length(), 1,0.0001);//regular case
	    // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Normalize() wrong result length",isZero(new Vector(0,0,0).normalize().length()));//in that case, the ctor of vector will throw an exception- the zero vector
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
	    assertEquals(v1.normalized().length(), 1,0.0001);//regular case
	    // =============== Boundary Values Tests ==================
        // 
	    try {
		assertTrue("Normalized() wrong result length",isZero(new Vector(0,0,0).normalized().length()));//in that case, the ctor of vector will throw an exception- the zero vector
		fail("Normalized() should throw an exception, but it failed");
	    } catch (Exception e) {}
	}

	
}
