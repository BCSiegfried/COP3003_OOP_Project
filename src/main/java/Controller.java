import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {


  @FXML
  private Tab tab1;

  @FXML
  private Tab tab2;

  @FXML
  private Tab tab3;

  //fields in Product Line tab
  @FXML
  private TextField prodId;

  @FXML
  private TextField manId;

  @FXML
  private ComboBox<String> productComboType;

  @FXML
  private ComboBox<String> productComboQuantity;

  @FXML
  private TableView<Product> productTableView;

  @FXML
  private TableColumn<?, ?> prodName;

  @FXML
  private TableColumn<?, ?> prodMan;

  @FXML
  private TableColumn<?, ?> prodType;

  @FXML
  private ListView<Product> produceTabListView;

  @FXML
  private TextArea productionLogToText;

  @FXML
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  @FXML
  void buttonRecordProduction(ActionEvent event) {
    int inventoryCount=0;
    String obsName = prodId.getText();
    String obsManufacturer = manId.getText();
    String obsType = productComboType.getValue();

    Product existingItem = new Widget(obsName, obsManufacturer, ItemType.valueOf(obsType));
    int comboboxSelection = Integer
        .valueOf(productComboQuantity.getSelectionModel().getSelectedItem());

    for (int j = 0; j < comboboxSelection; j++) {
      inventoryCount = inventoryCount + 1;
      ProductionRecord recordObject = new ProductionRecord(existingItem, inventoryCount);
      productionLogToText.appendText(recordObject.toString());
      System.out.println(recordObject.toString());
      System.out.println("Product Recorded");
    }

  }


  public void initialize() {

    TableView tableView = new TableView();
    // set editable tab for combobox in Product line tab
    productComboQuantity.setEditable(true);

    // set quantity equal to 1-10 in Produce tab
    for (int i = 1; i <= 10; i++) {
      productComboQuantity.getItems().add(Integer.toString(i));
    }
    //adds values of ItemType Enum to combobox in Produce tab
    for (ItemType it : ItemType.values()) {
      productComboType.getItems().add(String.valueOf((it)));
    }
    //observable array list
    productLine = FXCollections.observableArrayList();

    //observable list. This doesn't work at the moment
    ObservableList<Product> data = populateList();
    TableColumn<Product, String> prodMan = new TableColumn<>("Manufacturer");
    TableColumn<Product, String> prodType = new TableColumn<>("Name");
    TableColumn<Product, String> prodName = new TableColumn<>("Type");
  }

  @FXML
  void addProduct(ActionEvent event) {
    connectToDb();
    setupProductTable();
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

      String txtTypeId = productComboType.toString();
      String txtManId = manId.getText();
      String txtProdId = prodId.getText();

      String sql = "INSERT INTO Product(type, manufacturer, name) VALUES " +
          "( '" + txtTypeId + "', '" + txtManId + "', '" + txtProdId + "' )";

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
          if (i > 1) {
            System.out.print(" ");
          }
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

  void setupProductTable() {

    String obsName = prodId.getText();
    String obsManufacturer = manId.getText();
    String obsType = productComboType.getValue();

    Product product = new Widget(obsName, obsManufacturer, ItemType.valueOf(obsType));
    productLine.add(product);

    //PropertyValueFactory strings NEED TO MATCH VALUES OF FIELDS IN PRODUCT

    //column for product name
    prodName.setCellValueFactory(new PropertyValueFactory<>("Name"));

    //column for manufacturer
    prodMan.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));

    //column for type
    prodType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));

    //commits column names for listview in Produce tab
    productTableView.setItems(productLine);
    produceTabListView.setItems(productLine);
  }

  //may not be necessary. Circle back to this
  public static ObservableList<Product> populateList() {
    return FXCollections.observableArrayList(
    );
  }
}

