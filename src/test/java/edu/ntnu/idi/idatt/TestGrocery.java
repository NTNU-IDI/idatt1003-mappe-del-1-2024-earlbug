package edu.ntnu.idi.idatt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.models.Grocery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  @Test
  void emptyNameShouldReturnIllegalArgumentException() {
    // Arrange
  }

  @Test
  // Almost split into AAA, but works at least.
  void NegativeGroceryAmountShouldthowIllegalArgumentException() {

    // Arrange
    double invalidAmount = -1.5;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new Grocery(
        "Milk", invalidAmount, date1, 29.9, "liters")
    );
  }






}
