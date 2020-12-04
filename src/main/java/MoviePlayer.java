/**
 * @author Brandon Siegfried
 */

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  //methods from MultimediaControl
  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }


  //methods for screen and monitorType
  public void setMonitorType(MonitorType monitorType) {
  }

  MonitorType getMonitorType() {
    return monitorType;
  }

  public void getScreen(Screen screen) {
  }

  Screen setScreen() {
    return screen;
  }

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    //superclass for Screen
    super(name, ItemType.Visual, manufacturer);
    // These are necessary
    this.screen = screen;
    this.monitorType = monitorType;
  }


  public String toString() {
    return super.toString() + "\n" + screen.toString() + "\n" + "Monitor Type: " + monitorType;

  }


}
