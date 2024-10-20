package edu.ntnu.idi.idatt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Grocery {

  private String  nameOfGrocery;
  private float   amount;
  private Date    expirationDate;
  private float   pricePerUnit;
  private String  measuringUnit;

  public Grocery(String nameOfGrocery, float amount, Date expirationDate, float pricePerUnit, String measuringUnit) {
    this.nameOfGrocery = nameOfGrocery;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.pricePerUnit = pricePerUnit;
    this.measuringUnit = measuringUnit;
  }

  public String getNameOfGrocery(){
    return this.nameOfGrocery;
  }
  public float getAmountOfGrocery(){
    return this.amount;
  }
  public Date getExpirationDate(){
    return this.expirationDate; //find out if it Strong or Date is most desirable
  }
  public float getPricePerUnit(){
    return this.pricePerUnit;
  }
  public String getMeasuringUnit(){
    return this.measuringUnit;
  }



  @Override // does this have any unwanted side effects? or can i t be used for somthing else?
  public String toString() {
    return this.nameOfGrocery;
  }

}
