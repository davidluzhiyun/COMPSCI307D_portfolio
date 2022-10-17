package oolala;

public class TurtleStampCommand extends TurtleCommand{
  public TurtleStampCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    TurtleController controller = model.getMyController();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      controller.stamp(turtle);
    }
    model.setMyIndex(model.getMyIndex()+1);
  }
}
