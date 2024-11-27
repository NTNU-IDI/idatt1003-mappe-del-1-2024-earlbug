package edu.ntnu.idi.idatt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import edu.ntnu.idi.idatt.models.Grocery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.1
 */
public class TestFridge {

  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @BeforeEach
  void setup() {
    ArrayList<Grocery> groceryList = new ArrayList<>();
    Grocery grocery = new Grocery("Milk", 1.75, new Date(), 20.5, "liter");

  }

  @Test
  void addGroceryShouldAddGroceryToFridgeIfGroceryDontExist() {
    // Arrange
    String name = "Milk";
    double amount = 1.75;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();
    Grocery newGrocery = new Grocery(name, amount, expirationDate, pricePerUnit, measuringUnit);

    // Act
    fridge.addGrocery(name, amount, expirationDate, pricePerUnit, measuringUnit);

    //Assert
    assertTrue(fridge.getGroceryList().get(0).equals(newGrocery));
    assertFalse(fridge.getGroceryList().isEmpty());
  }

  @Test
  void addGroceryIncreasesAmountIfGroceryAlreadyExist() {
    // Arrange
    String name = "Milk";
    double amount1 = 1.75;
    double amount2 = 2;
    double newAmount = amount2 + amount1;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();
    Grocery grocery = new Grocery(name, amount1, expirationDate, pricePerUnit, measuringUnit);
    Grocery newGrocery = new Grocery(name, amount2, expirationDate, pricePerUnit, measuringUnit);

    int sizeOfArrayList = 1;

    // Act
    fridge.addGrocery(name, amount1, expirationDate, pricePerUnit, measuringUnit);
    fridge.addGrocery(name, amount2, expirationDate, pricePerUnit, measuringUnit);

    //Assert
    assertEquals(newAmount, fridge.getGroceryList().get(0).getAmount());
    assertEquals(sizeOfArrayList, fridge.getGroceryList().size());
  }

