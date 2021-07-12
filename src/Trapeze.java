public class Trapeze extends QGon {
	public Trapeze(Point2D[] p) throws Exception {
		super(p);

		if (Point2D.multi(Point2D.sub(p[0], p[1]), Point2D.sub(p[2], p[3])) / (Point2D.sub(p[2], p[3]).abs() * Point2D.sub(p[0], p[1]).abs()) != 1
			|| Point2D.multi(Point2D.sub(p[0], p[3]), Point2D.sub(p[1], p[2])) / (Point2D.sub(p[0], p[3]).abs() * Point2D.sub(p[1], p[2]).abs()) != 1)
			throw new Exception("Trapeze: Sides of QGon are not parallel");
	}

	@Override
	public double square() throws Exception {
		return super.square();
	}
}
