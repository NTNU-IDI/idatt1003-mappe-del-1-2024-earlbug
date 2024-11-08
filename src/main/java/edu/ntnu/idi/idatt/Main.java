package edu.ntnu.idi.idatt;

import java.util.Date;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    //Starter user interface, og darmed hele programmet
    UserInterface ui = new UserInterface();
    ui.init();
    ui.start();
  }
}
