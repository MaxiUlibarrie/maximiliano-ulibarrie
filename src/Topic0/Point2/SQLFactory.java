package Topic0.Point2;

public class SQLFactory extends AbstractFactoryDBConnection {

  @Override
  DBConnection getDBConnection(String DBConnectionType) {
    if (DBConnectionType.equalsIgnoreCase("MySQL")) {
      return new MySQLConnection();
    } else if (DBConnectionType.equalsIgnoreCase("PostgreSQL")) {
      return new PostgreSQLConnection();
    }

    return null;
  }
}
