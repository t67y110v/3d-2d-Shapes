import java.lang.Math;

public class Pyramid implements IFigure{
	private Point3D p0;
	private int n;
	private Point3D[] p;

	public Pyramid(Point3D p0, Point3D[] p) throws Exception {
		// Check if base of pyramid is plane
		double a = (p[1].getX(1) - p[0].getX(1)) * (p[2].getX(2) - p[0].getX(2))
			- (p[2].getX(1) - p[0].getX(1)) * (p[1].getX(2) - p[0].getX(2));
		double b = (p[1].getX(0) - p[0].getX(0)) * (p[2].getX(2) - p[0].getX(2))
			- (p[2].getX(0) - p[0].getX(0)) * (p[1].getX(2) - p[0].getX(2));
		double c = (p[1].getX(0) - p[0].getX(0)) * (p[2].getX(1) - p[0].getX(1))
			- (p[2].getX(0) - p[0].getX(0) * (p[1].getX(1) - p[0].getX(1)));
		
		double d = (-p[0].getX(0)) * a + (-p[0].getX(1)) * b + (-p[0].getX(2)) * c;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(a * p[i].getX(0) + b * p[i].getX(1) + c * p[i].getX(2) + d) != 0.0) {
				throw new Exception("Pyramid: Points supposed to be on one plain are not on plain\n");
			}
		}

		if (Math.round(a * p0.getX(0) + b * p0.getX(1) + c * p0.getX(2) + d) != 0.0) {
			throw new Exception("Pyramid: Top point is on base plain\n");
		}

		this.p0 = p0;
		this.p = p;
		this.n = p.length + 1;
	}

	public Pyramid(Point3D[] p) throws Exception {
		Point3D[] t = new Point3D[p.length - 1];
		for (int i = 1; i < p.length; i++) {
			t[i - 1] = p[i];
		}

		double a = (t[1].getX(1) - t[0].getX(1)) * (t[2].getX(2) - t[0].getX(2))
			- (t[2].getX(1) - t[0].getX(1)) * (t[1].getX(2) - t[0].getX(2));
		double b = (t[1].getX(0) - t[0].getX(0)) * (t[2].getX(2) - t[0].getX(2))
			- (t[2].getX(0) - t[0].getX(0)) * (t[1].getX(2) - t[0].getX(2));
		double c = (t[1].getX(0) - t[0].getX(0)) * (t[2].getX(1) - t[0].getX(1))
			- (t[2].getX(0) - t[0].getX(0) * (t[1].getX(1) - t[0].getX(1)));
		
		double d = (-t[0].getX(0)) * a + (-t[0].getX(1)) * b + (-t[0].getX(2)) * c;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(a * t[i].getX(0) + b * t[i].getX(1) + c * t[i].getX(2) + d) != 0.0) {
				throw new Exception("Pyramid: Points supposed to be on one plain are not on plain\n");
			}
		}

		if (Math.round(a * p[0].getX(0) + b * p[0].getX(1) + c * p[0].getX(2) + d) != 0.0) {
			throw new Exception("Pyramid: Top point is on base plain\n");
		}

		this.p0 = p[0];
		this.p = t;
		this.n = p.length;
 	}
	
	public int getN() {
		return n;
	}

	public Point3D[] getP() {
		return p;
	}

	public void setP(Point3D[] p) throws Exception {
		if (p.length != n)
			throw new Exception("Pyramid: Array size is not correct\n");

		this.p = p;
	}

	public Point3D getP(int pos) throws Exception {
		if (pos < 0 || pos >= n)
			throw new Exception("Pyramid: Position in get is not valid\n");

		return p[pos];
	}

	public void setP(Point3D p, int pos) throws Exception {
		if (pos < 0 || pos >= n)
			throw new Exception("Pyramid: Position in set is not valid");

		this.p[pos] = p;
	}

	@Override
	public double square() throws Exception {
		double s = 0.0;
		for (int i = 0; i < n - 1; i++) {
			double a = Point3D.sub(p[i], p0).abs();
			double b = Point3D.sub(p[i + 1], p0).abs();
			double c = Point3D.sub(p[i], p[i + 1]).abs();
			double hP = (a + b + c) / 2;
			s += Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
		}

		for (int i = 2; i < n; i++) {
			double a = Point3D.sub(p[0], p[i - 1]).abs();
			double b = Point3D.sub(p[0], p[i]).abs();
			double c = Point3D.sub(p[i], p[i - 1]).abs();
			double halfP = (a + b + c) / 2;
			s += Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
		}
		
		return s;
	}

	@Override
	public double volume() throws Exception {
		double a = (p[1].getX(1) - p[0].getX(1)) * (p[2].getX(2) - p[0].getX(2))
			- (p[2].getX(1) - p[0].getX(1)) * (p[1].getX(2) - p[0].getX(2));
		double b = (p[1].getX(0) - p[0].getX(0)) * (p[2].getX(2) - p[0].getX(2))
			- (p[2].getX(0) - p[0].getX(0)) * (p[1].getX(2) - p[0].getX(2));
		double c = (p[1].getX(0) - p[0].getX(0)) * (p[2].getX(1) - p[0].getX(1))
			- (p[2].getX(0) - p[0].getX(0) * (p[1].getX(1) - p[0].getX(1)));
		
		double d = (-p[0].getX(0)) * a + (-p[0].getX(1)) * b + (-p[0].getX(2)) * c;

		double s = 0.0;
		for (int i = 2; i < n; i++) {
			double aa = Point3D.sub(p[0], p[i - 1]).abs();
			double bb = Point3D.sub(p[0], p[i]).abs();
			double cc = Point3D.sub(p[i], p[i - 1]).abs();
			double halfP = (aa + bb + cc) / 2;
			s += Math.sqrt(halfP * (halfP - aa) * (halfP - bb) * (halfP - cc));
		}

		return (Math.abs(a * p0.getX(0) + b * p0.getX(1) + c * p0.getX(2) + d) / Math.sqrt(a * a + b * b + c * c)) * s * ((double) 1 / 3);
	}

	@Override
	public String toString() {
		String str = "Pyramid top point: " + p0.toString() + "\nPoints: ";
		for (int i = 0; i < n; i++) {
			str += p[i].toString();
		}
		return str;
	}
}
