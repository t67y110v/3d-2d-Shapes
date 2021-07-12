import java.lang.Math;

public class NGon implements IShape {
	protected int n;
	protected Point2D[] p;

	public NGon(Point2D[] p) throws Exception {
		if (p.length <= 2)
			throw new Exception("NGon: Array of points is too small\n");
		this.n = p.length;
		this.p = p;
	}

	public int getN() {
		return n;
	}

	public Point2D[] getP() {
		return p;
	}

	public Point2D getP(int pos) throws Exception {
		if (pos < 0 || pos >= n)
			throw new Exception("NGon: Pos in getP is not valid\n");

		return p[pos];
	}

	public void setP(Point2D[] p) throws Exception {
		if (p.length != n) 
			throw new Exception("NGon: Array size in setP is not correct\n");
		
		this.p = p;
	}

	public void setP(Point2D p, int pos) throws Exception {
		if (pos < 0 || pos >= n)
			throw new Exception("NGon: Pos in setP is not correct\n");

		this.p[pos] = p;
	}

	@Override
	public double square() throws Exception {
		double s = 0.;
		for (int i = 2; i < n; i++) {
			double a = Point2D.sub(p[0], p[i - 1]).abs();
			double b = Point2D.sub(p[0], p[i]).abs();
			double c = Point2D.sub(p[i], p[i - 1]).abs();
			double halfP = (a + b + c) / 2;
			s += Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
		}

		return s;
	}

	@Override
	public double length() throws Exception {
		double len = 0;
		for (int i = 1; i < n; i++) {
			len += Point2D.sub(p[i - 1], p[i]).abs();
		}
		len += Point2D.sub(p[n - 1], p[0]).abs();
		return len;
	}

	@Override
	public NGon shift(Point2D p) throws Exception {
		for (int i = 0; i < n; i++) {
			this.p[i].add(p);
		}

		return this;
	}

	@Override
	public NGon rot(double phi) throws Exception {
		for (int i = 0; i < n; i++) {
			this.p[i].rot(phi);
		}

		return this;
	}

	@Override
	public NGon symAxis(int i) throws Exception {
		for (int j = 0; j < n; j++) {
			this.p[j].symAxis(i);
		}

		return this;
	}


	@Override
	public boolean cross(IShape shape) throws Exception {
		Segment seg;
		for (int i = 1; i < n; i++) {
			seg = new Segment(p[i - 1], p[i]);
			if (seg.cross(shape))
				return true;
		}

		System.out.println(n);

		seg = new Segment(p[n - 1], p[0]);
		if (seg.cross(shape))
			return true;

		return false;
	}

	@Override
	public String toString() {
		String str = String.format("NGon(%d):\n", n);
		for (int i = 0; i < n; i++) {
			str += p[i].toString() + ",\n";
		}
		return str;
	}
}
