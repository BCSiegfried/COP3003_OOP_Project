public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  public String getResolution() {
    return resolution;
  }


  public void setResolution(String resolution) {

  }


  public int getRefreshRate() {
    return refreshRate;
  }


  public void setRefreshRate(int RefreshRate) {

  }


  public int getResponseTime() {
    return responseTime;
  }


  public void setResponseTime(int responseTime) {

  }

  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String toString() {
    return "Screen:" + '\n' + "Resolution: " + resolution + '\n' +"Refresh rate: " + refreshRate + '\n' + "Response time: " + responseTime;
  }


}
