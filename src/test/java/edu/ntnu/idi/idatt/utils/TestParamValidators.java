package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import edu.ntnu.idi.idatt.models.Grocery;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * @since 0.2.0
 * @author Erlend Sundsdal
 * @version 0.6.2
 */
public class TestParamValidators {



  @Test
  void validateStringShouldThrowIllegalArgumentExceptionWhenEmptyString() {
    // Arrange
    String emptyString = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateString(emptyString));
  }

  @Test
  void validateStringShouldThrowIllegalArgumentExceptionWhenNull() {
    // Arrange
    String nullString = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateString(nullString));
  }

  @Test
  void validateStringShouldNotThrowAnythingWhenValid() {
    // Arrange
    String validString = "valid";

    // Act & Assert
    assertDoesNotThrow(() -> ParamValidators.validateString(validString));
  }

  @Test
  void validatePositiveDoubleShouldThrowIllegalArgumentExceptionWhenZero() {
    // Arrange
    double zeroValue = 0;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validatePositiveDouble(zeroValue));
  }

  @Test
  void validatePositiveDoubleShouldThrowIllegalArgumentExceptionWhenNegative() {
    // Arrange
    double negativeValue = -1;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validatePositiveDouble(negativeValue));
  }

  @Test
  void validatePositiveDoubleShouldNotThrowAnythingWhenValid() {
    // Arrange
    double validDouble = 5.8;
    // Act & Assert
    assertDoesNotThrow(() -> ParamValidators.validatePositiveDouble(validDouble));
  }


  @Test
  void validatePositiveIntShouldThrowIllegalArgumentExceptionWhenZero() {
    // Arrange
    int zeroValue = 0;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validatePositiveInt(zeroValue));
  }

  @Test
  void validatePositiveIntShouldThrowIllegalArgumentExceptionWhenNegative() {
    // Arrange
    int negativeValue = -1;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validatePositiveInt(negativeValue));
  }

  @Test
  void validatePositiveIntShouldNotThrowAnythingWhenValid() {
    // Arrange
    int validint = 7;
    // Act & Assert
    assertDoesNotThrow(() -> ParamValidators.validatePositiveInt(validint));
  }


  @Test
  void validateDateShouldThrowIllegalArgumentExceptionWhenNull() {
    // Arrange
    Date nullDate = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateDate(nullDate));
  }

  @Test
  void validateDateShouldNotThrowAnythingWhenValid() {
    // Arrange
    Date validDate = new Date();

    // Act & Assert
    assertDoesNotThrow(() -> ParamValidators.validateDate(validDate));
  }

  @Test
  void validateArrayListShouldThrowIllegalArgumentExceptionWhenNull() {
    // Arrange
    ArrayList<Grocery> nullArrayList = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateArrayList(nullArrayList));
  }

  @Test
  void validateArrayListShouldThrowIllegalArgumentExceptionWhenEmpty() {
    // Arrange
    ArrayList<Grocery> emptyArrayList = new ArrayList<>();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateArrayList(emptyArrayList));
  }

  @Test
  void validateGroceryShouldThrowIllegalArgumentExceptionWhenNull() {
    // Arrange
    Grocery nullGrocery = null;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
      ParamValidators.validateGrocery(nullGrocery));
  }

  @Test
  void validateGroceryShouldNotThrowAnythingWhenValid() {
    // Arrange
    Grocery validGrocery = new Grocery("Apple", 1.0, new Date(), 0.5, "kg");

    // Act & Assert
    assertDoesNotThrow(() -> ParamValidators.validateGrocery(validGrocery));
  }

}
