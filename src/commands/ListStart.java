package commands;

public class ListStart extends CommandNode{

	public ListStart(double val) {
		super(val);
	}

	@Override
	public double run() {
		double lastExecutedCommandValue = 0;
		for(int i = 0; i < getChildren().size() - 1; i++){
			lastExecutedCommandValue = getChildren().get(i).run();
		}
		return lastExecutedCommandValue;
	}

}
