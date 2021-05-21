package primitives;
import static primitives.Util.*;

import java.util.Iterator;
import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * @author ronni&nov
 *
 */
public class Ray {
	 Point3D p0;//the place that the ray starts
	 Vector dir;//the direction of the ray 
	/**
	 * for constructing reflected and refracted rays: (the ctor with 3 parameters- p0, v, n)
	 */
		private static final double DELTA = 0.01;
	 //////////// ctor //////////////
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}
	/**
	 * Let us note that building a beam with moving a point - this is an operation that is repeated three times already, 
	 * so it is worthwhile to lower it to a separate function.
	 * The location of the function according to RDD is the fund department.
	 * Since this is a new fund construction (according to the original point,
	 * the direction of the fund, the normal vector (on the line it defines the point of the fund head must be moved))
	 * - we will add the function as another fund builder and update In all relevant places the code so that it will use the new builder of the fund. 
	 * Of course the permanent DELTA we defined earlier will be transferred to the Ray class
	 * @param point
	 * @param lightDirection
	 * @param norm
	 */
	public Ray(Point3D point, Vector lightDirection, Vector norm) {
		
		this.dir = lightDirection.normalize();
		double nV = norm.dotProduct(lightDirection);
		Vector delta = norm.scale(nV >= 0 ? DELTA : -DELTA);
		this.p0 = point.add(delta);
		
		
	}
	/////////////// get //////////////
	public Point3D getP0() {
		return p0;
	}
	public Vector getDir() {
		return dir;
	}
	/**
	 * 
	 * @param double t
	 * @return  p0 if t==0, else returns p0+ (direction*t)
	 */
	public Point3D getPoint(double t) {
		Point3D tmp=new Point3D(p0.x,p0.y,p0.z);
		return isZero(t) ? p0 : tmp.add(dir.scale(t));

	}

	////////////  admin ////////////
	@Override
	public String toString() {
		return "Ray: p0=" + p0 + ", dir=" + dir;
	}

	/**
	 * @param lst_point 
	 * @return point3D ,the closest point to the ray
	 */
	public Point3D findClosestPoint (List<Point3D> lst_point) {
		if (lst_point.isEmpty()) return null;
		double min_dis=p0.distance(lst_point.get(0));//for the sake of programming we assumed that the first point is the closest 
		double dis;
		Point3D target=lst_point.get(0);//as above...
		for(int i=1;i<lst_point.size();i++) {//this loop moves through the points of the given list and compares the distances between the 
			                                 //current point to the starting point of the ray 
			dis=p0.distance(lst_point.get(i));
			if(dis<min_dis) {
				min_dis=dis;
				target=lst_point.get(i);
			}
		}
		return target;
		
	}
	
	/**
	 * @param lst_point 
	 * @return GeoPoint ,the closest point to the ray
	 */
	public GeoPoint findClosestGeoPoint (List<GeoPoint> lst_point) {
		if (lst_point==null)//if the list is empty
			return null;
		double min_dis=p0.distance(lst_point.get(0).point);//for the sake of programming we assumed that the first point is the closest 
		double dis;
		GeoPoint target=lst_point.get(0);//as above...
		for(int i=1;i<lst_point.size();i++) {//this loop moves through the points of the given list and compares the distances between the 
			                                 //current point to the starting point of the ray 
			dis=p0.distance(lst_point.get(i).point);
			if(dis<min_dis) {
				min_dis=dis;
				target=lst_point.get(i);
			}
		}
		return target;
		
	}
	//////
	/**
	 * same as the function "findClosestPoint", but works with GeoPoints.
	 * @param points
	 * @return the GeoPoint in which its point is the closest to p0 of the ray.
	 */
	public GeoPoint getClosestGeoPoint(List<GeoPoint> points)
	{
		if (points==null)//if the list is empty
			return null;

		GeoPoint closestP=points.get(0);			//take the 1st point in the beginning. point and geometry.
		double min=p0.distance(points.get(0).point);	

		for(int i=0; i<points.size(); i++) 		//move on all the points
		{
			if (p0.distance(points.get(i).point)<min) //change the closest point if the dis < min
			{
				min=p0.distance(points.get(i).point);
				closestP=points.get(i);		    
			}
		}
		return closestP;	//return the closest point(and the geometry it intersects)-with min distance from p0.
	
	}

	
	
	/////
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		if (p0 == null) {
			if (other.p0 != null)
				return false;
		} else if (!p0.equals(other.p0))
			return false;
		return true;
	}
	
	///////////////////////////
	
	/**this function gets a scale and returns a new point p0+t*dir
	 * that means that we multiply the ray by the scale and return the final point
	 * 
	 * @author ronni & nov
	 *
	 */
	
	
	/**DK if belongs here
	 * @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D P0 = ray.getP0();
        Vector v = ray.getDirection();

        Vector n = _normal;

        if(_q0.equals(P0)){
            return  null;
        }

        Vector P0_Q0 = _q0.subtract(P0);

        double mechane = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(mechane)){
            return null;
        }

        //mone
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(mechane / nv);

        if (t <=0){
            return  null;
        }
        Point3D P = ray.getPoint(t);

        return List.of(P);
    }
}
	 */
}
