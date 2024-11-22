package edu.ntnu.idi.idatt.utils;

public class Validators {


  public static void checkIfFloatIsPositive(double inputFloat) {
    if (inputFloat <= 0) {
      throw new IllegalArgumentException("Number must be more than 0.");
    }
  }

  public static double parseToPositiveFloat(String inpDoubleAsString) {
    double returnValue;
    try {
      returnValue = Double.parseDouble(inpDoubleAsString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Please enter a valid number.from vaalidators");
    }
    if (returnValue <= 0) {
      throw new IllegalArgumentException("the number must be more than 0. printed from Valdiators");
    } else {
      return returnValue;
    }
  }

  public static void validateString(String inpString) {
    if (inpString.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("The input cannot be empty.");
    }
  }
}

  //prøv å parse til double
