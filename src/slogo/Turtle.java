package slogo;

import java.util.ArrayList;
import java.util.List;
import data.Line;
import data.Point;
import data.TurtleData;
import javafx.scene.image.Image;


public class Turtle implements TurtleData {

    private static final double DEFAULT_THICKNESS = 1;
    private static final double DEFAULT_COLOR = 0;
    private static final Point HOME = new Point(0, 0, 0);
    private static final int TURTLE_WIDTH = 30;
    private static final int TURTLE_HEIGHT = 30;
    private static final Image DEFAULT_IMAGE =
            new Image("resources/turtle.png", TURTLE_WIDTH, TURTLE_HEIGHT, true, true);
    private static final double CANVAS_RATIO = 1.0/4.0;

    private Point position;
    private Image myImage;
    private Boolean visible;
    private Boolean fence;
    private Boolean penDown;
    private double penColor;
    private double penThickness;
    private double shape;
    private int ID;
    private List<Line> lines = new ArrayList<Line>();

    private String GUID = java.util.UUID.randomUUID().toString();

    public String getGUID () {
        return GUID;
    }

    public Turtle (int ID) {
        myImage = DEFAULT_IMAGE;
        position = HOME.clone();
        penDown = true;
        visible = true;
        fence = false;
        penThickness = DEFAULT_THICKNESS;
        penColor = DEFAULT_COLOR;
        this.ID = ID;
    }

    public void move (double x, double y) {
        Point oldPos = position.clone();

        position.setX(x);
        position.setY(y);
        
        if (fence){        	
        	if (position.getX() > Integer.parseInt(Main.MainResources.getString("width"))*CANVAS_RATIO){
        		position.setX(Integer.parseInt(Main.MainResources.getString("width"))*CANVAS_RATIO);
        	}
        	if (position.getX() < -1*Integer.parseInt(Main.MainResources.getString("width"))*CANVAS_RATIO){
        		position.setX(-1*Integer.parseInt(Main.MainResources.getString("width"))*CANVAS_RATIO);
        	}
        	if(position.getY() > Integer.parseInt(Main.MainResources.getString("height"))*CANVAS_RATIO){
        		position.setY(Integer.parseInt(Main.MainResources.getString("height"))*CANVAS_RATIO);
        	}
        	if(position.getY() < -1*Integer.parseInt(Main.MainResources.getString("height"))*CANVAS_RATIO){
        		position.setY(-1*Integer.parseInt(Main.MainResources.getString("height"))*CANVAS_RATIO);
        	}
        }
        System.out.println("turtle x position: " + position.getX());
    	System.out.println("turtle y position: " + position.getY());
    	Point curPos = position.clone();

        if (isPenDown())
            lines.add(new Line(oldPos, curPos, getPenColor(), getPenThickness()));
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

    public void setPenThickness (double thickness) {
        penThickness = thickness;
    }

    public void setPenColor (double color) {
        penColor = color;
    }

    public void turn (Double angle) {
        position.setTheta(position.getTheta() + angle);
    }

    public List<Line> getLines () {
        return lines;
    }

    public void clearLines () {
        lines.clear();
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
    public double getPenThickness () {
        return penThickness;
    }

    @Override
    public double getPenColor () {
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
    public int getID () {
        return ID;
    }

    public void setShape (double shape) {
        this.shape = shape;
    }
    public void setFence(boolean fence){
    	this.fence = fence;
    }
}
