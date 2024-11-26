package edu.ntnu.idi.idatt.utils;

import edu.ntnu.idi.idatt.models.Grocery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class ParamValidators {
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


  public static void validatePositiveDouble(double inputFloat) {

    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0, please try again.");
    }
  }

  /**
   * Uses a simple parse function and an if test to validate if the string can be
   *      parsed to a valid positive float.
   *
   * @param inpDoubleAsString a String which shall be parsed.
   *
   * @return the parsed double value.
   *
   * @throws IllegalArgumentException if the string cannot be parsed to float
   *      or if the number is 0 or less.
   */
  public static double parseToPositiveDoubleAndValidate(String inpDoubleAsString) {
    double returnValue;
    if (inpDoubleAsString == null) {
      throw new IllegalArgumentException("The argument is null. Please try again.");
    }
    try {
      returnValue = Double.parseDouble(inpDoubleAsString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "\nThe input cannot be parsed to a float, please try again.");
    }
    if (returnValue <= 0) {
      throw new IllegalArgumentException("\nThe number is not positive, please try again.");
    } else {
      return returnValue;
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

  public static Date parseStringToDateAndValidate(String inpString) {
    Date expirationDate;
    if (inpString == null) {
      throw new IllegalArgumentException("The date is null. Please try again.");
    }
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field is empty, please try again.");
    }
    try {
      expirationDate = dateFormat.parse(inpString);
    } catch (ParseException e) {
      throw new IllegalArgumentException("\nThe date is invalid, please try again..");
    }
    return expirationDate;
  }

  public static void validateGroceryArrayList(ArrayList<Grocery> arrayList) {
    if (arrayList == null || arrayList.isEmpty()) {
      throw new IllegalArgumentException("The ArrayList is either null or empty. Please try again.");
    }
  }
}