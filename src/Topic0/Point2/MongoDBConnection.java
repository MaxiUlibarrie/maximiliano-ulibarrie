package Topic0.Point2;

public class MongoDBConnection implements DBConnection {

  @Override
  public void connect() {
    System.out.println("This is MongoDB connection");
  }
}
