package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import java.util.ArrayList;
import java.util.Date;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.6.0
 */
public class ParamValidators {


  public static void validatePositiveDouble(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0, please try again.");
    }
  }

  public static void validatePositiveInt(int inpValue) {
    if (inpValue <= 0) {
      throw new IllegalArgumentException("The number must be more than 0.");
    }
  }

  public static void validateDate(Date inpDate) {
    if (inpDate == null) {
      throw new IllegalArgumentException("The date is null. Please try again.");
    }
  }

  public static void validateString(String inpString) {
    if (inpString == null) {
      throw new IllegalArgumentException(("The String is null. Please try again."));
    }
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field is empty, please try again.");
    }
  }

  public static void validateArrayList(ArrayList arrayList) {
    if (arrayList == null) {
      throw new IllegalArgumentException("The ArrayList is null. Please try again.");
    }
    if (arrayList.isEmpty()) {
      throw new IllegalArgumentException("The ArrayList is empty. Please try again.");
    }
  }

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

  public static void validateRecipe(Recipe inpRecipe) {
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