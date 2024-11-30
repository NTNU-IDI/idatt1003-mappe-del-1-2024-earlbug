package edu.ntnu.idi.idatt.view;


import edu.ntnu.idi.idatt.controllers.CookBook;
import edu.ntnu.idi.idatt.controllers.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import edu.ntnu.idi.idatt.utils.ScannerValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * User Interface is what ensures communication between the user and the program. Its main task is
 *    to get information from <code>Fridge</code> and process it to be understandable for the user,
 *    and to take user input and make it readable for the program.
 *    A UserInterface instance is initiated when the user starts the program.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.5.0
 */
public class UserInterface {

  Fridge fridge;
  CookBook cookBook;
  boolean programRunning;
  Scanner scanner;
  SimpleDateFormat dateFormat;
  ScannerValidator scannerValidator;


  /**
   * Fridge constructor.
   */
  public UserInterface() {

  }

  /**
   * initializes the program by calling all the necessary objects.
   */
  public void init() {
    fridge = new Fridge();
    cookBook = new CookBook();
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

  /**
   * Shows menu repeatedly until the user exits the program.
   */
  public void start() {
    while (programRunning) {
      showMenu();
    }
  }

  /**
   * Displays a menu with options the user can choose to do, and calls for the chosen method.
   */
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
          askForWhatGroceryToCheck();
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

  private void askForWhatGroceryToCheck() {
    boolean validInput = false;
    while (!validInput) {
      try {
        System.out.println("What grocery do you want to check?:");
        String inpGrocery = scanner.nextLine();
        scannerValidator.validateStringScanner(inpGrocery);
        validInput = true;
        printAmountOfGrocery(inpGrocery);
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }
  }

  /**
   * Asks the user for all the details of the grocery it wants to add. If the input is invalid, then
   *    the user get asked again until the input is valid. When all details are filled, the grocery
   *    is added to the fridge.
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
    // Expired groceries are allowed, but the user will get notified.
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

    // Tries to add grocery to Fridge.
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
    System.out.println("The fridge currently contains:");
    for (Grocery grocery : fridge.getGroceryList()) {
      System.out.println(grocery.getAmount() + " " + grocery.getMeasuringUnit() + " of "
          + grocery.getName()
      );
    }
  }

  /**
   * Asks what the user wants to remove. The user will get notified if the specified
   *    <code>Grocery</code> does not exist.
   */
  public void askToRemoveGrocery() {
    boolean inputAccepted = false;
    String inpGrocery = "";

    // Asks for the Grocery name.
    while (!inputAccepted) {
      try {
        System.out.print("What grocery do you want to remove?: ");
        inpGrocery = scanner.nextLine();
        scannerValidator.validateStringScanner(inpGrocery);
        inputAccepted = true;
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }
    // Asks for the amount to be removed.
    inputAccepted = false;
    if (fridge.groceryExists(inpGrocery)) {
      while (!inputAccepted) {
        try {
          System.out.println("How much do you want to remove?");
          double inpAmount = scannerValidator.parseToPositiveDoubleAndValidate(scanner.nextLine());
          fridge.removeGrocery(inpGrocery, inpAmount);
          inputAccepted = true;

        } catch (IllegalArgumentException e) {
          System.out.println();
          printRed(e.getMessage());
        }
      }
    } else {
      printYellow("There are no " + inpGrocery + " in the fridge.");
    }
  }

  /**
   * Prints a list of all the expired groceries with name, amount and expiration date. The total
   *    value of all expired groceries will also be displayed.
   */
  public void printExpiredGroceries() {
    ArrayList<Grocery> expiredGroceries;
    expiredGroceries = fridge.findExpiredGroceries();
    double costOfExpiredGroceries = 0;

    if (expiredGroceries.isEmpty()) {
      System.out.println("There are no expired groceries!");

    } else {
      System.out.println("Expired groceries: \n");
      for (Grocery grocery : expiredGroceries) {
        costOfExpiredGroceries += grocery.getPricePerUnit() * grocery.getAmount();
        System.out.println(grocery.getMeasuringUnit() + " of "
            + grocery.getName() + " expired "
            + dateFormat.format(grocery.getExpirationDate()) + ".\n"
        );
      }
      System.out.println("For a total of " + costOfExpiredGroceries + "kr.");
    }
  }

  /**
   * Prints how much of the specified <code>Grocery</code> is present in the <code>Fridge</code>.
   *
   * @param inpGrocery The grocery which shall be counted.
   */
  public void printAmountOfGrocery(String inpGrocery) {
    try {
      ParamValidators.validateString(inpGrocery);
      String measuringUnit = fridge.getMeasuringUnitByName(inpGrocery);

      System.out.println("The fridge contains "
          + fridge.getAmountByName(inpGrocery) + " "
          + measuringUnit
          + " of "
          + inpGrocery + "."
      );
    } catch (IllegalArgumentException e) {
      if (fridge.getAmountByName(inpGrocery) == 0) {
        System.out.println("The fridge contains no " + inpGrocery + ".");
      } else {
        printRed(e.getMessage());
      }
    }
  }


  /**
   * Prints out which of the dishes in <code>CookBook</code> can be made based off what groceries
   *    are present in <code>groceryList</code>.
   */
  public void printAllPossibleDishesWithFridgeContent() {
    ArrayList<Recipe> listOfPossibleDishes = fridge.returnAllPossibleDishesWithFridgeContent(
        cookBook.getRecipeList());

    System.out.println("Dishes that can be made with the items currently in the fridge:");
    for (Recipe recipe : listOfPossibleDishes) {
      System.out.println(recipe.getName());
    }
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
   * @param inpString the string to be printed in yellow.
   */
  public void printYellow(String inpString) {
    System.out.println("\u001B[33m" + inpString + "\u001B[0m");
  }
}
