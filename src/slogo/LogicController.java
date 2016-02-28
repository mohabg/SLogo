package slogo;

public class LogicController {
    private Interpreter interpreter;

    public LogicController () {
        // TODO Auto-generated constructor stub
    }

    public void update(CommandNode command){
    	command.run();
    }
}