  @Test
  void addGroceryShouldThrowExceptionForInvalidName() {
    // Arrange
    String name = "";
    double amount = 1.75;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      fridge.addGrocery(name, amount, expirationDate, pricePerUnit, measuringUnit);
    });
  }

  @Test
  void addGroceryShouldThrowExceptionForNegativeAmount() {
    // Arrange
    String name = "Milk";
    double amount = -1.75;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      fridge.addGrocery(name, amount, expirationDate, pricePerUnit, measuringUnit);
    });
  }

  @Test
  void addGroceryShouldThrowExceptionForNegativePricePerUnit() {
    // Arrange
    String name = "Milk";
    double amount = 1.75;
    Date expirationDate = new Date();
    double pricePerUnit = -20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      fridge.addGrocery(name, amount, expirationDate, pricePerUnit, measuringUnit);
    });
  }

  @Test
  void addGroceryShouldThrowExceptionForInvalidMeasuringUnit() {
    // Arrange
    String name = "Milk";
    double amount = 1.75;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "";
    Fridge fridge = new Fridge();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      fridge.addGrocery(name, amount, expirationDate, pricePerUnit, measuringUnit);
    });
  }


  @Test
  void getAmountOfGroceryShouldSumAllGroceryAmountWithInputedName() {
    // Arrange
    String name1 = "Milk";
    String name2 = "egg";
    String name3 = "Milk";
    double amount1 = 3.8;
    double amount2 = 1;
    double amount3 = 1.4;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();

    double expectedAmount = amount1 + amount3;
    fridge.addGrocery(name1, amount1, expirationDate, pricePerUnit, measuringUnit);
    fridge.addGrocery(name2, amount2, expirationDate, pricePerUnit, measuringUnit);
    fridge.addGrocery(name3, amount3, expirationDate, pricePerUnit, measuringUnit);

    // Act
    double returnedAmount = fridge.getAmountOfGrocery(name1);

    //Assert
    assertEquals(expectedAmount, returnedAmount);
  }

  @Test
  void getAmountOfGroceryShouldReturnZeroIfNoGroceriesMatchName() {
    // Arrange
    String name1 = "Milk";
    String name2 = "egg";
    String name3 = "Milk";
    double amount1 = 3.8;
    double amount2 = 1;
    double amount3 = 1.4;
    Date expirationDate = new Date();
    double pricePerUnit = 20.5;
    String measuringUnit = "liter";
    Fridge fridge = new Fridge();

    double expectedAmount = 0;
    fridge.addGrocery(name1, amount1, expirationDate, pricePerUnit, measuringUnit);
    fridge.addGrocery(name2, amount2, expirationDate, pricePerUnit, measuringUnit);
    fridge.addGrocery(name3, amount3, expirationDate, pricePerUnit, measuringUnit);

    // Act
    double returnedAmount = fridge.getAmountOfGrocery("carrot");

    //Assert
    assertEquals(expectedAmount, returnedAmount);
  }

  @Test
  void getAmountOfGroceryShouldThrowIllegalArgumentExceptionIfNameIsInvalid() {
    // Arrange
    Fridge fridge = new Fridge();
    String invalidName = "";

    // Act & assert
    assertThrows(IllegalArgumentException.class, () -> fridge.getAmountOfGrocery(invalidName));
  }


  @Test
  void removeGroceryShouldReduceOldestGroceryAmountByParameter() throws ParseException {
    // Arrange
    String name = "Milk";
    double initialAmount = 5.0;
    double amountToRemove = 2.0;
    double remainingAmount = initialAmount - amountToRemove;
    Date expirationDate = simpleDateFormat.parse("2023-12-31");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name, initialAmount, expirationDate, 20.5, "liter");

    // Act
    fridge.removeGrocery(name, amountToRemove);

    // Assert
    assertEquals(remainingAmount, fridge.getGroceryList().get(0).getAmount());
  }

  @Test
  void removeGroceryShouldRemoveGroceryIfAmountIsEqualToGroceryAmount() throws ParseException {
    // Arrange
    String name = "Milk";
    double initialAmount = 5.0;
    double amountToRemove = 5.0;
    Date expirationDate = simpleDateFormat.parse("2023-12-31");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name, initialAmount, expirationDate, 20.5, "liter");

    // Act
    fridge.removeGrocery(name, amountToRemove);

    // Assert
    assertTrue(fridge.getGroceryList().isEmpty());

  }



  @Test
  void findExpiredGroceriesShouldReturnExpiredGroceries () throws ParseException {
    // Arrange
    String name1 = "Milk";
    String name2 = "Eggs";
    Date expiredDate = simpleDateFormat.parse("0000-00-00");
    Date validDate = simpleDateFormat.parse("3000-12-30");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name1, 1.0, expiredDate, 20.5, "liter");
    fridge.addGrocery(name2, 1.0, validDate, 10.0, "dozen");

    // Act
    ArrayList<Grocery> expiredGroceries = fridge.findExpiredGroceries();

    // Assert
    assertEquals(1, expiredGroceries.size());
    assertEquals(name1, expiredGroceries.get(0).getNameOfGrocery());
  }

  @Test
  void findExpiredGroceriesShouldReturnEmptyListIfNoGroceriesAreExpired () throws ParseException {
    // Arrange
    String name1 = "Milk";
    String name2 = "Eggs";
    Date validDate1 = simpleDateFormat.parse("3000-12-30");
    Date validDate2 = simpleDateFormat.parse("2200-00-00");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name1, 1.0, validDate1, 20.5, "liter");
    fridge.addGrocery(name2, 1.0, validDate2, 10.0, "dozen");
    System.out.println(new Date().toString());

    // Act
    ArrayList<Grocery> expiredGroceries = fridge.findExpiredGroceries();

    // Assert
    assertTrue(expiredGroceries.isEmpty());
  }

  @Test
  void getValueOfGroceriesInFridgeShouldReturnCorrectValue() {
    // Arrange

    Fridge fridge = new Fridge();
    double amount1 = 1.5;
    double price1 = 29.9;
    double amount2 = 5;
    double price2 = 5.40;
    double amount3 = 0.4;
    double price3 = 14.32;

    fridge.addGrocery("Milk", amount1, new Date(), price1, "liters");
    fridge.addGrocery("Bananas", amount2, new Date(), price2, "units");
    fridge.addGrocery("Flour", amount3, new Date(), price3, "kg");

    double expectedValue = (amount1 * price1) + (amount2 * price2) + (amount3 * price3);
    // Act
    double actualValue = fridge.getValueOfGroceriesInFridge();

    // Assert
    assertEquals(expectedValue, actualValue);
  }



  @Test
  void groceryExistsShouldReturnTrueIfGroceryExists() throws ParseException {
    // Arrange
    String name = "Milk";
    Date expirationDate = simpleDateFormat.parse("2023-12-31");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name, 1.0, expirationDate, 20.5, "liter");

    // Act
    boolean exists = fridge.groceryExists(name);

    // Assert
    assertTrue(exists);
  }

  @Test
  void groceryExistsShouldReturnFalseIfGroceryDoesNotExist() throws ParseException {
    // Arrange
    String name = "Milk";
    String nonExistentName = "Eggs";
    Date expirationDate = simpleDateFormat.parse("2023-12-31");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name, 1.0, expirationDate, 20.5, "liter");

    // Act
    boolean exists = fridge.groceryExists(nonExistentName);

    // Assert
    assertFalse(exists);
  }



  @Test
  void getGroceryListShouldReturnAllGroceries() throws ParseException {
    // Arrange
    String name1 = "Milk";
    String name2 = "Eggs";
    Date expirationDate1 = simpleDateFormat.parse("2023-12-31");
    Date expirationDate2 = simpleDateFormat.parse("2023-11-30");
    Fridge fridge = new Fridge();
    fridge.addGrocery(name1, 1.0, expirationDate1, 20.5, "liter");
    fridge.addGrocery(name2, 2.0, expirationDate2, 10.0, "dozen");

    // Act
    ArrayList<Grocery> groceryList = fridge.getGroceryList();

    // Assert
    assertEquals(2, groceryList.size());
    assertEquals(name1, groceryList.get(0).getNameOfGrocery());
    assertEquals(name2, groceryList.get(1).getNameOfGrocery());
  }











  @Test
  void k () {
    // Arrange

    // Act

    //Assert

  }
  @Test
  void n () {
    // Arrange

    // Act

    //Assert

  }
  @Test
  void o () {
    // Arrange

    // Act

    //Assert

  }


  }

