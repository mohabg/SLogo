package slogo;

import java.util.ArrayList;
import java.util.List;
import data.Point;
import data.TurtleData;
import javafx.scene.image.Image;

public class Turtle implements TurtleData {

	private static final double DEFAULT_THICKNESS = 1;
	private static final double DEFAULT_COLOR = 1;
	private static final Point HOME = new Point(0, 0, 0);
	private static final int TURTLE_WIDTH = 30;
	private static final int TURTLE_HEIGHT = 30;
	private static final Image DEFAULT_IMAGE = new Image("resources/turtle.png", TURTLE_WIDTH, TURTLE_HEIGHT, true, true);; // TODO: make non-constant

	private Point position;
	private Image myImage;
	private Boolean visible;
	private Boolean penDown;
	private double penColor;
	private double penThickness;
	private double shape;
	private int ID;
	private List<Point> points = new ArrayList<Point>();


	public Turtle (int ID) {
		myImage = DEFAULT_IMAGE;
		position = HOME;
		penDown = true;
		visible = true;
		penThickness = DEFAULT_THICKNESS;
		penColor = DEFAULT_COLOR;
		this.ID = ID;
	}

	public void move (double x, double y) {
		System.out.println("turtle " + x + " " + y);
		setX(x);
		setY(y);
		points.add(position.clone());
	}

	public void setImage (String path) {
		myImage = new Image(path, TURTLE_WIDTH, TURTLE_HEIGHT, true, true);
	}

	public Point getPosition () {
		return position.clone();
	}

	public double getX () {
		return position.getX();
	}

	public double getY () {
		return position.getY();
	}

	public double getOrientation () {
		return position.getTheta();
	}

	public void setOrientation (double orientationToSet) {
		position.setTheta(orientationToSet);
	}

	private void setX (double x) {
		position.setX(x);
	}

	private void setY (double y) {
		position.setY(y);
	}

	public void setPenThickness(double thickness){
		penThickness = thickness;
	}

	public void setPenColor(double color){
		penColor = color;
	}

	public void turn (Double angle) {
		position.setTheta(position.getTheta() + angle);
	}

	public List<Point> getPoints () {
		return points;
	}

	public void clearPoints () {
		points.clear();
	}

	public void setPenDown () {
		penDown = true;
	}

	public void setPenUp () {
		penDown = false;
	}

	public void setVisible () {
		visible = true;
	}

	public void setInvisible () {
		visible = false;
	}
	@Override
	public Image getImage () {
		return myImage;
	}
	@Override
	public double getPenThickness(){
		return penThickness;
	}

	@Override
	public double getPenColor(){
		return penColor;
	}

	@Override
	public Boolean isPenDown () {
		return penDown;
	}

	@Override
	public Boolean isVisible () {
		return visible;
	}

	@Override
	public double getShape () {
		return shape;
	}

	@Override
	public boolean containsPoint (Point pos) {
		double posX = position.getX();
		double posY = position.getY();

		double deltaX = Math.abs(pos.getX() - posX);
		double deltaY = Math.abs(pos.getY() - posY);
		double deltaXBound = myImage.getWidth() / 2;
		double deltaYBound = myImage.getHeight() / 2;
		return (deltaX < deltaXBound) && (deltaY < deltaYBound);
	}

	@Override
	public int getID() {
		return ID;
	}
}
