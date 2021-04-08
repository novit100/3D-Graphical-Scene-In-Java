package primitives;
public class Vector {
	Point3D head;
	////////////////////// ctors //////////////////////////
/**
 * 
 * @param a coordinate
 * @param b coordinate
 * @param c coordinate
 * builds a vector from 3 coordinates 
 */
	public Vector (Coordinate a,Coordinate b,Coordinate c) {
		Point3D p=new Point3D(a, b, c);
		Point3D nl=new Point3D(0, 0, 0);
		if(p.equals(nl)) {
			throw new IllegalArgumentException("cannot support zero vector");
		}
		this.head=p;

	}
	/**
	 * @param a double
	 * @param b double
	 * @param c double
	 * builds a vector from 3 doubles 
	 */

	public Vector (double a,double b,double c) {
		Point3D p=new Point3D(a, b, c);
		Point3D nl=new Point3D(0, 0, 0);
		if(p.equals(nl)) {
			throw new IllegalArgumentException("cannot support zero vector");
		}
		this.head=p;

	}
	/**
	 * Builds a vector from a point 
	 * @param head
	 */
	public Vector(Point3D head) {
		this.head = head;
	}

	//////////////////////// get /////////////////
	/**
	 * @return   the head of the vector
	 */
	public Point3D getHead() {
		return head;
	}
	
	/////////////////calculations //////////////////
	
/**
 * @return return the current normalize vector
 */
	public Vector normalize() {
		Vector v2 = new Vector(this.getHead());
		this.head=v2.head;
		return this;
	}
	/**
	 * @return a normalized vector
	 */
	public Vector normalized() {
		Vector v=new Vector(this.head);
		v.normalize();
		return v;
	}

	/**
	 * @param s
	 * @return the wanted vector by multiple each of it's coordinates by s
	 */
	public Vector scale(double sc) {
		
		Vector v=new Vector((this.head.x.coord)*sc,(this.head.y.coord)*sc, (this.head.z.coord)*sc);
		return v;
	}
	/**
	 * @param v
	 * @return the scalar
	 */
	public double dotProduct(Vector v) {
		double X=(this.head.x.coord)*(v.getHead().x.coord);
		double Y=(this.head.y.coord)*(v.getHead().y.coord);
		double Z=this.head.z.coord*v.getHead().z.coord;
		double sum=X+Y+Z;
		return sum;

	}
	/**
	 * subtract the wanted vector from it's head
	 * @param v vector
	 * @return a substracted vector
	 */
	public Vector substract(Vector v) {
		return (head.subtract(v.getHead()));
	
	}
	/**
	 * add the wanted vector to the current vector's head
	 * @param v
	 * @return the new vector
	 */
	public Vector add(Vector v) {
		
	return (new Vector(head.add((v))));
	
	}
	/**
	 * @param v
	 * @return a new vector of the cross product
	 */
	public Vector crossProduct(Vector v) {
		double X=this.head.y.coord*v.head.z.coord-this.head.z.coord*v.head.y.coord;
		double Y=this.head.z.coord*v.head.x.coord-this.head.x.coord*v.head.z.coord;
		double Z=this.head.x.coord*v.head.y.coord-this.head.y.coord*v.head.x.coord;
		Vector v_new=new Vector(X,Y,Z);
		return v_new;
	}
	
	/**
	 * @return the squared of the length
	 */
	public double lengthSquared () {
		double length_s=( this.head.x.coord*this.head.x.coord + this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord );
		return length_s;
	}
	
	/**
	 * @return the length
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared ());
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vector head=" + head ;
	}


}
