package oolala;

/**
 * Abstract class for common steps of commands like "fd", "bk", "lt", "rt"
 */
public abstract class TurtleMovementCommand extends TurtleCommand {

  public TurtleMovementCommand(CommandRunner model){
    super(model);
  }

  /**
   * Parses parameter, set error message and do nothing when problem occurs
   */
  protected int parseParameter() {
    try {
      CommandRunner model = getMyModel();
      int parameter = Integer.parseInt(model.getMyCommand()[model.getMyIndex() + 1]);
      model.setMyIndex(model.getMyIndex()+2);
      return parameter;
    }
    catch (IndexOutOfBoundsException e){
      getMyModel().throwUnexpectedEnd();
      return  0;
    }
    catch (NumberFormatException e){
      getMyModel().throwUnknownToken();
      return 0;
    }
  }
}
