package edu.ntnu.idi.idatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParamValidators {
  static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


  public static void checkIfDoubleIsPositive(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0, please try again.");
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
      throw new IllegalArgumentException("\nThe input cannot be parsed to a float, please try again.");
    }
    if (returnValue <= 0) {
      throw new IllegalArgumentException("\nThe number is not positive, please try again.");
    } else {
      return returnValue;
    }
  }

  public static void validateString(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field is empty, please try again.");
    }
  }

  public static Date parseStringToDateAndValidate(String inpString) {
    Date expirationDate;
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field is empty, please try again.");
    }
    try {
      expirationDate = dateFormat.parse(inpString);
    } catch (ParseException e) {
      throw new IllegalArgumentException("\nThe date is inveli, please try again..");
    }
    return expirationDate;
  }
}



