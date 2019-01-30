package Topic0.Point4;

public class MySQLBuilder implements DBConnectionBuilder {

  private DBConnection dbConnection;

  public MySQLBuilder() {
    this.dbConnection = new DBConnection();
  }

  @Override
  public void buildNameConnection() {
    dbConnection.setNameConnection("This is MySQL connection.");
  }

  @Override
  public void builPortConnection() {
    dbConnection.setPortConnection(3306);
  }

  @Override
  public DBConnection getDBConnection() {
    return this.dbConnection;
  }
}
