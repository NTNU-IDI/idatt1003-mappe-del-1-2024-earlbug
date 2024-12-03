package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import java.util.ArrayList;
import java.util.Date;

/**
 * The <code>ParamValidators</code> class contains static methods which checks if parameters
 *    passed into other methods are valid. Nothing will happen if the tested parameters are valid,
 *    and IllegalArgumentExceptions are thrown if not, with an explaining message.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.7.0
 */
public class ParamValidators {

  /**
   * Validates if the <code>double</code> passed is valid. An <code>IllegalArgumentException</code>
   *    is thrown if not. The <code>double</code> is checked for being 0, NaN and infinite.
   *
   * @param inputFloat the <code>double</code> to be checked
   * @throws IllegalArgumentException if the <code>float</code> is invalid; either 0, NaN or
   *      infinite
   */
  public static void validatePositiveDouble(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0, please try again.");
    }
    if (Double.isNaN(inputFloat)) {
      throw new IllegalArgumentException("This is not a number, please try again.");
    }
    if (Double.isInfinite(inputFloat)) {
      throw new IllegalArgumentException("Number cannot be infinite, please try again.");
    }
  }

  /**
   * Validates if the <code>int</code> passed is valid. An <code>IllegalArgumentException</code> is
   *    thrown if not. The <code>int</code> is checked for being 0.
   *
   * @param inpInt the <code>int</code> to be checked
   * @throws IllegalArgumentException if the <code>int</code> is invalid by being 0
   */
  public static void validatePositiveInt(int inpInt) {
    if (inpInt <= 0) {
      throw new IllegalArgumentException("The number must be more than 0.");
    }

  }

  /**
   * Validates if the <code>Date</code> passed is valid. An <code>IllegalArgumentException</code> is
   *    thrown if not. The <code>Date</code> is checked for being <code>null</code>.
   *
   * @param inpDate the <code>Date</code> to be checked
   * @throws IllegalArgumentException if the <code>Date</code> is invalid by being <code>null</code>
   */
  public static void validateDate(Date inpDate) {
    if (inpDate == null) {
      throw new IllegalArgumentException("The date is null. Please try again.");
    }
  }

  /**
   * Validates if the <code>String</code> passed is valid. An <code>IllegalArgumentException</code>
   *    is thrown if not. The <code>String</code> is checked for being <code>null</code>.
   *
   * @param inpString the <code>String</code> to be checked
   * @throws IllegalArgumentException if the <code>String</code> is invalid by being
   *      <code>null</code> or empty
   */
  public static void validateString(String inpString) {
    if (inpString == null) {
      throw new IllegalArgumentException(("The String is null. Please try again."));
    }
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field is empty, please try again.");
    }
  }

  /**
   * Validates if the <code>String</code> passed is valid. An <code>IllegalArgumentException</code>
   *    is thrown if not. The <code>String</code> is checked for being <code>null</code>.
   *
   * @param arrayList the <code>ArrayList</code> to be checked
   * @throws IllegalArgumentException if the <code>ArrayList</code> is invalid by being
   *      <code>null</code> or empty
   */
  public static void validateArrayList(ArrayList<?> arrayList) {
    if (arrayList == null) {
      throw new IllegalArgumentException("The ArrayList is null. Please try again.");
    }
    if (arrayList.isEmpty()) {
      throw new IllegalArgumentException("The ArrayList is empty. Please try again.");
    }
  }

  /**
   * Validates if the <code>Grocery</code> passed is valid. An <code>IllegalArgumentException</code>
   *    is thrown if not. The <code>Grocery</code> is checked for being <code>null</code>, and that
   *    every parameter is valid.
   *
   * @param inpGrocery the <code>Grocery</code> to be checked
   * @throws IllegalArgumentException if the <code>Grocery</code> is invalid by being
   *      <code>null</code> or having invalid instance variables
   */
  public static void validateGrocery(Grocery inpGrocery) {
    if (inpGrocery == null) {
      throw new IllegalArgumentException("The grocery is null. Please try again.");
    }
    try {
      validateString(inpGrocery.getName());
      validatePositiveDouble(inpGrocery.getAmount());
      validateDate(inpGrocery.getExpirationDate());
      validatePositiveDouble(inpGrocery.getPricePerUnit());
      validateString(inpGrocery.getMeasuringUnit());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Grocery is invalid:\n" + e.getMessage());
    }
  }

  /**
   * Validates if the <code>Grocery</code> ingredient passed is valid. An
   *    <code>IllegalArgumentException</code> is thrown if not. The <code>Grocery</code> is checked
   *    for being <code>null</code>, and that every parameter is valid. This only checks name,
   *    amount and measuring unit.
   *
   * @param inpGrocery the <code>Grocery</code> to be checked
   * @throws IllegalArgumentException if the <code>Grocery</code> is invalid by being
   *      <code>null</code> or having invalid instance variables
   */
  public static void validateGroceryIngredient(Grocery inpGrocery) {
    if (inpGrocery == null) {
      throw new IllegalArgumentException("The ingredient is null. Please try again.");
    }
    try {
      validateString(inpGrocery.getName());
      validatePositiveDouble(inpGrocery.getAmount());
      validateDate(inpGrocery.getExpirationDate());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Ingredient is invalid:\n" + e.getMessage());
    }
  }

  /**
   * Validates if the <code>Recipe</code> ingredient passed is valid. An
   *    <code>IllegalArgumentException</code> is thrown if not. The <code>Recipe</code> is checked
   *    for being <code>null</code>, and that every instance variable is valid.
   *
   * @param inpRecipe the <code>Grocery</code> to be checked
   * @throws IllegalArgumentException if the <code>Grocery</code> is invalid by being
   *      <code>null</code> or having invalid instance variables
   */
  public static void validateRecipe(Recipe inpRecipe) {
    if (inpRecipe == null) {
      throw new IllegalArgumentException("The recipe is null. Please try again.");
    }
    try {
      ParamValidators.validateString(inpRecipe.getName());
      ParamValidators.validateString(inpRecipe.getDescription());
      ParamValidators.validateString(inpRecipe.getProcedure());
      ParamValidators.validateArrayList(inpRecipe.getIngredientList());
      ParamValidators.validatePositiveInt(inpRecipe.getPortions());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid recipe:\n" + e.getMessage());
    }
  }
}