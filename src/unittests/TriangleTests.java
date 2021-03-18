package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import geometries.*;

/**
 * 
 * @author Ronni and Nov
 *
 */

public class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        Triangle tri = new Triangle(new Point3D(2,0,0),new Point3D(0,2,0),new Point3D(0,0,0));
        assertEquals(new Vector(0,0,1),tri.getNormal(null));
	}

}
