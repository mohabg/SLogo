package slogo;

import java.util.ArrayList;
import java.util.List;

import commands.CommandNode;

public class CommandIterator {
	
	public CommandIterator(){
	}
	
	public List<CommandNode> iterate(CommandNode command, List<CommandNode> allCommandsInTree){
		for(CommandNode commandInTree : command.getChildren()){
			if(!allCommandsInTree.contains(commandInTree)){
				allCommandsInTree.add(commandInTree);
				allCommandsInTree = iterate(commandInTree, allCommandsInTree);
			}
		}
		return allCommandsInTree;
	}
}
