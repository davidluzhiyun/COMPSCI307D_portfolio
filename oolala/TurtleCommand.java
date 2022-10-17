package oolala;

/**
 * Abstract class for turtle commands. execute should carry out action for
 * each turtle under control (unless command is tell) and manipulate the
 * index.
 */
public abstract class TurtleCommand {
  private CommandRunner myModel;
  public TurtleCommand(CommandRunner model){
    myModel = model;
  }

  public CommandRunner getMyModel() {
    return myModel;
  }

  public abstract void execute();

}