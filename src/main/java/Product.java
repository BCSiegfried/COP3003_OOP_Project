/**
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


  public Product(String name, ItemType itemType, String manufacturer) {
    this.name = name;
    this.itemType = itemType;
    this.manufacturer = manufacturer;

  }

  public Product(int id, String name, ItemType itemtype, String manufacturer) {
    this.id = id;
    this.name = name;
    this.itemType = itemtype;
    this.manufacturer = manufacturer;

  }

  public Product() {

  }

  ;

  public Product(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  }

  ;


  public String toString() {
    return "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + itemType;
  }
}
  /*
  * Widget child class for instantiating abstract class Product
 */
class Widget extends Product implements Item {

  public Widget(String name, ItemType type, String manufacturer) {
    super(name, type, manufacturer);
  }

  public Widget(int id, String name, ItemType type, String manufacturer) {
    super(id, name, type, manufacturer);

  }

}


