package oolala;

public class TurtleLeftTurnCommand extends TurtleMovementCommand{
  public TurtleLeftTurnCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    int parameter = parseParameter();
    CommandRunner model = getMyModel();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      turtle.leftTurn(parameter);
    }
  }
}
