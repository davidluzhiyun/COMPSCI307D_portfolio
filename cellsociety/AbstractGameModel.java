package cellsociety.alternativeModel;

import cellsociety.alternativeModel.cell.AbstractCell;
import cellsociety.alternativeModel.cell.EmptyCell;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Abstract Game Model, initialize with a grid, and output a grid. Has
 * two methods. One for updating one for outputting result.
 * @author david_luzhiyun
 */

public abstract class AbstractGameModel {
  public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.properties.";
  public static final String DEFAULT_ERRORS_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + "ModelErrors";
  //class variables
  private Map<Coordinate, AbstractCell> grid;
  private ResourceBundle myErrorResources;


  // size of the grid
  // represents the largest index, reachable
  private int maxX;
  private int maxY;

  public AbstractGameModel(Grid inputGrid) throws NullPointerException{
    try {
      myErrorResources  = ResourceBundle.getBundle(DEFAULT_ERRORS_RESOURCE_PACKAGE);
      maxX = inputGrid.getMaxX();
      maxY = inputGrid.getMaxY();
      grid = new HashMap<>();
      for (int i = 0; i <= inputGrid.getMaxX(); i++) {
        for (int j = 0; j <= inputGrid.getMaxY(); j++) {
          grid.put(new Coordinate(i,j), inputGrid.getCellAt(i,j));
        }
      }
    }
    catch (NullPointerException e){
      System.out.println(myErrorResources.getString("inputNull"));
      throw e;
    }
  }
  // Get the cell at given coordinates, might return null for some non-stationary CA
  // Screens against out of bound get attempts
  protected AbstractCell getCellAt(int X, int Y){
    try {
      assert withinBound(X,Y);
      return grid.get(new Coordinate(X, Y));
    }
    catch (AssertionError e){
      System.out.println(myErrorResources.getString("outOfBound"));
      throw e;
    }

  }
  // A way to completely set a new Map as grid. Screens against null input
  protected void setGrid(Map<Coordinate, AbstractCell> newGrid){
    try {
      assert newGrid != null;
      grid = new HashMap<>(newGrid);
    }
    catch (AssertionError e){
      System.out.println(myErrorResources.getString("inputNull"));
      throw e;
    }
  }
  public abstract void step();

  // Used to look at the Current Grid
  // defaults to empty cell if the coordinate holds null
  public ImmutableTypeGrid lookCurrentGrid(){
    Grid output = new Grid(maxX,maxY);
    for (int i = 0; i <= maxX; i++) {
      for (int j = 0; j <= maxY; j++) {
        AbstractCell myCell = grid.get(new Coordinate(i,j));
        if (myCell == null){
          myCell = new EmptyCell();
        }
        output.putCellAt(i,j,myCell);
      }
    }
    return output;
  }

  // Put a cell at give coordinates, doesn't screen against null, out of bound
  // put attempts will have no effect
  protected void putCellAt(int X, int Y, AbstractCell cell){
    try {
      assert X <= maxX && X >= 0;
      assert Y <= maxY && Y >= 0;
      grid.put(new Coordinate(X, Y), cell);
    }
    catch (AssertionError e){
      //do nothing
    }
  }

  // Auxiliary methods for constructing the neighbourhood
  private Coordinate wrapAround (int X, int Y){
    int x = X;
    int y = Y;
    if (x < 0){
      x = maxX;
    }
    else if (x > maxX){
      x = 0;
    }
    if (y < 0){
      y = maxY;
    }
    else if (y > maxY){
      y = 0;
    }
    return new Coordinate(x,y);
  }
  private Boolean withinBound (int X, int Y){
    return ((X <= maxX && X >= 0) && (Y <= maxY && Y >= 0));
  }

  private void setMooreNeighbours(int X, int Y,Neighbourhood myNeighbourhood){
    for (int i = X - 1; i <= X + 1; i = i + 2) {
      for (int j = Y - 1; j <= Y + 1; j = j + 2){
        if (!withinBound(i,j)){
          Coordinate myWrap = wrapAround(i,j);
          myNeighbourhood.putWrapAroundMooreNeighbour(myWrap.x(),myWrap.y(),getCellAt(myWrap.x(),myWrap.y()));
        }
        else {
          myNeighbourhood.putMooreNeighbour(i,j,getCellAt(i, j));
        }
      }
    }
  }
  private void setVonNeumannNeighbours(int X, int Y,Neighbourhood myNeighbourhood) {
    for (int i = X - 1; i <= X + 1; i++) {
      if (!withinBound(i, Y)) {
        int wrapI = wrapAround(i, Y).x();
        myNeighbourhood.putWrapAroundVonNeumannNeighbour(wrapI, Y, getCellAt(wrapI, Y));
      } else {
        myNeighbourhood.putVonNeumannNeighbour(i, Y, getCellAt(i, Y));
      }
    }
    for (int j = Y - 1; j <= Y + 1; j++) {
      if (!withinBound(X, j)) {
        int wrapJ = wrapAround(X, j).y();
        myNeighbourhood.putWrapAroundVonNeumannNeighbour(X, wrapJ, getCellAt(X, wrapJ));
      } else {
        myNeighbourhood.putVonNeumannNeighbour(X, j, getCellAt(X, j));
      }
    }
  }

  protected ImmutableNeighbourhood getNeighbourhoodAt(int X, int Y){
    Neighbourhood my = new Neighbourhood(X, Y);
    setVonNeumannNeighbours(X, Y, my);
    setMooreNeighbours(X, Y, my);
    return my;
  }

  // Getters for passing this info to subclasses, where it is needed for updating
  // No error handling because Grid already rules out invalid maxX maxY's
  public int getMaxX() {
    return maxX;
  }

  public int getMaxY() {
    return maxY;
  }
  // Getter for passing the ResourceBundles to subclasses

  public ResourceBundle getMyErrorResources() {
    return myErrorResources;
  }
}
