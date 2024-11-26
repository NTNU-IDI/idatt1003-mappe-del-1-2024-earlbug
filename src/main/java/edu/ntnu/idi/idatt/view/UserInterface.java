package edu.ntnu.idi.idatt.view;


import edu.ntnu.idi.idatt.controllers.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import edu.ntnu.idi.idatt.utils.ScannerValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class UserInterface {

  Fridge fridge;
  boolean programRunning;
  Scanner scanner;
  SimpleDateFormat dateFormat;
  ScannerValidator scannerValidator;


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
    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    scannerValidator = new ScannerValidator();
    scanner = new Scanner(System.in);

    // Dummy groceries.
    try {
      Date date1 = dateFormat.parse("2025-10-30");
      Date date2 = new Date(); // Today's date.
      Date date3 = dateFormat.parse("2023-10-10");

      fridge.addGrocery("Milk", 1.5, date1, 29.9, "liters");
      fridge.addGrocery("Bananas", 5, date2, 5.40, "units");
      fridge.addGrocery("eggs", 12, date3, 2.23, "Units");
    } catch (ParseException e) {
      printRed("The initial groceries dates could not be parsed.");
    } catch (IllegalArgumentException e) {
      printRed(e.getMessage());
    }
    programRunning = true;
    printFridgeContent();
  }

  public void start() {
    while (programRunning) {
      showMenu();
    }
  }

  public void showMenu() {
    int inpMenuChoice;
    System.out.println("\n\nPlease choose what you want to do:\n\n"
        + "0: Stop program\n"
        + "1: view contents of the fridge\n"
        + "2: Add grocery to the fridge\n"
        + "3: Remove grocery\n"
        + "4: View all the expired groceries and its total value\n"
        + "5: View how much the fridge contains of a certain grocery\n"
        + "6: Check the combined value of all the groceries in the fridge\n"
        + "\n"
    );

    try {
      inpMenuChoice = scannerValidator.parseToPositiveInt(scanner.nextLine());

      switch (inpMenuChoice) {
        case 0:
          System.out.println("You closed the fridge.");
          programRunning = false;
          break;
        case 1:
          printFridgeContent();
          break;
        case 2:
          askForGroceryToAdd();
          break;
        case 3:
          askToRemoveGrocery();
          break;
        case 4:
          printExpiredGroceries();
          break;
        case 5:
          try {
            System.out.println("What grocery do you want to check?:");
            String inpGrocery = scanner.nextLine();
            scannerValidator.validateStringScanner(inpGrocery);
            printAmountOfGrocery(inpGrocery);
          } catch (IllegalArgumentException e) {
            printRed(e.getMessage());
          }
          break;
        case 6:
          printTotalValueOfGroceriesInFridge();
          break;
        default:
          printRed(inpMenuChoice + " is not an option.");
          break;
      }
    } catch (IllegalArgumentException e) {
      printRed(e.getMessage());
    }
  }

  /**
   * Asks the user for all the details of the grocery it wants to add.
   * Every input is checked to give a robust explaination for a possible error.
   */
  public void askForGroceryToAdd() {

    // Takes the name of the new grocery.
    String inpGroceryName = "";
    boolean inpGroceryNameAccepted = false;
    while (!inpGroceryNameAccepted) {
      try {
        System.out.print("Please write the name of the new Grocery: ");
        inpGroceryName = scanner.nextLine();
        scannerValidator.validateStringScanner(inpGroceryName);
        inpGroceryNameAccepted = true;
        System.out.println();
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }

    // Takes the amount of the new grocery.
    double inpAmount = 0;
    boolean inpAmountAccepted = false;
    while (!inpAmountAccepted) {
      try {
        System.out.print("Please write the amount of the new grocery(use dot for decimals): ");
        inpAmount = scannerValidator.parseToPositiveDoubleAndValidate(scanner.nextLine());
        inpAmountAccepted = true;
        System.out.println();
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }

    // Takes the expiration date of the new grocery.
    Date expirationDate = null;
    boolean inpExpirationDateAccepted = false;
    while (!inpExpirationDateAccepted) {
      try {
        System.out.println("Please write the expiration date of the new Grocery.");
        System.out.print("The format is as follows: yyyy-MM-dd: ");
        expirationDate = scannerValidator.parseStringToDateAndValidate(scanner.nextLine());
        if (expirationDate.before(new Date())) {
          printYellow("Note: the grocery has already expired.");
        }
        System.out.println();
        inpExpirationDateAccepted = true;

      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }
    // Takes the price per unit of the new grocery.
    double inpPricePerUnit = 0;
    boolean inpPricePerUnitAccepted = false;
    while (!inpPricePerUnitAccepted) {
      try {
        System.out.print("Please write the price per unit of the new Grocery: ");
        inpPricePerUnit = scannerValidator.parseToPositiveDoubleAndValidate(scanner.nextLine());
        System.out.println();
        inpPricePerUnitAccepted = true;

      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }

    // Takes the measuring unit of the new grocery.
    String inpMeasuringUnit = "";
    boolean inpMeasuringUnitAccepted = false;
    while (!inpMeasuringUnitAccepted) {
      try {
        System.out.print("Please write the measuring unit of the new Grocery: ");
        inpMeasuringUnit = scanner.nextLine();
        scannerValidator.validateStringScanner(inpMeasuringUnit);
        inpMeasuringUnitAccepted = true;
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }

    try {
      fridge.addGrocery(inpGroceryName, inpAmount, expirationDate, inpPricePerUnit,
          inpMeasuringUnit);
      System.out.println(inpAmount + " " + inpMeasuringUnit + " of "
          + inpGroceryName + " added successfully.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Prints out all items in the fridge, with all it's attributes.
   */
  public void printFridgeContent() {
    StringBuilder outpString = new StringBuilder("The fridge currently contains:" + "\n");
    for (int i = 0; i < fridge.getGroceryList().size(); i++) {
      Grocery groceryi = fridge.getGroceryList().get(i);
      outpString.append(
          groceryi.getAmountOfGrocery() + " " + groceryi.getMeasuringUnit()
              + " of " + groceryi.getNameOfGrocery() + "\n"
      );
    }
    System.out.println(outpString);
  }

  /**
   * Asks for an input of what grocery the user wants removed.
   */
  public void askToRemoveGrocery() {
    System.out.print("What grocery do you want to remove?: ");
    String grocery = scanner.nextLine();
    if (fridge.groceryExists(grocery)) {
      boolean inputAccepted = false;
      while (!inputAccepted) {
        try {
          System.out.println("How much do you want to remove?");
          double inpAmount = scannerValidator.parseToPositiveDoubleAndValidate(scanner.nextLine());
          fridge.removeGrocery(grocery, inpAmount);
          inputAccepted = true;

        } catch (IllegalArgumentException e) {
          System.out.println();
          printRed(e.getMessage());
        }
      }

    } else {
      printYellow("There are no " + grocery + " in the fridge.");
    }
  }

  /**
   * Prints a list of all the expired groceries
   * Name, amount and expiration date.
   */
  public void printExpiredGroceries() {

    ArrayList<Grocery> expiredGroceries;
    expiredGroceries = fridge.findExpiredGroceries();
    StringBuilder returnString = new StringBuilder();
    double costOfExpiredGroceries = 0;
    SimpleDateFormat descriptiveDateFormat = new SimpleDateFormat("EEEEEE dd. MMMMMMMMM yyyy"); // Prints in norwegian.
    //SimpleDateFormat descriptiveDateFormat = new SimpleDateFormat("dd. MM yyyy"); //as only numbers
    System.out.println("Today's date: " + descriptiveDateFormat.format(new Date()));

    if (expiredGroceries.isEmpty()) {
      returnString.append("There are no expired groceries!");
    } else {
      returnString.append("Expired groceries: \n");
      for (int i = 0; i < expiredGroceries.size(); i++) {
        Grocery currentGrocery = expiredGroceries.get(i);
        returnString.append(currentGrocery.getAmountOfGrocery() + " "
            + currentGrocery.getMeasuringUnit() + " of "
            + currentGrocery.getNameOfGrocery() + " expired "
            + descriptiveDateFormat.format(currentGrocery.getExpirationDate()) + ".\n"
        );
        costOfExpiredGroceries += currentGrocery.getPricePerUnit() * currentGrocery.getAmountOfGrocery();
      }
    }
    returnString.append("\n For a total of " + costOfExpiredGroceries + " NOK");
    System.out.println(returnString);
  }

  /**
   * Prints the amount of the specified grocery.
   *
   * @param inpGrocery The grocery which shall be counted.
   */
  public void printAmountOfGrocery(String inpGrocery) {
    try {
      ParamValidators.validateString(inpGrocery);
    } catch (IllegalArgumentException e) {
      printRed(e.getMessage());
    }

    StringBuilder returnString = new StringBuilder();
    if (fridge.getAmountOfGrocery(inpGrocery) > 0) {
      String measuringUnit;
      // finds the measuring unit

      measuringUnit = fridge.getGroceryList().stream()
          .filter(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGrocery))
          .findFirst().get().getMeasuringUnit();

      // Original funksjon som finner måleenheten.
      /*for (int i = 0; i < fridge.getGroceryList().size(); i++) {
        Grocery grocery = fridge.getGroceryList().get(i);
        if (grocery.getNameOfGrocery().equals(inpGrocery)) {
          measuringUnit = grocery.getMeasuringUnit();
          break;
        }
      }*/

      returnString.append("The fridge contains "
              + fridge.getAmountOfGrocery(inpGrocery) + " "
              + measuringUnit + " of "
              + inpGrocery + "."
      );
    } else {
      returnString.append("The fridge contains no " + inpGrocery + ".");
    }
    System.out.println(returnString);
  }

  /**
   * Prints the total value of all the groceries in the fridge.
   */
  public void printTotalValueOfGroceriesInFridge() {
    System.out.println("The total value of all the groceries in the fridge are "
        + fridge.getValueOfGroceriesInFridge() + " NOK.");
  }

  /**
   * Prints red text, used for example when printing error messages.
   *
   * @param inpString The string to be printed in red.
   */
  public void printRed(String inpString) {
    System.out.println("\u001B[31m" + inpString + "\u001B[0m");
  }

  /**
   * Prints yellow text, used for example when printing warning messages.
   *
   * @param inpString The string to be printed in yellow.
   */
  public void printYellow(String inpString) {
    System.out.println("\u001B[33m" + inpString + "\u001B[0m");
  }
}
