package Topic0.Point2;

public class PostgreSQLConnection implements DBConnection {

  @Override
  public void connect() {
    System.out.println("This is PostgreSQL connection");
  }
}
