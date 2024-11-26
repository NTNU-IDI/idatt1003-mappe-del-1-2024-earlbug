package edu.ntnu.idi.idatt.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class TestGrocery {

  Grocery grocery1;
  Grocery grocery2;
  Grocery grocery3;
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  Date date1;
  Date date2;
  Date date3;


  @BeforeEach
    // Arrange
  void setUp() {
    try {
      date1 = dateFormat.parse("2023-10-30");
      date2 = new Date(); // today's date
      date3 = dateFormat.parse("2025-10-10");
    } catch (ParseException e) {
      throw new IllegalArgumentException("Date cannot be parsed. Please try again.");
    }

    grocery1 = new Grocery("Milk", 1.5, date1, 29.9, "liters");
    grocery2 = new Grocery("Bananas", 5, date2, 5.40, "units");
    grocery3 = new Grocery("Flour", 0.4, date3, 14.32, "kg");
  }

  @Test
  void getGetNameOfGroceryShouldReturnMilk() {
    // Arrange
    String expectedName1 = "Milk";

    // Act
    String returnedName1 = grocery1.getNameOfGrocery();

    // Assert
    assertEquals(expectedName1, returnedName1);
  }

  @Test
  void getAmountOfGroceryShouldReturn1Point5() {
    // Arrange
    double expectedAmount = 1.5;

    // Act
    double returnedAmount = grocery1.getAmountOfGrocery();

    // Assert
    assertEquals(expectedAmount, returnedAmount);
  }

  @Test
  void getExpirationDateShouldReturnCorrectDate() {
    // Arrange
    Date expectedDate = date1;

    // Act
    Date returnedDate = grocery1.getExpirationDate();

    // Assert
    assertEquals(expectedDate, returnedDate);
  }

  @Test
  void getPricePerUnitShouldReturnCorrectPrice() {
    // Arrange
    double expectedPrice = 29.9;

    // Act
    double returnedPrice = grocery1.getPricePerUnit();

    // Assert
    assertEquals(expectedPrice, returnedPrice);
  }

  @Test
  void getMeasuringUnitShouldReturnLiters() {
    // Arrange
    String expectedUnit = "liters";

    // Act
    String returnedUnit = grocery1.getMeasuringUnit();

    // Assert
    assertEquals(expectedUnit, returnedUnit);
  }

  @Test
  void removeAmountPointSixShouldEqualPointNine() {
    // Arrange
    double amountToBeRemoved = 0.6;
    double expectedReturn = 0.9;

    // Act
    grocery1.removeAmount(amountToBeRemoved);
    double actualReturn = grocery1.getAmountOfGrocery();

    // Assert
    assertEquals(expectedReturn, actualReturn);
  }



/*
  @Test
  // The closest I can get to splitting into AAA, but code don't work.
  void invalidGroceryName1() {
    //Arrange
    Class<IllegalArgumentException> expectedExceptioncClass = IllegalArgumentException.class;

    // Act
    Grocery groceryWithoutName = new Grocery("", 1.5, date1, 29.9, "liters");

    // Assert
    assertThrows(expectedExceptioncClass, () -> new Grocery("", 1.5, date1, 29.9, "liters"));
  }
 */

  // Exception tests
  @Test
  void emptyNameShouldReturnIllegalArgumentException() {
    // Arrange
    String emptyName = "";

    // Act & assert
    assertThrows(IllegalArgumentException.class, () ->
        new Grocery(emptyName, 1.5, date1, 29.9, "liters"));
  }

  @Test void nullNameShouldThrowIllegalArgumentException() {
    // Arrange
    String nullName = null;

    // Act & assert
    assertThrows(IllegalArgumentException.class, () ->
        new Grocery(nullName, 1.5, date1, 29.9, "liters"));
  }

  @Test
  // Almost split into AAA, but works at least.
  void NegativeGroceryAmountShouldthowIllegalArgumentException() {

    // Arrange
    double negativeAmount = -1.5;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", negativeAmount, date1, 29.9, "liters")
    );
  }

  @Test
  void zeroGroceryAmountShouldThrowIllegalArgumentException() {
    double zeroAmount = 0.0;

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, date1, zeroAmount, "liters")
    );
  }

  @Test
  void negativePricePerUnitShouldThrowIllegalArgumentException() {
    // Arrange
    double negativePrice = -29.9;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, date1, negativePrice, "liters")
    );
  }

  @Test
  void zeroPricePerUnitShouldThrowIllegalArgumentException() {
    double zeroPrice = 0.0;

    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, date1, zeroPrice, "liters")
    );
  }

  @Test
  void emptyMeasuringUnitShouldThrowIllegalArgumentException() {
    // Arrange
    String emptyUnit = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, date1, 29.9, emptyUnit)
    );
  }

  @Test
  void nullMeasuringUnitShouldThrowIllegalArgumentException() {
    // Arrange
    String nullUnit = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, date1, 29.9, nullUnit)
    );
  }

  @Test
  void nullExpirationDateShouldThrowIllegalArgumentException() {
    // Arrange
    Date nullDate = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", 1.5, nullDate, 29.9, "liters")
    );
  }

  @Test
  void negativeRemoveAmountShouldThrowIllegalArgumentException() {
    double negativeAmount = -15;

    assertThrows(IllegalArgumentException.class, () -> grocery1.removeAmount(negativeAmount));
  }

  @Test
  void RemoveAmountZeroShouldThrowIllegalArgumentException() {
    double zeroAmount = 0;

    assertThrows(IllegalArgumentException.class, () -> grocery1.removeAmount(zeroAmount));
  }
}