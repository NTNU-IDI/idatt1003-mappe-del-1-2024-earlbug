package edu.ntnu.idi.idatt.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @since 0.2.0
 * @author Erlend Sundsdal
 * @version 0.6.2
 */
public class TestScannerValidator {


  @Test
  void emptyStringShouldThrowIllegalArgumentException() {
    // Arrange
    String emptyString = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> ScannerValidator.validateStringScanner(emptyString));
  }

  @Test
  void invalidDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    String invalidDouble = "kjodl";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveDouble(invalidDouble));
  }

  @Test
  void zeroDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    String zeroDouble = "0";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveDouble(zeroDouble));
  }

  @Test
  void negativeDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    String negativeDouble = "-1.5";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveDouble(negativeDouble));
  }


  @Test
  void emptyDateShouldThrowIllegalArgumentException() {
    // Arrange
    String emptyDate = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseStringToDate(emptyDate));
  }

  @Test
  void invalidIntShouldThrowIllegalArgumentException() {
    // Arrange
    String invalidInt = "utco";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveInt(invalidInt));
  }

  @Test
  void negativeIntShouldThrowIllegalArgumentException() {
    // Arrange
    String negativeInt = "-6";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveInt(negativeInt));
  }

  @Test
  void emptyIntShouldThrowIllegalArgumentException() {
    // Arrange
    String emptyInt = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
        ScannerValidator.parseToPositiveInt(emptyInt));
  }

}
