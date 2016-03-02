package commands;

public class Difference extends CommandNode {

    public Difference (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
        return getChildren().get(0).run() - getChildren().get(1).run();
    }

}
