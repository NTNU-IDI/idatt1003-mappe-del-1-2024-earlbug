package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */

public class Fridge {

  private ArrayList<Grocery> groceryList;
  SimpleDateFormat simpleDateFormat;

  /**
   * Creates an instance of Fridge.
   */
  public Fridge() {
    groceryList = new ArrayList<>();
    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  }

  /**
   * Adds one or more groceries to the fridge.
   * When trying to add a Grocery to the fridge, addGrocery checks if the grocery already exists.
   *    If it does, then the amount of the added grocery will be added to the existing grocery.
   *
   * @param nameOfGrocery name of the grocery added.
   * @param amount specifies how much of the object is added.
   * @param expirationDate specifies at what date the grocery expires. As a Date object.
   * @param pricePerUnit the price of each unit of the grocery.
   * @param measuringUnit the unit the grocery is measured in. Preferably SI units.
   */
  public void addGrocery(String nameOfGrocery, double amount, Date expirationDate,
      double pricePerUnit, String measuringUnit) {
    Grocery newGrocery;
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.validatePositiveDouble(amount);
      ParamValidators.parseStringToDateAndValidate(simpleDateFormat.format(expirationDate));
      ParamValidators.validatePositiveDouble(pricePerUnit);
      ParamValidators.validateString(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw e;
    }
    // If the new grocery exists, increase existing grocery amount.
    newGrocery = new Grocery(nameOfGrocery, amount, expirationDate, pricePerUnit, measuringUnit);
    boolean groceryExists = groceryList.stream()
        .filter(grocery -> grocery.equals(newGrocery))
        .findFirst()
        .map(grocery -> {
          grocery.addAmount(newGrocery.getAmount());
          return true;
        })
        .orElse(false);

    if (!groceryExists) {
      groceryList.add(newGrocery);
    }
  }

  /**
   * Returns how much of the specified grocery is currently in the fridge, as a double.
   *
   * @param inpGrocery The grocery you want to find the amount of.
   *
   * @return Returns how much the fridge has of the grocery.
   */
  public double getAmountOfGrocery(String inpGrocery) {
    try {
      ParamValidators.validateString(inpGrocery);
    } catch (IllegalArgumentException e) {
      throw e;
    }
    return groceryList.stream()
        .filter(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGrocery))
        // For each Grocery element in groceryList, use grocery.getAmountOgGrocery
        .mapToDouble(Grocery::getAmount)
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
      ParamValidators.validatePositiveDouble(amount);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e);
    }

    groceryList.sort(
        Comparator.comparing(Grocery::getExpirationDate)); // Sorts array based on exp.date.


    for (int i = 0; i < groceryList.size(); i++) {
      if (groceryList.get(i).getNameOfGrocery().equalsIgnoreCase(inpGrocery)) { // If name match
        // If there are more grocery left to remove, remove current grocery(i) object
        // and subtract its amount from amount.
        if (groceryList.get(i).getAmount() < amount) {
          amount -= groceryList.get(i).getAmount();
          groceryList.remove(i);
          i -= 1; // All the indexes after the removed object will be subtracted by 1.
        } else {
          if (groceryList.get(i).getAmount() == amount) {
            groceryList.remove(i);
            i -= 1;
            amount = 0;
          } else {
            groceryList.get(i).removeAmount(amount);
            amount = 0;
          }
          System.out.println("Removal was a success");
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
        .mapToDouble(grocery -> grocery.getAmount() * grocery.getPricePerUnit())
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
    try {
      ParamValidators.validateString(inpGroceryString);
    } catch (IllegalArgumentException e) {
      throw e;
    }

    return groceryList.stream()
        .anyMatch(grocery -> grocery.getNameOfGrocery().equalsIgnoreCase(inpGroceryString));
  }

  // getter grocerylist
  public ArrayList<Grocery> getGroceryList() {
    return groceryList;
  }


}