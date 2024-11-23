package edu.ntnu.idi.idatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScannerValidator {
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


  public void checkIfFloatIsPositive(double inputFloat) {
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
  public double parseToPositiveDoubleAndValidate(String inpDoubleAsString) {
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

  public void validateStringScanner(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty");
    }
  }

  public Date parseStringToDateAndValidate(String inpString) {
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

  public int parseToPositiveInt(String inpIntString) {
    int returnInt;
    if (inpIntString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("The field cannot be empty.");
    }
    try {
      returnInt = Integer.parseInt(inpIntString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Please enter a valid number");
    }
    if (returnInt < 0) {
      throw new IllegalArgumentException("The number cannot be negative.");
    }
    return returnInt;
  }
}

