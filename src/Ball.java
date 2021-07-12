import java.lang.Math;

public class Ball implements IFigure {
    private Point3D p;
    private double r;

    Ball(Point3D p, double r) throws Exception {
        if (r <= 0)
            throw new Exception("Ball: incorrect radius\n");

        this.p = p;
        this.r = r;
    }

    public Point3D getP() {
        return p;
    }

    public void setP(Point3D p) throws Exception {
        this.p = p;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) throws Exception {
        if (r <= 0)
            throw new Exception("Ball: Incorrect radius in set\n");

        this.r = r;
    }

    @Override
    public double square() throws Exception {
        return 4 * Math.PI * r * r;
    }

    @Override
    public double volume() throws Exception {
        return (4 / 3) * Math.PI * r * r * r;
    }

    @Override
    public String toString() {
        return String.format("Ball: radius = %d, center = %s", r, p.toString());
    }
}
