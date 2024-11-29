package edu.ntnu.idi.idatt.models;

import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.Date;

/**
 * The class Grocery.
 * This class is the main entity which will represent a collection of food units. It is a mutable
 *    class where only the amount can be changed.
 *
 * @author Erlend Sundsdal
 * @version 0.5.1
 * @since 0.1.0
 * @see edu.ntnu.idi.idatt.utils.ScannerValidator
 */
public class Grocery {

  private final String nameOfGrocery;
  private double amount;
  private final Date expirationDate;
  private final double pricePerUnit;

  /**
   * Constructor for grocery class.
   * Sets all parameters.
   * The user cannot type the date straight into the constructor,
   * which requires a <code>Date</code> object.
   * The input from the user has to be parsed first, typically by <code>ScannerValidator</code>.
   *
   * @param nameOfGrocery grocery name
   * @param amount amount of the specified grocery,
   *               can be changed with <code>removeAmount(double)</code>. Amount must be positive.
   * @param expirationDate expiration date of the grocery as a <code>Date</code> object.
   * @param pricePerUnit price per measuring unit.
   *
   * @see edu.ntnu.idi.idatt.utils.ScannerValidator#parseStringToDateAndValidate(String)
   */
  public Grocery(String nameOfGrocery, double amount,
      Date expirationDate, double pricePerUnit) {
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.parseToPositiveDoubleAndValidate(Double.toString(amount));
      ParamValidators.validateDate(expirationDate);
      ParamValidators.parseToPositiveDoubleAndValidate(Double.toString(pricePerUnit));
    } catch (IllegalArgumentException e) {
      throw e;
    }

    this.nameOfGrocery = nameOfGrocery;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.pricePerUnit = pricePerUnit;
  }

  public String getNameOfGrocery() {
    return this.nameOfGrocery;
  }

  public double getAmount() {
    return this.amount;
  }

  public Date getExpirationDate() {
    return this.expirationDate;
  }

  public double getPricePerUnit() {
    return this.pricePerUnit;
  }


  /**
   * Subtracts the <code>amount</code> parameter by the <code>amountChanged</code> passed. There are
   *    no test to check if <code>amountChanged</code> is more than <code>amount</code>, which makes
   *    it possible for the <code>amount</code> to negative and has to be handled by Fridge.
   *
   *
   * @param amountChanged a positive value which will be subtracted to from <code>amount</code>.
   *
   * @throws IllegalArgumentException if <code>amount</code> is 0 or less.
   */
  public void removeAmount(double amountChanged) {
    try {
      ParamValidators.validatePositiveDouble(amountChanged);
    } catch (IllegalArgumentException e) {
      throw e;
    }
    this.amount -= amountChanged;
  }

  /**
   * Adds the <code>amount</code> parameter by the <code>amountChanged</code> passed.
   *
   * @param amountChanged a positive value which will be added to from <code>amount</code>.
   *
   * @throws IllegalArgumentException if <code>amount</code> is 0 or less.
   */
  public void addAmount(double amountChanged) {
    try {
      ParamValidators.validatePositiveDouble(amountChanged);
    } catch (IllegalArgumentException e) {
      throw e;
    }
    this.amount += amountChanged;
  }

  /**
   * Checks if this <code>Grocery</code> has the same values as the parameter <code>Grocery</code>.
   *    Primitive type values are checked with an <code>==</code> operator, while reference-type use
   *    <code>.equals</code>. Amount is not checked.
   *
   * @param inpGrocery the other Grocery which this grocery shall be compared with.
   * @return true if they are the same, false if not.
   */
  public boolean equals(Grocery inpGrocery) {
    try {
      ParamValidators.validateGrocery(inpGrocery);
    } catch (IllegalArgumentException e) {
      throw e;
    }

    return getNameOfGrocery().equalsIgnoreCase(inpGrocery.getNameOfGrocery())
        && getExpirationDate().equals(inpGrocery.getExpirationDate())
        && getPricePerUnit() == inpGrocery.getPricePerUnit();
  }
}
