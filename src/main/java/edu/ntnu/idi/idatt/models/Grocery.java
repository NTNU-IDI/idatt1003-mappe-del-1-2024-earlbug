package edu.ntnu.idi.idatt.models;

import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.Date;

public class Grocery {

  private final String nameOfGrocery;
  private double amount;
  private final Date expirationDate;
  private final double pricePerUnit;
  private final String measuringUnit;

  /**
   * Constructor for grocery class.
   * Sets all parameters.
   *
   * @param nameOfGrocery grocery name
   * @param amount amount of the specified grocery,
   * @param expirationDate expiration date of the grocery as ad date object
   * @param pricePerUnit price per measuring unit
   * @param measuringUnit measuring unit of the grocery
   */
  public Grocery(String nameOfGrocery, double amount,
      Date expirationDate, double pricePerUnit, String measuringUnit) {
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.parseToPositiveDoubleAndValidate(Double.toString(amount));
      ParamValidators.validateDate(expirationDate);
      ParamValidators.parseToPositiveDoubleAndValidate(Double.toString(pricePerUnit));
      ParamValidators.validateString(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw e;
    }

    this.nameOfGrocery = nameOfGrocery;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.pricePerUnit = pricePerUnit;
    this.measuringUnit = measuringUnit;
  }

  public String getNameOfGrocery() {
    return this.nameOfGrocery;
  }
  public double getAmountOfGrocery() {
    return this.amount;
  }
  public Date getExpirationDate() {
    return this.expirationDate;
  }
  public double getPricePerUnit() {
    return this.pricePerUnit;
  }
  public String getMeasuringUnit() {
    return this.measuringUnit;
  }

  public void removeAmount(double amountChanged) {
    try {
      ParamValidators.validatePositiveDouble(amountChanged);
    } catch (IllegalArgumentException e) {
      throw e;
    }
    this.amount -= amountChanged;
  }

}
