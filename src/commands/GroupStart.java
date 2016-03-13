package commands;

public class GroupStart extends CommandNode{

	public GroupStart(double val) {
		super(val);
	}

	@Override
	public boolean shouldSetParametersForChildren(){
		return true;
	}
	
	@Override
	public double run() {
		CommandNode command = getChildren().get(0);
		return command.run();
	}

}
