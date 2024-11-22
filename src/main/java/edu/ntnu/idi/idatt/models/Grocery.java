package edu.ntnu.idi.idatt.models;

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
  public Grocery(String nameOfGrocery, double amount, Date expirationDate, double pricePerUnit, String measuringUnit) {
    this.nameOfGrocery = nameOfGrocery;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.pricePerUnit = pricePerUnit;
    this.measuringUnit = measuringUnit;
  }
  // todo public enum unit, gjør grocery enhet lettere?

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

  public void setAmount(double setAmountTo) {
    this.amount = setAmountTo;
  }

  public void changeAmount(double amountChanged) {
    this.amount += amountChanged;
  }



  @Override // does this have any unwanted side effects? or can it be used for somthing else?
  public String toString() {
    return this.nameOfGrocery;
  }

}
