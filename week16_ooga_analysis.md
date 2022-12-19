# Journal : OOGA Project Analysis
#### David Lu
#### RagTag
#### 12/18/2022


## Design Review

### Change Scenarios

* New game variation
  * New classes that implement old interfaces contained in player package if there is a change of
  game rule.
  * New actions added to the enum if needed.
  * New files for new icons
  * If there are new Places, you need to add subclasses of the AbstractPlace that implements the
  Place interfaces
  * Modify the view and the controller to listen for the new events and accept the new data.

* Changing data file format
  * The model classes and controller classes for initialization. They directly parse the Json files
  for information during initialization.

* New Player view
  * Depends on the specific case
  * For the provided case, you need a new class in the popup package, extend upon the model to
  provide the required info. Finally, you need th extend the event handling classes in all three
  components to publish or catch the info.

* Extension Feature
  * I wanted the players to have the ability to "kill" other players for a certain amount of money
  * To achieve this, the GameModel class need to be extended to override the method that output the
  StationaryActions so that KILL is always included. The OnGameEvent method should be overridden to
  handle the new "kill" command. THe view need to add corresponding drop-downs and buttons to handle
  the new feature



### Design Pattern

* Design problem applied to
  * Factory
* Classes and methods that implement it
  * GameModel, Place, Player and related classes
* How it helped your design
  * By having GameModel interacting with the Place interface and the Player interface, changing
  game rules and adding new types of Places becomes easier.



### API Examples

#### Good
ModelOutput
* Describe as Service
  * Outputs required information to controller
  * Which includes a list of player and related status, places and related status, the result of
  Dice roll and collection of actions that can be taken.

* Easy to use and hard to misuse
  * Has most of the information easily accessible through getters
  * Lacks method that can use to modify the model
  * Uses ControllerPlace and ControllerPlayer to provide information, which only have getters
  * All collections used are copies of themselves that don't affect what happens inside the model.

* Encapsulation and extension
  * Uses abstractions such as ControllerPlace. Makes extending and adding new types of places easy.
  * Uses more abstract interfaces like list and collection allows for changing the internal
  implementation of the collections


#### Needs Improvement
ControllerPlace
* Describe as Service
  * Provide information tied to each place to the controller. This includes the number of houses, the
  action the current player can take when clicking open the place.
  * Uses 
* Easy to use and hard to misuse
  * Lack methods for modification. 
  * The only collection is again a copy that doesn't affect the model.

* Encapsulation and extension
  * Uses more abstract interfaces like list and collection allows for changing the internal
  implementation of the collections. 


### Design Examples

#### Good Example **teammate** implemented

* Design
  * Refactoring methods in ConcretePlayer class to individual classes that implements their own
  interface

* Evaluation
  * This choice has made it possible to create games with alternative rules by simply changing the
  data files to switch between different "AddOn" classes that implements the same interface. Though
  it is arguable the one related to color set is a bit redundant since I already have a class and
  interface for colorset.


#### Needs Improvement Example **teammate** implemented

* Design
  * Having the controller pick out a part of the model output to send to the view for different
  types of event while not keeping a pointer to the model.
* Evaluation
  * Having the controller pick out a part of the model output to update instead of updating
  everything that is potentially updated makes extension extremely hard. Not having a pointer to the
  model all the time make the matter worse. We have to change the whole model output api to include
  a GameState indicating what the controller need to pick out. They latter problem require the model
  api to provide a "query" index it receive from the view everytime the view need to check something
  that is in the model output. This defeats the whole purpose of having a controller.




## Your Design

### Assumption or choice that had a global or significant impact on your design
  * Individual players and places shouldn't have access to the whole board. 

### Design Challenges

#### Design #1
Implementing the color set system that decide whether a player get to build a house at a place
is hard under the current assumption is hard because it requires the situation of the whole board.
* Trade-offs
  * While the current predicate based solution allows us to keep good encapsulation and extendability
  It requires extra steps while initializing a game or loading a game. 

