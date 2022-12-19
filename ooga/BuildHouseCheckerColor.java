/**
 * Purpose: originally methods in the Player class that uses the colorset, refactored by Luyao to a
 * class to facilitate changing game rules by changing
 */
package ooga.model.player;

import ooga.model.exception.CannotBuildHouseException;
import ooga.model.exception.NoColorAttributeException;
import ooga.model.place.Place;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BuildHouseCheckerColor implements CanBuildOn {


  /**
   * @param place  the place to check
   * @param target the latter place
   * @author David Lu
   * Helper funtion to check if the place has at least a certain number of houses as another place
   */
  private boolean checkHouseNum(Place place, Place target) {
    try {
      int placeHouseNum = place.getHouseCount();
      return placeHouseNum >= target.getHouseCount();
    } catch (CannotBuildHouseException e) {
      return false;
    }
  }

  /**
   * Check if player can build a house on a place
   *
   * @param place a place to check
   * @return
   */
  @Override
  public boolean canBuildOn(Place place, Map<Integer, Predicate<Collection<Place>>> colorSetCheckers, Collection<Place> properties, int playerId) {
    try {
      int color = place.getColorSetId();
      Predicate<Collection<Place>> checker = colorSetCheckers.get(color);
      if (checker == null) {
        return false;
      }
      return checker.test(properties.stream().filter((Place p) -> checkHouseNum(p, place)).collect(
          Collectors.toSet()));
    } catch (NoColorAttributeException e) {
      // not something with a color
      return false;
    } catch (NullPointerException e) {
      throw new IllegalStateException("Input is null or checker unset", e);
    }
  }
}
