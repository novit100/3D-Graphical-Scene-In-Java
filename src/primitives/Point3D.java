package primitives;

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
	/**- Vector subtraction - receives a second point in the parameter,
	 *  returns a vector from the second point to the point on which the operation is performed*/
	public Vector subtract(Point3D p){
		Point3D new_p=new Point3D(p.x.coord-this.x.coord, p.y.coord-this.y.coord, p.z.coord-this.z.coord);
		Vector v=new Vector(new_p);
		return v;
		}
	/**
	 * Adding a vector to a point - Returns a new point
	 * @param v -vector that needs to be added to the point 
	 * @return a point
	 */
	public Point3D add(Vector v) {
		Point3D p= new Point3D(v.head.x.coord+this.x.coord, v.head.y.coord+this.y.coord, v.head.z.coord+this.z.coord);
		return p;
		
	}
	public double	distanceSquared(Point3D p) {
		double dis_s=((this.x.coord-p.x.coord)*(this.x.coord-p.x.coord))+((this.y.coord-p.y.coord)*(this.y.coord-p.y.coord))+((this.z.coord-p.z.coord)*(this.z.coord-p.z.coord));
	return dis_s;
	}
	public double distance(Point3D p) {
		double dis_s =this.distanceSquared(p);
		return Math.sqrt(dis_s);
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

}
