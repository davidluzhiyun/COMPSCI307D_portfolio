package oolala;

public class TurtlePenDownCommand extends TurtleCommand{
  public TurtlePenDownCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      turtle.penDown();
    }
    model.setMyIndex(model.getMyIndex()+1);
  }
}
