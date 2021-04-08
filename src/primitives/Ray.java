package primitives;
import static primitives.Util.*;

/**
 * @author ronni&nov
 *
 */
public class Ray {
	 Point3D p0;
	 Vector dir;
	 
	 //////////// ctor //////////////
	public Ray(Point3D p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}
	/////////////// get //////////////
	public Point3D getP0() {
		return p0;
	}
	public Vector getDir() {
		return dir;
	}
	public Point3D getPoint(double t) {
		Point3D tmp=new Point3D(p0.x,p0.y,p0.z);
		return isZero(t) ? p0 : tmp.add(dir.scale(t));

	}

	////////////  admin ////////////
	@Override
	public String toString() {
		return "Ray: p0=" + p0 + ", dir=" + dir;
	}
	/**this function gets a scale and returns a new point p0+t*dir
	 * that means that we multiply the ray by the scale and return the final point
	 * 
	 * @author ronni & nov
	 *
	 */
	

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
}
