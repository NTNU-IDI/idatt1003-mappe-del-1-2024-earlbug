package edu.ntnu.idi.idatt;

import java.util.Date;

public class Grocery {

  private String  nameOfGrocery;
  private double   amount;
  private Date    expirationDate;
  private double   pricePerUnit;
  private String  measuringUnit;

  public Grocery(String nameOfGrocery, double amount, Date expirationDate, double pricePerUnit, String measuringUnit) {
    this.nameOfGrocery = nameOfGrocery;
    this.amount = amount;
    this.expirationDate = expirationDate;
    this.pricePerUnit = pricePerUnit;
    this.measuringUnit = measuringUnit;
  }

  public String getNameOfGrocery(){
    return this.nameOfGrocery;
  }
  public double getAmountOfGrocery(){
    return this.amount;
  }
  public Date getExpirationDate(){
    return this.expirationDate;
  }
  public double getPricePerUnit(){
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
