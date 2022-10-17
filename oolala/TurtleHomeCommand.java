package oolala;

public class TurtleHomeCommand extends TurtleCommand{
  public TurtleHomeCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    for (TurtleModel turtle : model.getCurrentTurtles()) {
      turtle.home();
    }
    model.setMyIndex(model.getMyIndex() + 1);
  }
}
