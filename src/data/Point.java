package data;

public class Point {
	private double x, y, theta;
	private final double DEGREES = 360.0;

	public Point(double x, double y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getTheta() {
		return theta % DEGREES;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public Point clone() {
		return new Point(x, y, theta);
	}
}
