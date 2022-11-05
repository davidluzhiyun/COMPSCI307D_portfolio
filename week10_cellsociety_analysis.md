# Journal : Cell Society Project Analysis
## David Lu
## Team 04
## Date 11/5/2022


Design Review
=======

### Adding new Automata
For the controller part, add to the property files of parser to update reflector. Add the corresponding 
cells to the cell package within alternativeModel package. Depending on the specific details of the
CA, you either need a new subclass of AbstractModel or you can use the StationaryGameModel. Finally,
you need to add the corresponding buttons to the view splash screen

### Overall design
Parser reads the files and construct a Grid and put in the Cells at their corresponding coordinates,
controller constructs the GameModel by passing in the Grid. When updating, the controller calls the
step method of the GameModel and then the lookCurrentGrid method. The latter returns an immutable
copy of the updated grid to pass to the view.

### Design Principles

#### Liskov Substitution

* Needs Improvement
* AbstractCells have to be downcast to Stationary cells if one want to update a Stationary cell.
This is due to the difference between the way CAs like Game of Life and CAs like Wa-tor update cells
and a failure to unify the two


#### Dependency Inversion

* Needs Improvement
* SplashScreen relies on a concrete SplashController class.


#### Interface Segregation

* Needs Improvement
* SplashController class only used a few of Controller's method while not interacting with it
through a interface



### Code Design Examples

#### Made easier to implement by the code's design

* Adding some CAs such as spread of fire and rock paper scissors

* The Stationary model doesn't care about what are its cells so long it is a "Stationary CA" (CAs
where the cells update in place dependent only on its neighbors).


#### Good Example **teammate** implemented

* gridAssembly method in DataFileParser
* This method utilizes reflection with property files to create and fill a Grid instance
  (a wrapper around map) to pass to the gameModel constructor. Sadly it wasn't really implemented
due to team work problems.
* Due to my design flaws, the method directly outputs a grid instead using a interface



#### Needs Improvement Example **teammate** implemented

* public List<List<Integer>> getCellStateGrid() {
  return cellStateGrid;
  } in GameState.java

* This breaks encapsulation




## Your Design

### Design Challenge

* Trade off
  * Making the cells blind to the larger grid makes CAs like Segregation hard to implement,
but is better in the sense of access control.

* Alternative design
  * Pass in a copy of the entire grid in a wrapper

* Solution
  * Make the cells completely blind to the grid and only pass in the CellType information of its
  neighbors when updating, while the model is blind of the cells' internal parameters. For CAs where
  that information is needed by the cell, have a different model.

* Justification or Suggestion
  * This design choice makes changing the model and the cells somewhat easier. 


### Code Design Examples

#### Good Example **you** implemented

* Design
  * For StationaryModel and CAs that use it, individual cells are responsible for returning the 
updated cell, and they pass their parameter to the updated cell. The model pass information about
the types of their neighbours into the cells. The model doesn't know the internal workings of the
cells while the cells are blind to the grid held in the model.

* Evaluation
  * The good encapsulation of this design enables the easy implementation some interesting features.
  One example is that for spread of fire, one can make an entire forrest of tree cells with 
  different probability of catching fire. By pass the neighbor information in the Neighbour class,
  implementing different neighbour and border policies has also been made easier.


#### Needs Improvement Example **you** implemented

* Design
  * StationaryGameModel is constructed using an instance of Grid

* Evaluation
  * The methods in the Grid class such as putCellAt aren't used by StationaryGameModel, I should
  make an interface that only contain the used methods.


## Conclusions

#### What part(s) of the code did you spend the most time on?
* Refactoring my package to leave space for non-stationary CAs like Wa-Tor and segregation.

#### How has your perspective on and practice of coding changed?
* Now I am more mindful about what info each class is allowed to access and what should be hidden.
#### What specific steps can you take to become a better designer?
* The team workflow and planning is a mess this time. I need to facilitate communication within team
better. And I should definitely make clear how the classes interact.
