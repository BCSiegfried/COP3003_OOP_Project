/**
 * @author Brandon Siegfried
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductionRecord {

  static int productionNumber;
  int productID;
  String serialNumber;
  String dateProduced;
  static int inventoryCount = 0;
  Product product;

  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   *
   * @param productionNumber static method for incrementing production number
   */
  public void setProductionNumber(int productionNumber) {
    ProductionRecord.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getDateProduced() {

    return dateProduced;
  }

  public void setDateProduced(String dateProduced) {

    this.dateProduced = dateProduced;
  }

  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    ProductionRecord.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date().toString();
  }
    /*
    * @constructor for formatting serial number
   */
  public ProductionRecord(Product product, int inventoryCount) {
    productionNumber++;
    productID = product.getId();
    ProductionRecord.inventoryCount = productionNumber;
    this.serialNumber = product.getManufacturer().substring(0, 3) + product.getItemType().c + String
        .format("%05d", inventoryCount);
    this.dateProduced = format.format(new Date());

  }

  /**
   *
   * @return String values of ProductionRecord object.
   */
  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNumber() + " Product ID: " + productID +
        " Serial Num: " + serialNumber + " Date: " + dateProduced + "\n";
  }

}

