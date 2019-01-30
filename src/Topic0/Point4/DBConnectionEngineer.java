package Topic0.Point4;

public class DBConnectionEngineer {

  private DBConnectionBuilder dbConBuilder;

  public DBConnectionEngineer(DBConnectionBuilder dbConBuilder) {
    this.dbConBuilder = dbConBuilder;
  }

  public void setDbConBuilder(DBConnectionBuilder dbConBuilder) {
    this.dbConBuilder = dbConBuilder;
  }

  public DBConnection getDBConnection() {
    return this.dbConBuilder.getDBConnection();
  }

  public void createDBConnection() {
    this.dbConBuilder.buildNameConnection();
    this.dbConBuilder.builPortConnection();
  }

}
