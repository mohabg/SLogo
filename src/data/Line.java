package data;

public class Line {
	Point a, b;

	public Line(Point a, Point b) {
		this.a = a.clone();
		this.b = b.clone();
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}
}
