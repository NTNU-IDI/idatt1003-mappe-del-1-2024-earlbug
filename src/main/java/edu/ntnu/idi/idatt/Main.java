package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.view.UserInterface;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    //Starter user interface, og darmed hele programmet
    UserInterface ui = new UserInterface();
    ui.init();
    ui.start();
  }

  //Todo Lære meg streams!! veldig viktig og nyttig
  //Todo fjerne mengde grocery
  //finne ut av lambda
  // string format

  //forbedriger på sikt:

  // Ha et lager av standard/ferdiglagde gorceries, med navn,
  // så man bare kan velge en grocery neste gang.
  // Koble måleenheter til navnet til varen.
  // name, price og measuringunot er allerede utfylt.

  // Find out how to deal with multiple elements with different expiration dates




}
