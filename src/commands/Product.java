package commands;

public class Product extends CommandNode {

    public Product (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
        setValue(getChildren().get(0).run() * getChildren().get(1).run());
        return getValue();
    }

}
