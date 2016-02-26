package slogo;
import javafx.scene.image.Image;

public class Turtle {

	//public static final Image DEFAULT_IMAGE

	private double xCord;
	private double yCord;
	private double orientation;
	private Image myImage;
	private boolean penDown;
	// In case user wants to resize turtle
	//private double height;
	//private double width;

	public Turtle() {
		xCord = 0;
		yCord = 0;
		orientation = 0;
		penDown = true;
	}

	public double getX(){
		return xCord;
	}
	public double getY(){
		return yCord;
	}
	public double getOrientation(){
		return orientation;
	}
	public Image getImage(){
		return myImage;
	}
	public void setX(Double x){
		xCord = x;
	}
	public void setY(Double y){
		yCord = y;
	}
	public void turn(Double angle){
		orientation += angle;
	}
}
