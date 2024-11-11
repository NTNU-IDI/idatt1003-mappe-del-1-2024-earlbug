package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {

  Fridge fridge;

  /**
   * Fridge constructor.
   */
  public void UserInterface() {

  }

  /**
   * initializes the program by calling all the necessary objects.
   */
  public void init() {
    fridge = new Fridge();


    // dummy groceries.
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = new Date();
    Date date2 = new Date();
    Date date3 = new Date();

    try {
      date1 = dateFormat.parse("2024-10-30");
      date2 = dateFormat.parse("2024-10-20");
      date3 = dateFormat.parse("2024-10-10");
    } catch (ParseException e) {
      e.printStackTrace();
    }


    fridge.addGrocery("Milk", 1.5, date1, 29.9, "liters");
    fridge.addGrocery("Bananas", 5, date2, 5.40, "units");
    fridge.addGrocery("eggs", 12, date3, 2.23, "Units");

  }

  public void start(){

  }

  /**
   * Asks the user for all the details of the grocery it wants to add.
   */
  public void askForGroceryToAdd() {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date expirationDate = new Date();

    Scanner scanner = new Scanner(System.in);

    System.out.print("Please write the name of the new Grocery: ");
    String inpGroceryName = scanner.nextLine();
    System.out.print("Please write the amount of the new Grocery: ");
    double inpAmount = scanner.nextDouble(); // uses comma",", not dot"."
    scanner.nextLine(); //swallws the enter typed which would otherwise be an unwanted input for the next scanner
    System.out.println("Please write the expiration date of the new Grocery.");
    System.out.print("The format is as follows: yyyy-MM-dd: ");
    String inpExpirationDate = scanner.nextLine();
    System.out.print("Please write the price per unit of the new Grocery: ");
    double inpPricePerUnit = scanner.nextDouble();
    scanner.nextLine(); //swallws the enter typed which would otherwise be an unwanted input for the next scanner
    System.out.print("Please write the measuring unit of the new Grocery: ");
    String inpMeasuringUnit = scanner.nextLine();

    // TODO make the check exeption
    try {
      expirationDate = dateFormat.parse(inpExpirationDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    fridge.addGrocery(inpGroceryName, inpAmount, expirationDate, inpPricePerUnit, inpMeasuringUnit);
  }

  /**
   * Prints out all items in the fridge, with all it's attributes.
   */
  public void printFridgeContent() {
    StringBuilder returnString = new StringBuilder("The fridge currently contains:" + "\n");
    for (int i = 0; i < fridge.getGroceryList().size(); i++) {
      Grocery groceryi = fridge.getGroceryList().get(i);
      returnString.append(
          groceryi.getAmountOfGrocery() + " " + groceryi.getMeasuringUnit()
              + " of " + groceryi.getNameOfGrocery() + "\n"
      );
    }
    System.out.println(returnString);
  }

  /**
   * Asks for an input of what grocery the user wants removed.
   */
  public void askToRemoveGrocery() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What grocery do you want to remove?: ");
    String grocery = scanner.nextLine();
    fridge.removeGrocery(grocery);
  }

  /**
   * Prints a list of all the expired groceries
   * Name, amount and expiration date.
   */
  public void printExpiredgroceries() {

    ArrayList<Grocery> expiredGroceries;
    expiredGroceries = fridge.findExpiredGroceries();
    StringBuilder returnString = new StringBuilder();
    double costOfExpiredGroceries = 0;

    if (expiredGroceries.isEmpty()) {
      returnString.append("There are no expired groceries!");
    }
    returnString.append("Expired gorceries: \n");
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEE dd. MMMMMMMMM yyyy"); // skriver ut på norsk
    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM yyyy"); //as only numbers
    for (int i = 0; i < expiredGroceries.size(); i++) {
      Grocery currentGrocery = fridge.getGroceryList().get(i);
      returnString.append(currentGrocery.getAmountOfGrocery() + " "
          + currentGrocery.getMeasuringUnit() + " of "
          + currentGrocery.getNameOfGrocery() + " expired "
          + dateFormat.format(currentGrocery.getExpirationDate()) + ".\n"
      );
      costOfExpiredGroceries += currentGrocery.getPricePerUnit() * currentGrocery.getAmountOfGrocery();
    }
    returnString.append("\n For a total of " + costOfExpiredGroceries + " NOK");
    System.out.println(returnString.toString());
  }

  /**
   * Prints the amount of the specified grocery.
   *
   * @param inpGrocery The grocery which shall be counted.
   *
   */
  public void printAmountOfGrocery(String inpGrocery) {
    StringBuilder returnString = new StringBuilder();
    if (fridge.getAmountOfGrocery(inpGrocery) > 0) {
      String measuringUnit = new String();
      // finds the measuring unit
      for (int i = 0; i < fridge.getGroceryList().size(); i++) {
        if (fridge.getGroceryList().get(i).getNameOfGrocery().equals(inpGrocery)) {
          measuringUnit = fridge.getGroceryList().get(i).getMeasuringUnit();
          break;
        }
      }
      returnString.append(
          "The fridge contains "
              + fridge.getAmountOfGrocery(inpGrocery) + " "
              + measuringUnit + " of "
              + inpGrocery + "."
      );
    } else {
      returnString.append(
          "The fridge contains no "
              + inpGrocery + "."
      );
    }
    System.out.println(returnString.toString());
  }

  public void printTotalValueOfGroceriesInFridge() {
    System.out.println("The total value of all the groceries in the fridge are " + fridge.getValueOfGroceriesInFridge() + " NOK.");
  }

}
