package Topic0.Point1;

public class SingletonDBConnection {
  private static SingletonDBConnection dbConnection = new SingletonDBConnection();

  // -- attributes of the db connection
  private static String nameDBConnection;
  private static int portConnection;
  private static String user;
  private static String password;
  // --

  private SingletonDBConnection() {}

  public static SingletonDBConnection getDbConnection() {
    return dbConnection;
  }

  // -- methods of the db connection
  public static String getNameDBConnection() {
    return nameDBConnection;
  }

  public static void setNameDBConnection(String nameDBConnection) {
    SingletonDBConnection.nameDBConnection = nameDBConnection;
  }

  public static int getPortConnection() {
    return portConnection;
  }

  public static void setPortConnection(int portConnection) {
    SingletonDBConnection.portConnection = portConnection;
  }

  public static String getUser() {
    return user;
  }

  public static void setUser(String user) {
    SingletonDBConnection.user = user;
  }

  public static String getPassword() {
    return password;
  }

  public static void setPassword(String password) {
    SingletonDBConnection.password = password;
  }
  // --
}
