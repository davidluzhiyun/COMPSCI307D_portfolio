package cellsociety.alternativeModel;

import cellsociety.alternativeModel.cell.CellType;
import java.util.Map;

public interface ImmutableNeighbourhood {
  public Map<Coordinate, CellType> getMooreNeighbourhood() ;
  public Map<Coordinate, CellType> getVonNeumannNeighbourhood();
  public Map<Coordinate, CellType> getWrapAroundMooreNeighbourhood();
  public Map<Coordinate, CellType> getWrapAroundVonNeumannNeighbourhood();
}
