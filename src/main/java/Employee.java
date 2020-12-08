import java.util.regex.Pattern;

/**
 * Employee class for creating Employee DB table entries.
 *
 * @author Brandon Siegfried
 */

public class Employee {


  String name;
  String userName;
  String password;
  String email;
  /*
   * Regex patterns for detecting password validity
   */
  static final Pattern UPPER = Pattern.compile("[A-Z]");
  static final Pattern LOWER = Pattern.compile("[a-z]");
  static final Pattern SPECIAL = Pattern.compile("[!$#%]");

  static int flagLowercase = 0;
  static int flagUppercase = 0;
  static int flagSpecial = 0;

  /**
   * JavaDocs error from final method declaration.
   */
  static final char SPACE = ' ';

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  /**
   * Method for formatting name field in Employee class constructor.
   *
   * @param name Takes in name entered from the UI.
   */
  public void setUserName(String name) {
    int nameLength = name.length();
    StringBuilder userName1 = new StringBuilder();
    name = name.toLowerCase();
    userName1.append(name.charAt(0));

    for (int k = 0; k < nameLength; k++) {
      if (name.charAt(k) == SPACE) {
        for (k++; k < nameLength; k++) {
          userName1.append(name.charAt(k));
        }
      }
    }

    this.userName = userName1 + "";
  }

  protected String getPassword() {
    return reverseString(password);
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  /**
   * Method for creating and formatting email field in Employee class constructor.
   *
   * @param name name of Employee
   */
  public void setEmail(String name) {
    int nameLength = name.length(); // redundant
    name = name.toLowerCase();

    StringBuilder lastName = new StringBuilder();
    StringBuilder firstName = new StringBuilder();

    for (int l = 0; name.charAt(l) != SPACE; l++) {
      firstName.append(name.charAt(l));
    }

    for (int k = 0; k < nameLength; k++) {
      if (name.charAt(k) == SPACE) {

        for (k++; k < nameLength; k++) {
          lastName.append(name.charAt(k));
        }
      }
    }
    this.email = firstName + "." + lastName + "@oracleacademy.Test";
  }

  /**
   * Constructor for taking in textfields of UI and converting name into Email and Username fields.
   * Verifies applicable password.
   *
   * @param name     Name of employee.
   * @param password Password of employee.
   */
  public Employee(String name, String password) {

    this.name = name;

    if (checkName(name) == 1) {
      setUserName(name);
      setEmail(name);
    } else {
      this.userName = "Default";
      this.email = "user@oracleacademy.Test";
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }

  }

  /**
   * Constructor for Writing to Employee ArrayList of Employee DB table.
   *
   * @param name     Name of Employee
   * @param userName Username of Employee.
   * @param email    Email of Employee.
   * @param password Password of Employee.
   */
  public Employee(String name, String userName, String email, String password) {
    this.name = name;
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  /*
   * Check for first and last name for entry
   */
  private int checkName(String name) {
    int nameLength = name.length();
    int hasSpace = 0;

    for (int i = 0; i < nameLength; i++) {
      if (name.charAt(i) == SPACE) {
        hasSpace = 1;
        break;
      }

    }
    return hasSpace;
  }

  /*
   * Check for valid password. Must constain upper, lower, and special character
   */
  private boolean isValidPassword(String password) {

    if (password == null) {
      return false;
    } else {

      if (UPPER.matcher(password).find()) {
        ++flagUppercase;
      }
      if (LOWER.matcher(password).find()) {
        ++flagLowercase;
      }
      if (SPECIAL.matcher(password).find()) {
        ++flagSpecial;
      }
      return flagUppercase >= 1 && flagLowercase >= 1 && flagSpecial >= 1;

    }
  }

  /**
   * Method for Outputting information of Employee objects.
   *
   * @return Formatted String of Employee class object fields.
   */
  public String toString() {
    return "Employee Details" + '\n' + "Name : " + name + "\n" + "Username : " + userName + "\n"
        + "Email : " + email + "\n" + "Initial Password : " + password;
  }

  /**
   * Method for storing password to DB backwards for obscurity.
   *
   * @param password Takes in object field Password and reverses it through recursion.
   * @return Returns password formatted in reverse.
   */
  public String reverseString(String password) {
    if ((null == password) || (password.length() <= 1)) {
      return password;
    }
    return reverseString(password.substring(1)) + password.charAt(0);
  }
}

