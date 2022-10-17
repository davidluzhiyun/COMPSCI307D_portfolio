package oolala;

public class TurtleShowCommand extends TurtleCommand{
  public TurtleShowCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      turtle.show();
    }
    model.setMyIndex(model.getMyIndex()+1);
  }
}
