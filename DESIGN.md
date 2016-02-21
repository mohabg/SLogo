Introduction
==========

The intention of this project is to create a user friendly Integrated Development Environment (IDE) for inexperienced programmers to 
control the actions of a turtle. The project will enable the user to give a few, simple commands within the IDE that will then be 
processed in the backend to update the turtle based on the user’s input and then render the changes within the user interface. 

The primary goals of the design are to create an interface with simple syntax that can create more complex motion for the turtle, 
requiring flexibility in the logic of translating commands to actions, as well as flexibility in the controller of the turtle to allow 
for complex actions.


The project will be designed such that in the front end TALK ABOUT OPEN/CLOSED IN FRONT END
The intermediary between the front and back end will be closed, parsing the code inputted by the user in the same manner regardless of 
what commands are inputted and outputting that information to the logic. It is within the logic and controller that the user will have 
more ability to add functionality. Openness in these parts of the code will allow for additional commands and actions for the turtle to 
be implemented. The turtle itself, however, will be closed, simply holding the information delegated to it by the controller (such as 
position) and acting as an object for the controller to delegate to.

Design Overview
==============
How internal/external front end/back end work together

###API Details

#Front End
Internal
* Draw turtle - Draw the turtle given an image file on the canvas
* Draw lines - Draw the lines and colors on the canvas given a list of lines on the screen
* Send to parser - Handle when the user presses execute or enter, and sends the string command to the parser, then updates the canvas
* Update language - Handle when the user selects a new language within the drop down menu
* Display help page - Show a window with an HTML page that describes the functionality of our program and SLogo
* Handle error - Given a string message, print red error text in the command window

External
* Execute script - A user presses the execute button on the script pane, which sends the script to the parser to be executed
* Get declared functions - Get the functions that the user has declared either in the script or command window and display to the user
* Get declared variables - Get the variables the user has defined 
* Get canvas - Get line and turtle coordinates to be drawn

# Back End
Internal
* Set Coordinate- Sets a turtle's coordinate based on command to update turtle status
* Add Coordinate- Adds coordinate to data structure storing all coordinate changes, allow for move history to be accessed as well
as drawing of canvas every update
* Add Command- Allows for addition of a specific command to the program's syntax
* Add Turtle- Add a turtle to be controlled

External
* Get Turtle- Allows a specific turtle to be controlled by matching it up with commands
* Get Coordinates- gets the turtle's coordinates to execute a command, send this information to the front end
* Get User Functions- Allows backend to screen for errors in functions before sending them back to the front end to store
* Get User Variables- Allows backend to screen for errors in declared variables before sending them back to the front end for storage
* Check For Errors- Checks for errors in script or commands- syntax, command availability, turtle positioning (can the turtle be moved
and still be in bounds? etc.)
* Set Language- Takes input from user to change language for resource bundle on the fly

User Interface
=============
* The user interface has four main windows that the user interacts with.
* The Canvas is in the top left quadrant, which displays the turtle’s movement and trails. This is how the user sees the result of their 
commands.
* The upper right quadrant, the Script Window, allows the user to type in a multi-line script that they can edit and run repeatedly by 
pressing the run button.
* The Command Window is for executing commands one at a time, and displays a command history as well as any errors that have been 
encountered. The user will press enter to execute the currently typed command
* The User Functions and Variables window shows variables that have been defined in either the script or command window, in a string 
format

If the user enters an empty string and attempts the execute, nothing will happen. If the user enters an invalid command or code, a red 
error message will appear in the command window.



API Details
=========

The internal API contains a class called Canvas that displays the turtle and lines produced as the turtle moves around with the pen 
down. The classes CommandWindow and ScriptWindow send any user-entered text-based commands to the Interpreter. These two classes are 
created to display text from their respective text fields and relay it to the backend as Strings. Finally, the GUI class is responsible 
for incorporating all elements of the user interface. There will be a button (tentatively located near the upper left-hand corner) to 
update language, another to execute scripts, and another to display the help page in the GUI class.

The UML diagram generated from the backend API is shown below.



API Example Code
===============
Executing the command ‘fd 50’ requires CommandWindow.handleReturnKey() to be called when the user presses return after entering this 
text. Then, the command is passed into Interpreter.interpret() as a String, which returns a ReturnData object that contains all the 
information required to display the turtle’s position, lines on the canvas, etc.

Design Considerations
===================
Defining how the user will do things outside of the SLogo
* How will commands be executed?
* How will scripts be called?
* How will syntax errors/other user input errors be called? Back end does screening, front end doesn't give indication of errors until
after info is parsed and sent to logic

Classes to interact between front end and back end- Will the parser declare commands?
* Parser can either only turn string input into data structure, or have additional responsibilities involving processing of commands
* Would either make the parser more active with more responsibility, or more closed with more specialization

What data structure will be sent from the parser to the backend?
* Submit as a map, or maybe a tree
* On a higher level, what is the point of the parser? Should it be screening for syntax errors?
* What would make the parser as closed as possible? How would this trade off with how closed the logic is?

What data structure will be sent from the backend to the front end to update?
* Next data object, contains all settings for drawing screen and moving turtle
* Must contain all variables and functions declared
* What information does the front end need to draw the canvas without giving it all away?

How will we decide which commands to execute? Interpreter vs. factory vs. tree
* A factory might allow us to submit a variety of command rules while out putting commands in a standard way- increase closedness of 
logic
* Will we get lost in if statements and logic for the tree?
* Can an interpreter handle complex commands?

Will turtle move itself or will controller move turtle?
* Allowing the turtle to move itself will make it a more active class
* A turtle only holding its state will make it more passive, but also allow more flexibility
* A controller would have visibility of the whole canvas, while a turtle's vision is limited to itself- more flexibility could result
from a controller doing the moving

What will the relationship between the commands and the controller be?
* Will the controller object have its own actions or will that be matched up to a method in the controller?
* Encapsulation would be better if we only used the controller to match up turtles to commands
* Increased flexibility- could be used for multiple turtles and a variety of commands

Team Responsibilities
==================

Patrick - Co-design program frontend, testing

Tom - Co-design program frontend, testing

Mohab - Co-design backend, testing

Anita - Co-design backend, testing

