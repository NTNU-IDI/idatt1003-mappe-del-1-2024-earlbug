package edu.ntnu.idi.idatt.models;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.controllers.Fridge;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.6.2
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
    String returnedName1 = grocery1.getName();

    // Assert
    assertEquals(expectedName1, returnedName1);
  }

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
  void getAmountShouldReturn1Point5() {
    // Arrange
    double expectedAmount = 1.5;

    // Act
    double returnedAmount = grocery1.getAmount();

    // Assert
    assertEquals(expectedAmount, returnedAmount);
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
  void getExpirationDateShouldReturnCorrectDate() {
    // Arrange
    Date expectedDate = date1;

    // Act
    Date returnedDate = grocery1.getExpirationDate();

    // Assert
    assertEquals(expectedDate, returnedDate);
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
  void getPricePerUnitShouldReturnCorrectPrice() {
    // Arrange
    double expectedPrice = 29.9;

    // Act
    double returnedPrice = grocery1.getPricePerUnit();

    // Assert
    assertEquals(expectedPrice, returnedPrice);
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
  void getMeasuringUnitShouldReturnLiters() {
    // Arrange
    String expectedUnit = "liters";

    // Act
    String returnedUnit = grocery1.getMeasuringUnit();

    // Assert
    assertEquals(expectedUnit, returnedUnit);
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
  void removeAmountPointSixShouldEqualPointNine() {
    // Arrange
    double amountToBeRemoved = 0.6;
    double expectedReturn = 0.9;

    // Act
    grocery1.removeAmount(amountToBeRemoved);
    double actualReturn = grocery1.getAmount();

    // Assert
    assertEquals(expectedReturn, actualReturn);
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



  @Test
  void addAmountShouldIncreaseAmount() {
    // Arrange
    double amountToAdd = 0.5;
    double expectedAmount = 2.0;

    // Act
    grocery1.addAmount(amountToAdd);
    double actualAmount = grocery1.getAmount();

    // Assert
    assertEquals(expectedAmount, actualAmount);
  }

  @Test
  void addNegativeAmountShouldThrowIllegalArgumentException() {
    // Arrange
    double negativeAmount = -0.5;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> grocery1.addAmount(negativeAmount));
  }

  @Test
  void addZeroAmountShouldThrowIllegalArgumentException() {
    // Arrange
    double zeroAmount = 0.0;
    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> grocery1.addAmount(zeroAmount));
  }



  @Test
  void groceriesWithSameValuesShouldBeEqual() {
    Grocery grocery4 = new Grocery("Milk", 1.5, date1, 29.9, "liters");

    assertTrue(grocery1.equals(grocery4));
  }

  @Test
  void groceriesWithDifferentValuesShouldNotBeEqual() {

    assertFalse(grocery1.equals(grocery2));
  }


  @Test
  void removeAmountGreaterThanCurrentShouldThrowException() {
    double amountToRemove = grocery1.getAmount() + 1;

    assertThrows(IllegalArgumentException.class, () -> grocery1.removeAmount(amountToRemove));
  }
}