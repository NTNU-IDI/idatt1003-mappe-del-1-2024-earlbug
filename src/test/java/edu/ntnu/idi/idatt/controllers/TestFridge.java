package edu.ntnu.idi.idatt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import edu.ntnu.idi.idatt.models.Grocery;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @since 0.1.0
 * @author Erlend Sundsdal
 * @version 0.3.0
 */
public class TestFridge {

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
    assertEquals(newAmount,fridge.getGroceryList().get(0).getAmountOfGrocery());
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
  void addGroceryShouldThrowExceptionForInvalidExpirationDate() {
    // Arrange
    String name = "Milk";
    double amount = 1.75;
    Date expirationDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // Yesterday
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
  void m() {
    // Arrange


    // Act


    //Assert


  }
  @Test
  void k() {
    // Arrange


    // Act


    //Assert


  }
  @Test
  void n() {
    // Arrange


    // Act


    //Assert


  }
  @Test
  void o() {
    // Arrange


    // Act


    //Assert


  }


}

