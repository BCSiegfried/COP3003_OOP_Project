import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class Controller {


  @FXML
  private Tab tab1;

  @FXML
  private TextField prodId;

  @FXML
  private TextField typeId;

  @FXML
  private TextField manId;

  @FXML
  private Tab tab2;

  @FXML
  private Tab tab3;

  @FXML
  private ComboBox<String> productComboQuantity;

  public void initialize() {

    for (int count = 1; count <= 10; count++) {
      productComboQuantity.getItems().add(String.valueOf(count));
    }
    productComboQuantity.setEditable(true);
    productComboQuantity.getSelectionModel().selectFirst();
  }


  @FXML
  private void buttonRecordProduction() {

  }

  @FXML
  private void productButton() {

  };

  @FXML
  void addProduct(ActionEvent event) {
    connectToDb();
  }




  public void connectToDb() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/ProductionProject";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
        stmt = conn.createStatement();

      String txtTypeId = typeId.getText();
      String txtManId = manId.getText();
      String txtProdId = prodId.getText();



      String sql = "INSERT INTO Product(type, manufacturer, name) VALUES " +
          "( '"+txtTypeId+"', '"+txtManId+"', '"+txtProdId+"' )";

        stmt.executeUpdate(sql);
        //Begin new statement
      stmt.close();
      stmt = conn.createStatement();

      sql = "SELECT *" +
          " FROM Product";

      ResultSet rs = stmt.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      System.out.println("SELECT (type, manufacturer,name) FROM Product");
      int columnsNumber = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
          if (i > 1) System.out.print(" ");
          String columnValue = rs.getString(i);
          System.out.print(rsmd.getColumnName(i) + ": " + columnValue + "\n");
        }
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }



}

