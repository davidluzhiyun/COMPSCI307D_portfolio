# Journal : OOLALA Project Analysis
## Name
David Lu
## Team 10
## Date
10/14/2022


Design Review
=======

### Adding new application Command
We need a new runner class that parse the new commands and translate
them into turtle language that can be fed through CommandRunner.java.
To support that new runner class, we need command classes for polymorphism.
We need a new View class for the GUI. We will also need new resources file 
for the error messages, commands and GUI.
### Adding new feature
SETXY and GOTO xy can be implemented in a way that follows the OCP. To implement
this feature, one need to add a setter to the model .java class. He also need to
create a TurtleGotoCommand class which extends TurtleCommand and calls
the setter. Besides that, he needs to add the new commands to the Commands.properties
file and the run method in CommandRunner to parse the new command.

### Good Example **teammate** implemented

* Design
  * Andy Demma was in charge of the abstracted turtle. He chose to split it into a model
  class and a view class before CVM principle was talked about in class. The Model holds
  the name, position, heading , pen down/up and hide/show property of the turtle. The
  model also has methods I can call in the TurtleCommand classes to change its state.
  The view class only has one public class that takes in the Model and update the groups
  correspondingly. It hides the Javafx groups that hold the lines and stamps and the Image view
  for turtle from other classes. It has made the assumption that it is only making one movement
  for one turtle at the time and leave the job of controlling multiple turtles and updating
  over multiple steps to CommandRunner.
* Evaluation
  * This design choice does reign in interactions with javafx. For all the none UI related code, they
  can almost see turtle just as the abstraction in the model. However, this does come with the trade-off
  of having to deal with the update method in the CommandRunner class rather than being able
  to treat turtle as a abstracted, unified whole.

### Needs Improvement Example **teammate** implemented

* Design
  * The LCommandRunner class is a slightly modified duplication of a older version
  of CommandRunner for running the turtle code translated by LRunnerModel.
* Evaluation
  * The existence of this class is almost completely unnecessary and defeats the purpose
  of my work in LRunnerModel. The whole point of LRunnerModel is to translate L-system command to 
  turtle commands that CommandRunner can understand. Also, since it is based on an older version of
  CommandRunner, it is in serious need of refactoring.



## Your Design

### Design Challenge
#### Whether to have CommandRunner pass itself in as a parameter when calling new TurtleCommands
* Trade-offs
  * Doing so create two-way dependencies between CommandRunner and various TurtleCommands. Also creates
excessive getters and setters. However, it does make it easier to add more functionalities and keep
encapsulation complete.
* Alternate designs
  * Pass in a Data Structure and have the TurtleCommand.execute() methods return a Data Structure to
update CommandRunner Accordingly.
* Solution
  * Have CommandRunner pass itself in as a parameter when calling new TurtleCommands.
* Justification or Suggestion
  * Due to dependencies with the work of team members, the data structure might contain boolean flags 
that lead to long if else statements. Also, the alternative might require modifying the data 
structure, the planned update function as well as the CommandRunner Class for more complex added 
features. Moreover, the data structure passed might break encapsulation.

#### Good Example **you** implemented

* Design
  * The CommandRunner mainly keeps an Array of command line tokens, an index pointing to the current
token being read and a List for the turtles being controlled at the time (Originally, a Hash table
was used to hold all turtles present but was refactored by Andy into his Turtle controller). There
are two resource packages. One for commands and their alternative forms, one for error messages.
  * Aside from the setters and getters, there are 5 methods. "loadCommand" splits input into tokens
and turn them to lowercase. Two method are for setting different error messages. "matches" check if
token matches a form of some command. "run" parses commands and call new TurtleCommand classes. It 
also calls update on the Turtle Models. TurtleCommand classes calls corresponding methods of Turtle
model and cause corresponding changes in Commander Runner. These changes include the manipulation of
myIndex
* Evaluation
While the class does violate the relationship inversion rule, it is relatively extendable without
modification. It also has most of the errors handled.

#### Needs Improvement Example **you** implemented

* Design
  * THe LRunnerModel class generates turtle code according to the L-system code. The way it manages
index is in a way similar to CommandRunner. It uses two Dictionaries (internal implementation 
HashTable) to manage the translation. One for extending L code according to rule, the other is for 
translation to turtle code. Similar to CommandRunner, it also calls to LCommand's subclasses to
execute commands. The difference is that most of its command's effects on itself.
* Evaluation
Due to the similarity between LRunnerModel, CommandRunner and the terrible LCommandRunner, it might
be nice to put them under one superclass (Lost to git problems). Also, LRunnerModel should be 
refactored to use resource packages like CommandRunner. The way randomization is implemented in
LRunnerModel caused a lot of problems for further extension. 


## Conclusions

#### What part(s) of the code did you spend the most time on?
I spent most time on CommandRunner if refactoring is counted.
#### How has your perspective on the practice of coding changed?
Previously I avoided polymorphism because it seems a bit excessive for a few lines of code. Now I
realized it makes the code a lot more readable and extendable. Also, I learnt that during team work
I might need to pay attention of the code of others and some changes can only be done in a group. 
The latter makes planning and discussion more important
#### What specific steps can you take to become a better designer?
I need to pre-plan my code with cards and discuss beforehand with my teammates to prevent being 
forced to leave bad code without refactoring. I should also try to improve my understanding of the
rules.