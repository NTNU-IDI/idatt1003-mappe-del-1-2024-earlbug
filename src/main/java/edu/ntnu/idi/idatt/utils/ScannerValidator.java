package edu.ntnu.idi.idatt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A validator for scanner inputs. Methods throws <code>IllegalArgumentException</code>s with
 *    appropriate messages if the scanner input is invalid. <code>ScannerValidator</code> has only
 *    String as arguments to gather most exception handling in <code>ScannerValidator</code>. All
 *    methods are static.
 *
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.6.3
 */
public class ScannerValidator {


  /**
   * Parses the passed String to a double and validates.
   * Uses a simple parse function and an if test to validate if the string can be parsed to a valid
   *    positive float.
   *
   * @param inpDoubleAsString a String which shall be parsed.
   * @return the parsed valid double value.
   * @throws IllegalArgumentException if the string cannot be parsed to float
   *      or if the number is 0 or less.
   */
  public static double parseToPositiveDouble(String inpDoubleAsString) {
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
  public static void validateStringScanner(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty");
    }
  }

  /**
   * Validates if the given string is not empty and can be parsed to a Date.
   *
   * @param inpStringDate the string to be validated and parsed
   * @return a valid <code>Date</code>
   * @throws IllegalArgumentException if the string is empty, or cannot be parsed to a
   *      <code>Date</code>
   */
  public static Date parseStringToDate(String inpStringDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date expirationDate;
    if (inpStringDate.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("\nThe field cannot be empty.");
    }
    try {
      expirationDate = dateFormat.parse(inpStringDate);
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
   *      negative or zero
   *
   */
  public static int parseToPositiveInt(String inpIntString) {
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

