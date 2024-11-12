package edu.ntnu.idi.idatt;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

  Fridge fridge;
  boolean programRunning;
  Scanner scanner;

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
      date1 = dateFormat.parse("2025-10-30");
      date2 = dateFormat.parse("2024-10-20");
      date3 = dateFormat.parse("2023-10-10");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    fridge.addGrocery("Milk", 1.5, date1, 29.9, "liters");
    fridge.addGrocery("Bananas", 5, date2, 5.40, "units");
    fridge.addGrocery("eggs", 12, date3, 2.23, "Units");

    programRunning = true;
    scanner = new Scanner(System.in);
    printFridgeContent();

  }

  public void start() {

    while (programRunning) {
      int numberOfChoices = 6;
      int inpMenuChoice;
      while (true) {
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
          inpMenuChoice = scanner.nextInt();
          scanner.nextLine();
          if (inpMenuChoice > numberOfChoices || inpMenuChoice < 0) {
            printRed(inpMenuChoice + " is not an option.");
          } else {
            break; // Break out of the while loop
          }
        } catch (InputMismatchException e) {
          printRed("Please enter a valid number.");
          scanner.next(); // Clears the scanner, so the input does not remain.
        }
      }

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
          printExpiredgroceries();
          break;
        case 5:
          System.out.println("What grocery do you want to check?:");
          String inpGrocery = scanner.nextLine();
          printAmountOfGrocery(inpGrocery);
          break;
        case 6:
          printTotalValueOfGroceriesInFridge();
          break;
        default:
          break;
      }

    }

  }

  /**
   * Asks the user for all the details of the grocery it wants to add.
   * Every input is checked to give a robust explaination for a possible error.
   */
  public void askForGroceryToAdd() {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Scanner scanner = new Scanner(System.in);
    Date expirationDate;

    // Takes the name of the new grocery.
    String inpGroceryName;
    while (true) {
      System.out.print("Please write the name of the new Grocery: ");
      inpGroceryName = scanner.nextLine();
      System.out.println();
      if (inpGroceryName.isEmpty()) {
        printRed("The field cannot be empty.");
      } else {
        break; // Break out of the while loop and continue.
      }
    }

    // Takes the amount of the new grocery.
    double inpAmount;
    while (true) {
      try {
        System.out.print("Please write the amount of the new grocery(use commas for decimals): ");
        inpAmount = scanner.nextDouble(); // uses comma"," not dot"."
        scanner.nextLine(); // Swallows the enter typed which would otherwise be an unwanted input for the next scanner.
        System.out.println();
        if (inpAmount <= 0) {
          printRed("Amount must be more than 0.");
        } else {
          System.out.println();
          break;
        }
      } catch (InputMismatchException e) {
        printRed("\nPlease write a valid number.");
        scanner.nextLine();
      }
    }
    // Takes the expiration date of the new grocery.
    String inpExpirationDate;
    while (true) {
      try {
        System.out.println("Please write the expiration date of the new Grocery.");
        System.out.print("The format is as follows: yyyy-MM-dd: ");
        inpExpirationDate = scanner.nextLine();
        expirationDate = dateFormat.parse(inpExpirationDate);
        if (expirationDate.before(new Date())) {
          System.out.println("Note; the grocery has already expired.");
        }
        System.out.println();
        break;

      } catch (InputMismatchException e) {
        printRed("\nPlease write a valid number.");
        scanner.nextLine();
      } catch (ParseException e) {
        printRed("\nInvalid date. Please try again.");
      }
    }
    // Takes the price per unit of the new grocery.
    double inpPricePerUnit;
    while (true) {
      try {
        System.out.print("Please write the price per unit of the new Grocery: ");
        inpPricePerUnit = scanner.nextDouble();
        scanner.nextLine(); //swallows the enter typed which would otherwise be an unwanted input for the next scanner
        System.out.println();
        if (inpPricePerUnit <= 0) {
          printRed("Price must be more than 0.");
        } else {
          break;
        }
      } catch (InputMismatchException e) {
        printRed("\nPlease write a valid number.");
        scanner.nextLine();
      }
    }
    // Takes the measuring unit of the new grocery.
    String inpMeasuringUnit;
    while (true) {
      System.out.print("Please write the measuring unit of the new Grocery: ");
      inpMeasuringUnit = scanner.nextLine();
      System.out.println();
      if (inpMeasuringUnit.isEmpty()) {
        printRed("The field cannot be empty.");
      } else {
        break; // Break out of the while loop and continue.
      }
    }
    fridge.addGrocery(inpGroceryName, inpAmount, expirationDate, inpPricePerUnit, inpMeasuringUnit);
    System.out.println(inpAmount + " " + inpMeasuringUnit + " of " + inpGroceryName + " added successfully.");
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEE dd. MMMMMMMMM yyyy"); // skriver ut på norsk
    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM yyyy"); //as only numbers
    System.out.println("Today's date: " + dateFormat.format(new Date()));

    if (expiredGroceries.isEmpty()) {
      returnString.append("There are no expired groceries!");
    } else {
      returnString.append("Expired groceries: \n");
      for (int i = 0; i < expiredGroceries.size(); i++) {
        Grocery currentGrocery = expiredGroceries.get(i);
        returnString.append(currentGrocery.getAmountOfGrocery() + " "
            + currentGrocery.getMeasuringUnit() + " of "
            + currentGrocery.getNameOfGrocery() + " expired "
            + dateFormat.format(currentGrocery.getExpirationDate()) + ".\n"
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
   * Prints red text, used for example when printing errormessages.
   * @param inpString The string to be printed in red.
   */
  public void printRed(String inpString) {
    System.out.println("\u001B[31m" + inpString + "\u001B[0m");
  }

}
