package oolala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class CommandRunner {
  public static final String DEFAULT_RESOURCE_PACKAGE = "properties.";
  public static final String DEFAULT_ERRORS_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + "Errors";
  public static final String DEFAULT_COMMANDS_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + "Commands";
  private List<Model> currentTurtles;
  private String[] myCommand;
  private int myIndex;
  private String myError;
  private TurtleController myController;
  private ResourceBundle myErrorResources;
  private ResourceBundle myCommandResources;

  public CommandRunner(TurtleController controller) {
    try {
      myController = controller;
      currentTurtles = new ArrayList<>();
      // inputs first turtle automatically
      currentTurtles.add(myController.getInitialTurtle());
      myCommand = null;
      myIndex = 0;
      myError = null;
      myErrorResources = ResourceBundle.getBundle(DEFAULT_ERRORS_RESOURCE_PACKAGE);
      myCommandResources = ResourceBundle.getBundle(DEFAULT_COMMANDS_RESOURCE_PACKAGE);
    }
    catch (NullPointerException e){
      throw new NullPointerException("Check if the controller is null");
    }

  }

  /**
   *  Take a command, split it into tokens
   *  Store result in class variables
   */
  public void loadCommand(String command) throws NullPointerException{
    try {
      myError = null;
      myCommand = command.toLowerCase().split("\\s+");
      myIndex = 0;
    }
    catch (NullPointerException e){
      loadCommand("");
    }
  }

  /**
   * return an error message for current index
   */
  public void throwUnknownToken(){
    myError = myErrorResources.getString("UnknownToken").formatted(myCommand[myIndex]);
  }

  public void throwUnexpectedEnd(){
    myError = myErrorResources.getString("UnexpectedEnd");
  }

  /**
   * Method for checking if token matches a type of command
   */
  public boolean matches(String token,String key) throws NullPointerException, MissingResourceException {
    try {
      return Arrays.asList(myCommandResources.getString(key).split(" ")).contains(token);
    } catch (NullPointerException e) {
      throw new NullPointerException("Make sure arguments aren't null");
    }
    catch (MissingResourceException e) {
      throw e;
    }
  }

  /**
   * The overall running command, returns error message.
   */
  public String run(String command){
    loadCommand(command);
    String action = (myCommand[myIndex]);
    TurtleCommand myTurtleCommand;
    while ((myError == null) && (myIndex < myCommand.length)) {
      if (matches(action, "TurtleForward")) {
        myTurtleCommand = new TurtleForwardCommand(this);
      }
      else if (matches(action, "TurtleBackward")) {
        myTurtleCommand = new TurtleBackwardCommand(this);
      }
      else if (matches(action, "TurtleLeftTurn")) {
        myTurtleCommand = new TurtleLeftTurnCommand(this);
      }
      else if (matches(action, "TurtleRightTurn")) {
        myTurtleCommand = new TurtleRightTurnCommand(this);
      }
      else if (matches(action, "TurtlePenDown")) {
        myTurtleCommand = new TurtlePenDownCommand(this);
      }
      else if (matches(action, "TurtlePenUp")) {
        myTurtleCommand = new TurtlePenUpCommand(this);
      }
      else if (matches(action, "TurtleShow")) {
        myTurtleCommand = new TurtleShowCommand(this);
      }
      else if (matches(action, "TurtleHide")) {
        myTurtleCommand = new TurtleHideCommand(this);
      }
      else if (matches(action, "TurtleHome")) {
        myTurtleCommand = new TurtleHomeCommand(this);
      }
      else if (matches(action, "TurtleStamp")) {
        myTurtleCommand = new TurtleStampCommand(this);
      }
      else if (matches(action, "TurtleTell")) {
        myTurtleCommand = new TurtleTellCommand(this);
      }
      else {
        throwUnknownToken();
        break;
      }
      myTurtleCommand.execute();
      for (Model turtle : currentTurtles){
        myController.update(turtle);
      }
    }
    return myError;
  }

  public String[] getMyCommand () {
    return myCommand;
  }

  public int getMyIndex () {
    return myIndex;
  }

  public List<Model> getCurrentTurtles () {
    return currentTurtles;
  }

  public void setCurrentTurtles(List<Model> currentTurtles) {
    this.currentTurtles = currentTurtles;
  }

  public void setMyIndex (int myIndex){
    this.myIndex = myIndex;
  }

  public TurtleController getMyController () {
    return myController;
  }
}


