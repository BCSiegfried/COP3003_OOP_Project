/**
 * @author Brandon Siegfried
 */

public interface ScreenSpec {

  String getResolution();

  void setResolution(String resolution);

  int getRefreshRate();

  void setRefreshRate(int RefreshRate);

  int getResponseTime();

  void setResponseTime(int responseTime);
}