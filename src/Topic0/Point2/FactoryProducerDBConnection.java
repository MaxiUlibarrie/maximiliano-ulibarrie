package Topic0.Point2;

public class FactoryProducerDBConnection {

  public static AbstractFactoryDBConnection getDBConnection(String DBEngineType) {
    if (DBEngineType.equalsIgnoreCase("SQL")) {
      return new SQLFactory();
    } else if (DBEngineType.equalsIgnoreCase("NoSQL")) {
      return new NoSQLFactory();
    }

    return null;
  }
}
