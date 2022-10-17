package oolala;

import java.util.ArrayList;
import java.util.Hashtable;

public class TurtleTellCommand extends TurtleCommand{
  public TurtleTellCommand(CommandRunner model){
    super(model);
  }

  @Override
  public void execute() {
    CommandRunner model = getMyModel();
    model.setMyIndex(model.getMyIndex()+1);
    ArrayList<TurtleModel> currentTurtles = new ArrayList<TurtleModel>();
    model.setCurrentTurtles(new ArrayList<TurtleModel>());
    String[] tokens = model.getMyCommand();
    while (model.getMyIndex() < tokens.length){
      String name = tokens[model.getMyIndex()];
      if (model.matches(name, "TurtleAllCommands")){
        break;
      }
      else if ((model.getMyController().getTurtleDictionary()).containsKey(name)) {
        currentTurtles.add((TurtleModel)model.getMyController().getTurtleDictionary().get(name));
      }
      else {
        TurtleModel newTurtle = new TurtleModel(name);
        model.getMyController().getTurtleDictionary().put(name,newTurtle);
        currentTurtles.add(newTurtle);
        model.getMyController().createView(newTurtle);
      }
      model.setMyIndex(model.getMyIndex()+1);
    }
  }
}
