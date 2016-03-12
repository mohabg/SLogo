package data;

public class Line {
    private Point a, b;
    private double color;
    private double thickness;

    @Deprecated
    public Line (Point a, Point b) {
        this.a = a.clone();
        this.b = b.clone();
    }

    public Line (Point a, Point b, double d, double e) {
        this.a = a.clone();
        this.b = b.clone();
        this.color = d;
        this.thickness = e;
    }

    public Point getA () {
        return a;
    }

    public Point getB () {
        return b;
    }

    public double getColor () {
        return color;
    }

    public double getThickness () {
        return thickness;
    }
}
