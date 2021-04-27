package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**an interface that is responsible for finding intersections
 * @author Ronni & Nov
 *
 */
public interface Intersectable {
	List<Point3D> findIntersections(Ray ray);
/**
 * GeoPoint class is a passive static data structure (PSD) inside the interface Intersectable.
 * it is meant to help us using the emmission light
 * @author Ronni & Nov
 *
 */
public static class GeoPoint {
	///////////////    fields   //////////////////
    public Geometry geometry;
    public Point3D point;
    /////////////// constructor //////////////////
    /**
     * a constructor that gets two parameters and generates them 
     * @param geometry
     * @param point
     */
	public GeoPoint(Geometry geometry, Point3D point) {
		this.geometry = geometry;
		this.point = point;
	}
	
	//////////////// admin ////////////////////////
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		List<GeoPoint> intersections = null;
		for(int i=0;i<(Scene.geometries).length();i++) {
	}
	/**
	 * an admin function that checks if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoPoint other = (GeoPoint) obj;
		if (geometry == null) {
			if (other.geometry != null)
				return false;
		} else if (!geometry.equals(other.geometry))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
    
}

}
