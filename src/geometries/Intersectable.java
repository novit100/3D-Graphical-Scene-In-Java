package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**an interface that is responsible for finding intersections
 * @author user
 *
 */
public interface Intersectable {
	List<Point3D> findIntersections(Ray ray);
}
