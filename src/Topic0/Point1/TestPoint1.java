package Topic0.Point1;

public class TestPoint1 {
  public static void main(String[] args) {
    SingletonDBConnection instanceDBCon = SingletonDBConnection.getDbConnection();

    instanceDBCon.setNameDBConnection("MySQL");
    instanceDBCon.setPortConnection(3306);
    instanceDBCon.setUser("Maxi");
    instanceDBCon.setPassword("asd123");

    // I use the name of the class to see if attributes changed

    System.out.println(SingletonDBConnection.getNameDBConnection());
    System.out.println(SingletonDBConnection.getPortConnection());
    System.out.println(SingletonDBConnection.getUser());
    System.out.println(SingletonDBConnection.getPassword());


  }
}
