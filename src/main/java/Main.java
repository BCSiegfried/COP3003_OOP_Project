import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);

    //code scrap from repl.it 04 holding onto this for educational purposes
/*
   Product product1 = new AudioPlayer("iPod", "Apple", "supported audio", "supported playlist", "This shouldn't show" );
    System.out.println(product1.toString());

    AudioPlayer newProduct = new AudioPlayer("DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    System.out.println(newProduct);
    newProduct.play();
    newProduct.stop();
    newProduct.next();
    newProduct.previous();
*/
  //replit 06
       /* AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
            "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
        Screen newScreen = new Screen("720x480", 40, 22);
        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
            MonitorType.LCD);
        ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
        productList.add(newAudioProduct);
        productList.add(newMovieProduct);
        for (MultimediaControl p : productList) {
          System.out.println(p);
          p.play();
          p.stop();
          p.next();
          p.previous();*/


        }

  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root);

   /* TableView tableView = new TableView();

    TableColumn<Product, String> column1 = new TableColumn<>("ID");
    column1.setCellValueFactory(new PropertyValueFactory<>("ID"));

    TableColumn<Product, String> column2 = new TableColumn<>("Manufacturer");
    column2.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

    TableColumn<Product, String> column3 = new TableColumn<>("Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<Product, String> column4 = new TableColumn<>("Item Type");
    column2.setCellValueFactory(new PropertyValueFactory<>("ItemType"));


    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);

    VBox vbox = new VBox(tableView);*/



    /*Scene scene = new Scene(vbox);*/

    primaryStage.setTitle("Production Project");
    primaryStage.setScene(scene);
    primaryStage.show();
    scene.getStylesheets().add("GUIStyle1.css");

  }



}