package oolala;

public class TurtleRightTurnCommand extends TurtleMovementCommand{
  public TurtleRightTurnCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    int parameter = parseParameter();
    CommandRunner model = getMyModel();
    for (TurtleModel turtle: model.getCurrentTurtles()){
      turtle.rightTurn(parameter);
    }
  }
}
