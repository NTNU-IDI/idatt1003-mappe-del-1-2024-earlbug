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
 * @version 0.7.0
 */
public class UserInterface {

  Fridge fridge;
  CookBook cookBook;
  boolean programRunning;
  Scanner scanner;
  SimpleDateFormat dateFormat;

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
    scanner = new Scanner(System.in);

    // Dummy groceries.
    try {
      Date date1 = dateFormat.parse("2025-10-30");
      Date date2 = new Date(); // Today's date.
      Date date3 = dateFormat.parse("2023-10-10");

      fridge.addGrocery("milk", 1.5, date1, 29.9, "liters");
      fridge.addGrocery("bananas", 5, date2, 5.40, "units");
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
    System.out.println("\n\nPlease choose what you want to do:\n\n"
        + "0: Stop program\n"
        + "1: view contents of the fridge\n"
        + "2: Add grocery to the fridge\n"
        + "3: Remove grocery\n"
        + "4: View all the groceries which has expired given a certain date and its total value\n"
        + "5: View how much the fridge contains of a certain grocery\n"
        + "6: Check the combined value of all the groceries in the fridge\n"
        + "7: Sort the groceries alphabetically\n"
        + "8: Add a recipe to the cook book\n"
        + "9: Check if the fridge contains enough ingredients to make a certain dish\n"
        + "10: Check what recipes can be made with the groceries currently in the fridge\n"
        + "11: View all recipes\n"
        + "12: View one whole recipe\n"
        + "\n"
    );

    int inpMenuChoice = demandPositiveInt();
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
      case 7:
        printFridgeContentAlphabetically();
        break;
      case 8:
        askForRecipeToAddToCookBook();
        break;
      case 9:
        canDishBeMadeFromFridge();
        break;
      case 10:
        whatDishesCanBeMadeWithFridge();
        break;
      case 11:
        printAllRecipes();
        break;
      case 12:
        askWhatRecipeToPrint();
        break;
      default:
        printRed(inpMenuChoice + " is not an option.");
        break;
    }
  }

  /**
   * Asks the user what Grocery it wants to check the amount of.
   */
  private void askForWhatGroceryToCheck() {
    System.out.println("What grocery do you want to check?:");
    String inpGrocery = demandValidString();
    printAmountOfGrocery(inpGrocery);
  }

  /**
   * Asks the user for all the details of the grocery it wants to add. When all details are filled,
   *    the grocery is added to the fridge.
   */
  public void askForGroceryToAdd() {

    // Takes the name of the new grocery.
    System.out.print("Please write the name of the new Grocery: ");
    String inpGroceryName = demandValidString();

    // Takes the amount of the new grocery.
    System.out.print("Please write the amount of the new grocery(use dot for decimals): ");
    double inpAmount = demandPositiveDouble();


    // Takes the expiration date of the new grocery.
    // Expired groceries are allowed, but the user will get notified.
    System.out.println("Please write the expiration date of the new Grocery.");
    System.out.print("The format is as follows: yyyy-MM-dd: ");
    Date expirationDate = demandValidDate();
    if (expirationDate.before(new Date())) {
      printYellow("Note: the grocery has already expired.");
    }

    // Takes the price per unit of the new grocery.

    System.out.print("Please write the price per unit of the new Grocery: ");
    double inpPricePerUnit = demandPositiveDouble();

    // Takes the measuring unit of the new grocery.

    System.out.print("Please write the measuring unit of the new Grocery: ");
    String inpMeasuringUnit = demandValidString();

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
   * Prints out all items in the fridge with name, amount and measuring unit.
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
   * Asks what the user wants to remove. The oldest Grocery will be removed/reduced first. If more
   *    is asked to remove than is present in Fridge, all the specified Grocery will be removed.
   * The user will get notified if the specified <code>Grocery</code> does not exist.
   */
  public void askToRemoveGrocery() {

    // Asks for the Grocery name.
    System.out.print("What grocery do you want to remove?: ");
    String inpGrocery = demandValidString();

    // Asks for the amount to be removed.
    System.out.println("How much do you want to remove?");
    double inpAmount = demandPositiveDouble();

    if (fridge.groceryExists(inpGrocery)) {
      fridge.removeGrocery(inpGrocery, inpAmount);
    } else {
      printYellow("There are no " + inpGrocery + " in the fridge.");
    }
  }

  /**
   * Prints a list of all the Groceries which expired after the given date with name, amount and
   *    expiration date. The total value of all expired groceries will also be displayed.
   */
  public void printExpiredGroceries() {
    ArrayList<Grocery> expiredGroceries;

    System.out.println(
        "Please write the date you want to find out if any groceries has expired before:");
    System.out.println("The format is as follows: yyyy-MM-dd");
    Date inpDate = demandValidDate();
    expiredGroceries = fridge.findGroceriesWhichExpiresAfter(inpDate);
    double costOfExpiredGroceries = 0;

    if (expiredGroceries.isEmpty()) {
      System.out.println("There are no expired groceries after the given date!");

    } else {
      System.out.println("Expired groceries: \n");
      for (Grocery grocery : expiredGroceries) {
        costOfExpiredGroceries += grocery.getPricePerUnit() * grocery.getAmount();
        System.out.println(grocery.getMeasuringUnit() + " of "
            + grocery.getName() + " expires "
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
          + fridge.getAmountByName(inpGrocery) + " " + measuringUnit + " of " + inpGrocery + "."
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
   * Prints the total value of all the groceries in the fridge.
   */
  public void printTotalValueOfGroceriesInFridge() {
    System.out.println("The total value of all the groceries in the fridge are "
        + fridge.getValueOfGroceriesInFridge() + " NOK.");
  }

  /**
   * Prints all content of Fridge alphabetically, with name and amount.
   */
  public void printFridgeContentAlphabetically() {
    System.out.println("The fridge currently contains:");
    for (Grocery grocery : fridge.retrunAlphabeticaclArrayList()) {
      System.out.println(grocery.getAmount() + " " + grocery.getMeasuringUnit() + " of "
          + grocery.getName()
      );
    }
  }

  /**
   * Asks the user for details about the new recipe, and adds it to <code>cookBook</code>.
   */
  public void askForRecipeToAddToCookBook() {
    System.out.println("Please write the name of the new dish:");
    String recipeName = demandValidString();

    System.out.println("Please write the description of the new dish:");
    String recipeDescription = demandValidString();

    System.out.println("Please write how to make the new dish:");
    String recipeProcedure = demandValidString();

    System.out.println("Please write how many different ingredients are needed:");
    int ingredientAmount = demandPositiveInt();

    ArrayList<Grocery> ingredientList = new ArrayList<>();
    for (int i = 0; i < ingredientAmount; i++) {
      System.out.println("Please specify grocery nr. " + (i + 1));
      ingredientList.add(demandValidIngredientGrocery());
    }

    System.out.println("Please write how many portions the dish yields: ");
    int portions = demandPositiveInt();

    try {
      cookBook.addRecipe(recipeName, recipeDescription, recipeProcedure, ingredientList, portions);
      System.out.println(recipeName + " added successfully.");
    } catch (IllegalArgumentException e) {
      printRed(e.getMessage());
    }
  }

  /**
   * Prints out all dished which can be made based on what <code>fridge</code> contains.
   */
  public void whatDishesCanBeMadeWithFridge() {
    ArrayList<Recipe> recipeList =
        fridge.returnAllPossibleDishesWithFridgeContent(cookBook.getRecipeList());
    if (recipeList.isEmpty()) {
      System.out.println("No dishes can be made..");
    } else {
      System.out.println("Dishes that can be made:");
      for (Recipe recipe : recipeList) {
        System.out.println(recipe.getName());
      }
    }
  }

  /**
   * Prints the name of all recipes in <code>CookBook</code>.
   */
  public void printAllRecipes() {
    System.out.println("Recipes in the cook book:");
    for (Recipe recipe : cookBook.getRecipeList()) {
      System.out.println(recipe.getName());
    }
  }

  /**
   * Asks the user what recipe to print, then prints it.
   */
  public void askWhatRecipeToPrint() {
    System.out.println("What recipe do you want to view?");
    Recipe recipe = cookBook.getRecipeByName(demandValidString());
    if (recipe == null) {
      System.out.println("The dish does not exist.");
    } else {
      System.out.println("\nRecipe name:");
      System.out.println(recipe.getName());
      System.out.println("\nDescription:");
      System.out.println(recipe.getDescription());
      System.out.println("\nHow to make it:");
      System.out.println(recipe.getProcedure());
      System.out.println("\nRecipe name:");
      System.out.println(recipe.getName());
      System.out.println("\nIngredient list:");
      for (Grocery ingredient : recipe.getIngredientList()) {
        System.out.println(ingredient.getAmount() + " " + ingredient.getMeasuringUnit() + " of "
            + ingredient.getName());
      }
      System.out.println("\nAnd yields " + recipe.getPortions() + " portions.");
    }
  }

  /**
   * Asks the user for what dish it wants to check, and gives an answer whether the dish can be made
   *    based on what the fridge contains.
   */
  public void canDishBeMadeFromFridge() {
    System.out.println("Please write what dish you want to check:");
    String inpDish = demandValidString();
    Recipe recipe = cookBook.getRecipeByName(inpDish);
    if (recipe == null) {
      System.out.println("The dish does not exist.");
    } else {
      if (fridge.canRecipeBeMadeWithFridgeContent(recipe)) {
        System.out.println("Yes, the dish can be made!");
      } else {
        System.out.println("No, the dish cannot be made.");
      }
    }
  }


  /**
   * Asks the user for a positive integer. The method repeats until the user has provided a valid
   *    integer.
   *
   * @return a valid, positive integer.
   */
  public int demandPositiveInt() {
    boolean validInput = false;
    int inpInt = 0;
    while (!validInput) {
      try {
        String inpString = scanner.nextLine();
        ScannerValidator.validateStringScanner(inpString);
        inpInt = ScannerValidator.parseToPositiveInt(inpString);
        validInput = true;
      } catch (IllegalArgumentException e) {
        printRed(e.getMessage());
      }
    }
    return inpInt;
  }

  /**
   * Asks the user for a non-empty, non-null String. The method repeats until the user has provided
   *    a valid input.
   *
   * @return a valid lowercase String
   */
  public String demandValidString() {
    String inpString = "";
    boolean stringAccepted = false;
    while (!stringAccepted) {
      try {
        inpString = scanner.nextLine();
        ScannerValidator.validateStringScanner(inpString);
        stringAccepted = true;
        System.out.println();
      } catch (Exception e) {
        printRed(e.getMessage());
      }
    }
    return inpString.toLowerCase();
  }

  /**
   * Asks the user for a positive <code>float</code>. The method repeats until the user has provided
   *    a valid input.
   *
   * @return a valid, positive <code>double</code>.
   */
  public double demandPositiveDouble() {
    double inpFloat = 0;
    boolean inpFloatAccepted = false;
    while (!inpFloatAccepted) {
      try {
        inpFloat = ScannerValidator.parseToPositiveDouble(scanner.nextLine());
        inpFloatAccepted = true;
        System.out.println();
      } catch (Exception e) {
        printRed(e.getMessage());
      }
    }
    return inpFloat;
  }

  /**
   * Asks the user for a non-null Date. The method repeats until the user has provided a valid
   *    input.
   *
   * @return a valid Date.
   */
  public Date demandValidDate() {
    Date expirationDate = null;
    boolean inpExpirationDateAccepted = false;
    while (!inpExpirationDateAccepted) {
      try {
        expirationDate = ScannerValidator.parseStringToDate(scanner.nextLine());
        System.out.println();
        inpExpirationDateAccepted = true;
      } catch (Exception e) {
        printRed(e.getMessage());
      }
    }
    return expirationDate;
  }

  /**
   * Asks the user for details of a new Grocery ingredient. The method repeats until the user has
   *    provided a valid input.
   *
   * @return a valid Grocery ingredient.
   */
  public Grocery demandValidIngredientGrocery() {
    Grocery inpIngredient = null;
    boolean inpIngredientAccepted = false;
    while (!inpIngredientAccepted) {
      try {
        System.out.println("Please write it's name:");
        String ingredientName = demandValidString();

        System.out.println("Please write it's amount:");
        double ingredientAmount = demandPositiveDouble();

        System.out.println("Please write it's measuring unit:");
        String ingredientMeasuringUnit = demandValidString();

        inpIngredient = new Grocery(ingredientName, ingredientAmount, ingredientMeasuringUnit);

        inpIngredientAccepted = true;
      } catch (Exception e) {
        printRed(e.getMessage());
      }
    }
    return inpIngredient;
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
