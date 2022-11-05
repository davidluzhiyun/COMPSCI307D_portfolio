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

* Trade-offs

* Alternate designs

* Solution

* Justification or Suggestion


### Code Design Examples

#### Good Example **you** implemented

* Design

* Evaluation


#### Needs Improvement Example **you** implemented

* Design

* Evaluation



## Conclusions

#### What part(s) of the code did you spend the most time on?

#### How has your perspective on and practice of coding changed?

#### What specific steps can you take to become a better designer?
