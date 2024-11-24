package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.view.UserInterface;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    // Starts UserInterface, and then also the whole program.
    UserInterface ui = new UserInterface();
    ui.init();
    ui.start();
  }


}
