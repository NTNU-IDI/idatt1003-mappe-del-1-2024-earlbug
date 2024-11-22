package edu.ntnu.idi.idatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validator class to used to validate if the scanner gets the right input.
 */
public class Validators {
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


  public static void checkIfFloatIsPositive(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0.");
    }
  }

  /**
   * Uses a simple parse function and an if test to validate if the string can be
   * parsed to a valid positive float.
   * @param inpDoubleAsString a String which shall be parsed.
   *
   * @return the parsed double value.
   *
   * @throws IllegalArgumentException if the string cannot be parsed to float
   * or if the number is 0 or less.
   */
  public static double parseToPositiveDoubleAndValidate(String inpDoubleAsString) {
    double returnValue;
    try {
      returnValue = Double.parseDouble(inpDoubleAsString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("\nPlease enter a valid number.");
    }
    if (returnValue <= 0) {
      throw new IllegalArgumentException("\nThe number must be more than 0.");
    } else {
      return returnValue;
    }
  }

  public static void validateStringScanner(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty");
    }
  }

  public static Date parseStringToDateAndValidate(String inpString) {
    Date expirationDate;
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty.");
    }
    try {
      expirationDate = dateFormat.parse(inpString);
    } catch (ParseException e) {
      throw new IllegalArgumentException("\nPlease enter a valid date.");
    }
    return expirationDate;
  }
}