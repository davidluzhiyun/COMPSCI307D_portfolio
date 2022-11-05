package cellsociety.alternativeModel;


import cellsociety.alternativeModel.cell.AbstractCell;
import cellsociety.alternativeModel.cell.StationaryCell;
import java.util.HashMap;
import java.util.Map;

/**
 * Applicable to Game of Life, Rock Paper Scissors, Spread of fire and other
 * CAs that doesn't involve cells moving around.
 * The CAs this model is applicable to determines the state of its cells only on the cells
 * @author david_luzhiyun
 */
public class StationaryGameModel extends AbstractGameModel {
  //class variables

  public StationaryGameModel(Grid inputGrid){
    super(inputGrid);
  }


  // Updates the grid
  // Screens out cases where there are cells that are not stationary
  @Override
  public void step(){
    try {
      Map<Coordinate, AbstractCell> myFuture = new HashMap<>();
      for (int i = 0; i <= this.getMaxX(); i++) {
        for (int j = 0; j <= this.getMaxY(); j ++){
          AbstractCell selected = getCellAt(i,j);
          // Catches cases where there are cells that are not stationary
          // Down casting necessary due to grid holding AbstractCell in superclass
          assert (selected instanceof StationaryCell);
          myFuture.put(new Coordinate(i, j), ((StationaryCell) selected).update(getNeighbourhoodAt(i,j)));
        }
      }
      setGrid(myFuture);
    }
    catch (AssertionError e){
      System.out.println(getMyErrorResources().getString("wrongType"));
      throw e;
    }
  }

}
