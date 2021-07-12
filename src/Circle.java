import java.lang.Math;

public class Circle implements IShape {
    private Point2D p;
    private double r;

    public Circle(Point2D p, double r) {
        this.p = p;
        this.r = r;
    }

    public Point2D getP() {
        return p;
    }

    public double getR() {
        return r;
    }

    public void setP(Point2D p) {
        this.p = p;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double square() {
        return Math.PI * r * r;
    }

    @Override
    public double length() throws Exception {
        return 2 * Math.PI * r;
    }

    @Override
    public Circle shift(Point2D a) throws Exception {
        p.add(a);
        return this;
    }

    @Override
    public Circle rot(double phi) throws Exception {
        p.rot(phi);
        return this;
    }

    @Override
    public Circle symAxis(int i) throws Exception {
        p.symAxis(i);
        return this;
    }

    @Override
    public boolean cross(IShape s) throws Exception {
        if (s instanceof Circle) {
            Point2D curP = ((Circle) s).getP();
            double curR = ((Circle) s).getR();
            if ((Point2D.sub(curP, p)).abs() <= curR + r) {
                return true;
            }
            return false;
        }
        return s.cross(this);
    }
    
    @Override
    public String toString() {
        return String.format("Circle center=%s, radius=%d", p.toString(), r);
    }
}