* Alternate designs
  * Entering the whole board as a parameter everytime the Place class calculates the actions a 
  player can take. But this exposes the board to the place classes and requires changes to multiple
  established apis.

* Solution
  * Have a map mapping color to predicates that determine if a collection of places has every member
  with the color. Then put the map in each player class.

* Justification or Suggestion
  * This is the least amount of access the player class needs for this functionality. It also
  keeps our current assumptions.

#### Design #2
Whether the player class should keep a collection of Places to represent the properties it owns or
should it just keep the index.
* Trade-offs
  * In theory the player class can access all the information given the board and the index. However,
  since the player class has no access to the board. The latter plan still requires the index info
  because previous design changes by our team made the index of each place inaccessible from the place
  class. Plus my teammates have refactored much code to make them run on the index.
* Alternate designs
  * Entering the whole board as a parameter everytime the Place class calculates the actions.
  * Keeping both the collection of places and index while the interfaces only provide access to the
  indices
* Solution
  * The latter design

* Justification or Suggestion
  * The first design defeats the purpose of granting access of the board to the ColorSet class


### Abstraction Examples

#### Abstraction #1

* Classes/Interfaces included in abstraction
  * AbstractPlace, its subclasses, Place and ControllerPlace. 
* Design goal
  * Make it easy to add new types of places that generate PlaceActions and StationaryAction based on
  the Player class in their own way and to transform a Place to an immutable ControllerPlace.
* How it (could) help
  * AbstractPlace implements the Place interface and all its subclasses interacts with other classes 
  in the model through the interface. Place interface inherits from the immutable ControllerPlace
  interface. This allows quick transformation from a Place to a ControllerPlace.


#### Abstraction #2

* Classes/Interfaces included in abstraction
  * ConcreteColorSet, ImmutableColorSet

* Design goal
  * Prevents Player class to access the whole board. 
  * (Unimplemented) provides an easy way to change how color set is handled in the game.

* How it (could) help
  * Example for potential use: Extend from the ConcreteColorSet class, have the new class just sets 
  all predicates to true. Then use this class in a modified GameModel and set the output as color set
  checker for the player. Now the players can build anywhere they want. 
  * Wasn't used because teammate already implemented the functionality by refactoring a method in
  player to a class. His modification provides more variation to the mechanism.


### Design Examples

#### Good Example **you** implemented

* Design
  * Have each Place generate PlaceActions and StationaryAction using the player as a parameter

* Evaluation
  * This makes extending the game a lot easier. It also makes the Place classes more active. However,
  this can cause trouble with mechanisms that require additional information.

#### Needs Improvement Example **you** implemented

* Design
  * Using down-casting in the color set
* Evaluation
  * Originally this was a temporary method for filtering out any non-Street places that has already
  been added or will be added. Later, methods were added to include polymorphism and error catching 
  that can be used to remove the down-casting. But sadly it wasn't removed due to schedule problem.



## Conclusions

#### Thing #1 you have done to improve as a coder/designer
* Plan out a basic way how classes should interact in the beginning and improve upon that.

#### Thing #2 you have done to improve as a coder/designer
* Discuss designs with teammate who pair-program with me.


#### Thing #1 you have done to improve your teamwork or to be a better teammate
* Focus on how thing connect rather than detailed implementation in meetings of the whole team

#### Thing #1 you have done to improve your teamwork or to be a better teammate
* Get things moving by making compromises to my own design.

#### Thing #1 you have done this semester to improve how you manage "large", ambiguous, open ended projects
* Set reasonable temporary goals and adjust based on completion
#### Thing #2 you have done this semester to improve how you manage "large", ambiguous, open ended projects
* Frequent group meeting to report status

#### Biggest strength #1 as a coder/designer?
* Can justify my design choices in meeting
#### Biggest strength #2 as a coder/designer?
* Able to make compromises

#### Favorite part of working on "large" team software project
* While I mostly focus on the part I am familiar with in the backend, I get to see how I work with
the work of others in a larger whole.