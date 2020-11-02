public class AudioPlayer extends Product implements MultimediaControl{

  private String supportedAudioFormats;
  private String supportedPlaylistFormats;
  private String type;


  //supportedAudioFormats getter and setter
  public String setSupportedAudioFormats() {
    return supportedAudioFormats;
  }
  public void getSupportedAudioFormats(String newAudio) {
  }

  //supportedPlaylistFormats getter and setter
  public String setSupportedPlaylistFormats() {
    return supportedPlaylistFormats;
  }
  public void getSupportedPlaylistFormats(String newPlaylist) {

  }


//constructors


  AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {

    super(name, manufacturer, ItemType.Audio);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }
  @Override
  public String toString() {
    return  "Name: " + getName()/*used to be name*/ + '\n' +
        "Manufacturer: " + getManufacturer()/* used to be manufacturer*/ + '\n' + "Type: " + getItemType() + "\n" + "Supported Audio Formats: " + supportedAudioFormats +
        '\n' + "Supported Playlist Formats: " + supportedPlaylistFormats;
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
