
public class Forward extends TurtleCommand {

	private Double pixels;
	
	public Forward(Double pixels) {
		this.pixels = pixels;
	}

	public void run(Turtle turtle){
		turtle.setX(turtle.getX() + pixels*Math.sin(turtle.getOrientation()));
		turtle.setX(turtle.getY() + pixels*Math.cos(turtle.getOrientation()));
	}
}
