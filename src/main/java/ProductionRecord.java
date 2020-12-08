import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ProductionRecord reads and writes attributes of productionRecord object to and from the DB. Takes
 * attributes from Product class objects.
 *
 * @author Brandon Siegfried
 */
public class ProductionRecord {

  static int productionNumber;
  int productId;
  String serialNumber;
  String dateProduced;
  static int inventoryCount = 0;
  /*Product product;*/ // unused field

  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * Assigns static class field productionNumber to objects created- manually from ProductionRecord
   * table entries.
   *
   * @param productionNumber static method for incrementing production number
   */
  public void setProductionNumber(int productionNumber) {
    ProductionRecord.productionNumber = productionNumber;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
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

  /**
   * constructor for ProductionRecord.
   *
   * @param productionNumber static class field which gets iterated with each created object
   * @param productId        Identification of product
   * @param serialNumber     unique serial number of each production entry
   */
  public ProductionRecord(int productionNumber, int productId, String serialNumber,
      Date dateProduced) {
    ProductionRecord.productionNumber = productionNumber;
    this.productId = productId;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date().toString();
  }

  /**
   * constructor for formatting production record entries entered through the UI.
   *
   * @param product           object of type product
   * @param inventoryCount        count of this object created
   */
  public ProductionRecord(Product product, int inventoryCount) {
    productionNumber++;
    productId = product.getId();
    ProductionRecord.inventoryCount = productionNumber;
    this.serialNumber = product.getManufacturer().substring(0, 3) + product.getItemType().c + String
        .format("%05d", inventoryCount);
    this.dateProduced = format.format(new Date());

  }

  /**
   * Method returns String properties of an class ProductionRecord object.
   *
   * @return String values of ProductionRecord object.
   */
  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNumber()
        + " Product ID: " + productId
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced + "\n";
  }

}

