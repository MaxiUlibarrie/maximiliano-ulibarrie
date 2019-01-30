package Topic0.Point2;

public class MySQLConnection implements DBConnection {

  @Override
  public void connect() {
    System.out.println("This is MySQL connection");
  }
}
