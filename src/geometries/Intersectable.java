package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import java.util.stream.Collectors;

/**
 * an interface that is responsible for finding intersections of ray with
 * geometry
 * 
 * @author Ronni & Nov
 *
 */
public abstract class Intersectable {
	/**
	 * minX, maxX, minY, maxY, minZ, maxZ
	 * To create a box that is parallel to the axes we need 6 variables,
	 * because there are 8 points of the box double 3 coordinates and each
	 * coordinate appears 4 times the same
	 * so it is possible to divide 3 * 8/4 = 6
	 */
	protected double minX, maxX, minY, maxY, minZ, maxZ;
	/**
	 * middle Box Point, the inner middle point of the box
	 */
	protected Point3D middleBoxPoint;
	/**
	 * if the shapes are finite shapes like sphere = true
	 * otherwise if shape are like plane or tube it mean infinity = false
	 */
	protected boolean finityShape = false;
	/**
	 * To give an option with or without acceleration of speed bounding volume
	 * Hierarchy
	 * if we wont whit acceleration = true , whitout acceleration = false
	 */
	protected boolean BVHactivated = false;

	/**
	 * 
	 * Create box for the shape
	 * set the miniX value to the minimum coordinate x of the shape or collection of
	 * shape <br>
	 * set the miniY value to the minimum coordinate y of the shape or collection of
	 * shape<br>
	 * set the miniZ value to the minimum coordinate z of the shape or collection of
	 * shape<br>
	 */
	protected abstract void CreateBoundingBox();

	/**
	 * creating boxes for all shapes in the geometries list
	 * and setting the bounding to be true
	 */
	public void createBox() {
		BVHactivated = true;
		CreateBoundingBox();
	}

	/**
	 * Function for finding the midpoint inside the box
	 * 
	 * @return the center inner point of the box
	 */
	public Point3D getMiddlePoint() {
		return new Point3D(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2), minZ + ((maxZ - minZ) / 2));
	}

	/**
	 * Extremely fast algorithms<br>
	 * for checking whether a ray cuts a box
	 *
	 * @param ray we want to test whether it is cutting or not the box
	 * @return true if intersect false if not
	 */
	public boolean isIntersectWithTheBox(Ray ray) {
		Point3D head = ray.getDir().getHead();
		Point3D p = ray.getP0();
		double temp;

		double dirX = head.getX(), dirY = head.getY(), dirZ = head.getZ();
		double origX = p.getX(), origY = p.getY(), origZ = p.getZ();

		// Min/Max starts with X
		double tMin = (minX - origX) / dirX, tMax = (maxX - origX) / dirX;
		if (tMin > tMax) {
			temp = tMin;
			tMin = tMax;
			tMax = temp;
		} // swap

		double tYMin = (minY - origY) / dirY, tYMax = (maxY - origY) / dirY;
		if (tYMin > tYMax) {
			temp = tYMin;
			tYMin = tYMax;
			tYMax = temp;
		} // swap
		if ((tMin > tYMax) || (tYMin > tMax))
			return false;
		if (tYMin > tMin)
			tMin = tYMin;
		if (tYMax < tMax)
			tMax = tYMax;

		double tZMin = (minZ - origZ) / dirZ, tZMax = (maxZ - origZ) / dirZ;
		if (tZMin > tZMax) {
			temp = tZMin;
			tZMin = tZMax;
			tZMax = temp;
		} // swap
		return tMin <= tZMax && tZMin <= tMax;
	}

	////
	/**
	 * @param ray
	 * @return list of GeoPints: intersections and the geometries that are
	 *         intersected.
	 */
	abstract List<GeoPoint> findGeoIntersections(Ray ray);

	public List<GeoPoint> findIntersectWithBoundedBox(Ray ray) {
		return BVHactivated && !isIntersectWithTheBox(ray) ? null : findGeoIntersections(ray);
	}

	/**
	 * just for the tests until stage 6:
	 * 
	 * @param ray
	 * @return list of points: intersection point
	 */
	public List<Point3D> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
	}

	/**
	 * GeoPoint inner class is a passive static data structure (PSD) inside the
	 * interface Intersectable that all of it's fields are public. it is meant to
	 * help us using the emmission light
	 * 
	 * @author Ronni & Nov
	 *
	 */
//============================================================================//
	public static class GeoPoint {
		/////////////// fields //////////////////
		public Geometry geometry;
		public Point3D point;

		/////////////// constructor //////////////////
		/**
		 * a constructor that gets two parameters and generates them
		 * 
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}

		//////////////// admin ////////////////////////

	
		@Override
		public String toString() {
			return "GP{" + "G=" + geometry + ", P=" + point + '}';
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
