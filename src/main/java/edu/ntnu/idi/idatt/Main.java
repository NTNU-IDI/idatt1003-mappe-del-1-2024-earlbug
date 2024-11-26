package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.view.UserInterface;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    // Starts UserInterface, and then also the whole program.
    UserInterface ui = new UserInterface();
    ui.init();
    ui.start();
  }


}
