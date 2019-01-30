package Topic0.Point2;

public class CassandraConnection implements DBConnection {

  @Override
  public void connect() {
    System.out.println("This is Cassandra connection");
  }
}
