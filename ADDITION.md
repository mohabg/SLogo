Addition
========

For my addition, I decided to implement the fence and window commands. The default behavior of our Slogo was for a turtle that was out of the bounds of the
screen to move off screen until it returned to the correct bounds. As a result, the window command returned to default settings, whereas the fence command was
a "special" setting.


###Estimation

In order to make this addition, the following changes need to be made:

1. The commands for fence and window need to be implemented. This will likely only require the writing of one, very short method, and will take only a minute or two.

2. An additional boolean in the turtle needs to be added to indicate whether or not it should be fenced. This will allow the turtle's position to only be
updated according to this constraint. It should take about 5 minutes to add this to the turtle, and as long as the turtle is keeping track of whether it should
move or not there is nothing that needs to be changed in the controller/model/view.

3. The front end variables that indicate the canvas bounds needs to be accessed in the turtle's move command to determine if it should move or not.

4. An additional check needs to be added to the turtle position setters to see if the behavior is fenced or not. If so, the position needs to be
changed so that the turtle stays in bounds.


###Review

Luckily, the front end had already put the canvas dimensions in a properties file, so giving the back end access to that information was just a matter of accessing
that properties file in the turtle class.

Making sure that the turtle stayed on the edge that it needed to was a little more complicated than I originally thought because I forgot that we used cartesian
coordinates in the back end. However, this was for the best since it wasn't the back end's job to deal with JavaFX coordinates.

The fact that we had one move command for changing the turtle's position was really nice, because that way any command that would alter the turtle's position
was access this in the same way. This allowed me to make sure that all changes to position would be checked by the window type without changing all of the 
commands themselves, and saved me a ton of time.

I forgot that I would have to add the additional commands to the properties maps, and that to be compatible with all languages it would have to be done for all
of them. I added the commands to the English properties file, but since I didn't know the words in all of the other languages (and it was kind of a waste of time
for me to add them to all of them) I didn't bother with the extras.

Overall, this addition only took about 15 minutes, which is about how much time I expected it to take. The majority of the time was spent on me making sure that I
did the calculation of changing position properly.


###Analysis

For some reason, when I made a this branch from my analysis branch I had errors with issuing a command. I had to create a new branch from master and do it again, however
all I did was copy and paste the same additions I had already made and it worked. I guess there was an error or oversight in the other branch, but regardless this extension
was very simple. The other oversight was that I didn't realize the canvas was half the height and width of the properties map, but that was a simple error. Overall, I think the
API held up well even when I stopped working with it for a while. The changes that needed to be made to add an extra command, creating the command class itself and then adding
the command to the properties file, was very intuitive and easy. The degree to which one would have to change the other classes to implement an additional command outside of that
really depends on what the command is trying to accomplish, but in all likelihood the only class that needs to get touched is the turtle class to change something about the turtle's
behavior, or if a completely new feature was added maybe another object in the model with a getter/setter for that in data. However, this still means that the API itself supports
a lot of extensibility, and the fact that data is done entirely through getters and setters instead of having an actual data object that needs to be changed in the constructor
makes it more open to extensibility.

Although I was slightly less familiar with the API at this point, the way that packages were broken up and the naming of everything from classes to methods made it really easy to go
back and figure out what was happening. Even if I had forgotten more, it wouldn't have been too difficult to figure everything out from that. The biggest downfall to our project from that
perspective was that it wasn't JavaDoc'ed, so I wasn't totally clear on what things in the GUI and a few things in the parser did.
