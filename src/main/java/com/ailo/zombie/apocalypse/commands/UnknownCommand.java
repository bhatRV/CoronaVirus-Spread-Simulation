package com.ailo.zombie.apocalypse.commands;


import com.ailo.zombie.apocalypse.entities.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * UNKnownCommand is used to handle unknown user inputs.
 */
public class UnknownCommand implements Command {

  private final String commandString;

  /**
   * Handles bad request
   * @param commandString
   */
  public UnknownCommand(String commandString) {
    this.commandString = commandString;
  }

  @Override
  public List<Location> apply(Location currentLocation) {
    System.out.println(commandString + " is an Invalid Command!");

    List<Location> path = new ArrayList<>();

    if (currentLocation != null) {

      path.add(currentLocation);

    }
    return path;
  }

  @Override
  public String toString() {
    return null;
  }
}
