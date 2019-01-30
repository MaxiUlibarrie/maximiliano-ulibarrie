package Topic0.Point2;

public class NoSQLFactory extends AbstractFactoryDBConnection {

  @Override
  DBConnection getDBConnection(String DBConnectionType) {
    if (DBConnectionType.equalsIgnoreCase("MongoDB")) {
      return new MongoDBConnection();
    } else if (DBConnectionType.equalsIgnoreCase("Cassandra")) {
      return new CassandraConnection();
    }

    return null;
  }
}
