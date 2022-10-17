package oolala;

public class TurtlePenUpCommand extends TurtleCommand{
  public TurtlePenUpCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      turtle.penUp();
    }
    model.setMyIndex(model.getMyIndex()+1);
  }
}
