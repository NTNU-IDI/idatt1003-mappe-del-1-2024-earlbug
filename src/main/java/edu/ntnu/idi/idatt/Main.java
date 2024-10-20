package edu.ntnu.idi.idatt;

import java.util.Date;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    Fridge fridge = new Fridge();
    fridge.printFridgeContent();
    fridge.printExpiredgroceries();
    fridge.printTotalValueOfGroceriesInFridge();



  }
}
