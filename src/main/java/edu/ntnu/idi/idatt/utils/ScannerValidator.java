package edu.ntnu.idi.idatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A validator for scanner inputs. methods throws <code>IllegalArgumentException</code>s with
 *    appropriate messages if the scanner input is invalid. <code>ScannerValidator</code> has
 *    methods to parse for the possibility to make all exception handling happen in this class.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.5.0
 */
public class ScannerValidator {
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Checks if the given float is positive. Nothing happens if float is valid.
   *
   * @param inputFloat the float value to be checked
   * @throws IllegalArgumentException if the float value is 0 or less
   */
  public void checkIfFloatIsPositive(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0.");
    }
  }

  /**
   * Uses a simple parse function and an if test to validate if the string can be parsed to a valid
   *    positive float.
   *
   * @param inpDoubleAsString a String which shall be parsed.
   * @return the parsed double value.
   * @throws IllegalArgumentException if the string cannot be parsed to float
   *      or if the number is 0 or less.
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

  /**
   * Validates if the given string is not empty.
   *
   * @param inpString the string to be validated
   * @throws IllegalArgumentException if the string is empty
   */
  public void validateStringScanner(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty");
    }
  }

  /**
   * Validates if the given string is not empty.
   *
   * @param inpString the string to be validated
   * @throws IllegalArgumentException if the string is empty
   */
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

  /**
   * Parses a string to a positive int and validates it.
   *
   * @param inpIntString the string to be parsed and validated
   * @return the parsed valid int value
   * @throws IllegalArgumentException if the string is empty, cannot be parsed to an integer or is
   *      negative.
   */
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

