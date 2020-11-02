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



  //Constructors

  public Product(String name, String manufacturer,ItemType itemType){
    this.name = name;
    this.manufacturer = manufacturer;
    this.itemType = itemType ;
  }

  /*public Product(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  }*/
  public Product() {

  };
  public Product(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  };


  public String toString() {
    return  "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + itemType;
  }
}

class Widget extends Product implements Item {

  public Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

}


