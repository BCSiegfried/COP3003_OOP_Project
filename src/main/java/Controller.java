/**
 * @author Brandon Siegfried
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  @FXML
  private TextField prodId;

  @FXML
  private TextField manId;

  @FXML
  private ChoiceBox<ItemType> productComboType;

  @FXML
  private ComboBox<Integer> productComboQuantity;

  @FXML
  private TableView<Product> productTableView;

  @FXML
  private TableColumn<Integer, Product> productID;

  @FXML
  private TableColumn<String, Product> prodName;

  @FXML
  private TableColumn<String, Product> prodMan;

  @FXML
  private TableColumn<String, Product> prodType;

  @FXML
  private ListView<Product> produceTabListView;

  @FXML
  TextArea productionLogToText;

  ObservableList<Product> productLine = FXCollections.observableArrayList();
  ArrayList<Product> productArrayList = new ArrayList<>();
  ArrayList<ProductionRecord> productionRecordArray = new ArrayList<>();
  ArrayList<Employee> employeeArray = new ArrayList<>();

  //DB fields
    /*
    * Could not find a better way to implement
   */
  private static Statement statement;
  public Connection conn;
  final String USER = "";
  final String DB_URL = "jdbc:h2:./resources/ProductionProject";


  public void initialize() throws SQLException, IOException {
    connectToDb();
    SetupInterface();

    //define observable list

    setupProductLineTable();
    // pulls records from the product DB and loads them into product list
    loadProductList();
    loadProductionLog();
  }

  public static void connectToDb() throws IOException {

    //  Database credentials
    Properties prop = new Properties();
    prop.load(new FileInputStream("src/main/resources/Properties.properties"));

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/ProductionProject";
    final String USER = "";
    final String PASS = prop.getProperty("password");

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // no password

      //STEP 3: Execute a query
      statement = conn.createStatement();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

    /*
    * Adds product to DB.
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException, IOException {
    String sql = "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER) VALUES  ('"
        + prodId.getText()
        + "','"
        + productComboType.getValue()
        + "','"
        + manId.getText()
        + "');";

    sqlExecute(sql);
    loadProductList();
  }

  public static void sqlExecute(String sqlStatement) {
    try {
      statement.executeUpdate(sqlStatement);
      System.out.println(sqlStatement);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /*
  * Method called on startup to initialize Tableview
 */
  void setupProductLineTable() {
    productID.setCellValueFactory(new PropertyValueFactory<>("Id"));
    prodName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    prodMan.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    prodType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
  }

  /*
  * Called at startup and anytime a product is added. Removes items from product array and adds them to back with SQL query result set
 */
  public void loadProductList() throws SQLException, IOException {

    Properties prop = new Properties();
    prop.load(new FileInputStream("src/main/resources/Properties.properties"));

    final String PASS = prop.getProperty("password");

    conn = DriverManager.getConnection(DB_URL, USER, PASS);

    if (conn != null) {
      try {
        String sql = "SELECT * FROM PRODUCT";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        productArrayList.removeAll(productArrayList);

        while (rs.next()) {
          int id = rs.getInt("ID");
          String name = rs.getString("NAME");
          String code = rs.getString("TYPE");
          String manufacturer = rs.getString("MANUFACTURER");

          productArrayList.add(new Widget(id, name, ItemType.valueOf(code), manufacturer));
        }
        productTableView.getItems().clear();
        productTableView.getItems().addAll(productArrayList);

        produceTabListView.getItems().clear();
        produceTabListView.setItems(productLine);

        productLine.removeAll();
        productLine.addAll(productArrayList);


      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println(productArrayList.size());
  }
  /*
  * Records productionLog object to ProductRecord DB table.
 */
  @FXML
  void buttonRecordProduction(ActionEvent event) throws SQLException {
    int inventoryCount = 0;
    ProductionRecord.inventoryCount = 0;

    for (int j = 0; j < Integer.parseInt(String.valueOf(productComboQuantity.getValue())); j++) {
      //create a method which takes the current inventory amount from the production record arraylist.
      inventoryCount = 0;
      String query = "SELECT COUNT(PRODUCT_ID) FROM PRODUCTIONRECORD WHERE PRODUCT_ID = '"
          +
          produceTabListView.getSelectionModel().getSelectedItem().getId()
          + "'";

      statement = conn.createStatement();
      PreparedStatement ps = conn.prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int count = rs.getInt(1);
      inventoryCount = count + ++inventoryCount;

      ProductionRecord recordObject = new ProductionRecord(
          produceTabListView.getSelectionModel().getSelectedItem(), inventoryCount);

      System.out.println("Product Recorded");

      String date = recordObject.getDateProduced();
      String sql =
          "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES  ('"
              + recordObject.getProductionNumber()
              + "','"
              + recordObject.getProductID()
              + "','"
              + recordObject.getSerialNumber()
              + "','"
              + date
              + "');";

      sqlExecute(sql);

      loadProductionLog();

    } // create an arraylist of production record items, within the for loop add the item to the arraylist,

  }
  /*
  * loads production log array list and repopulates with SQL query. prints to third tab
 */
  public void loadProductionLog() throws SQLException {
    if (conn != null) {
      productionRecordArray.clear();

      String sql = "SELECT * FROM PRODUCTIONRECORD";

      statement = conn.createStatement();

      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        int productionNum = rs.getInt("PRODUCTION_NUM");
        int productId = rs.getInt("PRODUCT_ID");
        String serialNum = rs.getString("SERIAL_NUM");
        Date dateProduced = rs.getDate("DATE_PRODUCED");

        productionRecordArray
            .add(new ProductionRecord(productionNum, productId, serialNum, dateProduced));
      }
      showProduction();
    }
  }
    /*
    * Sets values for production text field, combobox quantity, and combobox itemType
   */
  public void SetupInterface() {
    productionLogToText.setEditable(false);

    productComboQuantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    productComboQuantity
        .setEditable(true);
    productComboQuantity.getSelectionModel().selectFirst();

    productComboType.getItems().setAll(ItemType.values());
    productComboType.getSelectionModel().selectFirst();
  }
    /*
    * Alternative to ProductionRecord toString method.
   *  SQL query finds product ID in products table matches product ID matches in productionrecord table.
  *   enhanced for loop prints product name in lieu of product ID
 */
  public void showProduction() throws SQLException {
    productionLogToText.setText("");
    String productName = "";
    for (ProductionRecord record : productionRecordArray) {

      String sql = "SELECT NAME FROM PRODUCT WHERE ID = '"
          +
          record.getProductID()
          + "'";

      statement = conn.createStatement();

      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        productName = rs.getString("NAME");
      }

      productionLogToText.appendText("Prod. Num: " + (productionRecordArray.indexOf(record) + 1)
          + " Product Name: "
          + productName
          + " Serial Num: "
          + record.getSerialNumber()
          + " Date: "
          + record.getDateProduced()
          + "\n");
    }
  }

  // Employee tab methods

  @FXML
  private Label employeeVerifyButton;

  @FXML
  private TextField nameTextField;

  @FXML
  private TextField passwordTextField;

  @FXML
    /*
    * HCI measures for account creation. Determines if fields are blank. Inserts employee into Employee table
   */
  void createAccountButton(ActionEvent event) {

    if (nameTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
      employeeVerifyButton.setText("Full name and password required.");
    } else {
      String txtName = nameTextField.getText();
      String txtPassword = passwordTextField.getText();

      Employee employee = new Employee(txtName, txtPassword);
      System.out.println(employee);
      employeeVerifyButton.setText("User account created successfully.");
      nameTextField.clear();
      passwordTextField.clear();

      String sql = "INSERT INTO EMPLOYEE(Name, Username, Email, Password) VALUES  ('"
          + employee.getName()
          + "','"
          + employee.getUserName()
          + "','"
          + employee.getEmail()
          + "','"
          + employee.getPassword()
          + "');";

      sqlExecute(sql);
    }

  }
    /*
    * result set pulls current employees.compares name and password stored for matching value. Notifies user if fields entered matches database entry.
   */
  public void loginButton(ActionEvent actionEvent) throws SQLException {
    if (nameTextField.getText().isBlank() || passwordTextField.getText().isBlank()) {
      employeeVerifyButton.setText("Incorrect name or password.");
    } else {
      if (conn != null) {

        String sql = "SELECT * FROM EMPLOYEE";

        statement = conn.createStatement();

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

          String name = rs.getString("Name");
          String username = rs.getString("username");
          String email = rs.getString("email");
          String password = rs.getString("Password");

          employeeArray
              .add(new Employee(name, username, email, password));
        }

        boolean found = false;

        for (Employee employee : employeeArray) {
          if ((employee.getName().equals(nameTextField.getText())) && (employee.getPassword()
              .equals(passwordTextField.getText()))) {
            found = true;
          }
        }
        System.out.println(found);
        if (!found) {
          employeeVerifyButton
              .setText("Your name or password was not correct. Please create an account.");
        } else {
          employeeVerifyButton.setText("You have successfully logged in.");
        }
        nameTextField.clear();
        passwordTextField.clear();
      }

    }
  }
}


