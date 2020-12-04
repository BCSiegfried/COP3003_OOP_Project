/**
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
