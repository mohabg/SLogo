package commands;

public class SetPalette extends CommandNode {

	public SetPalette(double val) {
		super(val);
		setParametersNeeded(4);
	}

	@Override
	public double run() {
		// setValue(getChildren().get(0).run());
		// get model, set palette here
		
		double[] params = new double[4];
		for(int i = 0; i < 4; i++)
			params[i] = getChildren().get(i).run();
		
		getColorSetter().addPaletteColor(params[0], params[1], params[2], params[3]);
		return params[0];
		// return 0;
	}

}
