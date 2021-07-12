import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
	public static Scanner cin = new Scanner(System.in);
	public static TreeSet<String> shapes = new TreeSet<>() {{
		add("Circle");
		add("Segment");
		add("TGon");
		add("NGon");
		add("QGon");
		add("Trapeze");
		add("Rectangle");
		add("Polyline");
	}
	};

	public static TreeSet<String> figures = new TreeSet<>() {{
		add("Ball");
		add("Parallelepiped");
		add("Prism");
		add("Pyramid");
	}
	};

	public static TreeSet<String> moves = new TreeSet<>() {{
		add("Rot");
		add("SymAsix");
		add("Shift");
	}
	};

	public static List<IShape> list = new ArrayList<>();
	public static List<IShape> additionalList = new ArrayList<>();
	public static List<String> types = new ArrayList<>();
	public static List<IFigure> List3D = new ArrayList<>();

	public static Point insertPoint(int n) throws Exception {
		double[] arr = new double[n];
		System.out.println("Insert Point's coordinates (one by line):");
		for (int i = 0; i < n; i++) {
			arr[i] = cin.nextDouble();
		}
		if (n == 2)
			return new Point2D(arr);
		else if (n == 3)
			return new Point3D(arr);

		return new Point(n, arr);
	}

	public static IShape addCircle() throws Exception {
		Point2D point = (Point2D) insertPoint(2);
		System.out.println("Insert radius:");
		double r = cin.nextDouble();
		return new Circle(point, r);
	}

	public static IShape addNGon() throws Exception {
		System.out.println("Insert number of vertices:");
		int n = cin.nextInt();
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < n; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new NGon(points);
	}

	public static IShape addQGon() throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < 4; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new QGon(points);
	}

	public static IShape addRectangle() throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < 4; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new Rectangle(points);
	}

	public static IShape addTrapeze() throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < 4; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new Trapeze(points);
	}

	public static IShape addPolyline() throws Exception {
		System.out.println("Insert number of vertices:");
		int n = cin.nextInt();
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < n; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new Polyline(points);
	}

	public static IShape addTGon() throws Exception {
		int n = 3;
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < n; i++) {
			points[i] = (Point2D) insertPoint(2);
		}
		return new TGon(points);
	}

	public static IShape addSegment() throws Exception {
		return new Segment((Point2D)insertPoint(2), (Point2D)insertPoint(2));
	}

	public static IShape getShape(String type) throws Exception {
		if (type.equals("Circle")) {
			return addCircle();
		} else if (type.equals("Segment")) {
			return addSegment();
		} else if (type.equals("TGon")) {
			return addTGon();
		} else if (type.equals("NGon")) {
			return addNGon();
		} else if (type.equals("QGon")) {
			return addQGon();
		} else if (type.equals("Trapeze")) {
			return addTrapeze();
		} else if (type.equals("Rectangle")) {
			return addRectangle();
		} else if (type.equals("Polyline")) {
			return addPolyline();
		} else {
			throw new Exception("Something went wrong\n");
		}
	}

	public static IFigure addBall() throws Exception {
		return new Ball((Point3D) insertPoint(3), cin.nextDouble());
	}

	public static IFigure addParallelepiped() throws Exception {
		System.out.println("Insert number of vertices:");
		int n = cin.nextInt();
		Point3D[] p1 = new Point3D[n];
		for (int i = 0; i < n; i++) {
			p1[i] = (Point3D) insertPoint(3);
		}

		return new Parallelepiped(p1);
	}

	public static IFigure addPrism() throws Exception {
		System.out.println("Insert number of vertices:");
		int n = cin.nextInt();
		Point3D[] p1 = new Point3D[n];
		for (int i = 0; i < n; i++) {
			p1[i] = (Point3D) insertPoint(3);
		}

		return new Prism(p1);
	}

	public static IFigure addPyramid() throws Exception {
		System.out.println("Insert number of vertices:");
		int n = cin.nextInt();
		Point3D[] p1 = new Point3D[n];
		for (int i = 0; i < n; i++) {
			p1[i] = (Point3D) insertPoint(3);
		}

		return new Pyramid(p1);
	}

	public static IFigure getFirgure(String type) throws Exception {
		if (type.equals("Ball")) {
			return addBall();
		} else if (type.equals("Parallelepiped")) {
			return addParallelepiped();
		} else if (type.equals("Prism")) {
			return addPrism();
		} else if (type.equals("Pyramid")) {
			return addPyramid();
		} else {
			throw new Exception("Something went wrong (getFigure)\n");
		}
	}

	public static IShape rotate(IShape shape) throws Exception {
		System.out.println("Insert angle:");
		return shape.rot(cin.nextDouble());
	}

	public static IShape symmetric(IShape shape) throws Exception {
		System.out.println("Insert axis which to make symmetric by:");
		return shape.symAxis(cin.nextInt());
	}

	public static IShape shift(IShape shape) throws Exception {
		return shape.shift((Point2D) insertPoint(2));
	}

	public static IShape moveFigure(IShape shape, String move) throws Exception {
		if (move.equals("Rot")) {
			return rotate(shape);
		} else if (move.equals("SymAxis")) {
			return symmetric(shape);
		} else if (move.equals("Shift")) {
			return shift(shape);
		} else {
			throw new Exception("Something went wrong (movement)\n");
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Insert number of figures:");
			int n = cin.nextInt();

			System.out.println("Types of figures can be one of follwing:");

			for (String str: shapes) {
				System.out.printf("%s\n", str);
			}
			
			for (int i = 0; i < n; i++) {
				System.out.println("Insert type of figure:");

				String type = cin.next();
				if (!shapes.contains(type)) {
					System.out.println("There is no figure matching your name. Please enter again:");
					i--;
					continue;
				}
				System.out.println("Insert figure:");
				list.add(getShape(type));
				types.add(type);
			}

			double s = 0.0;
			double l = 0.0;
			for (int i = 0; i < list.size(); i++) {
				s += list.get(i).square();
				l += list.get(i).length();
			}

			System.out.printf("Summary square: %.2f\n", s);
			System.out.printf("Summary length: %.2f\n", l);
			if (list.size() != 0)
				System.out.printf("Average square: %.2f\n", s / ((double) list.size()));

			for (int i = 0; i < n; i++) {
				System.out.println("Insert figure:");
				IShape shape = getShape(types.get(i));
				if (shape.cross(list.get(i)))
					System.out.println("Crosses");
				else
					System.out.println("Not crosses");
				
				additionalList.add(shape);
			}
			// should be one loop or not ?

			System.out.println("Movement can be one of follwing:");

			for (String str: moves) {
				System.out.printf("%s\n", str);
			}

			for (int i = 0; i < n; i++) {
				System.out.println("Insert move:");
				String move = cin.next();
				if (!moves.contains(move)) {
					System.out.println("There is no movement matching your input. Please enter again:");
					i--;
					continue;
				}

				if (moveFigure(additionalList.get(i), move).cross(list.get(i)))
					System.out.println("Crosses");
				else
					System.out.println("Not crosses");
			}

			System.out.println("Insert number of figures:");
			n = cin.nextInt();

			s = 0.0;
			l = 0.0;
			for (int i = 0; i < n; i++) {
				System.out.println("Insert type of figure:");
				String type = cin.next();

				if (!figures.contains(type)) {
					System.out.println("There is no figure matching your name. Please enter again:");
					i--;
					continue;
				}
				System.out.println("Insert figure:");
				IFigure figure = getFirgure(type);
				s += figure.square();
				l += figure.volume();
			}
			
			System.out.printf("Summary volume: %.2f\n", l);
			System.out.printf("Summary square of edges: %.2f\n", s);
			if (n != 0)
				System.out.printf("Average square of edges: %.2f\n", s / ((double)n));			
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
