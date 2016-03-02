package commands;

public class Minus extends CommandNode {

    public Minus (double val) {
        super(val);
        setParametersNeeded(1);
    }

    @Override
    public double run () {
        setValue(-1 * getChildren().get(0).run());
        return getValue();
    }

}
