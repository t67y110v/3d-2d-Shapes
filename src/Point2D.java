import java.lang.Math;

public class Point2D extends Point {
	public Point2D() throws Exception {
		super(2);
	}

	public Point2D(double[] x) throws Exception {
		super(2, x);
	}

	public static Point2D rot(Point2D p, double phi) throws Exception {
		double angle;
		if (p.getX(0) >= 0) {
			angle = Math.asin(p.getX(1) / p.abs());
		} else {
			angle = Math.PI - Math.asin(p.getX(1) / p.abs());
		}

		p.setX(0, Math.cos(angle + phi));
		p.setX(1, Math.sin(angle + phi));

		return p;
	}

	public Point2D rot(double phi) throws Exception {
        double angle;
        if (x[0] >= 0) {
            angle = Math.asin(x[1] / abs());
        } else {
            angle = Math.PI - Math.asin(x[1] / abs());
        }

        x[0] = Math.cos(angle + phi);
        x[1] = Math.sin(angle + phi);

        return this;
    }
}
