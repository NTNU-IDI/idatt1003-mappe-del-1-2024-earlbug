package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Fridge {

  ArrayList<Grocery> groceryList;

  public Fridge() {
    groceryList = new ArrayList<>();
    // dummy groceries.
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = new Date();
    Date date2 = new Date();
    Date date3 = new Date();

    try{
      date1 = dateFormat.parse("2024-10-30");
      date2 = dateFormat.parse("2024-10-20");
      date3 = dateFormat.parse("2024-10-10");
    } catch (ParseException e) {
      e.printStackTrace();
    }


    addGrocery("Milk", (float)1.5, date1, (float)29.9, "liters");
    addGrocery("Bananas", (float)5, date2, (float)5.40, "units");
    addGrocery("eggs", (float)12, date3, (float)2.23, "Units");
  }

  /**
   * Adds one or more groceries to the fridge.
   * @param nameOfGrocery name of the grocery added.
   * @param amount specifies how much of the object is added.
   * @param expirationDate specifies at what date the grocery expires as a Date object
   * @param pricePerUnit
   * @param measuringUnit
   */
  private void addGrocery(String nameOfGrocery, float amount, Date expirationDate,
      float pricePerUnit, String measuringUnit) {
    Grocery newGrocery = new Grocery(nameOfGrocery, amount, expirationDate, pricePerUnit,
        measuringUnit);
    groceryList.add(newGrocery);
  }

  public void askForGrocery() {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date expirationDate = new Date();

    Scanner scanner = new Scanner(System.in);

    System.out.print("Please write the name of the new Grocery: ");
    String inpGroceryName = scanner.nextLine();
    System.out.print("Please write the amount of the new Grocery: ");
    float inpAmount = scanner.nextFloat(); // uses comma",", not dot"."
    scanner.nextLine(); //swallws the enter typed which would otherwise be an unwanted input for the next scanner
    System.out.println("Please write the expiration date of the new Grocery.");
    System.out.print("The format is as follows: yyyy-MM-dd: ");
    String inpExpirationDate = scanner.nextLine();
    System.out.print("Please write the price per unit of the new Grocery: ");
    float inpPricePerUnit = scanner.nextFloat();
    scanner.nextLine(); //swallws the enter typed which would otherwise be an unwanted input for the next scanner
    System.out.print("Please write the measuring unit of the new Grocery: ");
    String inpMeasuringUnit = scanner.nextLine();

    // TODO make the check exeption
    //todo convert String date to Date date
    try {
       expirationDate = dateFormat.parse(inpExpirationDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }




    addGrocery(inpGroceryName, inpAmount, expirationDate, inpPricePerUnit, inpMeasuringUnit);
  }
  // returns how much of the specified argument is currently in the fridge, as a float.
  private float amountOfGrocery(String inpGrocery) {
    float numberOfGroceries = 0;
    String grocery = inpGrocery.toLowerCase();
    for (int i = 0; i < groceryList.size(); i++) {
      String currentGrocery = groceryList.get(i).getNameOfGrocery(); // get name of grocery with index i
      if (currentGrocery.equals(grocery)){ // if grocery i is the same as the method input
        numberOfGroceries += groceryList.get(i).getAmountOfGrocery(); // add the amount found to the to be returned value
      }
    }
    return numberOfGroceries;
  }
  //
  public String amountOfGroceryAsText(String inpGrocery) {
    StringBuilder returnString = new StringBuilder();
    if(amountOfGrocery(inpGrocery) > 0) {
      String measuringUnit = new String();
      // finds the measuring unit
      for (int i = 0; i < groceryList.size(); i++) {
        if (groceryList.get(i).getNameOfGrocery().equals(inpGrocery)) {
          measuringUnit = groceryList.get(i).getMeasuringUnit();
          break;
        }
      }
      returnString.append("The fridge contains ");
      returnString.append(amountOfGrocery(inpGrocery) + " ");
      returnString.append(measuringUnit + " of ");
      returnString.append(inpGrocery + ".");
    } else {
      returnString.append("The fridge contains no ");
      returnString.append(inpGrocery + ".");
    }
    return returnString.toString();
  }
  // Find out how to deal with multiple elements with different expiration dates
  private void removeGrocery(String grocery){
    for (int i = 0; i < groceryList.size(); i++) {
      if (groceryList.get(i).getNameOfGrocery().equals(grocery)){
        groceryList.remove(i);
      }
    }
  }

  public void askToRemoveGrocery() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("What grocery do you want to remove?: ");
    String grocery = scanner.nextLine();
    removeGrocery(grocery);
  }

  public void printFridgeContent(){
    StringBuilder returnString = new StringBuilder("The fridge currently contains:" + "\n");
    for (int i = 0; i < groceryList.size(); i++) {
      Grocery groceryi = groceryList.get(i);
      returnString.append(
          groceryi.getAmountOfGrocery() + " " + groceryi.getMeasuringUnit() + " of " + groceryi.getNameOfGrocery() + "\n"
      );
    }
    System.out.println(returnString);
  }

  private ArrayList<Integer> findIndexOfExpiredGroceries(){
    ArrayList<Integer> indexOfExpiredGroceries = new ArrayList<>();
    Date currentDate = new Date();
    for (int i = 0; i < groceryList.size(); i++) {
      if(currentDate.before(groceryList.get(i).getExpirationDate())) {
        indexOfExpiredGroceries.add(i);
      }
    }
    return indexOfExpiredGroceries;
  }

  public void printExpiredgroceries(){
    ArrayList<Integer> indexOfExpiredGroceries;
    indexOfExpiredGroceries = findIndexOfExpiredGroceries();
    StringBuilder returnString = new StringBuilder();
    float costOfExpiredGroceries = 0;
    if (indexOfExpiredGroceries.isEmpty()){
      returnString.append("There are no expired groceries!");
    }
    returnString.append("Expired gorceries: \n");
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEE dd MMMMMMMMM yyyy"); // skriver ut på norsk
    for (int i = 0; i < indexOfExpiredGroceries.size(); i++) {
      Grocery currentGrocery = groceryList.get(indexOfExpiredGroceries.get(i));
      returnString.append(
          currentGrocery.getAmountOfGrocery() + " "
          + currentGrocery.getMeasuringUnit() + " of "
          + currentGrocery.getNameOfGrocery() + " expired "
          + dateFormat.format(currentGrocery.getExpirationDate()) + ".\n"
      );
      costOfExpiredGroceries += currentGrocery.getPricePerUnit() * currentGrocery.getAmountOfGrocery();

    }
    returnString.append("\n For a total of " + costOfExpiredGroceries + " NOK");
    System.out.println(returnString.toString());
  }

  private float getValueOfGroceriesInFridge(){
    float valueOfFridge = 0;
    for (int i = 0; i < groceryList.size(); i++) {
      Grocery currentGrocery = groceryList.get(i);
      valueOfFridge += currentGrocery.getAmountOfGrocery() * currentGrocery.getPricePerUnit();

    }
    return valueOfFridge;
  }

  public void printTotalValueOfGroceriesInFridge(){
    System.out.println("The total value of all the groceries in the fridge are " + getValueOfGroceriesInFridge() + " NOK.");
  }
}