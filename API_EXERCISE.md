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
