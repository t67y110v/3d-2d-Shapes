public class Segment extends OpenFigure {
	private Point2D start;
	private Point2D finish;

	public Segment(Point2D a, Point2D b) throws Exception {
		if (a.getX(0) == b.getX(0) && a.getX(1) == b.getX(1)) {
			throw new Exception("Segment: Start and finish are the same point\n");
		}
		start = a;
		finish = b;
	}

	public Point2D getStart() {
		return start;
	}

	public void setStart(Point2D p) {
		start = p;
	}

	public Point2D getFinish() {
		return finish;
	}

	public void setFinish(Point2D p) {
		finish = p;
	}

	@Override
	public double length() throws Exception {
		return (new Point2D(Point.sub(start, finish).getX())).abs();
	}

	@Override
	public Segment shift(Point2D a) throws Exception {
		start.add(a);
		finish.add(a);
		return this;
	}

	@Override
	public Segment rot(double phi) throws Exception {
		start.rot(phi);
		finish.rot(phi);
		return this;
	}

	@Override
	public Segment symAxis(int i) throws Exception {
		start.symAxis(i);
		finish.symAxis(i);
		return this;
	}

	@Override
	public boolean cross(IShape i) throws Exception {
		if (i instanceof Segment) {
			Point2D a = ((Segment) i).getStart();
			Point2D b = ((Segment) i).getFinish();

			Point2D[] aa = new Point2D[2];
			aa[0] = a;
			aa[1] = b;
			Point2D[] bb = new Point2D[2];
			bb[0] = start;
			bb[1] = finish;

			for (int k = 0; k < 2; k++) {
				for (int j = 0; j < 2; j++) {
					if (aa[k].getX(0) == bb[j].getX(0) && aa[k].getX(1) == bb[j].getX(1))
						return true;
				}
			}

			double a1 = (a.getX(0) - start.getX(0)) * (finish.getX(1) - start.getX(1))
				- (finish.getX(0) - start.getX(0)) * (a.getX(1) - start.getX(1));
			double a2 = (b.getX(0) - a.getX(0)) * (finish.getX(1) - a.getX(1))
				- (finish.getX(0) - a.getX(0)) * (b.getX(1) - a.getX(1));
			double a3 = (start.getX(0) - b.getX(0)) * (finish.getX(1) - b.getX(1))
				- (finish.getX(0) - b.getX(0)) * (start.getX(1) - b.getX(1));

			if (a1 <= 0 && a2 <= 0 && a3 <= 0 || a1 >= 0 && a2 >= 0 && a3 >= 0)
				return false;
			a3 = (b.getX(0) - start.getX(0)) * (finish.getX(1) - start.getX(1))
			- (finish.getX(0) - start.getX(0)) * (b.getX(1) - start.getX(1));
			if (a1 * a3 <= 0)
				return true;

			return false;
		} else if (i instanceof Polyline) {
			Point2D[] coords = ((Polyline) i).getP();
			Segment seg;
			for (int j = 1; j < coords.length; j++) {
				seg = new Segment(coords[j - 1], coords[j]);
				if (seg.cross(this))
					return true;
			}
			seg = new Segment(coords[coords.length - 1], coords[0]);
			return seg.cross(this);
		} else if (i instanceof NGon) {
			Point2D[] coords = ((NGon) i).getP();
			Segment seg;

			double[] arr1 = new double[coords.length];
			double[] arr2 = new double[coords.length];

			for (int j = 1; j < coords.length; j++) {
				seg = new Segment(coords[j - 1], coords[j]);
				arr1[j - 1] = (start.getX(0) - coords[j - 1].getX(0)) * (coords[j].getX(1) - coords[j - 1].getX(1))
					- (start.getX(1) - coords[j - 1].getX(1)) * (coords[j].getX(0) - coords[j - 1].getX(0));
				arr2[j - 1] = (finish.getX(0) - coords[j - 1].getX(0)) * (coords[j].getX(1) - coords[j - 1].getX(1))
					- (finish.getX(1) - coords[j - 1].getX(1)) * (coords[j].getX(0) - coords[j - 1].getX(0));
				if (seg.cross(this))
					return true;
			}
			seg = new Segment(coords[coords.length - 1], coords[0]);
			arr1[coords.length - 1] = (start.getX(0) - coords[coords.length - 1].getX(0)) * (coords[0].getX(1) - coords[coords.length - 1].getX(1))
				- (start.getX(1) - coords[coords.length - 1].getX(1)) * (coords[0].getX(0) - coords[coords.length - 1].getX(0));
			arr2[coords.length - 1] = (finish.getX(0) - coords[coords.length - 1].getX(0)) * (coords[0].getX(1) - coords[coords.length - 1].getX(1))
				- (finish.getX(1) - coords[coords.length - 1].getX(1)) * (coords[0].getX(0) - coords[coords.length - 1].getX(0));

			boolean checker = true;

			for (int k = 1; k < coords.length; k++) {
				if (arr1[0] * arr1[k] < 0) {
					checker = false;
					break;
				}

				if (arr2[0] * arr2[k] < 0) {
					checker = false;
					break;
				}
			}
			
			return (checker | seg.cross(this));
		} else {
			// this is for Circle

			Point2D c = ((Circle) i).getP();
			double r = ((Circle) i).getR();

			if ((new Segment(c, start)).length() <= r || (new Segment(c, finish)).length() <= r) {
				return true;
			}
			return false;
		}
	}

	@Override
	public String toString() {
		String str = String.format("Segment = (%s, %s)", start.toString(), finish.toString());
		return str;
	}
}
