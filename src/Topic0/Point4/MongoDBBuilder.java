package Topic0.Point4;

public class MongoDBBuilder implements DBConnectionBuilder {

  private DBConnection dbConnection;

  public MongoDBBuilder() {
    this.dbConnection = new DBConnection();
  }

  @Override
  public void buildNameConnection() {
    dbConnection.setNameConnection("This is MongoDB connection");
  }

  @Override
  public void builPortConnection() {
    dbConnection.setPortConnection(27017);
  }

  @Override
  public DBConnection getDBConnection() {
    return this.dbConnection;
  }
}
