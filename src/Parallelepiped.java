public class Parallelepiped extends Prism {
	public Parallelepiped(Point3D[] p) throws Exception {
		super(p);

		if (n != 4)
			throw new Exception("Parallelepiped: Array size is incorrect\n");
	}

	@Override
	public double square() throws Exception {
		double a = Point3D.sub(p1[0], p2[0]).abs();
		double b = Point3D.sub(p1[0], p1[1]).abs();
		double c = Point3D.sub(p1[1], p2[2]).abs();
		return 2 * (a * b + a * c + b * c);
	}

	@Override
	public double volume() throws Exception {
		double a = Point3D.sub(p1[0], p2[0]).abs();
		double b = Point3D.sub(p1[0], p1[1]).abs();
		double c = Point3D.sub(p1[1], p2[2]).abs();
		return a * b * c;
	}
}
