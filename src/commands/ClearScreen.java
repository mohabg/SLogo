package commands;
//This entire file is part of my masterpiece
//Mohab Gabal
/*
 * This is included as an example of the ease with which a new command can be included. In order to
 * clear the screen, using our design, all that is necessary is to reset the turtle's position, orientation,
 * and lines to what they were when it was constructed.
 */
public class ClearScreen extends CommandNode {

	public ClearScreen(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		double distanceMoved = Math.sqrt(Math.pow(getTurtle().getX(), 2) + Math.pow(getTurtle().getY(), 2));
		getTurtle().move(0, 0);
		getTurtle().setOrientation(0);
		getTurtle().setVisible();
		getTurtle().clearLines();
		return distanceMoved;
	}

}
