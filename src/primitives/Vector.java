package primitives;

public class Vector {
	Point3D head;

	public Vector (Coordinate a,Coordinate b,Coordinate c) {
		Point3D p=new Point3D(a, b, c);
		Point3D nl=new Point3D(0, 0, 0);
		if(p.equals(nl)) {
			throw new IllegalArgumentException("cannot support zero vector");
		}
		this.head=p;

	}
	public Vector (double a,double b,double c) {
		Point3D p=new Point3D(a, b, c);
		Point3D nl=new Point3D(0, 0, 0);
		if(p.equals(nl)) {
			throw new IllegalArgumentException("cannot support zero vector");
		}
		this.head=p;

	}
	public Vector(Point3D head) {
		this.head = head;
	}

	public Point3D getHead() {///////
		double length = Math.sqrt( this.head.x.coord*this.head.x.coord + this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord );
		if (length != 0) {
			Coordinate a =new Coordinate(this.head.x.coord/length) ;
			Coordinate b =new Coordinate(this.head.y.coord/length) ;
			Coordinate c=new Coordinate(this.head.z.coord/length);
			Point3D p=new Point3D(a, b, c);
			return p;
		}
		else {
			throw new IllegalArgumentException("cannot support zero length");

		}
	}
	// Normalize a vectors length....

	public Vector normalize() {
		Vector v2 = new Vector(this.getHead());
		this.head=v2.head;
		return this;
	}
	public Vector normalized() {
		Vector v=new Vector(this.head);
		v.normalize();
		return v;
	}

	public Vector scale(double sc) {
		
		Vector v=new Vector((this.head.x.coord)*sc,(this.head.y.coord)*sc, (this.head.z.coord)*sc);
		return v;
	}
	public double dotProduct(Vector v) {
		double X=(this.head.x.coord)*(v.head.x.coord);
		double Y=(this.head.y.coord)*(v.head.y.coord);
		double Z=this.head.z.coord*v.head.z.coord;
		double sum=X+Y+Z;
		return sum;

	}
	public Vector substract(Vector v) {
	
	double X=this.head.x.coord-v.head.x.coord;
	double Y=this.head.y.coord-v.head.y.coord;
	double Z=this.head.z.coord-v.head.z.coord;
	Vector v_new=new Vector(X,Y,Z);
	return v_new;
	
	}
	public Vector add(Vector v) {
		
	double X=this.head.x.coord+v.head.x.coord;
	double Y=this.head.y.coord+v.head.y.coord;
	double Z=this.head.z.coord+v.head.z.coord;
	Vector v_new=new Vector(X,Y,Z);
	return v_new;
	
	}
	public Vector crossProduct(Vector v) {
		double X=this.head.y.coord*v.head.z.coord-this.head.z.coord*v.head.y.coord;
		double Y=this.head.z.coord*v.head.x.coord-this.head.x.coord*v.head.z.coord;
		double Z=this.head.x.coord+v.head.y.coord-this.head.y.coord*v.head.x.coord;
		Vector v_new=new Vector(X,Y,Z);
		return v_new;
	}
	public double lengthSquared () {
		double length_s=( this.head.x.coord*this.head.x.coord + this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord );
		return length_s;
	}
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
