package edu.ntnu.idi.idatt.controllers;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * The class <code>Fridge</code> class represents a storage for food, in the sense that it stores
 *    <code>Grocery</code> instances in an <code>ArrayList</code> stored as a class variable.
 *    <code>Fridge</code> has methods which execute changes to the <code>groceryList</code>, and
 *    methods that returns information of the current state of the <code>Grocery</code>
 *    objects, like the combined value of the content of the <code>Fridge</code>. These ar only
 *    values, and has to be processed/formulated further to be readable for a user.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.7.0
 */
public class Fridge {

  private ArrayList<Grocery> groceryList;
  SimpleDateFormat simpleDateFormat;

  /**
   * Creates an instance of Fridge. The groceryList is initialized, and the date format is set.
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
   * @throws IllegalArgumentException if any of the parameters are invalid
   */
  public void addGrocery(String nameOfGrocery, double amount, Date expirationDate,
      double pricePerUnit, String measuringUnit) {
    Grocery newGrocery;
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.validatePositiveDouble(amount);
      ParamValidators.validateDate(expirationDate);
      ParamValidators.validatePositiveDouble(pricePerUnit);
      ParamValidators.validateString(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Grocery could not be added:\n" + e.getMessage());
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
   * Returns how much of the specified grocery is currently in the fridge, as a double. If there are
   *    no such <code>Grocery</code>, then 0.0 will be returned.
   *
   * @param inpGrocery The grocery you want to find the amount of.
   *
   * @return Returns how much the fridge has of the grocery. Return 0.0 if there are no such
   *      <code>Grocery</code>.
   * @throws IllegalArgumentException if the passed Grocery is invalid
   */
  public double getAmountByName(String inpGrocery) {
    try {
      ParamValidators.validateString(inpGrocery);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not get amount:\n" + e.getMessage());
    }
    return groceryList.stream()
        .filter(grocery -> grocery.getName().equalsIgnoreCase(inpGrocery))
        // For each Grocery element in groceryList, use grocery.getAmountOgGrocery
        .mapToDouble(Grocery::getAmount)
        .sum();
  }

  /**
   * Removes <code>amountToRemove</code> of specified grocery. The oldest groceries will be removed
   *      first. If the <code>amount</code> is less than the oldest <code>amountToRemove</code>,
   *      then the existing <code>Grocery</code> will be removed. The program checks if there
   *      are more <code>Grocery</code> with the same name, and to the whole process again until
   *      the specified amount has been removed, or all the <code>inpString</code> is removed.
   *      In other words: there isn't enough groceries to remove, all of the specified groceries
   *      will be removed.
   *
   * @param inpGrocery name of the <code>Grocery</code> which shall be removed.
   * @param amountToRemove the amount which shall be removed.
   * @throws IllegalArgumentException if any of the parameters are invalid
   */
  public void removeGrocery(String inpGrocery, double amountToRemove) {
    try {
      ParamValidators.validateString(inpGrocery);
      ParamValidators.validatePositiveDouble(amountToRemove);

      groceryList.sort(
          Comparator.comparing(Grocery::getExpirationDate)); // Sorts array based on exp.date.

      Iterator<Grocery> iterator = groceryList.iterator();
      while (iterator.hasNext() && amountToRemove > 0) {
        Grocery grocery = iterator.next();
        if (grocery.getName().equalsIgnoreCase(inpGrocery)) {
          if (grocery.getAmount() <= amountToRemove) {
            amountToRemove -= grocery.getAmount();
            iterator.remove();
          } else {
            double groceryAmount = grocery.getAmount();
            grocery.removeAmount(amountToRemove);
            amountToRemove -= groceryAmount;
          }
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not remove Grocery:\n" + e.getMessage());
    }
  }

  /**
   * Finds all the groceries which expires before the passed date, and returns them as
   *    <code>Grocery</code> instances in an <code>ArrayList</code>.
   *
   * @return ArrayList of grocery objects. An empty Arraylist will be returned of nothing is
   *      found.
   */
  public ArrayList<Grocery> findGroceriesWhichExpiresAfter(Date inpDate) {
    return groceryList.stream()
        .filter(grocery -> grocery.getExpirationDate().before(inpDate))
        // Collects the elements of a stream to an ArrayList
        // Normally it would be collect to a list.(Collector.toList())
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Finds the combined value of all the items in the <code>Fridge</code> by adding the products of
   *    <code>amount</code> and <code>pricePerUnit</code> of all the <code>Grocery</code> objects
   *    together.
   *
   * @return the combined value of all items in fridge.
   */
  public double getValueOfGroceriesInFridge() {
    return groceryList.stream()
        .mapToDouble(grocery -> grocery.getAmount() * grocery.getPricePerUnit())
        .sum();
  }

  /**
   * Makes a copy of <code>groceryList</code> and sorts the contents in alphabetical order.
   *
   * @return an ArrayList of Groceries sorted alphabetically.
   */
  public ArrayList<Grocery> retrunAlphabeticaclArrayList() {
    return groceryList.stream()
        .sorted(Comparator.comparing(Grocery::getAmount))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Finds out if a grocery exists in the fridge.
   *
   * @param inpGroceryString the name which shall be used to search for <code>Grocery</code>
   *                         instances.
   * @return true if the grocery is found, false if not.
   * @throws IllegalArgumentException if the parameters is invalid
   */
  public boolean groceryExists(String inpGroceryString) {
    try {
      ParamValidators.validateString(inpGroceryString);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not check if Grocery exists:\n" + e.getMessage());
    }

    return groceryList.stream()
        .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(inpGroceryString));

  }

  /**
   * Gets the measuring unit for the corresponding Grocery name if it exists. If not, then an
   *    <code>IllegalArgumentException</code> will be thrown.
   *
   * @param inpName name of the <code>Grocery</code> to find the <code>measuringUnit</code> to.
   * @return measuring unit of specified <code>Grocery</code>.
   * @throws IllegalArgumentException if the parameters is invalid or the Grocery does not exist
   */
  public String getMeasuringUnitByName(String inpName) {
    try {
      return groceryList.stream()
          .filter(grocery -> grocery.getName().equalsIgnoreCase(inpName))
          .findFirst()
          .map(Grocery::getMeasuringUnit)
          .orElseThrow(() -> new IllegalArgumentException("No Groceries with this name."));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not get measuring unit:\n" + e.getMessage());
    }
  }

  /**
   * Checks if the fridge has enough groceries to make a certain dish. The method iterates through
   *    all the ingredients needed and checks if <code>groceryList</code> contains enough to make
   *    the dish.
   *
   * @param recipe the <code>Recipe</code> that shall be checked
   * @return true if the dish can be made, false if not
   * @throws IllegalArgumentException if the parameter is invalid
   */
  public boolean canRecipeBeMadeWithFridgeContent(Recipe recipe) {
    try {
      ParamValidators.validateRecipe(recipe);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "Could not check if recipe could be made:\n" + e.getMessage());
    }
    return recipe.getIngredientList().stream()
        .allMatch(ingredient -> getAmountByName(ingredient.getName()) >= ingredient.getAmount());
  }

  /**
   * Finds out what dishes the Fridge has enough groceries to make. Each recipe in the
   *    <code>ArrayList</code> argument is checked, and if a <code>Recipe</code> can be made, it's
   *    added to the returned <code>ArrayList</code>.
   *
   * @param recipeList the Arraylist<code></code> of Recipes to be checked
   * @return an ArrayList of recipes that can be made.
   * @throws IllegalArgumentException if the parameter is invalid
   */
  public ArrayList<Recipe> returnAllPossibleDishesWithFridgeContent(ArrayList<Recipe> recipeList) {
    try {
      ParamValidators.validateArrayList(recipeList);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not find possible dishes:\n" + e.getMessage());
    }

    return recipeList.stream()
        .filter(this::canRecipeBeMadeWithFridgeContent)
        .collect(Collectors.toCollection(ArrayList<Recipe>::new));
  }


  /**
   * Gets the list of groceries in the fridge.
   *
   * @return an ArrayList of <code>Grocery</code> objects currently in the fridge.
   */
  public ArrayList<Grocery> getGroceryList() {
    return groceryList;
  }

  /**
   * Sets the list of groceries in the fridge.
   *
   * @param newGroceryList the new list of groceries to be set
   * @throws IllegalArgumentException if the new grocery list is invalid
   */
  private void setGroceryList(ArrayList<Grocery> newGroceryList) {
    try {
      ParamValidators.validateArrayList(newGroceryList);
      this.groceryList = newGroceryList;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Could not set Fridge ArrayList:\n" + e.getMessage());
    }
  }


}