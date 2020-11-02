import java.util.Date;

public class ProductionRecord {
  //static is based on the class not the object
static int productionNumber = 0;
int productID;
String serialNumber;
Date dateProduced;
static int inventoryCount =0;
Product product;

  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
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

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(int productID) {
    this.productionNumber++;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  public ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date();
  }
//week 10 constructor
  public ProductionRecord(Product product, int inventoryCount) {
    productionNumber++;
    productID = product.getId();
    this.inventoryCount = productionNumber;
    this.serialNumber = product.getManufacturer().substring(0,3) + product.getItemType().c + String.format("%05d",inventoryCount);
    this.dateProduced = new Date();

  }

  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNumber() + " Product ID: " + productID +
        " Serial Num: " + serialNumber +  " Date: " + dateProduced + "\n";
  }

}

