package slogo;
public class Forward extends Command {

	private Double pixels;
	
	public Forward(Double pixels, int iterations) {
		super(iterations);
		this.pixels = pixels;
	}

	public void run(Turtle turtle){
		turtle.setX(turtle.getX() + pixels*Math.sin(turtle.getOrientation()));
		turtle.setX(turtle.getY() + pixels*Math.cos(turtle.getOrientation()));
	}
}
