public class TGon extends NGon {
	public TGon(Point2D[] p) throws Exception {
        super(p);
		if (p.length != 3)
			throw new Exception("TGon: Array size is not correct\n");
	}

	@Override
	public double square() throws Exception {
		double s = 0.0;
		double a = Point2D.sub(p[0], p[1]).abs();
		double b = Point2D.sub(p[0], p[2]).abs();
		double c = Point2D.sub(p[2], p[1]).abs();
		double halfP = (a + b + c) / 2;
		s += Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
		return s;
	}
}
