package edu.ntnu.idi.idatt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class GroceryTest {
  Grocery grocery1;
  Grocery grocery2;
  Grocery grocery3;
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  Date date1 = new Date();
  Date date2 = new Date(); //today's date
  Date date3 = new Date();
  public GroceryTest() {

    try {
      date1 = dateFormat.parse("2023-10-30");
      //date2 = dateFormat.parse("2024-10-20");
      date3 = dateFormat.parse("2025-10-10");
    } catch (ParseException e) {


      e.printStackTrace();
    }
    grocery1 = new Grocery("Milk", 1.5, date1, 29.9, "liters");
    grocery2 = new Grocery("Bananas", 5, date2, 5.40, "units");
    grocery3 = new Grocery("Flour", 0.4, date3, 14.32, "kg");
  }
  
  @Test
  void getGetNameOfGroceryTest(){
    assertEquals("Milk", grocery1.getNameOfGrocery());
    assertEquals("Bananas", grocery2.getNameOfGrocery());
    assertEquals("Flour", grocery3.getNameOfGrocery());
  }
  @Test
  void getAmountOfGroceryTest(){
    assertEquals(1.5, grocery1.getAmountOfGrocery());
    assertEquals(5, grocery2.getAmountOfGrocery());
    assertEquals(0.4, grocery3.getAmountOfGrocery());

  }

  @Test
  void getExpirationDateTest(){
    assertEquals(date1, grocery1.getExpirationDate());
    assertEquals(date2, grocery2.getExpirationDate());
    assertEquals(date3, grocery3.getExpirationDate());

  }

  @Test
  void getPricePerUnitTest(){
    assertEquals(29.9, grocery1.getPricePerUnit());
    assertEquals(5.4, grocery2.getPricePerUnit());
    assertEquals(14.32, grocery3.getPricePerUnit());

  }

  @Test
  void getMeasuringUnitTest(){
    assertEquals("liters", grocery1.getMeasuringUnit());
    assertEquals("units", grocery2.getMeasuringUnit());
    assertEquals("kg", grocery3.getMeasuringUnit());
  }

  /*
  @Test
  void returnNameOnObjectCall(){
    assertEquals("Milk", grocery1);
    assertEquals("Bananas", grocery2);
    assertEquals("Flour", grocery3);


    String string1 = "Milk";
    String string2 = "Milk";
    String string3 = "Milk";

    assertTrue(string1.equals(grocery1.toString()));
    assertTrue(string2.equals(grocery2.toString()));
    assertTrue(string3.equals(grocery3.toString()));
  }

  */

}
