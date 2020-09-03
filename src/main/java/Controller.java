import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class Controller {

  public void addProduct(){
    System.out.println("Product added");

  }

  public void buttonRecordProduction() {
    System.out.println("Record Production button pressed");
  }

  @FXML
  Tab tab1;

  @FXML
  Tab tab2;

  @FXML
  Tab tab3;

/* Check up on what this code does later, used it to initialize tabs originally
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    FXMLLoader loader = new FXMLLoader();
    try
    {
      AnchorPane anch1 = loader.load(getClass().getResource("Tab1.fxml"));
      tab1.setContent(anch1);
    }
    catch(IOException iex)
    {
      System.out.println("File not found");
    }

    loader = new FXMLLoader();
    try
    {
      AnchorPane anch2 = loader.load(getClass().getResource("Tab2.fxml"));
      tab2.setContent(anch2);
    }
    catch(IOException iex)
    {
      System.out.println("File not found");
    }

    loader = new FXMLLoader();
    try
    {
      AnchorPane anch3 = loader.load(getClass().getResource("Tab3.fxml"));
      tab3.setContent(anch3);
    }
    catch(IOException iex)
    {
      System.out.println("File not found");
    }

*/

  }

