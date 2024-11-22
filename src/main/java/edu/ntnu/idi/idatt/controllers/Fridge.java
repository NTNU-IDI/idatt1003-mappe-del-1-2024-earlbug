package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;


public class Fridge {

  private ArrayList<Grocery> groceryList;

  /**
   * Creates an instance of Fridge.
   */
  public Fridge() {
    groceryList = new ArrayList<>();
  }

  /**
   * Adds one or more groceries to the fridge.
   *
   * @param nameOfGrocery name of the grocery added.
   * @param amount specifies how much of the object is added.
   * @param expirationDate specifies at what date the grocery expires as a Date object
   * @param pricePerUnit
   * @param measuringUnit
   */
  public void addGrocery(String nameOfGrocery, double amount, Date expirationDate, double pricePerUnit, String measuringUnit) {
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.checkIfDoubleIsPositive(amount);
      ParamValidators.parseStringToDateAndValidate(expirationDate.toString());
      ParamValidators.checkIfDoubleIsPositive(pricePerUnit);
      ParamValidators.validateString(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw e;
    }

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

  /**
   * Removes specified grocery.
   *
   * @param inpGrocery Grocery which shall be removed.
   */
  public void removeGrocery(String inpGrocery, double amount) {
    try {
      ParamValidators.validateString(inpGrocery);
      ParamValidators.checkIfDoubleIsPositive(amount);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }

    groceryList.sort(
        Comparator.comparing(Grocery::getExpirationDate)); // Sorts array based on exp.date.


    for (int i = 0; i < groceryList.size(); i++) {
      if (groceryList.get(i).getNameOfGrocery().equalsIgnoreCase(inpGrocery)) { // If name match
        // If there are more grocery left to remove, remove current grocery(i) object
        // and subtract its amount from amount.
        if (groceryList.get(i).getAmountOfGrocery() < amount) {
          amount -= groceryList.get(i).getAmountOfGrocery();
          groceryList.remove(i);
          i -= 1; // The all indexes after the removed object will be subtracted by 1.
        } else { // Remove amount from grocery.
          groceryList.get(i).changeAmount(-amount);
          System.out.println("Removal was a success");
          amount = 0;
          break;
        }
      }
    }
    if (amount > 0) {
      System.out.println("All the " + inpGrocery + " was removed.");
    }
  }
  /*
  private static void removeGroceryValidator(String inpGrocery, double amount) {
    if (amount < 0 || amount == string) {
      throw new IllegalArgumentException("Amount must be positive double value.");
    }
    if (inpGrocery) {

    }
  }
*/

  /**
   * Finds all expired groceries.
   *
   * @return ArrayList of expired grocery objects.
   */
  public ArrayList<Grocery> findExpiredGroceries() {
    return groceryList.stream()
        .filter(grocery -> grocery.getExpirationDate().before(new Date()))
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

  /**
   * Finds out if a grocery exists in the fridge.
   *
   * @param inpGroceryString Checks in the name matches any name present in the fridge.
   *
   * @return  Boolean if the grocery is found or not.
   */
  public boolean groceryExists(String inpGroceryString) {
    return groceryList.stream()
        .anyMatch(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGroceryString));
  }

  // getter grocerylist
  public ArrayList<Grocery> getGroceryList() {
    return groceryList;
  }



}