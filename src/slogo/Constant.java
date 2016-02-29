package slogo;

public class Constant extends CommandNode{
	
	public Constant(double val) {
		super(val);
	}
	@Override
	void setParameters() {
		// TODO Auto-generated method stub	
	}
	@Override
	double run() {
		return getValue();
	}
}
