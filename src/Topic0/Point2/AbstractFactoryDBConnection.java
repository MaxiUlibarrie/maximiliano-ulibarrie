package Topic0.Point2;

public abstract class AbstractFactoryDBConnection {

  abstract DBConnection getDBConnection(String DBConnectionType);
}
