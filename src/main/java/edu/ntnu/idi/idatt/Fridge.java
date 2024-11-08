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

  }

  /**
   * Adds one or more groceries to the fridge.
   * @param nameOfGrocery name of the grocery added.
   * @param amount specifies how much of the object is added.
   * @param expirationDate specifies at what date the grocery expires as a Date object
   * @param pricePerUnit
   * @param measuringUnit
   */

  //todo find out where it is the most appropriate to have input tests:
  //to have the tests in the "askFor" methods and be certain the result passed to the next method is clean
  //or to have the test in the secound method, which throws an exeption, vhech can easly be caugth by the ask for methods
  public void addGrocery(String nameOfGrocery, double amount, Date expirationDate,
      double pricePerUnit, String measuringUnit) {
    Grocery newGrocery = new Grocery(nameOfGrocery, amount, expirationDate, pricePerUnit,
        measuringUnit);
    groceryList.add(newGrocery);
  }



  // returns how much of the specified argument is currently in the fridge, as a double.
  public double amountOfGrocery(String inpGrocery) {
    double numberOfGroceries = 0;
    String grocery = inpGrocery.toLowerCase();
    for (int i = 0; i < groceryList.size(); i++) {
      String currentGrocery = groceryList.get(i).getNameOfGrocery(); // get name of grocery with index i.
      if (currentGrocery.equals(grocery)) { // if grocery i is the same as the method input
        numberOfGroceries += groceryList.get(i).getAmountOfGrocery(); // add the amount found to the to be returned value
      }
    }
    return numberOfGroceries;
  }



  // Find out how to deal with multiple elements with different expiration dates
  public void removeGrocery(String grocery) {
    for (int i = 0; i < groceryList.size(); i++) {
      if (groceryList.get(i).getNameOfGrocery().equals(grocery)) {
        groceryList.remove(i);
        i++; // because all the elements after gets shifted. All indexes after subtracts 1.
      }
    }
  }





  public ArrayList<Integer> findIndexOfExpiredGroceries(){
    ArrayList<Integer> indexOfExpiredGroceries = new ArrayList<>();
    Date currentDate = new Date();
    for (int i = 0; i < groceryList.size(); i++) {
      if(currentDate.before(groceryList.get(i).getExpirationDate())) {
        indexOfExpiredGroceries.add(i);
      }
    }
    return indexOfExpiredGroceries;
  }

  /**
   * finds the combined value of all the items in the fridge.
   *
   * @return value of items in fridge.
   */
  public double getValueOfGroceriesInFridge() {
    double valueOfFridge = 0;
    for (int i = 0; i < groceryList.size(); i++) {
      Grocery currentGrocery = groceryList.get(i);
      valueOfFridge += currentGrocery.getAmountOfGrocery() * currentGrocery.getPricePerUnit();

    }
    return valueOfFridge;
  }

  // getter grocerylist
  public ArrayList<Grocery> getGroceryList() {
    return groceryList;
  }
}