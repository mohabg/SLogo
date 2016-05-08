### Estimation
* Before looking at the code, I think that implementing these extensions might be a little troublesome. 
I think so, because I cannot recall ever using the bounds of the screen while I was creating commands. Therefore, I 
would have to find where to get it from first, before being able to use it. I think this will probably require updating the data
going into the commands which shouldn't require updating more than 2-3 classes.
### Review
* This ended up being very easy. After some digging around, I realized that the width and height were in resource files, and I 
could just get them easily and give them to the turtle. Whenever the turtle moves, it would check if its going off the screen
and act according to the new commands I implemented. I ended up only needing to update the Turtle class, and adding the corresponding
command classes. This was because all I needed was the width and height which was easily obtained from the resource file. Although,
I did not get it right on the first try, this was due to small errors that occurred due to working too quickly. 
### Analysis
* I think that this exercise demonstrates how well designed our project was. I was able to implement all three
commands, in about ten minutes. With that being said, there is always room for improvement. The way it is implemented now is a 
little messy because it uses booleans and conditional statements. This could definitely be changed to a more generic, elegant way.
* If I was not familiar with the code at all, I still do not think it would be too bad. I would go to the commands that make
the turtle move, see what happens there, and act accordingly. In the forward command, it calls "move" on the turtle, so I would
go to that method and see that is where the x and y positions are changing. Next, I would find where the width and height of the 
screen are stored, and get the turtle access to it. After that is done, all that is left is telling the turtle not to move past
the edge, or to appear on the other side, etc. which isn't very difficult to do.