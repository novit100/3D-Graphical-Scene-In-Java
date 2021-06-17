package primitives;
import static primitives.Util.*;
public class Point3D {
	final Coordinate x;
	final Coordinate y;
	final Coordinate z;
	public static Point3D ZERO=new Point3D(0, 0, 0);
	/**constructor that gets three Coordinates
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**constructor that gets three doubles 
	 */
	public Point3D(double x, double y, double z) {
		this.x= new Coordinate(x) ;
		this.y = new Coordinate(y);
		this.z = new Coordinate(z);
		
	}
	
	public double getX() {
		return x.coord;
	}
	public double getY() {
		return y.coord;
	}
	public double getZ() {
		return z.coord;
	}
	/**- Vector subtraction - receives a second point in the parameter,
	 *  returns a vector from the second point to the point on which the operation is performed*/
	public Vector subtract(Point3D p){
		Point3D new_p=new Point3D(x.coord-p.x.coord,y.coord- p.y.coord, z.coord-p.z.coord);
		Vector v=new Vector(new_p);
		return v;
		}
	/**
	 * Adding a vector to a point - Returns a new point
	 * @param v -vector that needs to be added to the point 
	 * @return a point
	 */
	public Point3D add(Vector v) {
		Point3D p= new Point3D(v.getHead().x.coord+this.x.coord, v.getHead().y.coord+this.y.coord, v.getHead().z.coord+this.z.coord);
		return p;
		
	}
	/**
	 * public double	distanceSquared(Point3D p) {
	//	double dis_s=((this.x.coord-p.x.coord)*(this.x.coord-p.x.coord))+((this.y.coord-p.y.coord)*(this.y.coord-p.y.coord))+((this.z.coord-p.z.coord)*(this.z.coord-p.z.coord));
    //	return dis_s;
		Vector v = this.subtract(p); 
		return v.dotProduct(v);
	}
	
	/**
	 * The distance between two points squares
	 * distanceSquared((x1,y1,z1)(x2,y2,z2))=(x1-x2)^2+(y1-y2)^2+(z1-z2)^2
	 * 
	 * @param other
	 * @return double
	 */
	public double distanceSquared(Point3D other) {
		return (this.x.coord - other.x.coord) * (this.x.coord - other.x.coord)
				+ (this.y.coord - other.y.coord) * (this.y.coord - other.y.coord)
				+ (this.z.coord - other.z.coord) * (this.z.coord - other.z.coord);
	}


	public double distance(Point3D p) {
		double dis_s =this.distanceSquared(p);
		return Math.sqrt(dis_s);
	}
	/**
	 * Function to the center of the circle,<br>
	 * which receives a circle,<br>
	 * and returns a random point on the circle
	 * 
	 * @param dir    The normal exiting the circle,<br>
	 *               together with the radius or height and length representing the
	 *               circle,
	 * @param radius radius of circle
	 * @return Returns a random point on the circle
	 */
	public Point3D randomPointOnRadius(Vector dir, double radius) {
		double diameter = radius * 2;
		Point3D p = randomPointOnRectangle(dir, diameter, diameter);
		double t = p.distance(this);
		while (t > radius) {
			p = randomPointOnRectangle(dir, diameter, diameter);
			t = p.distance(this);
		}
		return p;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Point3D x=" + x + ", y=" + y + ", z=" + z ;
	}
	////////////////////////////// blurry and glossy /////////////////////////
	public Point3D randomPointOnRectangle(Vector dir, double width, double height) {
		Vector firstNormal = dir.createOrthogonalVector();
		Vector secondNormal = firstNormal.crossProduct(dir).normalize();
		Point3D randomCirclePoint = this;
		double r;
		double wHalf = width / 2;
		r = random(0, wHalf);
		double x = random(-r, r);
		double hHalf = height / 2;
		r = random(0, hHalf);
		double y = random(-r, r);
		if (x != 0)
			randomCirclePoint = randomCirclePoint.add(firstNormal.scale(x));
		if (y != 0)
			randomCirclePoint = randomCirclePoint.add(secondNormal.scale(y));
		return randomCirclePoint;
		
	}
	/**
	* find the Absolute minimum Coordinate
	*
	* @return if Coordinate x is minimum return 'x'<br>
	*         if Coordinate y is minimum return 'y'<br>
	*         if Coordinate z is minimum return 'z'<br>
	*         if all Coordinates are equal return 'x'
	*/
	public char findAbsoluteMinimumCoordinate() {
	double minimum = this.x.coord < 0 ? -this.x.coord : this.x.coord; // abs(x)
	char index = 'x';
	double y = this.y.coord < 0 ? -this.y.coord : this.y.coord; // abs(y)
	if (y < minimum) {
	minimum = y;
	index = 'y';
	}
	double z = this.z.coord < 0 ? -this.z.coord : this.z.coord; // abs(z)
	if (z < minimum) {
	index = 'z';
	}
	return index;
	}
}
