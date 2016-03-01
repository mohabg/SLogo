
package commands;

public class Constant extends CommandNode{
	
	public Constant(double val) {
		super(val);
	}
	void setParameters() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public double run() {
		return getValue();
		
	}

}