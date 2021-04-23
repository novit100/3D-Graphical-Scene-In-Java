package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {
/**
 * a constructor of the class RayTracerBasic using a scene
 * @param scene
 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}
	/**
	 * an override method that get's a ray and returns a color, if there are no intersections with the object-it returns the color of the 
	 * background,if there are intersections-it returns the color of the closest point to the ray.
	 * @param ray
	 * @return color
	 */
	@Override
	public Color traceRay(Ray ray) {
		List<Point3D> intersects=scene.geometries.findIntersections(ray);
		if(intersects==null) {
			return scene.background;// if there are no intersections -return the color of the backgroun
		}
		else {
			Point3D closest_p= ray.findClosestPoint(intersects);
			return calcColor(closest_p);
			
		}
			
	}
	/**
	 * calculates the color of a specific point
	 * ===at the moment ,returns only the intensity of the ambient light==== 
	 * @param p
	 * @return color
	 */
private Color calcColor(Point3D p) {
	return scene.ambientLight.getIntensity();
}
}
