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
    ScannerValidator validator = new ScannerValidator();
    String emptyString = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.validateStringScanner(emptyString));
  }

  @Test
  void invalidDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String invalidDouble = "kjodl";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveDouble(invalidDouble));
  }

  @Test
  void zeroDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String zeroDouble = "0";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveDouble(zeroDouble));
  }

  @Test
  void negativeDoubleShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String negativeDouble = "-1.5";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveDouble(negativeDouble));
  }


  @Test
  void emptyDateShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String emptyDate = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseStringToDate(emptyDate));
  }

  @Test
  void invalidIntShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String invalidInt = "utco";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveInt(invalidInt));
  }

  @Test
  void negativeIntShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String negativeInt = "-6";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveInt(negativeInt));
  }

  @Test
  void emptyIntShouldThrowIllegalArgumentException() {
    // Arrange
    ScannerValidator validator = new ScannerValidator();
    String emptyInt = "";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> validator.parseToPositiveInt(emptyInt));
  }

}
