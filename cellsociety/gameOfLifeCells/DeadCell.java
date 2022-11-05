package cellsociety.alternativeModel.cell.gameOfLifeCells;

import cellsociety.alternativeModel.Coordinate;
import cellsociety.alternativeModel.ImmutableNeighbourhood;
import cellsociety.alternativeModel.cell.AbstractCell;
import cellsociety.alternativeModel.cell.StationaryCell;
import cellsociety.alternativeModel.cell.CellType;
import java.util.Map;

public class DeadCell extends StationaryCell {
  public DeadCell(){
    super();
    setType(CellType.DEAD);
  }

  @Override
  public StationaryCell update(ImmutableNeighbourhood neighbourhood) {
    int livingNeighbours = countNeighbour(CellType.ALIVE,neighbourhood.getMooreNeighbourhood());
    if (livingNeighbours == 3){
      return new AliveCell();
    }
    else {
      return new DeadCell();
    }
  }
}
