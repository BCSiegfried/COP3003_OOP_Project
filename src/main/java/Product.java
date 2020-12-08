/**
 * Object class for Product.
 *
 * @author Brandon Siegfried
 */

public abstract class Product implements Item {


  private int id;
  private String manufacturer;
  private String name;
  private ItemType itemType;

  public void setId(int id) {
    this.id = id;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }


  @Override
  public int getId() {

    return id;
  }

  public void setName(String newString) {

  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String tempString) {

  }

  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Constructor user for entering a product through the UI.
   *
   * @param name         name of product.
   * @param itemType     enum classification of item type.
   * @param manufacturer name of manufacturer.
   */
  public Product(String name, ItemType itemType, String manufacturer) {
    this.name = name;
    this.itemType = itemType;
    this.manufacturer = manufacturer;

  }

  /**
   * Constructor used for loading the product ArrayList.
   *
   * @param id           Identification of product.
   * @param name         Name of product.
   * @param itemtype     Enum item type of product.
   * @param manufacturer Manufacturer of item type.
   */
  public Product(int id, String name, ItemType itemtype, String manufacturer) {
    this.id = id;
    this.name = name;
    this.itemType = itemtype;
    this.manufacturer = manufacturer;

  }

  public Product() {

  }

  public Product(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  }

  /**
   * Method for retrieving Product objects details.
   *
   * @return Object field declarations of class Product objects.
   */
  public String toString() {
    return "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + itemType;
  }

}

/**
 * Child class of Product. Producing JavaDoc error from not existing within it's own class.
 */
class Widget extends Product implements Item {

  public Widget(int id, String name, ItemType type, String manufacturer) {
    super(id, name, type, manufacturer);

  }

}


