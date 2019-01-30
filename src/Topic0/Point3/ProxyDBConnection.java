package Topic0.Point3;

public class ProxyDBConnection implements DBConnection {

  private RealDBConnection realDBCon;
  private String nameDBConnection;
  private int portConnection;
  private String user;
  private String password;

  public ProxyDBConnection(String nameDBConnection, int portConnection, String user,
      String password) {
    this.nameDBConnection = nameDBConnection;
    this.portConnection = portConnection;
    this.user = user;
    this.password = password;
  }

  @Override
  public void connect() {
    if (realDBCon == null) {
      realDBCon = new RealDBConnection(this.nameDBConnection,this.portConnection,this.user,this.password);
    }
    realDBCon.connect();
  }
}
