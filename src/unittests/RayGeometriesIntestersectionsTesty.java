package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import elements.Camera;
import primitives.*;
import geometries.*;
public class RayGeometriesIntestersectionsTesty {
/**
 * 
 */
	@Test
	public void testSphere(){
		Point3D p0=new Point3D(0, 0, 0);
		Vector vUp=new Vector(0,1,0);
		Vector vTo=new Vector(-1,0,0);
		Camera cam=new Camera(p0,vUp, vTo);
		Point3D center=new Point3D(0, 0, -3);
		Sphere spherush=new Sphere(center, 1);
	}

}
