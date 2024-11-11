package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Fridge {

  ArrayList<Grocery> groceryList;

  /**
   * Creates an instance of Fridge.
   */
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
  //or to have the test in the second method, which throws an exeption, vhech can easly be caugth by the ask for methods
  public void addGrocery(String nameOfGrocery, double amount, Date expirationDate,
      double pricePerUnit, String measuringUnit) {
    Grocery newGrocery = new Grocery(
        nameOfGrocery, amount, expirationDate, pricePerUnit, measuringUnit);
    groceryList.add(newGrocery);
  }





  /**
   * Returns how much of the specified grocery is currently in the fridge, as a double.
   *
   * @param inpGrocery The grocery you want to find the amount of.
   *
   * @return Returns how much the fridge has of the grocery.
   */
  public double getAmountOfGrocery(String inpGrocery) {
    return groceryList.stream()
        .filter(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGrocery))
        // For each Grocery element in groceryList, use grocery.getAmountOgGrocery
        .mapToDouble(Grocery::getAmountOfGrocery)
        .sum();
  }



  // Find out how to deal with multiple elements with different expiration dates

  /**
   * Removes specified grocery.
   *
   * @param inpGrocery Grocery which shall be removed.
   */
  public void removeGrocery(String inpGrocery) {
    groceryList.removeIf(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGrocery));
  }


  /**
   * Finds all expired groceries.
   *
   * @return ArrayList of expired grocery objects.
   */

  public ArrayList<Grocery> findExpiredGroceries() {
    return groceryList.stream()
        .filter(grocery -> grocery.getExpirationDate().after(new Date()))
        // Collects the elements of a stream to an ArrayList
        // Normally it would be collect to a list.(Collector.toList())
        .collect(Collectors.toCollection/*static, makes new empty collection*/(ArrayList::new));
  }

  /**
   * finds the combined value of all the items in the fridge.
   *
   * @return value of items in fridge.
   */
  public double getValueOfGroceriesInFridge() {
    return groceryList.stream()
        .mapToDouble(grocery -> grocery.getAmountOfGrocery() * grocery.getPricePerUnit())
        .sum();
  }

  // getter grocerylist
  public ArrayList<Grocery> getGroceryList() {
    return groceryList;
  }
}