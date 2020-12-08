/**
 * Class which uses the MultiMediaControl interface. Child class of Product. Unused Getters and
 * Setters.
 *
 * @author Brandon Siegfried
 */


public class AudioPlayer extends Product implements MultimediaControl {

  private String supportedAudioFormats;
  private String supportedPlaylistFormats;
  /*private String type;*/ // unused field


  public String setSupportedAudioFormats() {
    return supportedAudioFormats;
  }

  public void getSupportedAudioFormats(String newAudio) {
  }

  public String setSupportedPlaylistFormats() {
    return supportedPlaylistFormats;
  }

  public void getSupportedPlaylistFormats(String newPlaylist) {

  }

  /**
   * Constructor for AudioPlayer object Class.
   *
   * @param name                     Name of AudioPlayer.
   * @param manufacturer             Manufacturer of AudioPlayer.
   * @param supportedAudioFormats    Supported audio formats of AudioPlayer.
   * @param supportedPlaylistFormats Supported Playlist formats of AudioPlayer.
   */
  AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {

    super(name, ItemType.Audio, manufacturer);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * Display AudioPlayer attributes.
   *
   * @return Formatted String of AudioPlayer object fields.
   */

  @Override
  public String toString() {
    return "Name: " + getName() + '\n'
        + "Manufacturer: " + getManufacturer() + '\n' + "Type: "
        + getItemType() + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats + '\n'
        + "Supported Playlist Formats: " + supportedPlaylistFormats;
  }

  public void play() {
    System.out.println("Playing");
  }


  public void stop() {
    System.out.println("Stopping");
  }


  public void previous() {
    System.out.println("Previous");
  }


  public void next() {
    System.out.println("Next");
  }
}
