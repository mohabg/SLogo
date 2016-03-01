package commands;

public class Variable extends CommandNode{

	public Variable(double val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		// TODO Auto-generated method stub
		return getValue();
	}

}
