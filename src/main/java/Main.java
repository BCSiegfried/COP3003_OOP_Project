import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the driver class.
 *
 * @author Brandon Siegfried
 */

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root);

    primaryStage.setTitle("Production Line Project");
    primaryStage.setScene(scene);
    primaryStage.show();
    scene.getStylesheets().add("GUIStyle1.css");
  }

}