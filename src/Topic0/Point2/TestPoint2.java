package Topic0.Point2;

public class TestPoint2 {

  public static void main(String[] args) {
    AbstractFactoryDBConnection dbcSQLFactory =
        FactoryProducerDBConnection.getDBConnection("SQL");

    DBConnection dbcMySQL = dbcSQLFactory.getDBConnection("MySQL");
    dbcMySQL.connect();

    DBConnection dbcPostgreSQL = dbcSQLFactory.getDBConnection("PostgreSQL");
    dbcPostgreSQL.connect();

    AbstractFactoryDBConnection dbcNoSQLFactory =
        FactoryProducerDBConnection.getDBConnection("NoSQL");

    DBConnection dbcMongoDB = dbcNoSQLFactory.getDBConnection("MongoDB");
    dbcMongoDB.connect();

    DBConnection dbcCassandra = dbcNoSQLFactory.getDBConnection("Cassandra");
    dbcCassandra.connect();
  }
}
