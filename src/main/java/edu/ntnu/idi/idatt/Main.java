package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.view.UserInterface;

/**
 * This is the main application of the program. It initializes the user interface.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class Main {

  /**
   * This is the <code>public static void main(String[] args)</code>- method and initializes an
   *    instance of UserInterface and starts the program for the user.
   */
  public static void main(String[] args) {

    // Starts UserInterface, and then also the whole program.
    UserInterface ui = new UserInterface();
    ui.init();
    ui.start();
  }
}
