package edu.ntnu.idi.idatt.models;

import edu.ntnu.idi.idatt.utils.ParamValidators;
import java.util.Date;


/**
 * The class Grocery.
 * This class is the main entity which will represent an amount of food units. It is a mutable
 *    class where only the amount can be changed.
 *
 * @author Erlend Sundsdal
 * @version 0.6.2
 * @since 0.1.0
 * @see edu.ntnu.idi.idatt.utils.ScannerValidator
 */
public class Grocery {

  private String name;
  private double amount;
  private Date expirationDate;
  private double pricePerUnit;
  private String measuringUnit;

  /**
   * Constructor for grocery class.
   * Sets all parameters.
   * The user cannot type the date straight into the constructor,
   * which requires a <code>Date</code> object.
   * The input from the user has to be parsed first, typically by <code>ScannerValidator</code>.
   *
   * @param nameOfGrocery the name of the grocery
   * @param amount amount of the specified grocery,
   *               can be changed with <code>removeAmount(double)</code>. Amount must be positive.
   * @param expirationDate expiration date of the grocery as a <code>Date</code> object.
   * @param pricePerUnit price per measuring unit.
   * @param measuringUnit measuring unit of the grocery, preferably in SI units to make the
   *                      groceries universal to both <code>Fridge</code> and <code>Recipe</code>.
   *
   * @see edu.ntnu.idi.idatt.utils.ScannerValidator#parseStringToDate(String)
   */
  public Grocery(String nameOfGrocery, double amount,
      Date expirationDate, double pricePerUnit, String measuringUnit) {
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.validatePositiveDouble(amount);
      ParamValidators.validateDate(expirationDate);
      ParamValidators.validatePositiveDouble(pricePerUnit);
      ParamValidators.validateString(measuringUnit);

      setName(nameOfGrocery);
      setAmount(amount);
      setExpirationDate(expirationDate);
      setPricePerUnit(pricePerUnit);
      setMeasuringUnit(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Grocery cannot be made:\n" + e.getMessage());
    }
  }

  /**
   * Constructor for the Grocery class, but only with three parameters. Usually used to represent an
   *    ingredient, which don't need expiration date nor price.
   *
   * @param nameOfGrocery the name of the grocery
   * @param amount amount of the specified grocery,
   *               can be changed with <code>removeAmount(double)</code>. Amount must be positive.
   * @param measuringUnit measuring unit of the grocery, preferably in SI units to make the
   *                      groceries universal to both <code>Fridge</code> and <code>Recipe</code>.
   */
  public Grocery(String nameOfGrocery, double amount, String measuringUnit) {
    try {
      ParamValidators.validateString(nameOfGrocery);
      ParamValidators.validatePositiveDouble(amount);
      ParamValidators.validateString(measuringUnit);

      setName(nameOfGrocery);
      setAmount(amount);
      setMeasuringUnit(measuringUnit);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Grocery cannot be made:\n" + e.getMessage());
    }
  }


  public String getName() {
    return this.name;
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

  public String getMeasuringUnit() {
    return this.measuringUnit;
  }



  // Setters
  private void setName(String inpGroceryName) throws IllegalArgumentException {
    ParamValidators.validateString(inpGroceryName);
    this.name = inpGroceryName;
  }

  private void setAmount(double amount) throws IllegalArgumentException {
    ParamValidators.validatePositiveDouble(amount);
    this.amount = amount;
  }

  private void setExpirationDate(Date expirationDate) throws IllegalArgumentException  {
    ParamValidators.validateDate(expirationDate);
    this.expirationDate = expirationDate;
  }

  private void setPricePerUnit(double pricePerUnit) throws IllegalArgumentException {
    ParamValidators.validatePositiveDouble(pricePerUnit);
    this.pricePerUnit = pricePerUnit;
  }

  private void setMeasuringUnit(String measuringUnit) throws IllegalArgumentException {
    ParamValidators.validateString(measuringUnit);
    this.measuringUnit = measuringUnit;
  }


  /**
   * Subtracts the <code>amount</code> parameter by the <code>amountChanged</code> passed. There are
   *    no test to check if <code>amountChanged</code> is more than <code>amount</code>, which makes
   *    it possible for the <code>amount</code> to negative.
   *
   *
   * @param amountChanged a positive value which will be subtracted to from <code>amount</code>.
   * @throws IllegalArgumentException if <code>amount</code> is 0 or less.
   */
  public void removeAmount(double amountChanged) throws IllegalArgumentException {
    ParamValidators.validatePositiveDouble(amountChanged);
    setAmount(getAmount() - amountChanged);
  }

  /**
   * Adds the <code>amount</code> parameter by the <code>amountChanged</code> passed.
   *
   * @param amountChanged a positive value which will be added to from <code>amount</code>.
   * @throws IllegalArgumentException if <code>amount</code> is 0 or less.
   */
  public void addAmount(double amountChanged) throws IllegalArgumentException {
    ParamValidators.validatePositiveDouble(amountChanged);

    setAmount(getAmount() + amountChanged);
  }

  /**
   * Checks if this <code>Grocery</code> has the same values as the parameter <code>Grocery</code>.
   *    Primitive type values are checked with an <code>==</code> operator, while reference-type use
   *    <code>.equals</code>. Amount is not checked.
   *
   * @param inpGrocery the other Grocery which this grocery shall be compared with.
   * @return true if they are the same, false if not.
   */
  public boolean equals(Grocery inpGrocery) throws IllegalArgumentException {
    ParamValidators.validateGrocery(inpGrocery);

    return getName().equalsIgnoreCase(inpGrocery.getName())
        && getExpirationDate().equals(inpGrocery.getExpirationDate())
        && getPricePerUnit() == inpGrocery.getPricePerUnit()
        && getMeasuringUnit().equalsIgnoreCase(inpGrocery.getMeasuringUnit());
  }
}
