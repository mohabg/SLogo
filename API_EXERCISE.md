Review of CellSociety team 6

API Method Analysis
===================

Internal
========

###Simulation
Cell: findAdjacentCells

###Configuration
MapBuilder: getWidth, setWidth, getOffset/setOffset (x and y)


###Visualization
GridBuilder class- API External: make()

External
=========

###Simulation
Simulation class: Pause, Play, Step, Constructor
Cell class: constructor, setGrid, updateBefore, updateAfter


###Configuration
GridBuilder class: makeCellMap
MapBuilder: get/setConfigs

###Visualization
GUI: loadFunction, saveFunction

Non-API
========

###Simulation
The methods in the simulation class shouldn't be in the API because in order to extend to another simulation, this class doesn't need 
to be altered. Allowing the API user access to this class might compromise the functionality of the simulation overall.

###Configuration


###Visualization
The user shouldn't have access to the methods that change the grid shape because the functionality of the cells and grids would be
compromised.


SLogo Architechture Design
=========================

###What does parsing need to take place and what does it need to start properly
Parsing occurs when the user presses enter on the GUI. This passes down the command as a string to the backend. The parser has memory to remember the turtle's state between commands.

###What is the result of parsing and who receives it
The parser will either throw an error, or it will return a package describing the current simulation state. This will include data such as the turtle's position, lines that have been drawn, backrground color.

###When are errors detected and how are they reported
Errors can be thrown by the parser or the controller in the backend. The will throw exceptions that need to be handled by the GUI. The frontend handles these errors by popping up a dialog box and not completing the command.

###What do commands know, when do they know it, and how do they get it?
Each command has access to the simulation controller, which manages the current state.

###How is the GUI updated after a command has completed execution
The GUI is returned the state of the simulation after a command is executed. This state package will hold all the data that the GUI needs to draw.