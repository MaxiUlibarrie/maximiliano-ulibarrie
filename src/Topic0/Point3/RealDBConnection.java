package Topic0.Point3;

public class RealDBConnection implements DBConnection {

  private String nameDBConnection;
  private int portConnection;
  private String user;
  private String password;

  public RealDBConnection(String nameDBConnection, int portConnection, String user,
      String password) {
    this.nameDBConnection = nameDBConnection;
    this.portConnection = portConnection;
    this.user = user;
    this.password = password;

    establishConnection();
  }

  @Override
  public void connect() {
    System.out.println("Connection established.");
  }

  private void establishConnection() {
    System.out.println("Establishing connection.");
  }
}
