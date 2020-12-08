/**
 * Enumeration object class used for identifying the item type of each Product class field itemType.
 * JavaDoc error from method c.
 *
 * @author Brandon Siegfried
 */

public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");
  final String c;

  ItemType(String c) {
    this.c = c;
  }

  public String getType() {
    return c;
  }


}
