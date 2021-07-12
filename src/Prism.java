public class Prism implements IFigure {
	protected int n;
	protected Point3D[] p1;
	protected Point3D[] p2;

	public Prism(Point3D[] p1, Point3D[] p2) throws Exception {
		if (p1.length != p2.length || p1.length < 3)
			throw new Exception("Prism: Arrays are not correct\n");

		double a = (p1[1].getX(1) - p1[0].getX(1)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(1) - p1[0].getX(1)) * (p1[1].getX(2) - p1[0].getX(2));
		double b = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(0) - p1[0].getX(0)) * (p1[1].getX(2) - p1[0].getX(2));
		double c = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(1) - p1[0].getX(1))
			- (p1[2].getX(0) - p1[0].getX(0) * (p1[1].getX(1) - p1[0].getX(1)));
		
		double d = (-p1[0].getX(0)) * a + (-p1[0].getX(1)) * b + (-p1[0].getX(2)) * c;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(a * p1[i].getX(0) + b * p1[i].getX(1) + c * p1[i].getX(2) + d) != 0.0) {
				throw new Exception("Prism: Points supposed to be on one plain are not on plain\n");
			}
		}

		double aa = (p2[1].getX(1) - p2[0].getX(1)) * (p2[2].getX(2) - p2[0].getX(2))
			- (p2[2].getX(1) - p2[0].getX(1)) * (p2[1].getX(2) - p2[0].getX(2));
		double bb = (p2[1].getX(0) - p2[0].getX(0)) * (p2[2].getX(2) - p2[0].getX(2))
			- (p2[2].getX(0) - p2[0].getX(0)) * (p2[1].getX(2) - p2[0].getX(2));
		double cc = (p2[1].getX(0) - p2[0].getX(0)) * (p2[2].getX(1) - p2[0].getX(1))
			- (p2[2].getX(0) - p2[0].getX(0) * (p2[1].getX(1) - p2[0].getX(1)));

		if (a != aa || b != bb || c != cc)
			throw new Exception("Prism: Edges are not parallel\n");
		
		d = (-p2[0].getX(0)) * aa + (-p2[0].getX(1)) * bb + (-p2[0].getX(2)) * cc;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(aa * p2[i].getX(0) + bb * p2[i].getX(1) + cc * p2[i].getX(2) + d) != 0.0) {
				throw new Exception("Prism: Points supposed to be on one plain are not on plain\n");
			}
		}

		this.p1 = p1;
		this.p2 = p2;
		this.n = p1.length;
	}

	public Prism(Point3D[] p) throws Exception {
		if (p.length % 2 != 0)
			throw new Exception("Prism: Array is not correct\n");

		p1 = new Point3D[p.length / 2];
		p2 = new Point3D[p.length / 2];
		n = p.length / 2;
		for (int i = 0; i < n; i++) {
			p1[i] = p[i];
			p2[i] = p[i + n];    
		}

		double a = (p1[1].getX(1) - p1[0].getX(1)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(1) - p1[0].getX(1)) * (p1[1].getX(2) - p1[0].getX(2));
		double b = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(0) - p1[0].getX(0)) * (p1[1].getX(2) - p1[0].getX(2));
		double c = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(1) - p1[0].getX(1))
			- (p1[2].getX(0) - p1[0].getX(0) * (p1[1].getX(1) - p1[0].getX(1)));
		
		double d = (-p1[0].getX(0)) * a + (-p1[0].getX(1)) * b + (-p1[0].getX(2)) * c;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(a * p1[i].getX(0) + b * p1[i].getX(1) + c * p1[i].getX(2) + d) != 0.0) {
				throw new Exception("Prism: Points supposed to be on one plain are not on plain\n");
			}
		}

		double aa = (p2[1].getX(1) - p2[0].getX(1)) * (p2[2].getX(2) - p2[0].getX(2))
			- (p2[2].getX(1) - p2[0].getX(1)) * (p2[1].getX(2) - p2[0].getX(2));
		double bb = (p2[1].getX(0) - p2[0].getX(0)) * (p2[2].getX(2) - p2[0].getX(2))
			- (p2[2].getX(0) - p2[0].getX(0)) * (p2[1].getX(2) - p2[0].getX(2));
		double cc = (p2[1].getX(0) - p2[0].getX(0)) * (p2[2].getX(1) - p2[0].getX(1))
			- (p2[2].getX(0) - p2[0].getX(0) * (p2[1].getX(1) - p2[0].getX(1)));

		if (a != aa || b != bb || c != cc)
			throw new Exception("Prism: Edges are not parallel\n");
		
		d = (-p2[0].getX(0)) * aa + (-p2[0].getX(1)) * bb + (-p2[0].getX(2)) * cc;

		for (int i = 0; i < n - 1; i++) {
			if (Math.round(aa * p2[i].getX(0) + bb * p2[i].getX(1) + cc * p2[i].getX(2) + d) != 0.0) {
				throw new Exception("Prism: Points supposed to be on one plain are not on plain\n");
			}
		}
	}

	public int getN() {
		return n * 2;
	}

	public Point3D[] getP() {
		Point3D[] pp = new Point3D[2 * n];

		for (int i = 0; i < n; i++) {
			pp[i] = p1[i];
		}

		for (int i = 0; i < n; i++) {
			pp[i + n] = p2[i];
		}

		return pp;
	}

	public void setP(Point3D[] p) throws Exception {
		if (p.length % 2 != 0 || n * 2 != p.length)
			throw new Exception("Prism: Array in set is not correct\n");

		for (int i = 0; i < n; i++) {
			p1[i] = p[i];
			p2[i] = p[i + n];
		}
	}

	public Point3D getP(int pos) throws Exception {
		if (pos < 0 || pos >= n * 2)
			throw new Exception("Prism: Position is not correct\n");
		
		if (pos < n)
			return p1[pos];
		else
			return p2[pos - n];
	}

	public void setP(Point3D p, int pos) throws Exception {
		if (pos < 0 || pos >= n * 2)
			throw new Exception("Prism: Position in set is not correct\n");

		if (pos < n)
			p1[pos] = p;
		else
			p2[pos - n] = p;
	}

	@Override
	public double square() throws Exception {
		double s = 0.0;
		for (int i = 0; i < n - 1; i++) {
			double a = Point3D.sub(p1[i], p1[0]).abs();
			double b = Point3D.sub(p1[i + 1], p1[0]).abs();
			double c = Point3D.sub(p1[i], p1[i + 1]).abs();
			double hP = (a + b + c) / 2;
			s += Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));

			a = Point3D.sub(p1[i], p1[i + 1]).abs();
			b = Point3D.sub(p1[i + 1], p2[i + 1]).abs();
			c = Point3D.sub(p2[i + 1], p1[i]).abs();
			hP = (a + b + c) / 2;
			s += Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
		}
		s *= 2;
		return s;
	}

	@Override
	public double volume() throws Exception {
		double a = (p1[1].getX(1) - p1[0].getX(1)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(1) - p1[0].getX(1)) * (p1[1].getX(2) - p1[0].getX(2));
		double b = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(2) - p1[0].getX(2))
			- (p1[2].getX(0) - p1[0].getX(0)) * (p1[1].getX(2) - p1[0].getX(2));
		double c = (p1[1].getX(0) - p1[0].getX(0)) * (p1[2].getX(1) - p1[0].getX(1))
			- (p1[2].getX(0) - p1[0].getX(0) * (p1[1].getX(1) - p1[0].getX(1)));
		
		double d = (-p1[0].getX(0)) * a + (-p1[0].getX(1)) * b + (-p1[0].getX(2)) * c;

		double h = (Math.abs(a * p2[0].getX(0) + b * p2[0].getX(1) + c * p2[0].getX(2) + d) / Math.sqrt(a * a + b * b + c * c));

		double s = 0.0;
		for (int i = 2; i < n; i++) {
			double aa = Point3D.sub(p1[0], p1[i - 1]).abs();
			double bb = Point3D.sub(p1[0], p1[i]).abs();
			double cc = Point3D.sub(p1[i], p1[i - 1]).abs();
			double halfP = (aa + bb + cc) / 2;
			s += Math.sqrt(halfP * (halfP - aa) * (halfP - bb) * (halfP - cc));
		}

		return h * s;
	}

	@Override
	public String toString() {
		String str = "Prism:\nFirst edge:\n";
		for (int i = 0; i < n; i++) {
			str += p1[i].toString() + "\n";
		}
		str += "Second edge:\n";
		for (int i = 0; i < n; i++) {
			str += p2[i].toString() + "\n";
		}

		return str;
	}
}
