package commands;

import java.util.Stack;

public class Variable extends CommandNode{
	
	private Stack<Double> variableValues;
	private boolean settingInitialVariableValue = false;
	
	public Variable(double val) {
		super(val);
		variableValues = new Stack<Double>();
	}
	
	@Override
	public void setValue(double value){
		if(!settingInitialVariableValue){
			settingInitialVariableValue = true;
			super.setValue(value);
		}
		variableValues.push(value);
	}
	
	@Override 
	public double getValue(){
		if(variableValues.size() == 0){
			return super.getValue();
		}
		return variableValues.peek();
	}
	
	public Stack<Double> getVariableStack(){
		return variableValues;
	}
	@Override
	public double run() {
		// TODO Auto-generated method stub
		return getValue();
	}

}
